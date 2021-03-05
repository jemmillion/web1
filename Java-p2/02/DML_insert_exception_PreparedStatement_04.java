package jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DML_insert_exception_PreparedStatement_04 {

	public static void main(String[] args) {
		
		Connection conn = null;
		// Connection conn 은 오라클 데이터베이스 서버와 연결을 맺어주는 객체이다.
		
		PreparedStatement pstmt = null;
		// PreparedStatement pstmt 은 Connection conn(특정 오라클 서버)에 전송할 SQL문(편지)을 전달할 객체(우편배달부)이다.       

		Scanner sc = new Scanner(System.in);
		
		int n_fk_classno = 0;
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
			
			
			// >>> 2. 어떤 오라클 서버에 연결을 할래? <<<  //
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
			
			// === Connection conn 기본값은 auto commit 이다. === //
			// === Connection conn 의 기본값인 auto commit 을  수동 commit 으로 전환하겠다. === // 
			conn.setAutoCommit(false); // 수동 commit 으로 전환 
			
			// >>> 3. SQL문(편지)을 작성한다. <<< //
			System.out.print("▷ 학생명 : ");
			String name = sc.nextLine();
			
			System.out.print("▷ 연락처 : ");
			String tel = sc.nextLine();
			
			System.out.print("▷ 주소 : ");
			String addr = sc.nextLine();
			
			System.out.print("▷ 학급번호 : ");
			String fk_classno = sc.nextLine();
			n_fk_classno = Integer.parseInt(fk_classno);
					
			String sql = " insert into jdbc_tbl_student(stno, name, tel, addr, fk_classno) "+
					     " values(jdbc_seq_stno.nextval, ?, ?, ?, ?)"; // SQL문 맨 뒤에 ; 을 넣으면 오류이다.!!!!
					     
			// ? 를 "위치홀더" 라고 부른다.
			
			
			// >>> 4. 연결한 오라클서버(conn)에 SQL문(편지)을 전달할 PreparedStatement 객체(우편배달부) 생성하기 <<< //
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);  // 1 은 String sql 에서 첫번째 위치홀더(?)를 말한다. 첫번째 위치홀더(?)에 name 을 넣어준다. 
			pstmt.setString(2, tel);   // 2 는 String sql 에서 두번째 위치홀더(?)를 말한다. 두번째 위치홀더(?)에 tel 를 넣어준다. 
			pstmt.setString(3, addr);  // 3 은 String sql 에서 세번째 위치홀더(?)를 말한다. 세번째 위치홀더(?)에 addr 를 넣어준다.
			pstmt.setInt(4, n_fk_classno);  // 4 는 String sql 에서 네번째 위치홀더(?)를 말한다. 네번째 위치홀더(?)에 Integer.parseInt(fk_classno) 를 넣어준다. 
			
		//	System.out.println("SQL 문장 : " + sql);
			
			// >>> 5. PreparedStatement pstmt 객체(우편배달부)는 작성된 SQL문(편지)을 오라클 서버에 보내서 실행이 되도록 해야 한다 <<< // 
			int n = pstmt.executeUpdate(); 
			/*  .executeUpdate(); 은 SQL문이 DML문(insert, update, delete, merge) 이거나 
			                        SQL문이 DDL문(create, drop, alter, truncate) 일 경우에 사용된다. 
			    
			     SQL문이 DML문이라면 return 되어지는 값은 적용되어진 행의 개수를 리턴시켜준다.
			         예를 들어, insert into ... 하면 1 개행이 입력되므로 리턴값은 1 이 나온다. 
			             update ... 할 경우에 update 할 대상의 행의 개수가 5 이라면 리턴값은 5 가 나온다. 
			             delete ... 할 경우에 delete 되어질 대상의 행의 개수가 3 이라면 리턴값은 3 가 나온다.
			             
			     SQL문이 DDL문이라면 return 되어지는 값은 무조건 0 이 리턴된다.       
			     
			    .executeQuery(); 은 SQL문이 DQL문(select) 일 경우에 사용된다.
			*/
			
			if(n == 1) {
				
				String yn = "";
				
				do {
					////////////////////////////////////////
					System.out.print("▷  정말로 입력하시겠습니까?[Y/N] : "); 
					yn = sc.nextLine();
					
					if("y".equalsIgnoreCase(yn)) {
						conn.commit(); // 커밋
						System.out.println(">> 데이터 입력 성공!! <<");
					//	break;
					}
					else if("n".equalsIgnoreCase(yn)) {
						conn.rollback(); // 롤백
						System.out.println(">> 데이터 입력 취소!! <<");
					//	break;
					}
					else {
						System.out.println(">> Y 또는 N 만 입력하세요!! << \n");
					}
					////////////////////////////////////////
					
			//	} while (true);
			    } while ( !("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)) );
				
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
		} catch (SQLException e) {
		//	e.printStackTrace();
			if(e.getErrorCode() == 2291) {
				System.out.println("\n>> 학급번호 "+n_fk_classno+" 은 우리학교에 존재하지 않는 학급번호 입니다. 학급번호를 올바르게 입력하세요 << \n");
			}
			else {
				e.printStackTrace();
			}
			
		} finally {
			// >>> 6. 사용하였던 자원을 반납하기 <<< //
			// 반납의 순서는 생성순서의 역순으로 한다.
			
			try {
				  if(pstmt != null) 
					  pstmt.close(); 
				  
				  if(conn != null) 
					  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		sc.close();
		System.out.println("~~~ 프로그램 종료 ~~~");
		
	}// end of main()------------------------------

}
