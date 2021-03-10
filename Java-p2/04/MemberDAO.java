package jdbc.day04;

import java.sql.*;
import java.util.*;

import jdbc.day04.singleton.dbconnection.MyDBConnection;

public class MemberDAO implements InterMemberDAO {

	// field, attribute, property, 속성 
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// === 자원반납 메소드  === //
	private void close() {
		try {
			 if(rs != null)    rs.close();
			 if(pstmt != null) pstmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()----------------
	
	
	@Override
	public int memberRegister(MemberDTO member, Scanner sc) {
		
		int result = 0;
		
		try {
			 conn = MyDBConnection.getConn();
			 
			 String sql = " insert into jdbc_member(userseq, userid, passwd, name, mobile) "+ 
					      " values(userseq.nextval, ?, ?, ?, ?) ";
			
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, member.getUserid());
			 pstmt.setString(2, member.getPasswd());
			 pstmt.setString(3, member.getName());
			 pstmt.setString(4, member.getMobile());
			
			 result = pstmt.executeUpdate();
			 
			 if(result == 1) {
				String yn = "";
				do {
					System.out.print(">> 회원가입을 정말로 하시겠습니까?[Y/N] ");
					yn = sc.nextLine();
					
					if("Y".equalsIgnoreCase(yn)) {
						conn.commit(); // 커밋
					}
					else if("N".equalsIgnoreCase(yn)) {
						conn.rollback(); // 롤백
						result = 0;
					}
					else {
						System.out.println(">>> Y 또는 N 만 입력하세요!! \n");
					}
					
				} while (!("Y".equalsIgnoreCase(yn) || "N".equalsIgnoreCase(yn)));
				
			 }// end of if(result == 1) { } --------------------
			 
		} catch (SQLIntegrityConstraintViolationException e) { 
			if(e.getErrorCode() == 1) 
				result = -1;
		} catch (SQLException e) {
				result = -2;
		} finally {
			close();
		}
		
		return result; 
		/* 
		    0 => 회원가입을 취소한 것임. 
		    1 => 정상적으로 회원가입한 것임.
		   -1 => 사용자아이디에 중복이 발생한 것임.
		   -2 => SQL구문에 오류발생한 것임.
		*/
	}// end of public int memberRegister(MemberDTO member)------------
	

	@Override
	public MemberDTO login(Map<String, String> paraMap) {
		
		MemberDTO member = null;
		
		try {
			 conn = MyDBConnection.getConn();
			 
			 String sql = "select userseq, userid, passwd, name, mobile, point "+
					      "     , to_char(registerday, 'yyyy-mm-dd') AS registerday, status "+
					      "from jdbc_member "+
					      "where userid = ? and passwd = ? ";
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, paraMap.get("userid"));
			 pstmt.setString(2, paraMap.get("passwd"));
							
			 rs = pstmt.executeQuery();
				
			 if(rs.next()) {
				member = new MemberDTO();
				
				member.setUserseq(rs.getInt(1));
				member.setUserid(rs.getString(2));
				member.setPasswd(rs.getString(3));
				member.setName(rs.getString(4));
				member.setMobile(rs.getString(5));
				member.setPoint(rs.getInt(6));
				member.setRegisterday(rs.getString(7));
				member.setStatus(rs.getInt(8));
			 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return member;
	}// end of public MemberDTO login(Map<String, String> paraMap)---------- 


	// 게시판에서 글을 쓴 작성자에게 point 를 10 올려주기 
	@Override
	public int updateMemberPoint(String userid) {
		
		int result = 0;
		
		try {
			  conn = MyDBConnection.getConn(); // conn 은 수동commit 으로 되어져 있다.
			  
			  String sql = " update jdbc_member set point = point + 10  "     
			  		     + " where userid = ? ";
			  
			  pstmt = conn.prepareStatement(sql);
			  
			  pstmt.setString(1, userid);
			  			  
			  result = pstmt.executeUpdate();
			  // update 가 성공되어지면 result 에는 1이 들어간다.
			  
		} catch (SQLException e) {
			
		} finally {
			close();
		}
		
		return result;
	    //  result 는 1 또는 0 을 리턴 할 것이다. 
		
	}// end of public int updateMemberPoint(String userid)-------


	
	// 모든회원정보조회(관리자 전용 메뉴)
	@Override
	public List<MemberDTO> selectAllMember(String sortChoice) {
		
		List<MemberDTO> memberList = new ArrayList<>();
		
		try {
			 conn = MyDBConnection.getConn();
			 
			 String sql = " select userseq, userid, name, mobile, point, to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') AS REGISTERDAY "+
					      " from jdbc_member "+
					      " where userid != 'admin' ";
			 
			 switch (sortChoice) {
				case "1": // 회원명의 오름차순
					sql += " order by name asc ";
					break;

				case "2": // 회원명의 내림차순 
					sql += " order by name desc ";
					break;
					
				case "3": // 가입일자의 오름차순
					sql += " order by REGISTERDAY asc ";
					break;
					
				case "4": // 가입일자의 내림차순 
					sql += " order by REGISTERDAY desc ";
					break;
					
				default:
					break;
			 }// end of switch (sortChoice)------------------
			 
			 pstmt = conn.prepareStatement(sql);
			 							
			 rs = pstmt.executeQuery();
				
			 while(rs.next()) {
				 MemberDTO member = new MemberDTO();
				
				 member.setUserseq(rs.getInt(1));
				 member.setUserid(rs.getString(2));
				 member.setName(rs.getString(3));
				 member.setMobile(rs.getString(4));
				 member.setPoint(rs.getInt(5));
				 member.setRegisterday(rs.getString(6));
				 
				 memberList.add(member);
			 }// end of while(rs.next())-------------------------
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return memberList;
		
	}// end of public List<MemberDTO> selectAllMember(String sortChoice)-------------

}
