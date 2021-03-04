package jdbc.day01;

import java.sql.*;
import java.util.Scanner;

public class DQL_select_where_PreparedStatement_03 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
					
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		System.out.print("▷ 연결할 오라클 서버의 IP 주소: ");
		String ip = sc.nextLine();
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":1521:xe", "HR", "cclass");
		
		String sql = "select no, name, msg, to_char(writeday,'yyyy-mm-dd hh24:mi:ss') as writeday "+
					 "from jdbc_tbl_memo "+
					 "order by no desc"; 
		
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		  
		
		System.out.println("----------------------------------------");
		System.out.println("글번호\t글쓴이\t글내용\t작성일자");
		System.out.println("----------------------------------------");
		
		StringBuilder sb = new StringBuilder();
		
		while(rs.next()) {
			
			int no = rs.getInt(1);  // 1은 select 되어져 나오는 컬럼의 순서를 말하는데 컬럼의 순서는 1부터 시작한다. 즉, NO 컬럼을 말한다. 
			String name = rs.getString(2);  // 2는 select 되어져 나오는 2번째 컬럼인 NAME 컬럼을 말한다.
			String msg = rs.getString(3);  // 3은 select 되어져 나오는 3번째 컬럼인 MSG 컬럼을 말한다.
			String writeday = rs.getString(4);  // 4는 select 되어져 나오는 4번째 컬럼인 WRITEDAY 컬럼을 말한다.
			
			sb.append(no);
			sb.append("\t"+name);
			sb.append("\t"+msg);
			sb.append("\t"+writeday+"\n");
		
			
			
		}// end of while(rs.next())-------------
		
		System.out.println(sb.toString());
		
		/////////////////////////////////////////////////////////////
		
		// StringBuilder sb을 초기화하기
		sb = new StringBuilder();
		// 또는
		// sb.setLength(0);
		
		sb.append("------- >>> 조회할 대상 <<< -------\n");
		sb.append("1.글번호	2.글쓴이	3.글내용	4.종료\n");
		sb.append("------------------------------\n");
		
		String menu = sb.toString();
		String menuNo = ""; 
				
		do {
			System.out.println(menu);
			System.out.print("▷번호선택 : ");
			menuNo = sc.nextLine();
			
			String colName = ""; // where 절에 들어올 컬럼명
			
			switch (menuNo) {
			case "1": // 글번호
				colName = "no";
				break;
				
			case "2": // 글쓴이
				colName = "name";
				break;
			case "3": // 글내용
				colName = "msg";
				break;
				
			case "4": // 종료
				
				break;
				
			default:
				System.out.println("~~~ 메뉴에 없는 번호입니다 ~~~\n");
				break;
			}// end of switch()----------------------------
			
			
			if("1".equals(menuNo) || "2".equals(menuNo) || "3".equals(menuNo)){
					
				System.out.print("▷ 검색어: ");
				String search = sc.nextLine();	
		
				sql = " select no, name, msg, to_char(writeday,'yyyy-mm-dd hh24:mi:ss') as writeday "+
					  " from jdbc_tbl_memo ";
		 	
				if(!"3".equals(menuNo)) {// 글쓴이 또는 글번호로 검색시
					
					sql += " where "+colName+" = ? ";
					// 컬럼명 또는 테이블명에는 위치홀더 ?가 올 수 없고, 변수로 처리해야 한다.
					// 데이터값만 위치홀더인 ? 를 써야 한다!!!
				} 
				else {// 글내용으로 검색시
					
					sql += " where "+colName+" like '%'|| ? ||'%'";
				}
				
				sql += " order by no desc "; 
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, search);
				
				rs = pstmt.executeQuery();
				
		
				// StringBuilder sb을 초기화하기
				// sb = new StringBuilder();
				// 또는
				sb.setLength(0);
				
				int cnt=0;
				while(rs.next()) {
					cnt++;
					
					if(cnt == 1) {
						System.out.println("----------------------------------------");
						System.out.println("글번호\t글쓴이\t글내용\t작성일자");
						System.out.println("----------------------------------------");
					}
						
					int no = rs.getInt(1);  // 1은 select 되어져 나오는 컬럼의 순서를 말하는데 컬럼의 순서는 1부터 시작한다. 즉, NO 컬럼을 말한다. 
					String name = rs.getString(2);  // 2는 select 되어져 나오는 2번째 컬럼인 NAME 컬럼을 말한다.
					String msg = rs.getString(3);  // 3은 select 되어져 나오는 3번째 컬럼인 MSG 컬럼을 말한다.
					String writeday = rs.getString(4);  // 4는 select 되어져 나오는 4번째 컬럼인 WRITEDAY 컬럼을 말한다.
					
					sb.append(no);
					sb.append("\t"+name);
					sb.append("\t"+msg);
					sb.append("\t"+writeday+"\n");
				
				}// end of while()--------------------------------------
				
				if(cnt > 0) {
					System.out.println(sb.toString());
				}
				else {
					String searchType = "";
					
					switch (menuNo) {
					case "1":
						searchType = "글번호";
						break;
					case "2":
						searchType = "글쓴이";
						break;
					case "3":
						searchType = "글내용";
						break;
					}
					
					System.out.println(">>> "+searchType+" 중 "+search+"에 해당하는 데이터가 없습니다.<<<\n");
					
				}
				
			}// end of if-------------------------------------------	
			
		} while (!("4".equals(menuNo)));
		// end of do~while
		
		
		
		} catch (ClassNotFoundException e) {
			System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
		} catch (SQLException e) {
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
