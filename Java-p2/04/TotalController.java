package jdbc.day04;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import jdbc.day04.singleton.dbconnection.MyDBConnection;
import my.util.MyUtil;


public class TotalController {

	// field, attribute, property, 속성
	InterMemberDAO mdao = new MemberDAO();
	InterBoardDAO  bdao = new BoardDAO(); 
	
	
	// **** 시작메뉴 **** //
	public void menu_Start(Scanner sc) {
		
		MemberDTO member = null;
		
		String s_Choice = "";
		
		do {
			String loginName = (member==null)?"":"["+ member.getName() +" 로그인중..]"; 
			String login_logout = (member==null)?"로그인":"로그아웃";	
			String myInfoView = (member==null)?"":"4.내정보보기";
			String goBoardMenu = (member==null)?"":"5.게시판가기";
			
			System.out.println("\n >>> ----- 시작메뉴 "+ loginName +" ----- <<< \n"
					         + "1.회원가입   2."+ login_logout +"   3.프로그램종료    "+ myInfoView + "   "+ goBoardMenu + "\n"                
					         + "---------------------\n");
			
			System.out.print("▷ 메뉴번호 선택 : ");
			s_Choice = sc.nextLine();
			
			switch (s_Choice) {
				case "1": // 회원가입
					memberRegister(sc);
					break;
					
				case "2": // 로그인 또는 로그아웃
					
					if("로그인".equals(login_logout)) {
						member = login(sc); // 로그인시도하기 
					 /*
					    if(member != null) { // 로그인이 성공한 경우
					        menu_Board();    // 게시판 메뉴에 들어간다.
					    }
					 */   
					} 
					
					else {
						member = null; // 로그아웃하기
						System.out.println(">>> 로그아웃 되었습니다. <<< \n");
					}
					
					break;	
	
				case "3": // 프로그램종료
					MyDBConnection.closeConnection(); // Connection 객체 자원반납
					break;
					
				
				case "4": // 내정보보기  ==> 로그인을 했을때에만 작동되도록 해야 한다. 
					if(member != null)
						System.out.println(member);
					break;	
				
				
				case "5": // 게시판가기  ==> 로그인을 했을때에만 작동되도록 해야 한다. 
					if(member != null)
						menu_Board(member, sc);   // 게시판 메뉴에 들어간다.
					break;	
							
					
				default:
					System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요! <<< \n"); 
					break;
			}// end of switch (s_Choice)-----------
			
		} while ( !("3".equals(s_Choice) ) );		
		
	}// end of public void menu_Start(Scanner sc)---------------



	// **** 회원가입 **** //
	private void memberRegister(Scanner sc) {
		
		System.out.println("\n >>> ---- 회원가입 ---- <<<");
		
		System.out.print("1. 아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("2. 비밀번호 : ");
		String passwd = sc.nextLine();
		
		System.out.print("3. 회원명 : ");
		String name = sc.nextLine();
		
		System.out.print("4. 연락처(휴대폰) : ");
		String mobile = sc.nextLine();
		
		MemberDTO member = new MemberDTO();
		member.setUserid(userid);
		member.setPasswd(passwd);
		member.setName(name);
		member.setMobile(mobile);
		
		int n = mdao.memberRegister(member, sc);
		/* 
		    0 => 회원가입을 취소한 것임. 
		    1 => 정상적으로 회원가입한 것임.
		   -1 => 사용자아이디에 중복이 발생한 것임.
		   -2 => SQL구문에 오류발생한 것임.
	    */
		
		if(n==0) {
			System.out.println("\n >>> 회원가입을 취소하셨습니다. <<<");
		}
		else if(n==1) {
			System.out.println("\n >>> 회원가입을 축하드립니다. <<<");
		}
		else if(n== -1) {
			System.out.println("\n >>> 아이디가 이미 사용중이므로 다른 아이디로 입력하세요. <<<");
		}
		else if(n== -2) {
			System.out.println("\n >>> SQL 구문에 오류가 발생함!! <<<");
		}
		
	}// end of private void memberRegister(Scanner sc)----------

	
	
	// **** 로그인 **** //
	private MemberDTO login(Scanner sc) {
		
		MemberDTO member = null;
		
		System.out.println("\n >>> ---- 로그인 ---- <<< ");
		
		System.out.print("▷ 아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("▷ 비밀번호 : ");
		String passwd = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("userid", userid);
		paraMap.put("passwd", passwd);
		
		member = mdao.login(paraMap);
		
		if(member != null) {
			System.out.println("\n >>> 로그인 성공!! <<< \n");
		}
		else {
			System.out.println("\n >>> 로그인 실패!! <<< \n");
		}
		
		return member;
	}// end of private MemberDTO login(Scanner sc)--------------

	
	
	// **** 게시판 메뉴 **** //
	private void menu_Board(MemberDTO member, Scanner sc) {
		
		String allMemberView = ("admin".equals(member.getUserid()))?"10.모든회원정보조회":"";
		String menuNo = "";

		do {
			System.out.println("\n---------- 게시판메뉴["+ member.getName() +"님 로그인중..] ---------- \n"
					         + " 1.글목록보기   2.글내용보기   3.글쓰기   4.댓글쓰기 \n"
					         + " 5.글수정하기   6.글삭제하기   7.최근1주일간 일자별 게시글 작성건수 \n"
					         + " 8.이번달 일자별 게시글 작성건수   9.나가기   "+ allMemberView +" \n"
					         + "---------------------------------------------");
			
			System.out.print("▷ 메뉴번호 선택 : ");
			menuNo = sc.nextLine();
			
			switch (menuNo) {
				case "1": // 글목록보기
					boardList();
					break;
					
					
					
				case "2": // 글내용보기
					viewContents(member.getUserid(), sc);
					break;
					
					
					
				case "3": // 글쓰기(insert).  
					/* 
					     글쓰기는 jdbc_board 테이블에 insert 가 성공 되어지면
					   jdbc_member 테이블에서 글을 쓴 작성자의  point 컬럼의 값을 10 씩 증가(update) 시켜주겠다.
					   
					   -- *** Transaction(트랜잭션) 처리 *** --
                       --> Transaction(트랜잭션)이라 함은 관련된 일련의 DML로 이루어진 한꾸러미(한세트)를 말한다.
                       --> Transaction(트랜잭션)이라 함은 데이터베이스의 상태를 변환시키기 위하여 논리적 기능을 수행하는 하나의 작업단위를 말한다.        
					   
					   Transaction(트랜잭션) 처리에서 가장 중요한 것은 
                                            모든 DML문이 성공해야만 최종적으로 모두 commit 을 해주고,
                       DML문중에 1개라도 실패하면 모두 rollback 을 해주어야 한다는 것이다.       
					*/
					int n = write(member, sc);
					
					if(n == 1) {
						System.out.println(">> 글쓰기 성공!! << \n");
					}
					
					else if(n == 0) {
						System.out.println(">> 글쓰기를 취소하셨습니다. << \n");
					}
					
					else if(n == -1) {
						System.out.println(">> 글쓰기 실패!! << \n");
					}
					
					break;
					
					
					
				case "4": // 댓글쓰기(jdbc_comment 테이블에 insert 하기) 
					n = writeComment(member, sc);
					
					if(n == 1) {
						System.out.println(">> 댓글쓰기 성공!! << \n");
					}
					
					else if(n == 0) {
						System.out.println(">> 댓글쓰기를 취소하셨습니다. << \n");
					}
					
					else if(n == -1) {
						System.out.println(">> 원글번호가 존재하지 않으므로 댓글쓰기 실패!! << \n");
					}
					
					break;
					
					
					
				case "5": // 글수정하기
					
					n = updateBoard(member, sc);
					
					if(n == 0)
						System.out.println(">> 수정할 글번호가 글목록에 존재하지 않습니다. << \n"); 
					
					else if(n == 1)
						System.out.println(">> 다른 사용자의 글은 수정불가 합니다.!! << \n"); 
					
					else if(n == 2)
						System.out.println(">> 글암호가 올바르지 않습니다.!! << \n"); 
					
					else if(n == 3)
						System.out.println(">> 장애발생으로 인해 글 수정 실패!! << \n");
					
					else if(n == 4)
						System.out.println(">> 글 수정 취소!! << \n");
					
					else if(n == 5)
						System.out.println(">> 글 수정 성공!! << \n");
					
					break;
					
					
					
				case "6": // 글삭제하기
					
					n = deleteBoard(member, sc);
					
					if(n == 0)
						System.out.println(">> 삭제할 글번호가 글목록에 존재하지 않습니다. << \n"); 
					
					else if(n == 1)
						System.out.println(">> 다른 사용자의 글은 삭제불가 합니다.!! << \n"); 
					
					else if(n == 2)
						System.out.println(">> 글암호가 올바르지 않습니다.!! << \n"); 
					
					else if(n == 3)
						System.out.println(">> 장애발생으로 인해 글 삭제 실패!! << \n");
					
					else if(n == 4)
						System.out.println(">> 글 삭제 취소!! << \n");
					
					else if(n == 5)
						System.out.println(">> 글 삭제 성공!! << \n");
					
					break;
					
					
					
				case "7": // 최근1주일간 일자별 게시글 작성건수 
					statisticsByWeek();
					break;
					
					
					
				case "8": // 이번달 일자별 게시글 작성건수
					statisticsByCurrentMonth();
					break;
					
					
					
				case "9": // 나가기
					
					break;
					
					
					
				case "10": // 모든회원정보조회(관리자 전용 메뉴)
					
					if("admin".equals(member.getUserid())) {
						System.out.print("▷ 정렬 [1:회원명의 오름차순  / 2:회원명의 내림차순 / \n" + 
								         "      3:가입일자의 오름차순 / 4:가입일자의 내림차순]: ");
						String sortChoice = sc.nextLine();
						
						selectAllMember(sortChoice);
					}	
					
					else
						System.out.println(">> 메뉴에 없는 번호 입니다. << \n");
					
					break;					
	
				default:
					System.out.println(">> 메뉴에 없는 번호 입니다. << \n");
					break;
					
			}// end of switch (menuNo)------------------------
			
			
		} while ( !("9".equals(menuNo)) );
		
		
	}// end of private void menu_Board()-----------------------
		



	// **** 글쓰기(Transaction 처리) **** //
	// ( 글쓰기[jdbc_board 테이블에 insert] + 글쓴회원의 포인트를 10증가[jdbc_member 테이블에 update] ==> Transaction 처리 )
	private int write(MemberDTO member, Scanner sc) {
		
		int result = 0;
		
		System.out.println("\n>>> 글쓰기 <<<");
		
		System.out.println("1. 작성자명 : " + member.getName());
		
		System.out.print("2. 글제목 : ");
		String subject = sc.nextLine();
		
		System.out.print("3. 글내용 : ");
		String contents = sc.nextLine();
		
		System.out.print("4. 글암호 : ");
		String boardpasswd = sc.nextLine();
		
		BoardDTO bdto = new BoardDTO();
		bdto.setFk_userid(member.getUserid());
		bdto.setSubject(subject);
		bdto.setContents(contents);
		bdto.setBoardpasswd(boardpasswd);
		
		int n_insert = bdao.write(bdto); 
		// insert 가 성공되어지면 n_insert 에는 1 이 들어올것이다.
		// insert 가 실패되어지면 n_insert 에는 0 이 들어올것이다. 
		
		int n_update = 0;
		
		if(n_insert == 1) {
		    
			n_update = mdao.updateMemberPoint(member.getUserid());
			// 회원테이블에서 글을 작성한 회원의 point를 10 증가하도록 하는 update.
			// update 가 성공되어지면 n_update 에는 1 이 들어올것이다.
			// 그런데 jdbc_member 테이블에 point 컬럼에는 check 제약으로 인해서
			// 30을 넘지 못한다. 30을 넘으면 check 제약으로 인해 오류가 발생할 것이다.
			// check 제약으로 인해 오류가 발생할 경우에는 n_update 에 0 을 넣어주도록 하겠다.
		}
		
	 	Connection conn = MyDBConnection.getConn();
		
	 	try {
	 	
			if(n_insert == 1 && n_update == 1) {
			// 게시판에 글쓰기 및 회원의 point 값이 10증가 한 것이 모두 성공이라면 	
				
				do {
					System.out.print(">> 정말로 글쓰기를 하시겠습니까?[Y/N] => ");  
					String yn = sc.nextLine();
					
					if("y".equalsIgnoreCase(yn)) {
						conn.commit();
						result = 1;
						break;
					}
					
					else if("n".equalsIgnoreCase(yn)) {
						conn.rollback();
						result = 0;
						break;
					}
					
					else {
						System.out.println(">> Y 또는 N 만 입력하세요!! << \n");
					}
					
				} while (true); // end of do~while(true)------------------
				
				
			}
			
			else {
				// 제약조건을 포함한 SQL구문 장애가 발생한 경우
				conn.rollback();
				result = -1;
			}
			
	 	} catch (SQLException e) {
			
		}
		
		return result;
		/*
		       리턴되는 result 값이 1 인 경우는 글쓰기가 성공한 경우
		       리턴되는 result 값이 0 인 경우는 글쓰기를 취소한 경우   
		       리턴되는 result 값이 -1 인 경우는 장애가 발생해서 글쓰기가 실패한 경우    
		 */
	}// end of private int write(MemberDTO member, Scanner sc)--------------
	
	
	
	// **** 글목록보기 **** //
	private void boardList() {
		
		List<BoardDTO> boardList = bdao.boardList();
		
		if(boardList.size() > 0) {
			// 게시글이 존재하는 경우
			
			System.out.println("\n--------------------- [ 게시글 목록 ] ---------------------"); 
			System.out.println("글번호\t글제목\t\t작성자\t작성일자\t\t조회수");
			System.out.println("---------------------------------------------------------");
			
		/*	
		    ===> 댓글의 개수를 보여주지 않을 때 <===
		    
			for(int i=0; i<boardList.size(); i++) { 
				BoardDTO bdto = boardList.get(i);
				
				System.out.println(bdto.getBoardno()+"\t"+
				                   bdto.getSubject()+"\t"+
						           bdto.getMember().getName()+"\t"+
				                   bdto.getWriteday()+"\t"+
						           bdto.getViewcount() 
				                  );
			}// end of for-----------------------------------
		*/	
			
		 //	===> 댓글의 개수를 보여줄 때 <=== //
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<boardList.size(); i++) {
				sb.append( boardList.get(i).viewInfo() + "\n");  // boardList.get(i) 은 BoardDTO 이다. 
			}// end of for-------------------------------------
			
			System.out.println(sb.toString());
		}
		
		else {
			// 게시글이 1개도 존재하지 않는 경우
			System.out.println(">> 글목록이 없습니다. << \n");
		}
		
	}// end of private void boardList()--------------------
	
	
	
	
	// **** 글내용보기 ****
	private void viewContents(String login_userid, Scanner sc) {
		
		System.out.println("\n>>> 글내용 보기 <<<");
		
		System.out.print("▷ 글번호 : ");
		String boardno = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("boardno", boardno);
		paraMap.put("login_userid", login_userid);
		
		BoardDTO bdto = bdao.viewContents(paraMap);
		
		if(bdto != null) {
			// 존재하는 글번호를 입력한 경우 
			System.out.println("[글내용] " + bdto.getContents());
			
			if( !bdto.getFk_userid().equals(login_userid) ) {
			    // 현재 로그인 되어진 사용자가 자신의 글이 아닌 다른 사용자가 쓴 글을 조회했을 경우에만
			    // 조회수 1증가 시키기
				bdao.updateViewCount(boardno);
			}
			
			////////////////////////////////////////////////////
			
			System.out.println("[댓글]\n-----------------------------------------------------");
			
			List<BoardCommentDTO> commentList = bdao.commentList(boardno); 
			// 원글에 대한 댓글을 가져오는 것 (특정 게시글 글번호에 대한 jdbc_comment 테이블과 jdbc_member 테이블을 JOIN 해서 보여준다) 
			
			if(commentList != null) {
				// 댓글이 존재하는 원글인 경우 
				System.out.println("댓글내용\t\t작성자\t작성일자");
				System.out.println("--------------------------------------------------------");
				
				StringBuilder sb = new StringBuilder();
				
				for(BoardCommentDTO cmdto : commentList) { // 개선된 for문 , 확장 for문, for each문 
					sb.append(cmdto.viewInfo()+"\n");
				}// end of for------------------------------
				
				System.out.println(sb.toString());
			}
			
			else {
				// 댓글이 존재하지 않는 원글인 경우 
				System.out.println(">> 댓글 내용 없음 << \n");
			}
			////////////////////////////////////////////////////
		}
		
		else {
			// 존재하지 않는 글번호를 입력한 경우
			System.out.println(">> 글번호 "+ boardno + "은 글목록에 존재하지 않습니다 << \n"); 
		}
		
	}// end of private void viewContents(String login_userid, Scanner sc)---------------

	
	
	
	// **** 댓글쓰기(jdbc_comment 테이블에 insert 하기) **** 
	private int writeComment(MemberDTO member, Scanner sc) {
		
		int result = 0;
		
		System.out.println("\n>>> 댓글쓰기 <<<");
		
		System.out.println("1. 작성자명 : " + member.getName());
		
		System.out.print("2. 원글의 글번호 : ");
		String boardno = sc.nextLine();  // 2342 와 같이 존재하지 않는 원글의 글번호를 입력할 수 있다. 
		
		// 댓글의 내용을 입력할 때 그냥 엔터 및 공백만으로 되어진것은 입력하면 되지 않도록 만들어야 한다.
		String contents = "";
		do {
			System.out.print("3. 댓글내용 : ");
			contents = sc.nextLine(); // "안녕하세요" 
			                                 // 또는 "         "
			                                 // 또는 그냥엔터
			if( contents.trim().isEmpty() ) {
				// "         " 또는 그냥엔터 인 경우 
				System.out.println(">> 댓글내용은 필수로 입력하셔야 합니다. << \n");
			}
			else {
				break;
			}
			
		} while (true);
				
		BoardCommentDTO cmdto = new BoardCommentDTO();
		cmdto.setFk_boardno(boardno);           // 원글의 글번호
		cmdto.setFk_userid(member.getUserid()); // 댓글을 작성한 사용자의 userid ==> 현재 로그인한 사용자의 userid
		cmdto.setContents(contents);            // 댓글내용 
		
		result = bdao.writeComment(cmdto, sc); // 댓글쓰기(jdbc_comment 테이블에 insert) 
		
		return result;
		/* result 는 1(commit   해서 댓글쓰기를 완료한 것)
		     또는            0(rollback 해서 댓글쓰기를 취소한 것) 
		     또는          -1(foreign key 제약에 위배가 되어 오류가 발생한것. 즉, 원글번호가 존재하지 않을 경우) 
		     을 가질것이다. 
		*/
	}// end of private int writeComment(MemberDTO member, Scanner sc)--------
	
	
	
	// **** 글수정하기 ****
	private int updateBoard(MemberDTO member, Scanner sc) {
		
		int result = 0;
		/*
		 result == 0    수정할 글번호가 글목록에 존재하지 않습니다.

         result == 1  다른 사용자의 글은 수정불가 합니다.!!

		 result == 2    글암호가 올바르지 않습니다.!!
			
		 result == 3    장애발생으로 인해 글 수정 실패!!
			
		 result == 4    글 수정 취소!!
			
		 result == 5    글 수정 성공!! 
		 */
		
		 System.out.println("\n>>> 글 수정 하기 <<<");
		 
		 System.out.print("▷ 수정할 글번호 : "); 
		 String boardno = sc.nextLine();
		 
		 Map<String, String> paraMap = new HashMap<>();
		 paraMap.put("boardno", boardno);
					
		 BoardDTO bdto = bdao.viewContents(paraMap);
		 // 현재 paraMap 에는 글번호인 "boardno" 만 있는 상태이다.
		 
		 if(bdto != null) {
			 // 수정할 글번호가 글목록에 존재하는 경우
			 
			 if( !member.getUserid().equals(bdto.getFk_userid()) ) {
			     // 수정할 글번호가 다른 사용자의 글인 경우
				 result = 1;
			 }
			 
			 else {
				// 수정할 글번호가 로그인한 사용자의 글인 경우
				 System.out.print("▷ 글암호 : ");
				 String boardpasswd = sc.nextLine();
				 
				 if( !boardpasswd.equals(bdto.getBoardpasswd()) ) {
					 // 글을 수정하려고 사용자가 입력한 글암호가 글쓰기를 할때 입력해준 글암호와 같지 않을 경우  
					 result = 2;
				 }
				 
				 else {
					// 글을 수정하려고 사용자가 입력한 글암호가 글쓰기를 할때 입력해준 글암호와 같을 경우 
					// 수정할 글을 보여주고서 글수정 작업에 들어가도록 한다. 
					System.out.println("-----------------------------------------");
					System.out.println("글제목 : " + bdto.getSubject());
					System.out.println("글내용 : " + bdto.getContents());
					System.out.println("----------------------------------------- \n");
					
					System.out.print("▷ 글제목[변경하지 않으려면 엔터]: "); 
					String subject = sc.nextLine();  
					if( subject != null && subject.trim().isEmpty()  ) {
						subject = bdto.getSubject();
					}
					 
					System.out.print("▷ 글내용[변경하지 않으려면 엔터]: "); 
					String contents = sc.nextLine();  
					if( contents != null && contents.trim().isEmpty()  ) {
						contents = bdto.getContents();
					}
					
					paraMap.put("subject", subject);
					paraMap.put("contents", contents);
					
					int n = bdao.updateBoard(paraMap); // 글 수정하기
					
					if(n != 1) {
						// 장애발생으로 인해 글 수정 실패한 경우
						result = 3;
					}
					
					else {
						// 정상적으로 글 수정을 한 경우 
						
						Connection conn = MyDBConnection.getConn();
						
						do {
							System.out.print("▷ 정말로 수정하시겠습니까?[Y/N] "); 
							String yn = sc.nextLine();
							
							try {
								
								if("y".equalsIgnoreCase(yn)) {
									conn.commit(); // 커밋 
									result = 5;
									break;
								}
								
								else if("n".equalsIgnoreCase(yn)) {
									conn.rollback(); // 롤백 
									result = 4;
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
					
				 }
				 
			 }
				 
		 }
		 
		
		return result;
		
	}// end of private int updateBoard(MemberDTO member, Scanner sc)----------
	
	
	
	// **** 글삭제하기 ****
	private int deleteBoard(MemberDTO member, Scanner sc) {
		
		int result = 0;
		/*
		 result == 0    삭제할 글번호가 글목록에 존재하지 않습니다.

         result == 1  다른 사용자의 글은 삭제불가 합니다.!!

		 result == 2    글암호가 올바르지 않습니다.!!
			
		 result == 3    장애발생으로 인해 글 삭제 실패!!
			
		 result == 4    글 삭제 취소!!
			
		 result == 5    글 삭제 성공!! 
		 */
		
		 System.out.println("\n>>> 글 삭제 하기 <<<");
		 
		 System.out.print("▷ 삭제할 글번호 : "); 
		 String boardno = sc.nextLine();
		 
		 Map<String, String> paraMap = new HashMap<>();
		 paraMap.put("boardno", boardno);
					
		 BoardDTO bdto = bdao.viewContents(paraMap);
		 // 현재 paraMap 에는 글번호인 "boardno" 만 있는 상태이다.
		 
		 if(bdto != null) {
			 // 삭제할 글번호가 글목록에 존재하는 경우
			 
			 if( !member.getUserid().equals(bdto.getFk_userid()) ) {
			     // 삭제할 글번호가 다른 사용자의 글인 경우
				 result = 1;
			 }
			 
			 else {
				// 삭제할 글번호가 로그인한 사용자의 글인 경우
				 System.out.print("▷ 글암호 : ");
				 String boardpasswd = sc.nextLine();
				 
				 if( !boardpasswd.equals(bdto.getBoardpasswd()) ) {
					 // 글을 삭제하려고 사용자가 입력한 글암호가 글쓰기를 할때 입력해준 글암호와 같지 않을 경우  
					 result = 2;
				 }
				 
				 else {
					// 글을 삭제하려고 사용자가 입력한 글암호가 글쓰기를 할때 입력해준 글암호와 같을 경우 
					// 삭제할 글을 보여주고서 글삭제 작업에 들어가도록 한다. 
					System.out.println("-----------------------------------------");
					System.out.println("글제목 : " + bdto.getSubject());
					System.out.println("글내용 : " + bdto.getContents());
					System.out.println("----------------------------------------- \n");
					
					paraMap.put("boardpasswd", boardpasswd);
					
					int n = bdao.deleteBoard(paraMap); // 글 삭제하기
					
					if(n != 1) {
						// 장애발생으로 인해 글 삭제 실패한 경우
						result = 3;
					}
					
					else {
						// 정상적으로 글 삭제를 한 경우 
						
						Connection conn = MyDBConnection.getConn();
						
						do {
							System.out.print("▷ 정말로 삭제하시겠습니까?[Y/N] "); 
							String yn = sc.nextLine();
							
							try {
								
								if("y".equalsIgnoreCase(yn)) {
									conn.commit(); // 커밋 
									result = 5;
									break;
								}
								
								else if("n".equalsIgnoreCase(yn)) {
									conn.rollback(); // 롤백 
									result = 4;
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
					
				 }
				 
			 }
				 
		 }
		 
		
		return result;
		
	}// end of private int deleteBoard(MemberDTO member, Scanner sc)----------
	
	
	
	// **** 최근1주일간 일자별 게시글 작성건수 ****
	private void statisticsByWeek() {
		
		System.out.println("\n--------------- [최근 1주일간 일자별 게시글 작성건수] ---------------"); 
		
		String title = "전체\t";
				
		for(int i=0; i<7; i++) {
			title += MyUtil.addDay(i-6) + "  "; // -6  -5  -4  -3  -2  -1  0 
		}// end of for----------------------------------------------
	
		System.out.println(title); // 만약 오늘이 2021-03-09 이라면
		// 전체	2021-03-03	2021-03-04	2021-03-05	2021-03-06	2021-03-07	2021-03-08	2021-03-09         
		
		System.out.println("----------------------------------------------------------");
		
		Map<String, Integer> resultMap = bdao.statisticsByWeek(); // 최근 1주일간에 대해 select 되어져 나온 결과물
		
		String str = resultMap.get("TOTAL") + "\t" + 
				     resultMap.get("PREVIOUS6") + "\t" + 
				     resultMap.get("PREVIOUS5") + "\t" + 
				     resultMap.get("PREVIOUS4") + "\t" + 
				     resultMap.get("PREVIOUS3") + "\t" + 
				     resultMap.get("PREVIOUS2") + "\t" + 
				     resultMap.get("PREVIOUS1") + "\t" + 
				     resultMap.get("TODAY");
		
		System.out.println(str);
		
	}// end of private void statisticsByWeek()-------------
	
	
	
	
	// **** 이번달 일자별 게시글 작성건수 ****
	private void statisticsByCurrentMonth() {
		
		Calendar currentDate = Calendar.getInstance(); 
		// 현재 날짜와 시간을 얻어온다.
		
		SimpleDateFormat sdateFmt = new SimpleDateFormat("yyyy년 MM월");
		String currentMonth = sdateFmt.format(currentDate.getTime()); 
		
		System.out.println("\n>>> [" + currentMonth + " 일자별 게시글 작성건수] <<<"); 
		System.out.println("-------------------------");
		System.out.println(" 작성일자\t작성건수");
		System.out.println("-------------------------");
		
		List<Map<String, String>> mapList = bdao.statisticsByCurrentMonth();
		
		if( mapList.size() > 0 ) {
			
			StringBuilder sb = new StringBuilder();
			
			for(Map<String, String> map : mapList) {
				
				sb.append( map.get("WRITEDAY") + "\t" + map.get("CNT") + "\n" ); 
				
			}// end of for-----------------------------
			
			System.out.println( sb.toString() );
			
		}
		
		else {
			System.out.println(" 작성된 게시글이 없습니다.");
		}
		
		
	}// end of private void statisticsByCurrentMonth()--------------

	
	
	// **** 모든회원정보조회(관리자 전용 메뉴) ****
	private void selectAllMember(String sortChoice) {
		
		System.out.println("\n>>>    모든회원정보    <<<");
		System.out.println("------------------------------------------");
		System.out.println("회원번호   아이디   성명   연락처   포인트   가입일자");
		System.out.println("------------------------------------------"); 
		
		List<MemberDTO> memberList = mdao.selectAllMember(sortChoice);
		
		if(memberList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			
			for(MemberDTO member : memberList) {
				
				sb.append("  "+member.getUserseq()+"  "+
						  member.getUserid()+"  "+
						  member.getName()+"  "+
						  member.getMobile()+"  "+
						  member.getPoint()+"  "+
						  member.getRegisterday()+"\n");
				
			}// end of for------------------------
			
			System.out.println(sb.toString());
		}
		
		else {
			System.out.println("가입된 회원이 없습니다.");
		}
		
	}// end of private void selectAllMember(String sortChoice)-------------
	
	
	
}
