package jdbc.day01;

import java.sql.*;
import java.util.Scanner;

public class DQL_delete_PreparedStatement_05 {

	public static void main(String[] args) {

		Connection conn = null;
		// 오라클 데이터 베이스 서버와 연결을 맺어주는 객체이다.
		
		PreparedStatement pstmt = null;
		// PreparedStatement pstmt는 Connection conn(특정 오라클 서버)에 전송할 SQL문(편지)을 전달할 객체(우편배달부)이다.
		
		ResultSet rs = null;
		// ResultSet rs 은 select 되어진 결과물이 저장되어지는 곳.
		
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.print("▷ 연결할 오라클 서버의 IP 주소: ");
		String ip = sc.nextLine();
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":1521:xe", "HR", "cclass");
		
		conn.setAutoCommit(false);
		
		// >>> 3. SQL문(편지)을 작성한다. <<< //
		
		String sql = "select no, name, msg, to_char(writeday,'yyyy-mm-dd hh24:mi:ss') as writeday "+
					 "from jdbc_tbl_memo "+
					 "order by no desc";  // SQL문 맨 뒤에 ; 을 넣으면 오류이다.!!!
		
		// >>> 4. 연결한 오라클서버(conn)에  SQL문(편지)을 전달할 PreparedStatement 객체(우편배달부) 생성하기 <<< //
		
		pstmt = conn.prepareStatement(sql);
		
		// >>> 5. PreparedStatement pstmt 객체(우편배달부)는 작성된 SQL문(편지)을 오라클 서버에 보내서 실행이 되도록 해야 한다. <<< //
		
		
		rs = pstmt.executeQuery();
 
		
		System.out.println("----------------------------------------");
		System.out.println("글번호\t글쓴이\t글내용\t작성일자");
		System.out.println("----------------------------------------");
		
		StringBuilder sb = new StringBuilder();
		
		while(rs.next()) {

			int no = rs.getInt("NO");  // "NO" 은 select 해온 컬럼명이다.
			String name = rs.getString("NAME");  // "NAME" 은 select 해온 컬럼명이다.
			String msg = rs.getString("MSG");  // "MSG" 은 select 해온 컬럼명이다.
			String writeday = rs.getString("WRITEDAY");  // "WRITEDAY" 은 select 해온 컬럼명이다.
			
			sb.append(no);
			sb.append("\t"+name);
			sb.append("\t"+msg);
			sb.append("\t"+writeday+"\n");
			
			
		}// end of while(rs.next())-------------
		
		System.out.println(sb.toString());
		
		//////////////////////////////////////////////////////
		System.out.print("▷삭제할 글번호 : ");
		String no = sc.nextLine();
		
		sql = " select name, msg "
			+ " from jdbc_tbl_memo "
			+ " where no = ? ";
		
		pstmt.close();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		
		rs.close();
		rs = pstmt.executeQuery();
		
		
		if(rs.next()) {
			String name = rs.getString(1);
			String msg = rs.getString(2);

			System.out.println("\n=== 삭제할 글 내용 ===");
			System.out.println("\n□글쓴이 : "+name);
			System.out.println("□글내용 : "+msg);
			
			sql = " delete from jdbc_tbl_memo " 
				+ " where no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			int n = pstmt.executeUpdate();
			
			if(n==1) {
				System.out.println("▷정말로 삭제하시겠습니까?[Y/N] : ");
				String yn = sc.nextLine();
				
				do {
					if("y".equalsIgnoreCase(yn)) {
						conn.commit();
						System.out.println(">> 글번호 "+no+"번 글을 삭제 완료했습니다. <<");
					}
					else if("n".equalsIgnoreCase(yn)) {
						conn.rollback();
						System.out.println(">> 글번호 "+no+"번 글을 삭제 취소했습니다. <<");
					}
					else {
						System.out.println(">> Y 또는 N 만 입력하세요!! <<");
					}
				} while ( !("y".equalsIgnoreCase(yn) || "n".equalsIgnoreCase(yn)) );
				
			}// end of if(n==1) ---------------------------------------
			
			
		}
		else {
			System.out.println(">>> 글번호 "+no+"은 존재하지 않습니다<<<\n");
		}
				
			
		} catch (ClassNotFoundException e) {
			System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
		} catch (SQLException e) {
			System.out.println(">> 작성하신 SQL 문장에 오류가 있습니다. <<");
			e.printStackTrace();
		} finally {
			/// >>> 6. 사용하였던 자원을 반납하기 <<< //
			// 반남의 순서는 생성순서의 역순으로 한다.
			
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null) 
					pstmt.close();
				if(conn!=null) 
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		sc.close();
		System.out.println("\n~~~프로그램 종료~~~");
		
	}// end of main()--------------------------------

}
