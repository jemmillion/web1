package jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DDL_create_PreparedStatement_06 {

	public static void main(String[] args) {
		
		Connection conn = null;
		// Connection conn 은 오라클 데이터베이스 서버와 연결을 맺어주는 객체이다.
		
		PreparedStatement pstmt = null;
		// PreparedStatement pstmt 은 Connection conn(특정 오라클 서버)에 전송할 SQL문(편지)을 전달할 객체(우편배달부)이다.       

		ResultSet rs = null;
		// ResultSet rs 은 select 되어진 결과물이 저장되어지는 곳.
		
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
			
			
			// >>> 3. SQL문(편지)을 작성한다. <<< //
			String sql1 = " select * "
					    + " from user_tables "
					    + " where table_name = 'JDBC_TBL_EXAMTEST' ";
			
			String sql2 = " drop table JDBC_TBL_EXAMTEST purge ";
			
			String sql3 = " create table jdbc_tbl_examtest "
					    + " (no    number(4) "
					    + " ,name  varchar2(40) "
					    + " ,msg   varchar2(200) "
					    + ") ";
			
			String sql4 = " select * "
					    + " from user_sequences "
					    + " where sequence_name = 'JDBC_SEQ_EXAMTEST' ";
			
			String sql5 = " drop sequence JDBC_SEQ_EXAMTEST ";
			
			String sql6 = " create sequence jdbc_seq_examtest "
					    + " start with 1 "
					    + " increment by 1 "
					    + " nomaxvalue "
					    + " nominvalue "
					    + " nocycle "
					    + " nocache ";
			
			String sql7 = " insert into jdbc_tbl_examtest(no, name, msg) "
					    + " values(jdbc_seq_examtest.nextval, '이순신', '안녕하세요? 이순신 입니다') "; 
			
			String sql8 = " select * "
					    + " from jdbc_tbl_examtest"
					    + " order by no asc ";
			
			
			// >>> 4. 연결한 오라클서버(conn)에 SQL문(편지)을 전달할 PreparedStatement 객체(우편배달부) 생성하기 <<< //
			// "JDBC_TBL_EXAMTEST" 테이블이 존재하는지 알아본다. 
			pstmt = conn.prepareStatement(sql1); 
			
			// >>> 5. PreparedStatement pstmt 객체(우편배달부)는 작성된 SQL문(편지)을 오라클 서버에 보내서 실행이 되도록 해야 한다 <<< // 
			rs = pstmt.executeQuery(); 
			
			int n = 0;
			if(rs.next()) {
				// "JDBC_TBL_EXAMTEST" 테이블이 존재하는 경우 
				
				// >>> 4. 연결한 오라클서버(conn)에 SQL문(편지)을 전달할 PreparedStatement 객체(우편배달부) 생성하기 <<< //
				// "JDBC_TBL_EXAMTEST" 테이블을 먼저 drop 한다.
				pstmt.close();
				pstmt = conn.prepareStatement(sql2); 
				
				// >>> 5. PreparedStatement pstmt 객체(우편배달부)는 작성된 SQL문(편지)을 오라클 서버에 보내서 실행이 되도록 해야 한다 <<< // 
				n = pstmt.executeUpdate(); 
				/*	 pstmt.executeUpdate()은  
                     DML(insert, update, delete, merge)문 및 DDL(create table, create view, drop table 등)문을 수행해주는 메소드로서 
     	                        결과값은 int 형태로서 DML문 이라면 적용된 행의 갯수가 나오고 DDL문 이라면 0이 나온다.
                */
				System.out.println("drop table : " + n);
				// drop table : 0 
			}
			
			
			// >>> 4. 연결한 오라클서버(conn)에 SQL문(편지)을 전달할 PreparedStatement 객체(우편배달부) 생성하기 <<< //
			// "JDBC_TBL_EXAMTEST" 테이블을 create 한다.
			pstmt = conn.prepareStatement(sql3); 
			
			// >>> 5. PreparedStatement pstmt 객체(우편배달부)는 작성된 SQL문(편지)을 오라클 서버에 보내서 실행이 되도록 해야 한다 <<< // 
			n = pstmt.executeUpdate(); 
			/*	 pstmt.executeUpdate()은  
                 DML(insert, update, delete, merge)문 및 DDL(create table, create view, drop table 등)문을 수행해주는 메소드로서 
 	                        결과값은 int 형태로서 DML문 이라면 적용된 행의 갯수가 나오고 DDL문 이라면 0이 나온다.
            */
			System.out.println("create table : " + n);
			// create table : 0 
			
			
		///////////////////////////////////////////////////////////
			
			// >>> 4. 연결한 오라클서버(conn)에 SQL문(편지)을 전달할 PreparedStatement 객체(우편배달부) 생성하기 <<< //
			// "JDBC_SEQ_EXAMTEST" 시퀀스가 존재하는지 알아본다. 
			pstmt = conn.prepareStatement(sql4); 
			
			// >>> 5. PreparedStatement pstmt 객체(우편배달부)는 작성된 SQL문(편지)을 오라클 서버에 보내서 실행이 되도록 해야 한다 <<< // 
			rs.close();
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				// "JDBC_SEQ_EXAMTEST" 시퀀스가 존재하는 경우 
				
				// >>> 4. 연결한 오라클서버(conn)에 SQL문(편지)을 전달할 PreparedStatement 객체(우편배달부) 생성하기 <<< //
				// "JDBC_SEQ_EXAMTEST" 시퀀스를 먼저 drop 한다.
				pstmt = conn.prepareStatement(sql5); 
				
				// >>> 5. PreparedStatement pstmt 객체(우편배달부)는 작성된 SQL문(편지)을 오라클 서버에 보내서 실행이 되도록 해야 한다 <<< // 
				n = pstmt.executeUpdate(); 
				/*	 pstmt.executeUpdate()은  
                     DML(insert, update, delete, merge)문 및 DDL(create table, create view, drop table 등)문을 수행해주는 메소드로서 
     	                        결과값은 int 형태로서 DML문 이라면 적용된 행의 갯수가 나오고 DDL문 이라면 0이 나온다.
                */
				System.out.println("drop sequence : " + n);
				// drop sequence : 0 
			}
			
			
			// >>> 4. 연결한 오라클서버(conn)에 SQL문(편지)을 전달할 PreparedStatement 객체(우편배달부) 생성하기 <<< //
			// "JDBC_SEQ_EXAMTEST" 시퀀스를 create 한다.
			pstmt = conn.prepareStatement(sql6); 
			
			// >>> 5. PreparedStatement pstmt 객체(우편배달부)는 작성된 SQL문(편지)을 오라클 서버에 보내서 실행이 되도록 해야 한다 <<< // 
			n = pstmt.executeUpdate(); 
			/*	 pstmt.executeUpdate()은  
                 DML(insert, update, delete, merge)문 및 DDL(create table, create view, drop table 등)문을 수행해주는 메소드로서 
 	                        결과값은 int 형태로서 DML문 이라면 적용된 행의 갯수가 나오고 DDL문 이라면 0이 나온다.
            */
			System.out.println("create sequence : " + n);
			// create sequence : 0    	
			
			
			pstmt = conn.prepareStatement(sql7);
			n = pstmt.executeUpdate();
			System.out.println("insert 를 한 DML문의 n : " + n);
			// insert 를 한 DML문의 n : 1
			
			
			pstmt = conn.prepareStatement(sql8);
			rs.close();
			rs = pstmt.executeQuery();
			
			StringBuilder sb = new StringBuilder();
			int cnt = 0;
			
			while(rs.next()) {
				cnt++;
			    if(cnt == 1) {	
					System.out.println("----------------------------------");
					System.out.println("일련번호\t성명\t글내용");
					System.out.println("----------------------------------");
			    }
			    
			    sb.append( rs.getInt("NO") + "\t" + rs.getString("NAME") + "\t" + rs.getString("MSG") + "\n" );
			}// end of while(rs.next())---------------------------
			
			if(cnt > 0) {
				System.out.println(sb.toString());
			}
			else {
				System.out.println(">> 입력된 데이터가 없습니다. <<");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// >>> 6. 사용하였던 자원을 반납하기 <<< //
			// 반납의 순서는 생성순서의 역순으로 한다.
			
			try {
				  if(rs != null) 
					  rs.close();
				
				  if(pstmt != null) 
					  pstmt.close(); 
				  
				  if(conn != null) 
					  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("~~~ 프로그램 종료 ~~~");
		
	}// end of main()------------------------------

}
