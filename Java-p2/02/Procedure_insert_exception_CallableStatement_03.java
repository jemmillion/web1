/*
  >>>> Stored Procedure 란? <<<<<
  Query 문을 하나의 파일형태로 만들거나 데이터베이스에 저장해 놓고 함수처럼 호출해서 사용하는 것임.
  Stored Procedure 를 사용하면 연속되는 query 문에 대해서 매우 빠른 성능을 보이며, 
  코드의 독립성과 함께 보안적인 장점도 가지게 된다.
  
  
     --- *** tbl_member_test1 테이블에 insert 할 수 있는 요일명과 시간을 제한하겠습니다. *** ---
     --- tbl_member_test1 테이블에 insert 할 수 있는 요일명은 월,화,수,목,금 만 가능하며
     --- 또한 월,화,수,목,금 중에 오전 9시 이후 부터 오후 4시 59분 59초 까지만 가능하도록 한다.
     --- 그 이외는 영업마감 이므로 insert 를 할 수 없습니다. 라는 오류메시지를 띄우도록 한다.
     -- tbl_member_test1 테이블의 passwd 컬럼에는 
     -- 글자수가 최소 5글자 이상이면서 영문자 및 숫자 및 특수기호가 혼합되어져야 한다.
     -- 위의 조건에 맞아야만 insert 할 것이고, 위의 조건에 위배되면 insert 를 하지 않도록 할 것이다.
     
create or replace procedure pcd_tbl_member_test1_insert 
(p_userid   tbl_member_test1.userid%type    -- IN은 생략가능함.
,p_passwd   tbl_member_test1.passwd%type    -- IN은 생략가능함.
,p_name     tbl_member_test1.name%type    	-- IN은 생략가능함.
)
is
   v_length        number(20);
   v_ch            varchar2(3);
   v_flagAlphabet  number(1) := 0;
   v_flagNumber    number(1) := 0;
   v_flagSpecial   number(1) := 0;
   
   error_dayTime   exception;
   error_insert    exception; 
   error_passwd    exception;
   
begin
      -- 오늘의 요일명을 알아오도록 한다.
      if ( to_char(sysdate, 'd') in('1','7') OR   -- to_char(sysdate, 'd') => '1'(일),'2'(월),'3'(화),'4'(수),'5'(목),'6'(금),'7'(토) 
           to_char(sysdate, 'hh24') < '09' OR to_char(sysdate, 'hh24') > '17'
         ) then
           raise  error_dayTime;
         
      else   
         
          v_length := length(p_passwd);
          
          if v_length < 5 then 
             raise error_insert;  -- 사용자가 정의하는 예외절(Exception)을 구동시켜라. 
          else
             for i in 1..v_length loop
                 v_ch := substr(p_passwd, i, 1);
                 
                 if (v_ch between 'a' and 'z') OR (v_ch between 'A' and 'Z') then  -- 영문자 이라면
                     v_flagAlphabet := 1;
                 elsif (v_ch between '0' and '9') then  -- 숫자 이라면 
                     v_flagNumber := 1;
                 else     -- 특수문자 이라면 
                     v_flagSpecial := 1;
                 end if;    
                     
             end loop;
             
             if(v_flagAlphabet * v_flagNumber * v_flagSpecial = 1) then
                insert into tbl_member_test1(userid, passwd, name)
                values(p_userid, p_passwd, p_name);
             else
                raise  error_passwd; -- 사용자정의 EXCEPTION 인 error_passwd 를 구동시켜라.
             end if;   
             
          end if; 
          
      end if;    
      
      exception 
         when  error_dayTime then 
               raise_application_error(-20002, '>> 현재 영업시간(월~금 09시 ~ 17시 이전까지)이 아니므로 데이터 입력이 불가합니다. <<');
      
         when  error_insert then 
               raise_application_error(-20003, '>> passwd 컬럼의 길이는 최소 5글자 이상이어야 합니다. <<');
              --    -20002 은 오류넘버(오류번호)로써 사용자가 정의하는 Exception은 항상 -20001 부터 -20999 이내의 값중 아무거나 쓰면 된다.  
         
         when  error_passwd then  
               raise_application_error(-20004, '>> passwd 컬럼의 값은 영문자, 숫자, 특수문자가 혼합되어져야만 합니다. <<');
               
end pcd_tbl_member_test1_insert;
-- Procedure PCD_TBL_MEMBER_TEST1_INSERT이(가) 컴파일되었습니다.     

*/
package jdbc.day02;

import java.sql.*;
import java.util.Scanner;

public class Procedure_insert_exception_CallableStatement_03 {

	public static void main(String[] args) {

		Connection conn = null;
		// 오라클 데이터 베이스 서버와 연결을 맺어주는 객체이다.
		
		CallableStatement cstmt = null;
		// CallableStatement cstmt는 Connection conn(특정 오라클 서버)에 존재하는 Procedure를 호출할 객체(우편배달부)이다.
		
		String userid = null;
		
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
			cstmt=conn.prepareCall("{call pcd_tbl_member_test1_insert (?,?,?) }");
			
			/*
		            오라클 서버에 생성한 프로시저 pcd_tbl_member_test1_insert 의 
		            매개변수 갯수가 7개 이므로 ? 를 7개 준다.
		                    
		            다음으로 오라클의 프로시저를 수행( executeUpdate() ) 하기에 앞서서  
		            반드시 해야할 일은 IN mode 로 되어진 파라미터에 값을 넣어주고,
		           OUT mode 로 설정된 곳에 그 결과값을 받아오도록 아래와 같이 설정해야 한다.    
		                   
		            프로시저의 IN mode 로 되어진 파라미터에 값을 넣어줄때는 
		           cstmt.setXXX() 메소드를 사용한다.
		           
			*/
			
			Scanner sc = new Scanner(System.in);
			System.out.print("▷ 아이디 : ");
			userid = sc.nextLine();
			
			System.out.print("▷ 비밀번호 : ");
			String passwd = sc.nextLine();
			
			System.out.print("▷ 성명 : ");
			String name = sc.nextLine();
			
			cstmt.setString(1, userid); // 숫자 1은 프로시저 파라미터 중 첫번째 파라미터인 IN 모드의 ? 를 말한다.
			cstmt.setString(2, passwd); // 숫자 2은 프로시저 파라미터 중 듀번째 파라미터인 IN 모드의 ? 를 말한다.
			cstmt.setString(3, name); // 숫자 3은 프로시저 파라미터 중 세번째 파라미터인 IN 모드의 ? 를 말한다.
			
			// >>>> 4. CallableStatement cstmt 객체를 사용하여 오라클의 프로시저 실행하기 <<<<
	        int n = cstmt.executeUpdate(); // 오라클 서버에게 해당 프로시저를 실행하라는 것이다.
	        // 프로시저의 실행은 cstmt.executeUpdate(); 또는 cstmt.execute(); 이다. 
			
	        if( n == 1 ) {
	        	System.out.println(">>>  데이터 입력 성공 !!! <<<");
	        }
	       
	        
			sc.close();
		} catch (ClassNotFoundException e) {
			System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
		} catch (SQLException e) {
			// e.printStackTrace();
			int errorCode = e.getErrorCode();
			
			if(errorCode == 20002) {
				System.out.println(">>> 지금은 영업시간(월~금 09~-14시 이전까지)이 아니므로 데이터 입력이 불가합니다. 나중에 다시 하세요!! <<< \n");
			}
			else if(errorCode == 20003) {
				System.out.println(">>> 비밀번호의 길이는 최소 5글자 이상 20글자 이하이어야 합니다. <<< \n");
			}
			else if(errorCode == 20004) {
				System.out.println(">>> 비밀번호는 영문자, 숫자, 특수문자가 혼합되어져야만 합니다. <<< \n");
			}
			else if(errorCode == 1){
				System.out.println(">>> 아이디 "+userid+" 은 현재 사용중입니다. <<< \n");
			}
			
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
