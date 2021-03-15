package bookrental;

import java.sql.*;
import java.util.*;


public class librarianDAO implements interLibrarianDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	   
    private void close() {
		try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();

		} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}// end of private void close()--------------
	
	
   
   
	// *** 사서 회원가입 ***
   @Override
	public int librarianRegister(librarianDTO ldto, Scanner sc) {
		
		int result = 0;
		conn = ProjectDBConnection.getConn();
		
		try {
			String sql = " insert into lib_admin(adminseq,adminid,passwd) " + 
					     " values(adminseq.nextval,?,?) ";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ldto.getAdminid());
			pstmt.setString(2, ldto.getPasswd());
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
	         }//end of if(result == 1) ---------------
			
		}catch(SQLIntegrityConstraintViolationException e) {
			if(e.getErrorCode() == 1)
			result = -1;
		}catch(SQLException e) {
			result = -2;
			e.printStackTrace();
		}finally {
			close();
		}
		
		return result;
	}

   
   
   
   // *** 사서 로그인 ***
	@Override
	public librarianDTO login(librarianDTO ldto, Map<String, String> paraMap) {

		ldto = null;
	      
	      try {
	         conn = ProjectDBConnection.getConn();
	         
	         String sql = " select adminid, passwd "+
		                  " from lib_admin "+
		                  " where adminid = ? and passwd = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, paraMap.get("adminid"));
	         pstmt.setString(2, paraMap.get("passwd"));
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 ldto = new librarianDTO();
	             ldto.setAdminid(rs.getString(1));
	        	 ldto.setPasswd(rs.getString(2));	            
	         }
	         
	      }catch(SQLException e) {
	         System.out.println("SQL 구문이 잘못되었습니다");
	      }finally {
	    	  close();
	      }
	      
	      return ldto;
	      
	   }





	
}
