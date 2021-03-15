package bookrental;

import java.sql.*;
import java.util.*;


public class bookDAO implements interBookDAO {
	
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

	
	
	// 도서정보등록
	@Override
	   public int bookInfoInput(bookDTO book, Scanner sc) {
	      int result=0;
	      
	      try {
	         //도서 정보 insert(sql)
	         conn=ProjectDBConnection.getConn();
	         
	         String sql = "insert into book_isbn(bookisbn, bookctg, booktitle, authorname, publishcom, price)\n"+
	                   " values(?, ?, ?, ?, ?, ?) ";
	      
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, book.getBookisbn());
	         pstmt.setString(2, book.getBookctg());
	         pstmt.setString(3, book.getBooktitle());
	         pstmt.setString(4, book.getAuthorname());
	         pstmt.setString(5, book.getPublishcom());
	         pstmt.setString(6, book.getPrice());
	         
	         result = pstmt.executeUpdate();
	         
	         // 커밋,롤백
	         if(result == 1) {
	            
	               String yn = "";
	               do {
	                  System.out.print(">> 도서를 정말로 등록하시겠습니까?[Y/N] ");
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
	            }// end of if(result == 1)---------------------
	         
	      }catch (SQLIntegrityConstraintViolationException e) {
	         if(e.getErrorCode()==1)
	            result = -1;
	      } catch (SQLException e) {
	            result = -2;
	      } finally {
	         close();
	      }
	      
	      return result; 
	   }// end of public int bookInfoInput(bookDTO book, Scanner sc) ---------------

	
	 /*
	   // 개별 도서 등록(*조연재)
	   @Override
	   public int bookNoInfo(bookDTO book, Scanner sc) {
	      int result=0;

	      try {      
	         conn=ProjectDBConnection.getConn();
	         
	         String sql = " insert into book_list (bookisbn, bookid) "+
	                    " values( ?, ? ) ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, book.getBookisbn());
	         pstmt.setString(2, book.getBookid());

	         result = pstmt.executeUpdate();
	         
	         if(result==1) {
	            // n==1 update 구문이 성공
	            
	               do {
	                  System.out.print("▷ 정말로 등록하시겠습니까?[Y/N] : ");
	                  String yn = sc.nextLine();
	                  
	                  if("y".equalsIgnoreCase(yn)) {
	                     conn.commit(); //커밋
	                     break;
	                  }
	                  else if("n".equalsIgnoreCase(yn)) {
	                     conn.rollback();
	                     result = 0;
	                     break;
	                  }
	                  else {
	                     System.out.println(">> Y 또는 N 만 입력하세요!! <<\n");
	                  }
	                  
	               } while(true);
	               
	            }// end of if(result==1) ---------------------------------------
	                        
	         else {
	            result=-1;
	         }
	                     
	      } catch (SQLIntegrityConstraintViolationException e) {
	         
	         if(e.getErrorCode()==1) {
	            System.out.println(">> 아이디가 중복되었습니다. 새로운 아이디를 입력하세요 !! <<");
	         }
	         else if(e.getErrorCode()==2291) {// foreign key 제약조건 위배 오류코드 //2291
	            result = -1;
	            
	         }
	      } catch (SQLException e) {
	         System.out.println(">> 작성하신 SQL 문장에 오류가 있습니다. <<");
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return result; 
	   }// end of int bookNoInfo(bookDTO book, Scanner sc)-----------------------
	*/

	// 개별 도서 등록(*김다님)
	public int registerBookid(bookDTO book, Scanner sc) {
		
		int result = 0;
		
		try {      
			conn=ProjectDBConnection.getConn();
			
			// 등록된 ISBN인지 확인하기
			String sql = "select bookisbn\n"+
					     "from book_isbn\n"+
					     "where bookisbn = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookisbn());
						
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	// 입력한 ISBN이 book_isbn에 존재하는 경우
				
				sql = "select *\n"+
					  "from book_list\n"+
				      "where bookisbn = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book.getBookisbn());
							
				rs = pstmt.executeQuery();
				
				if(rs.next()) {	// 해당 ISBN의 이전에 등록한 도서아이디가 있는 경우
					
					// 해당 ISBN의 도서아이디를 갖고 있는 도서의 count(*) 합을 갖고 오기
					sql = "select count(*)\n"+
						  "from book_list\n"+
						  "where bookisbn = ?";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, book.getBookisbn());
								
					rs = pstmt.executeQuery();
					
					int totalBookCnt = 0;	// 도서아이디를 갖고 있는 기존 ISBN 등록된 도서 총 권수
					while(rs.next()) {
						totalBookCnt = rs.getInt(1);	
					}
					
					for(int i=0; i<Integer.parseInt(book.getBookqty()); i++) {
						int idNum = i+1+totalBookCnt;	// 기존 도서권수 총합 + 입력받은 도서권수만큼 숫자가 1씩 증가
						
						String bookid = String.format("%03d", idNum);
						
						sql = "insert into book_list(bookisbn, bookid)\n"+
							  "values('"+book.getBookisbn()+"', '"+book.getBookisbn()+"-"+bookid+"')";
						
						pstmt = conn.prepareStatement(sql);
						
						result = pstmt.executeUpdate();
						
					}
					
				}
				else { // 처음 도서아이디를 등록하는 경우
					
					for(int i=0; i<Integer.parseInt(book.getBookqty()); i++) {
						int idNum = i+1;	// 도서권수만큼 숫자가 1씩 증가
						
						String bookid = String.format("%03d", idNum);
						
						sql = "insert into book_list(bookisbn, bookid)\n"+
							  "values('"+book.getBookisbn()+"', '"+book.getBookisbn()+"-"+bookid+"')";
						
						pstmt = conn.prepareStatement(sql);
						
						result = pstmt.executeUpdate();
					}
				}
					if(result==1) {
		               do {
		                  System.out.print("▷ 정말로 등록하시겠습니까?[Y/N] : ");
		                  String yn = sc.nextLine();
		                  
		                  if("y".equalsIgnoreCase(yn)) {
		                     conn.commit(); //커밋
		                     break;
		                  }
		                  else if("n".equalsIgnoreCase(yn)) {
		                     conn.rollback();
		                     result = 0;
		                     break;
		                  }
		                  else {
		                     System.out.println(">> Y 또는 N 만 입력하세요!! <<\n");
		                  }
		                  
		               } while(true);
			               
			        }
				
			}
			else {	// ISBN이 book_list에 존재하지 않는 경우
				result = -1;			
				// Total Controller에서 존재하지 않는 ISBN 메세지 출력.
				
			}
			
		}catch (SQLIntegrityConstraintViolationException e) {

		} catch (SQLException e) {
	         System.out.println(">> 작성하신 SQL 문장에 오류가 있습니다. <<");
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return result; 
	}	
	

	// 등록 도서 삭제
	public int deleteBook(Map<String, String> paraMap) {
		
		int result = 0;
		
		try {
			  conn = ProjectDBConnection.getConn();
			  
			  String sql = " delete from book_list "
			  		     + " where bookid = ? and status = 0";
				  
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1, paraMap.get("bookid"));
			  
			  
			  result = pstmt.executeUpdate();
				
			  
		} catch (SQLException e) {
		     e.printStackTrace();
		} finally {
			close();
		}		
		return result;
	}// end of public int deleteBook(Map<String, String> paraMap) --------------

/* java.sql.SQLIntegrityConstraintViolationException: 
 * ORA-02292: integrity constraint (SEMIUSER_5.FK_RENT_BOOK) violated - child record found
 */
	
	
	// 삭제 도서 정보 확인
	@Override
	public bookDTO viewContents(Map<String, String> paraMap) {
		bookDTO bdto = null;
		try {
			conn = ProjectDBConnection.getConn();
			
			String sql = "select I.booktitle, I.authorname \n"+
						 "from book_isbn I JOIN book_list L ON I.bookisbn=L.bookisbn\n"+
						 "where bookid=?";

			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("bookid"));
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				bdto = new bookDTO();
				bdto.setBooktitle(rs.getString(1));
				bdto.setAuthorname(rs.getString(2));
			}// end of if(rs.next())---------------------
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return bdto;
	} 
	
	// 도서 대여여부 체크
		@Override
		public boolean checkBookRent(String bookid) {
			boolean rentFlag = false;
			// false는 비치중(0), true는 대여중(1)
			try {
		         conn = ProjectDBConnection.getConn();
		         
		         String sql = " select bookid "+
		                      " from book_rent "+
		                      " where bookid=? and rentstatus=1 ";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, bookid);
		         
		         rs = pstmt.executeQuery();
		         
		         if(rs.next()) {
		        	 rentFlag=true; // 대여중인 책임!
		         }
		         else {
		        	 rentFlag=false; // 비치중인 책임!
		         }
		         
		     }catch(SQLException e) {
		    	  System.out.println("SQL 구문이 잘못되었습니다");
		     }finally {
		    	  close();
		     }
			
			return rentFlag;
		}
	
		
	// 도서 대여하기
	@Override
	public boolean checkRentStatus(String bookid, int no, String userid) {
		boolean b = false;
		int n = 0;
		
		try {
	         conn = ProjectDBConnection.getConn();
	         
	         String sql = "select status "+
	                      "from book_list "+
	                      "where bookid = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, bookid);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next() && "0".equals(rs.getString(1))) {	// 비치중인 도서인 경우
	        	sql = " insert into book_rent(rentseq, bookid, userid, rentstatus) "+	// book_rent에 수정하기
	        		  " values(rentseq.nextval, ?, ?, 1) ";
	        	 
	    	 	pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, bookid); 
		        pstmt.setString(2, userid); 
		        n = pstmt.executeUpdate();
		        
		        if(n==1) {
			        sql = " update book_list set status=1 where bookid=? ";     // book_list에 수정하기
				        
		    	 	pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1, bookid);
			        n = pstmt.executeUpdate();
			        
			        if(n==1) {
				        sql = " update lib_member set totalrent=totalrent+1 where userid=? ";     // lib_member 회원 총 대여수에 가산
				        
			        	pstmt = conn.prepareStatement(sql);
				        pstmt.setString(1, userid);			        
				        n = pstmt.executeUpdate();
				        
				        conn.commit();
			        	b=true;	
			        }
		        }
		      
	         }	else
	        	 System.out.println("대여중인 도서입니다~ 다른 도서ID를 입력하세요");

	     } catch(SQLException e) {
	    	  System.out.println("SQL 구문이 잘못되었습니다");
	    	  e.printStackTrace();
	     } finally {
	    	  close();
	     }
		
		return b;
	}// end of public boolean checkRentStatus(String bookid, int no, String userid) ------------------------------
		
	
	
	// 대여중 도서목록
	@Override
	public List<bookDTO> checkOutInfo() {
		List<bookDTO> bookDTOList = new ArrayList<>();
		
		try {
			  conn = ProjectDBConnection.getConn(); // conn 은 수동commit 으로 되어져 있다.
			  
			  String sql = " select B.bookid, L.bookisbn, booktitle, authorname, publishcom, B.userid, username, mobile, rentday, returnday from "+
						  "(   select R.bookid as bookid, R.userid as userid, rentstatus, rentday, returnday, username, mobile "+
						  "    from book_rent R join lib_member M\n"+
						  "    ON R.userid = M.userid "+
						  "    where rentstatus=1 "+
						  ") B "+
						  "LEFT JOIN book_list L "+
						  "ON B.bookid = L.bookid "+
						  "LEFT JOIN  "+
						  "(select booktitle, authorname, publishcom, bookisbn from book_isbn) I "+
						  "ON L.bookisbn = I.bookisbn ";
			  
			  pstmt = conn.prepareStatement(sql);
			  rs = pstmt.executeQuery();
			  			  
			  while(rs.next()) {
				  bookDTO bdto = new bookDTO();
				  
				  bdto.setBookid(rs.getString(1));
				  bdto.setBookisbn(rs.getString(2));
				  bdto.setBooktitle(rs.getString(3));
				  bdto.setAuthorname(rs.getString(4));
				  bdto.setPublishcom(rs.getString(5));
				  
				  userDTO udto = new userDTO();
				  udto.setUserid(rs.getString(6));
				  udto.setUsername(rs.getString(7));
				  udto.setMobile(rs.getString(8));

				  bdto.setUser(udto);
				  
				  bdto.setRentday(rs.getString(9));
				  bdto.setReturnday(rs.getString(10));
				  
				  bookDTOList.add(bdto);
			  }	
			   
		  } catch(SQLException e) {
			  System.out.println("SQL 구문 오류입니다");
		  } finally {
				close();
		  }
		return bookDTOList;
	}// end of public List<bookDTO> checkOutInfo() -------------------------------------------------


	
	// 도서 반납하기
	@Override
	public boolean returnBook(String bookid) {
		boolean b = false;
		int n = 0;
		
		try {
	         conn = ProjectDBConnection.getConn();
	         
	         String sql = "select rentstatus, userid "+
	                      "from book_rent "+
	                      "where bookid = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, bookid);	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next() && "1".equals(rs.getString(1))) {		// 입력한 도서ID가 대여중인 도서인 경우
	        	sql = " update book_rent set rentstatus=0 where bookid=? "; 	// book_rent에 수정하기
	        	 
	    	 	pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, bookid); 
		        n = pstmt.executeUpdate();
		        
		        if(n==1) {
			        sql = " update book_list set status=0 where bookid=? ";     // book_list에 수정하기
				        
		    	 	pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1, bookid);
			        n = pstmt.executeUpdate();
			        
			        if(n==1) {
				        sql = " update lib_member set totalrent=totalrent-1 where userid=? ";     // lib_member 회원 총 대여수에 가산
				        
			        	pstmt = conn.prepareStatement(sql);
				        pstmt.setString(1, rs.getString(2));			        
				        n = pstmt.executeUpdate();
				        
				        conn.commit();
			        	b=true;	
			        	System.out.println("도서별 연체료: "+returnFee(bookid));
			        }
		        }
		      
	         }	else if (rs.next() && "0".equals(rs.getString(1))) {	// 입력한 도서ID가 비치중인 도서인 경우
	        	 System.out.println(">> 현재 비치중인 도서ID입니다. 대여할 도서ID를 입력해주세요! ");
	         } else  System.out.println(">> 존재하지 않는 도서ID입니다. 다른 도서ID를 입력해주세요! ");

	     } catch(SQLException e) {
	    	  System.out.println("SQL 구문이 잘못되었습니다");
	    	  e.printStackTrace();
	     } finally {
	    	  close();
	     }
		
		return b;
	}// end of public boolean returnBook(String bookid) --------------------------------------------------


	
	// 연체료 계산하기 메소드
	public int returnFee(String bookid) {
		int fee = 100;	// 연체일당 기본 연체료 100원
		
		try {
	         conn = ProjectDBConnection.getConn();
	         
	         String sql = "select trunc(rentday-returnday) "+
	                      "from book_rent "+
	                      "where bookid = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, bookid);	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {			        	 
	        	int period = rs.getInt(1);
	        	if(period<=0) fee=fee*0;
	        	else fee=fee*period;
		     }
		 } catch(SQLException e) {
	    	  System.out.println("SQL 구문이 잘못되었습니다");
	    	  e.printStackTrace();
	     } finally {
	    	  close();
	     }
		
		return fee;
	}// end of public int returnFee(String bookid) ---------------------------------------------------------

 
	// 일반회원 도서검색
	@Override
	public List<bookDTO> showBookInfo(String bookctg, String booktitle, String authorName, String publishcom) {

		String[] menuAnswer = new String[4];
		menuAnswer[0] = bookctg;
		menuAnswer[1] = booktitle;
		menuAnswer[2] = authorName;
		menuAnswer[3] = publishcom;
		
		String[] choiceCase = new String[4];
		choiceCase[0] = "bookctg";
		choiceCase[1] = "booktitle";
		choiceCase[2] = "authorName";
		choiceCase[3] = "publishcom";
		
		boolean[] bArr = new boolean[4];
		bArr[0] = bookctg.trim().isEmpty();
		bArr[1] = booktitle.trim().isEmpty();
		bArr[2] = authorName.trim().isEmpty();
		bArr[3] = publishcom.trim().isEmpty();
		
		int cnt = 0, totalCnt = 0;
		
		for (int i=0; i<bArr.length; i++) {
			if(!bArr[i]) cnt++;
		}
		totalCnt=cnt;
		
		List<bookDTO> bookList = new ArrayList<>();
		String where = "";
		
		try {
			  conn = ProjectDBConnection.getConn(); // conn 은 수동commit 으로 되어져 있다.
			  
			  if(cnt!=0) {
				  where = " where ";
				  for (int i=0; i<4; i++) {
					   if(bArr[i]==false) {
						   where += choiceCase[i]+" like ? "; 
					       cnt--;
					       if(cnt!=0) where += "or "; 
					       else break;
					   } 	   
				  }
			  }
			  
			  
			  String sql = "select a.bookisbn as bookisbn, a.bookid as bookid,  b.booktitle as booktitle,  "+
			  		      " b.authorname as authorname, b.publishcom as publishcom, b.price as price, "+
			  		      " decode(a.rentstatus,0,'비치중','대여중') as rentstatus, b.bookctg as bookctg "+
						  " from "+
						  "(select l.bookid, l.bookisbn,r.userid, r.rentstatus\n"+
						  "from book_list L LEFT JOIN book_rent R\n"+
						  "ON R.bookid = L.bookid \n"+
						  ") A\n"+
						  "LEFT JOIN\n"+
						  "(select booktitle, authorname, publishcom, bookisbn, price, bookctg\n"+
						  "from book_isbn\n"+
						  ") B\n"+
						  "ON A.bookisbn = B.bookisbn "+ where;
					  	   
			  // System.out.println("확인용:"+sql);
			  
			  pstmt = conn.prepareStatement(sql);
			  
			  cnt = 0;
			  if(totalCnt!=0) {
				  for (int i=0; i<4; i++) {
					  if(bArr[i]==false)  pstmt.setString(cnt+=1, "%" +menuAnswer[i] + "%");
				  }
			  }

			  
			  rs = pstmt.executeQuery();
		  		
			  while(rs.next()) {
				  bookDTO bdto = new bookDTO();
				  
				  bdto.setBookisbn(rs.getString(1));
				  bdto.setBookid(rs.getString(2));
				  bdto.setBooktitle(rs.getString(3));
				  bdto.setAuthorname(rs.getString(4));
				  bdto.setPublishcom(rs.getString(5));
				  bdto.setPrice(rs.getString(6));
				  bdto.setRentstatus(rs.getString(7));
				  bdto.setBookctg(rs.getString(8));
				  
				  bookList.add(bdto);
			  }	
			  
		  } catch(SQLException e) {
			  System.out.println("SQL 구문 오류입니다");
			  e.printStackTrace();
		  } catch(Exception e) { 
			  e.printStackTrace();
		  } finally {
				close();
		  }
		return bookList;
		
		}// end of public List<bookDTO> showBookInfo() --------------------------------------------------



	// 일반회원 도서대여현황 조회
	@Override
	public List<bookDTO> checkRentalInfo(userDTO udto) {
		
		List<bookDTO> rentalList = new ArrayList<>();
		
		try {
			  conn = ProjectDBConnection.getConn(); // conn 은 수동commit 으로 되어져 있다.
			  
			  //도서ID ISBN 도서명 작가명 출판사 회원ID 대여일자 반납예정일
			  
			  String sql = "select L.bookid, I.bookisbn, I.booktitle, I.authorname, I.publishcom, R.userid, R.rentday, R.returnday\n"+
						   "from book_isbn I INNER JOIN book_list L ON I.bookisbn=L.bookisbn\n"+
						   "INNER JOIN book_rent R ON L.bookid=R.bookid\n"+
						   "where userid = ? and rentstatus = 1 ";
			  
			  
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1, udto.getUserid());
			  
			  rs = pstmt.executeQuery();
			  		
				  while(rs.next()) {
					  bookDTO bdto = new bookDTO();
					  
					  bdto.setBookid(rs.getString(1));
					  bdto.setBookisbn(rs.getString(2));
					  bdto.setBooktitle(rs.getString(3));
					  bdto.setAuthorname(rs.getString(4));
					  bdto.setPublishcom(rs.getString(5));
					  
					  udto.setUserid(rs.getString(6));
					  
					  bdto.setRentday(rs.getString(7));
					  bdto.setReturnday(rs.getString(8));
	
					  bdto.setUser(udto);
					  
					  rentalList.add(bdto);
				  }	
			  
		  } catch(SQLException e) {
			  System.out.println("SQL 구문 오류입니다");
			  e.printStackTrace();
		  } finally {
				close();
		  }
		return rentalList;
			
		}// public List<bookDTO> checkRentalInfo(userDTO udto) -------------------------------------


	
	//bookisbn 이 존재하는지 체크하는 메소드
		@Override
		public boolean checkBookisbn(String isbn_input) {
			boolean b = false;
			
			try {
	         conn = ProjectDBConnection.getConn();
	         
	         String sql = "select bookisbn "+
	                      "from book_list "+
	                      "where bookisbn = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, isbn_input);
	         
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



		
		// 현재 연체된 회원의 도서목록
		@Override
		public String showDelayFeeList(String answer) {
			String str = "";
			
			do {
				if("y".equalsIgnoreCase(answer)) {				
					try {
				         conn = ProjectDBConnection.getConn();
				         
				         String sql = "select rentstatus "+
				                      "from book_rent "+
				                      "where rentstatus = 1 ";
				         
				         pstmt = conn.prepareStatement(sql);
				         rs = pstmt.executeQuery();
				         
				         if(rs.next()) {
				        	 List<bookDTO> showDelayFeeList = new ArrayList<>();
				        	 
				        	 sql = "select B.bookid, booktitle, authorname, B.userid, username, mobile, "
				        	 	 + "rentday, returnday, trunc(sysdate-returnday)\n"+
			        			 "from\n"+
			        			 "(\n"+
			        			 "    select R.bookid as bookid, R.userid as userid, rentstatus, rentday, returnday, username, mobile\n"+
			        			 "    from book_rent R join lib_member M\n"+
			        			 "    ON R.userid = M.userid\n"+
			        			 "    where rentstatus=1\n"+
			        			 ") B\n"+
			        			 "LEFT JOIN book_list L\n"+
			        			 "ON B.bookid = L.bookid\n"+
			        			 "LEFT JOIN \n"+
			        			 "(select booktitle, authorname, publishcom, bookisbn from book_isbn) I\n"+
			        			 "ON L.bookisbn = I.bookisbn\n"+
			        			 "where sysdate-returnday>0 "+
			        			 "order by 1";
				         
				        	 // System.out.println(sql);
				        	 
					         pstmt = conn.prepareStatement(sql);
					         rs = pstmt.executeQuery();
					         
					         while(rs.next()) {
								  bookDTO bdto = new bookDTO();
								  userDTO udto = new userDTO();
								  
								  bdto.setBookid(rs.getString(1));
								  bdto.setBooktitle(rs.getString(2));
								  bdto.setAuthorname(rs.getString(3));
								  
								  udto.setUserid(rs.getString(4));
								  udto.setUsername(rs.getString(5));
								  udto.setMobile(rs.getString(6));
								  bdto.setUser(udto);
								  
								  bdto.setRentday(rs.getString(7));
								  bdto.setReturnday(rs.getString(8));
								  bdto.setDelayFee(rs.getString(9));
								  
								  showDelayFeeList.add(bdto);
							  }	
					         
					         
					         if(showDelayFeeList.size()>0) {
						         str = "\n=======================================================================================================================================\n"
					         		 + "회원ID\t회원명\t전화번호\t\t연체료(원)\t\t도서ID\t\t\t도서명\t\t작가명\t대여일\t\t반납예정일\n"
					         		 + "=======================================================================================================================================\n";
						         
						         for(bookDTO bdto : showDelayFeeList) {
									str+= bdto.getUser().getUserid()+"\t"+bdto.getUser().getUsername()+"\t"+bdto.getUser().getMobile()+"\t"+
										  String.valueOf((Integer.parseInt(bdto.getDelayFee())*100))+"\t\t"+bdto.getBookid()+"\t"+bdto.getBooktitle()+"\t"+bdto.getAuthorname()+"\t"+ 
										  bdto.getRentday().substring(0, 10)+"\t"+bdto.getReturnday().substring(0, 10)+"\n";		
								 }
					         }
				         } 				         
				     }catch(SQLException e) {
				    	  System.out.println(">> SQL 구문이 잘못되었습니다");
				    	  e.printStackTrace();
				     }finally {
				    	  close();
				     }
					 break;
				} else if ("n".equalsIgnoreCase(answer)) {
					str = ""; break;
				} else {
					System.out.println(">> Y 또는 N 을 입력해주세요!");
					str="wrong";
				}
				
			} while ("y".equalsIgnoreCase(answer)||"n".equalsIgnoreCase(answer)) ;
			
			return str;
		}
}
