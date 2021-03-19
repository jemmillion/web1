package bookrental;


import java.util.*;
import java.sql.*;


public class TotalController {

	interLibrarianDAO ldao = new librarianDAO();
	interUserDAO udao = new userDAO();
	interBookDAO bdao = new bookDAO();
	
	String menuNo;
	String menuNo2;
	
// menu_Start
	public void menu_Start(Scanner sc) {
		
		do {	
			System.out.println("\n===> 도서대여 프로그램 <===");
			System.out.println("1. 사서 전용메뉴     2. 일반회원 전용메뉴     3. 프로그램종료");
			System.out.print("=> 메뉴번호선택 : ");
			menuNo = sc.nextLine();	
			
			switch (menuNo) {
			case "1":
				librarianMenu(sc);
				break;
			case "2":
				userMenu(sc);
				break;
			case "3":
				ProjectDBConnection.closeConnection();
				break;
			default:
				System.out.println(">> 메뉴에 없는 번호입니다. 다시 입력하세요.\n");
				break;
			}   
		} while(!("3".equals(menuNo)));
	
	}

	


// librarianMenu
	private void librarianMenu(Scanner sc) {
		
		librarianDTO ldto = null;
		
		do {
			String loginId = (ldto == null)?" ":"["+ ldto.getAdminid() +"님 로그인 중..]"; 
			String addMenu = (ldto == null)?"4. 나가기":"4. 도서정보등록     5. 개별도서등록\n"
							+ "6. 도서대여해주기      7. 대여중인도서조회     8. 도서반납해주기        9. 등록도서삭제        10.나가기"; 
	        
	      		System.out.println("\n===> 사서 전용 메뉴 "+loginId+"<===");
			System.out.println("1. 사서가입      2. 로그인      3. 로그아웃     "+addMenu);
			System.out.print("=> 메뉴번호선택 : ");
			
			
			// 로그인 안되어 있을 때
			if(ldto == null) {
				menuNo = sc.nextLine();
				
				switch (menuNo) {
				case "1":
					librarianRegister(sc); // 사서 가입하기
					break;
				case "2":
					ldto=login(sc); // 로그인
					break;
				case "3":
					System.out.println(">> 이미 로그아웃 상태입니다! ");
					break;				
				case "4":
					System.out.println("\n");
					break;	
				default:
					System.out.println(">> 메뉴에 없는 번호입니다. 다시 입력하세요.\n");
					break;
				}
			
				
			// 로그인 되어 있을 때
			} else {
				menuNo2 = sc.nextLine();
				
				switch (menuNo2) {
				case "1":
					librarianRegister(sc); // 사서 가입하기
					break;
				case "2":
					System.out.println(">> 이미 로그인 상태입니다! 다른 ID로 로그인로 로그인하시려면 로그아웃해주세요~");
					break;
				case "3":
					ldto=logout(); // 로그아웃
					break;				
				case "4":
					int n = bookInfoInput(sc); // 도서정보등록
					
					 if(n==0) {
			               System.out.println("\n >>> 도서 등록을 취소하셨습니다. <<<");
			               break;
			            }
			            else if(n==1) {
			               System.out.println("\n >>> 도서 등록을 완료하셨습니다. <<<");
			               break;
			            }
			            else if(n==-1) {
			               System.out.println("\n >>> 이미 등록된 도서번호입니다. <<<");
			               break;
			            }
			            else if(n==-2) {
			               System.out.println("\n >>> ISBN이나 가격에는 숫자만 입력하세요! <<<");
			               break;
			            }
			            
		        	    break;

				case "5":
					n = bookDetailNo(sc); // 개별도서등록
	            
		            if(n==1) {
		               System.out.println(">> 도서등록 성공!! << \n");
		            }
		            else if(n==0) {
		               System.out.println(">> 도서등록을 취소하셨습니다. <<\n");
		            }
		            else if(n==-1) {
		               System.out.println(">> 입력하신 국제표준도서번호(ISBN)가 존재하지 않으므로 도서등록 실패!! << \n");
		            }
		            

					break;
				case "6":
					checkOut(sc); // 도서대여해주기
					break;
				case "7":
					checkOutInfo(sc); // 대여중인도서조회
					break;
				case "8":
					returnBook(sc); //도서반납하기
					break;
				case "9":
					deleteBook(null, sc);// 등록도서삭제
					
					break;
				case "10":
					break;
				default:
					System.out.println(">> 메뉴에 없는 번호입니다. 다시 입력하세요.\n");
					break;
				}
			}
			
		} while(!("4".equals(menuNo) || "10".equals(menuNo2)));
	}

	



// userMenu
	
	private void userMenu(Scanner sc) {       
         userDTO udto = null;

        
         do {
        	 String loginName = (udto == null)?"":"["+ udto.getUsername() +"님 로그인 중..]"; 
        	 String addMenu = (udto == null)?"4. 나가기":"4.도서검색하기   5.나의대여현황보기   6.회원탈퇴하기   7.나가기";
        	 
             System.out.println("\n===> 일반회원 전용메뉴 "+loginName+"<===");
             System.out.println("1.일반회원가입      2. 로그인     3. 로그아웃   "+addMenu);
             System.out.print("=> 메뉴번호선택 : ");
            
             // 회원이 로그인 안 되어 있을 때
             if(udto == null) {
 				 menuNo = sc.nextLine();
 				 
		         switch (menuNo) {
		         case "1":   //일반회원가입
		            memberRegister(sc);		            
		            break;
		         case "2":   //로그인
		        	 udto=userLogin(sc);		         
		            break;
		         case "3":   //로그아웃
		        	 System.out.println(">> 이미 로그아웃 상태입니다! ");	            
		            break;
		         case "4": 
		        	 System.out.println("\n");	            
		            break;
		   
		         default:
		            System.out.println(">> 메뉴에 없는 번호입니다. 다시 입력하세요.\n");
		            break;
		         }
		         
		         
		      // 회원이 로그인 되어 있을 때
             } else {
 				 menuNo2 = sc.nextLine();

		         switch (menuNo2) {
		         case "1":   //일반회원가입
		            memberRegister(sc);		            
		            break;
		         case "2":   //로그인
		        	 System.out.println(">> 이미 로그인 상태입니다! 다른 ID로 로그인로 로그인하시려면 로그아웃해주세요~");		         
		            break;
		         case "3":   //로그아웃
		        	 udto=userLogout();		            
		            break;
		         case "4":   //도서검색하기
		            bookSearch(sc);		            
		            break;
		         case "5":   //일반회원 도서대여현황 조회
		        	 rentalStatus(udto);		            
		            break;
		         case "6": 	 //회원탈퇴
		        	udto = leaveMember(udto, sc);           
		            break;
		         case "7": 
     	            System.out.println("\n");
			        break;
		         default:
		            System.out.println(">> 메뉴에 없는 번호입니다. 다시 입력하세요.\n");
		            break;
		         }
             }
	         
	       } while(!("4".equals(menuNo) || "7".equals(menuNo2)));
  	}
	

	
	
 //[사서 메서드]  ////////////////////////////////////////////////////////////////////////////////////////////////////
      
	

	// 사서 가입하기
	private void librarianRegister(Scanner sc) {
		int n = 0;
	
		do {
			System.out.println("\n=== 사서 가입하기 ===");
			System.out.print("▶ 사서ID: ");
			String adminid = sc.nextLine();
			System.out.print("▶ 암호: ");
			String passwd = sc.nextLine();
			
			librarianDTO ldto = new librarianDTO();
			ldto.setAdminid(adminid);
			ldto.setPasswd(passwd);
			
			n = ldao.librarianRegister(ldto, sc);
			
			
			 if(n == 0) {
		         System.out.println("\n >> 회원가입을 취소하셨습니다. ");
		      }
		      if(n == 1) {
		         System.out.println("\n >> 회원등록 성공! ");
		      }
		      if(n == -1) {
		         System.out.println("\n >> "+adminid+"가 이미 사용중이므로 다른 아이디를 입력하세요. ");
		      }
		      if(n == -2) {
		         System.out.println("\n >> SQL 구문에 오류가 발생함 ");
		      }
		} while (!(n == 0||n == 1));
    
	}
	
	
	
	
	// 로그인
	private librarianDTO login(Scanner sc) {
		
		librarianDTO ldto = null;
		
		System.out.println("\n=== 로그인하기 ===");
		System.out.print("▶ 사서ID: ");
		String adminid = sc.nextLine();
		System.out.print("▶ 암호: ");
		String passwd = sc.nextLine();
		
	      
	      Map<String, String> paraMap = new HashMap<>();
	      paraMap.put("adminid", adminid);
	      paraMap.put("passwd", passwd);
	      
	      ldto = ldao.login(ldto,paraMap);
	      
	      if(ldto != null) {
	         System.out.println("\n>>  로그인 성공   <<\n");
	      }
	      else {
	         System.out.println("\n>>  로그인 실패  <<\n");
	      }
	      
	      return ldto;
	}
	
	
	
	
	// 로그아웃
	private librarianDTO logout() {	
		librarianDTO ldto = null;
		System.out.println(">> 로그아웃 되었습니다. <<");
		
		return ldto;		
	}

	
	
	
	// 도서정보등록
	private int bookInfoInput(Scanner sc) {
		 int result=0;
	      
	      do {
	         System.out.println("\n=== 도서정보 등록하기 ===");
	         
	         System.out.print("▶ 국제표준도서번호(ISBN): ");
	         String isbn = sc.nextLine();
	         
	         System.out.print("▶ 도서분류카테고리: ");
	         String bookctg = sc.nextLine();
	         
	         System.out.print("▶ 도서명: ");
	         String booktitle = sc.nextLine();
	         
	         System.out.print("▶ 작가명: ");
	         String authorname = sc.nextLine();
	         
	         System.out.print("▶ 출판사: ");
	         String publishcom = sc.nextLine();
	         
	         System.out.print("▶ 가격: ");
	         String price = sc.nextLine();
	      
	         // 도서 등록 항목 설정

	         bookDTO bdto = new bookDTO();
	         
	         bdto.setBookisbn(isbn);
	         bdto.setBookctg(bookctg);
	         bdto.setBooktitle(booktitle);
	         bdto.setAuthorname(authorname);
	         bdto.setPublishcom(publishcom);
	         bdto.setPrice(price);
	               
	         result = bdao.bookInfoInput(bdto, sc);
	      
	         if(result==0) 
	            break;
	         
	         else if(result==1) 
	            break;
	         
	         else if(result==-1) 
	            break;
	         
	         else if(result==-2)
	            break;
	         
	         } while(true);   
	      
	          return result;
	}

	
	
	// 개별도서정보등록
	private int bookDetailNo(Scanner sc) {
		 int result=0;
	      
	      System.out.println("\n== 개별 도서 등록하기 ==");
	      
	      System.out.print("▶ 국제표준도서번호(ISBN): ");
	      String isbn_input = sc.nextLine();
	      System.out.print("▶ 등록할 도서권수 : ");
	      String bookqty = sc.nextLine();
	      
	      
	      bookDTO newbook = new bookDTO();

	      newbook.setBookisbn(isbn_input);
	      newbook.setBookqty(bookqty);
	      
	      result = bdao.registerBookid(newbook, sc);
	      
	      return result;
	  }

	
	
	// 도서대여하기
	private void checkOut(Scanner sc) {
		String userid = ""; 
		boolean b = false;
		int no = 0;
		
		do {
			System.out.println("\n=== 도서 대여하기 ===");
			System.out.print("▶ 회원ID: ");
			userid  = sc.nextLine();
			b = udao.checkUserid(userid);
			
			if(b==false) 
				System.out.println("~~~ 등록된 회원ID가 아닙니다 ~~~");
		} while(!b);		
		
		
			System.out.print("▶ 총대여권수: ");
			no = sc.nextInt();
			sc.nextLine();

		for (int i=0; i<no; i++) {
				System.out.print("▶ 도서ID: ");
				String bookid = sc.nextLine();
				b = bdao.checkRentStatus(bookid, no, userid);

				if(b==true && i==no-1) {
					System.out.println(">> 도서대여가 완료되었습니다!");
					break;
				} else if(b==false) {
					i--;
				}
			
			}
		
	}// end of private void checkOut(Scanner sc)  -----------------------------------------------------
	
	
	// 대여중인 도서정보 조회
	private void checkOutInfo(Scanner sc) {
		System.out.println("\n=============================================================================================================================================================");
		System.out.println("도서ID\t\t\tISBN\t\t\t도서명\t\t작가명\t출판사\t회원ID\t회원명\t연락처\t\t대여일자\t\t반납예정일");
		System.out.println("=============================================================================================================================================================");
		List<bookDTO> checkoutList = bdao.checkOutInfo();
		String str = "";
		
		if(checkoutList.size()>0) {
			for(bookDTO bdto : checkoutList) {
				System.out.println(bdto.getBookid()+"\t"+bdto.getBookisbn()+"\t"+bdto.getBooktitle()+"\t"+bdto.getAuthorname()
							+"\t"+bdto.getPublishcom()+"\t"+bdto.getUser().getUserid()+"\t"+bdto.getUser().getUsername()+"\t"+bdto.getUser().getMobile()
							+"\t"+bdto.getRentday().substring(0, 10)+"\t"+bdto.getRentday().substring(0,10));	
			}
			
			do {
				System.out.print("\n▶ 연체내역을 조회하시겠습니까? [Y/N] : ");
				String answer = sc.nextLine();
				str = bdao.showDelayFeeList(answer);
				if(!"wrong".equals(str)) System.out.println(str);
			} while("wrong".equals(str));
		} 
			
	}// end of private void checkOutInfo() -----------------------------------------------------------------------
	
	
	//도서반납하기
	private void returnBook(Scanner sc) {
		boolean b = false;
		
		System.out.println(">>> 도서반납하기 <<<");
		System.out.print("▶ 총반납권수: ");
		int returnNo  = sc.nextInt();
		sc.nextLine();
	
		for (int i=0; i<returnNo; i++) {
			System.out.print("▶ 반납도서ID: ");
			String bookid = sc.nextLine();
			b = bdao.returnBook(bookid);
			
			if(b==true && i==returnNo-1) {
				System.out.println(">> 도서반납이 완료되었습니다!");
				break;
			} else if(b==false) {
				i--;
			}
			
		}		
	}// end of private void returnBook(Scanner sc) ---------------------------------------------------------------

	// 등록 도서 삭제
	private int deleteBook(bookDTO book, Scanner sc) {

		int result = 0;
		boolean bool=false;
		/*
		 result == 0    삭제할  도서 번호가 존재하지 않습니다.		
		 result == 1    장애발생으로 인해 도서 삭제 실패!!
		 result == 2    도서 삭제 취소!!
		 result == 3    도서 삭제 성공!! 
		 result == 4    대여중이므로 삭제 불가!!
		 */
		String bookid ="";
		
		do {
			 System.out.println("\n>>> 등록 도서 삭제 하기 <<<");
			 
			 System.out.print("▷ 삭제할 도서아이디 : "); 
			 bookid = sc.nextLine();
			 
			 bool=bdao.checkBookRent(bookid); 
			 // 대여중인지 확인하기, 기본으로 false(비치중) 반환
			
			 if(bool==true) {// 대여중이라면
				 System.out.println(">> 대여중이므로 삭제 불가!! << \n");
				 continue;			
			 }

		} while(bool);
		 	 		 
		 Map<String, String> paraMap = new HashMap<>();
		 paraMap.put("bookid", bookid);
					
		 bookDTO bdto = bdao.viewContents(paraMap);

		 if(bdto != null) {
			 // 삭제할 도서아이디가 존재하는 경우
			
			// 삭제할 도서 제목,작가 확인 
			System.out.println("-----------------------------------------");
			System.out.println("도서제목 : " + bdto.getBooktitle());
			System.out.println("작가명 : " + bdto.getAuthorname());
			System.out.println("----------------------------------------- \n");
		

			int n = bdao.deleteBook(paraMap); // 글 삭제하기

			if( n != 1 ) {
				// 장애발생으로 인해 도서 삭제 실패한 경우
				System.out.println(">> 장애발생으로 인해 도서 삭제 실패!! << \n");
			}
			else {
				// 정상적으로 도서 삭제한 경우 
				
				Connection conn = ProjectDBConnection.getConn();
				
				do {
					System.out.print("▷ 정말로 삭제하시겠습니까?[Y/N] "); 
					String yn = sc.nextLine();
					
					try {
						
						if("y".equalsIgnoreCase(yn)) {
							conn.commit(); // 커밋 
							System.out.println(">> 도서 삭제 성공!! << \n");
							break;
						}
						
						else if("n".equalsIgnoreCase(yn)) {
							conn.rollback(); // 롤백 
							System.out.println(">> 도서 삭제 취소!! << \n");
							break;
						}
						
						else {
							System.out.println(">> Y 또는 N 만 입력하세요!! << ");
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} while (true);
				
			}
		 }// end of if(bdto != null)------------------
		 else {
			 System.out.println(">> 삭제할 도서가 존재하지 않습니다. << \n"); 
		 }
		return result;
		 
	}// end of private int deleteBook(bookDTO book, Scanner sc)----------
	
	
	
  //[일반회원 메서드]     ////////////////////////////////////////////////////////////////////////////////////////////////////


	// ----- *** 일반회원가입 *** -----
    private void memberRegister(Scanner sc) {
    	int n = 0;
		
    	do {
	    	System.out.println("\n >> ======== 일반회원가입 ======== <<");
	        
	        System.out.print("▶  아이디 : ");
	        String userid= sc.nextLine();
	        
	        System.out.print("▶ 암호: ");
	        String passwd = sc.nextLine();
	        
	        System.out.print("▶  성명 : ");
	        String username = sc.nextLine();
	        
	        System.out.print("▶ 연락처(휴대폰) : ");
	        String mobile = sc.nextLine();
	         
	        userDTO udto = new userDTO();
	        udto.setUserid(userid);
	        udto.setPasswd(passwd);
	        udto.setUsername(username);
	        udto.setMobile(mobile);
	        
	        n = udao.memberRegister(udto, sc);
	        
	        if(n == 0) {
	           System.out.println("\n >> 회원가입을 취소하셨습니다. ");
	        } else if(n == 1) {
	           System.out.println("\n >> 회원등록 성공! ");
	        } else if(n == -1) {
	           System.out.println("\n >> "+userid+"가 이미 사용중이므로 다른 아이디를 입력하세요. ");
	        } else if(n == -2) {
	           System.out.println("\n >> SQL 구문에 오류가 발생함 ");
	        }
    	} while (!(n == 0||n == 1));
        
      
   }
    
  
   // ----- *** 일반회원 로그인 *** ----- 
    private userDTO userLogin(Scanner sc) {
       
    	userDTO udto = null;
        System.out.println("\n >>> --- 로그인 하기 --- <<<");
        
        System.out.print("▷ 아이디 : ");
        String userid = sc.nextLine();
        System.out.print("▷ 비밀번호 : ");
        String passwd = sc.nextLine();
        
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("userid", userid);
        paraMap.put("passwd", passwd);
        
        udto= udao.login(udto, paraMap);
        
        if(udto != null) {
           System.out.println("\n>>  로그인 성공   <<\n");
        }
        else {
           System.out.println("\n>>  로그인 실패  <<\n");
        }
        
        return udto;
           
         
   }//end of private userDTO userLogin(Scanner sc)--------
   
  
    
   // ----- *** 일반회원 로그아웃 *** -----
    private userDTO userLogout() {
    	userDTO udto = null;
	    System.out.println("\n >> 로그아웃 되었습니다. <<");
	    
	    return udto;
	} //end of private void userLogout(userDTO user) --------------------
       

 // ----- *** 일반회원 도서검색 *** -----
    private void bookSearch(Scanner sc) {

       
       System.out.println(">>> 도서 검색하기 <<<\n"
                     + "[주의사항] 검색어를 입력하지 않고 엔터를 하면 검색대상에서 제외됩니다.");
       System.out.print("▶ 도서분류 카테고리(IT, 현대문학 등): ");
       String bookctg = sc.nextLine();
       
       System.out.print("▶ 도서명 :");
       String booktitle = sc.nextLine();
      
       System.out.print("▶  작가명 :");
       String authorName = sc.nextLine();
       
       System.out.print("▶ 출판사명 :");
       String publisherName = sc.nextLine();
       
       
       List<bookDTO> bookDTOList = bdao.showBookInfo(bookctg, booktitle, authorName, publisherName);
       
		  
	   System.out.println("========================================================================================================================");
	   System.out.println("카테고리\tISBN\t\t\t도서아이디\t\t\t도서명\t\t작가명\t출판사\t가격\t대여상태");
	   System.out.println("========================================================================================================================");
		
	   if(bookDTOList.size()>0) {
			for(bookDTO bdto : bookDTOList) {
				System.out.println(bdto.getBookctg()+"\t"+bdto.getBookisbn()+"\t"+bdto.getBookid()+"\t"+bdto.getBooktitle()+"\t"+bdto.getAuthorname()
							+"\t"+bdto.getPublishcom()+"\t"+bdto.getPrice()+"\t"+bdto.getRentstatus());		
			}
	   } else System.out.println(">> 해당 도서 내역이 없습니다!");
	   
        
   }
    
    
   // ----- *** 일반회원 도서대여현황 *** -----
   private void rentalStatus(userDTO udto) {   
	   
      System.out.println("\n========================================================================================================================");
      System.out.println("도서ID\t\t\tISBN\t\t\t도서명\t\t작가명\t출판사\t회원ID\t대여일자\t\t\t반납예정일");
      System.out.println("==========================================================================================================================");
      
      
      List<bookDTO> rentalList = bdao.checkRentalInfo(udto);
      
      if(rentalList.size()>0) {
          for(bookDTO bdto : rentalList) {
             System.out.println(bdto.getBookid()+"\t"+bdto.getBookisbn()+"\t"+bdto.getBooktitle()+"\t"+bdto.getAuthorname()
                      +"\t"+bdto.getPublishcom()+"\t"+bdto.getUser().getUserid()
                      +"\t"+bdto.getRentday()+"\t"+bdto.getReturnday());      
          }
       }

      else {
    	  System.out.println(">> 대여하신 도서가 없습니다.");
      }
      
   }
   

   // ------ *** 회원 탈퇴하기 *** --------
   private userDTO leaveMember(userDTO user,Scanner sc) {

	      System.out.print("\n>>> 회원탈퇴하기 <<<\n");

	      String userid=user.getUserid().toString();
	      String passwd="";
	      
	      boolean bool=udao.checkUserid(userid);
	      
	      do{
	         System.out.print("▶비밀번호 확인: ");
	         passwd=sc.nextLine();
	         
	         if(!passwd.equals(user.getPasswd())) {
	            System.out.println("\n>> 비밀번호가 틀렸습니다. 다시 입력해주세요.<<");
	         }
	         else {
	            break;
	         }
	      }while(true);
	      
	      
	      Map<String, String> paraMap = new HashMap<>();
	      paraMap.put("userid", userid);
	      paraMap.put("passwd", passwd);
	      
	      if(bool==true) {
	         
	         do {
	            Connection conn = ProjectDBConnection.getConn();
	             
	            System.out.print("\n▷ 정말로 탈퇴하시겠습니까?[Y/N] "); 
	            String yn = sc.nextLine();
	            
	            try {
	               int n=udao.leaveMember(paraMap); // 회원탈퇴하기
	               
	                  if("y".equalsIgnoreCase(yn)) {   
	                     if(n!=1) {
	                        System.out.println(">>>> 대여중인 도서가 있므로 탈퇴 불가!! <<<<");
	                        break;
	                     }
	                     
	                     conn.commit(); // 커밋 
	                     System.out.println(">> 회원 탈퇴 성공!! << \n");
	                     user = null;
	                     break;
	                  }
	                  
	                  else if("n".equalsIgnoreCase(yn)) {
	                     conn.rollback(); // 롤백 
	                     System.out.println(">> 회원 탈퇴 취소!! << \n");
	                     break;
	                  }
	                  
	                  else {
	                     System.out.println(">> Y 또는 N 만 입력하세요!! << ");
	                  }
	                     
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }

	         } while (true);
	      }// end of if(bool==true)------------------------------------
	      return user;
	   }// end of private userDTO leaveMember(userDTO user,Scanner sc)-----
	
}

   
