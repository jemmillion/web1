package bookrental;

import java.sql.*;
import java.util.*;



public class userDAO implements interUserDAO{

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
	   
	   
	   
	   
	   
	   // *** 일반회원가입 ***
	   @Override
	   public int memberRegister(userDTO udto, Scanner sc) {
		      
		   int result = 0;
			
			try {
				conn = ProjectDBConnection.getConn();

				String sql = "insert into lib_member(userseq, userid, passwd, username, mobile)\n"+
							 "values(userseq.nextval, ?, ?, ?, ?)";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, udto.getUserid());
				pstmt.setString(2, udto.getPasswd());
				pstmt.setString(3, udto.getUsername());
				pstmt.setString(4, udto.getMobile());
				
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
			}finally {
				close();
			}
			
			return result;
	   }

	   
	   
	   

	   // *** 일반회원 로그인 ***
	   @Override
	   public userDTO login(userDTO udto, Map<String, String> paraMap) {
	      udto = null;
	      
	      try {
	         conn = ProjectDBConnection.getConn();
	         
	         String sql = "select userid, passwd, username "+
	                      "from lib_member "+
	                      "where userid = ? and passwd = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, paraMap.get("userid"));
	         pstmt.setString(2, paraMap.get("passwd"));
	         
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	udto = new userDTO();
	        	udto.setUserid(rs.getString(1));
	        	udto.setPasswd(rs.getString(2));
	        	udto.setUsername(rs.getString(3));
	         }
	         
	      }catch(SQLException e) {
	    	  System.out.println("SQL 구문이 잘못되었습니다");
	      }finally {
	    	  close();
	      }
	      
	     return udto;
	   }


		// 회원id가 존재하는지 체크하는 메소드
		@Override
		public boolean checkUserid(String userid) {
			boolean b = false;
			
			try {
	         conn = ProjectDBConnection.getConn();
	         
	         String sql = "select userid "+
	                      "from lib_member "+
	                      "where userid = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, userid);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	b=true;
	         }
		         
		     }catch(SQLException e) {
		    	  System.out.println("SQL 구문이 잘못되었습니다");
		     }finally {
		    	  close();
		     }
			
			return b;
		}

	
		   // 회원 탈퇴 메소드
	      @Override
	      public int leaveMember(Map<String, String> paraMap) {
	         int result = 0;
	         
	         try {
	              conn = ProjectDBConnection.getConn();
	              
	              String sql = " delete from lib_member "
	                         + " where userid = ? and totalrent=0 ";
	                 
	              pstmt = conn.prepareStatement(sql);
	              pstmt.setString(1, paraMap.get("userid"));

	              result = pstmt.executeUpdate();
	           
	         } catch (SQLException e) { 
	              e.printStackTrace();
	         } finally {
	            close();
	         }      
	         return result;
	      }

	}