/*
  >>>> Stored Procedure 란? <<<<<
  Query 문을 하나의 파일형태로 만들거나 데이터베이스에 저장해 놓고 함수처럼 호출해서 사용하는 것임.
  Stored Procedure 를 사용하면 연속되는 query 문에 대해서 매우 빠른 성능을 보이며, 
  코드의 독립성과 함께 보안적인 장점도 가지게 된다.
*/

/*
create or replace procedure pcd_student_select_one
(p_stno          IN   jdbc_tbl_student.stno%type
,o_name          OUT  jdbc_tbl_student.name%type
,o_tel           OUT  jdbc_tbl_student.tel%type
,o_addr          OUT  jdbc_tbl_student.addr%type
,o_registerdate  OUT  varchar2
,o_classname     OUT  jdbc_tbl_class.classname%type
,o_teachername   OUT  jdbc_tbl_class.teachername%type
)
is
   v_cnt  number(1);
begin
      select count(*) INTO v_cnt 
      from jdbc_tbl_student
      where stno = p_stno;
      
      if v_cnt = 0 then
         o_name := null;
         o_tel := null;
         o_addr := null;
         o_registerdate := null;
         o_classname := null;
         o_teachername := null;
         
      else
         select S.name, S.tel, S.addr, 
                to_char(S.registerdate, 'yyyy-mm-dd hh24:mi:ss'),
                C.classname, C.teachername
                INTO
                o_name, o_tel, o_addr, o_registerdate, o_classname, o_teachername 
         from jdbc_tbl_student S JOIN jdbc_tbl_class C
         on S.fk_classno = C.classno
         where S.stno = p_stno;
      end if;

end pcd_student_select_one;

exec pcd_student_select_one(9001);
*/
package jdbc.day02;

import java.sql.*;
import java.util.Scanner;

public class Procedure_select_one_CallableStatement_01 {

	public static void main(String[] args) {

		Connection conn = null;
		// 오라클 데이터 베이스 서버와 연결을 맺어주는 객체이다.
		
		CallableStatement cstmt = null;
		// CallableStatement cstmt는 Connection conn(특정 오라클 서버)에 존재하는 Procedure를 호출할 객체(우편배달부)이다.
		
		try {
			// >>> 1. 오라클 드라이버 로딩 <<<  //
	        /*
	           === OracleDriver(오라클 드라이버)의 역할 ===
	           1). OracleDriver 를 메모리에 로딩시켜준다.
	           2). OracleDriver 객체를 생성해준다.
	           3). OracleDriver 객체를 DriverManager에 등록시켜준다.
	               --> DriverManager 는 여러 드라이버들을 Vector 에 저장하여 관리해주는 클래스이다.
	        */
				
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// >>> 2. 어떤 오라클 서버에 연결? <<<  //
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
			
			// >>>> 3. Connection conn 객체를 사용하여 prepareCall() 메소드를 호출함으로써 
	        //         CallableStatement cstmt 객체를 생성한다.
	        //         즉, 우편배달부(택배기사) 객체 만들기  
			cstmt=conn.prepareCall("{call pcd_student_select_one(?,?,?,?,?,?,?) }");
			
			/*
		            오라클 서버에 생성한 프로시저  pcd_student_select_one 의 
		            매개변수 갯수가 7개 이므로 ? 를 7개 준다.
		                    
		            다음으로 오라클의 프로시저를 수행( executeUpdate() ) 하기에 앞서서  
		            반드시 해야할 일은 IN mode 로 되어진 파라미터에 값을 넣어주고,
		           OUT mode 로 설정된 곳에 그 결과값을 받아오도록 아래와 같이 설정해야 한다.    
		                   
		            프로시저의 IN mode 로 되어진 파라미터에 값을 넣어줄때는 
		           cstmt.setXXX() 메소드를 사용한다.
		            
		            프로시저의 OUT mode 로 되어진 파라미터에 저장되어진 값을 자바에서 꺼내 오려면 
		           cstmt.registerOutParameter() 메소드를 사용한다.
		                   
		           ※ registerOutParameter() 메소드는?
		            ==> public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException 
		                : 프로시저를 실행하여 받아온 값을 JDBC타입(자바에서 인식하는 타입)으로 등록시켜주는 메소드이다.
		             
		             자바에서는 오라클의 OUT mode 변수에 오라클 데이터타입으로 저장되어 있는 값들을 읽어와서
		           JDBC타입(자바에서 인식하는 타입)으로 변경하는 과정을 거쳐야만 한다.
		             대표적인  sqlType을 알아보면 NULL, FLOAT, INTEGER, VARCHAR, DATE, CLOB, BLOB 등이 있다.
			*/
			
			Scanner sc = new Scanner(System.in);
			System.out.print("▷학번 : ");
			String stno = sc.nextLine();
			
			cstmt.setString(1, stno); // 숫자 1은 프로시저 파라미터 중 첫번째 파라미터인 IN 모드의 ? 를 말한다.
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR); // 숫자 2는 프로시저 파라미터중 두번째 파라미터인 OUT 모드의 ?를 말한다. 
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR); // 숫자 3는 프로시저 파라미터중 세번째 파라미터인 OUT 모드의 ?를 말한다. 
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR); // 숫자 4는 프로시저 파라미터중 네번째 파라미터인 OUT 모드의 ?를 말한다. 
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR); // 숫자 5는 프로시저 파라미터중 다섯번째 파라미터인 OUT 모드의 ?를 말한다. 
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR); // 숫자 6는 프로시저 파라미터중 여섯번째 파라미터인 OUT 모드의 ?를 말한다. 
			cstmt.registerOutParameter(7, java.sql.Types.VARCHAR); // 숫자 7는 프로시저 파라미터중 일곱번째 파라미터인 OUT 모드의 ?를 말한다. 
			
			// >>>> 4. CallableStatement cstmt 객체를 사용하여 오라클의 프로시저 실행하기 <<<<
	        cstmt.executeUpdate(); // 오라클 서버에게 해당 프로시저를 실행하라는 것이다.
	        // 프로시저의 실행은 cstmt.executeUpdate(); 또는 cstmt.execute(); 이다. 
			
	        if( cstmt.getString(2) == null ) {
	        	System.out.println(">>> 입력하신 학번 "+ stno +"은 존재하지 않습니다.");
	        }
	        
	        else {
	        	System.out.println("-------------------------------------");
	        	System.out.println("▶ 학생명 : "+cstmt.getString(2)); // 숫자 2는 프로시저 파라미터중 두번째 파라미터의 ?(OUT MODE)를 말한다. 
	        	System.out.println("▶ 연락처 : "+cstmt.getString(3)); // 숫자 3는 프로시저 파라미터중 세번째 파라미터의 ?(OUT MODE)를 말한다.
	        	System.out.println("▶ 주소 : "+cstmt.getString(4)); // 숫자 4는 프로시저 파라미터중 네번째 파라미터의 ?(OUT MODE)를 말한다.
	        	System.out.println("▶ 입학일자 : "+cstmt.getString(5)); // 숫자 5는 프로시저 파라미터중 다섯번째 파라미터의 ?(OUT MODE)를 말한다.
	        	System.out.println("▶ 학급명 : "+cstmt.getString(6)); // 숫자 6는 프로시저 파라미터중 여섯번째 파라미터의 ?(OUT MODE)를 말한다.
	        	System.out.println("▶ 교사명 : "+cstmt.getString(7)); // 숫자 7는 프로시저 파라미터중 일곱번째 파라미터의 ?(OUT MODE)를 말한다.
	        	System.out.println("-------------------------------------");
	        }
	        
			sc.close();
		} catch (ClassNotFoundException e) {
			System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/// >>> 6. 사용하였던 자원을 반납하기 <<< //
			// 반남의 순서는 생성순서의 역순으로 한다.
			
			try {
				if(cstmt!=null) 
					cstmt.close();
				if(conn!=null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("\n~~~프로그램 종료~~~");
		
	}// end of main()---------------------------------------------

}
