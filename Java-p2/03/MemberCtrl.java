package jdbc.day03;

import java.util.*;

public class MemberCtrl {
	
	// attribute, property, 속성
	InterMemberDAO mdao = new MemberDAO();

	// **** 시작메뉴 **** //
	public void menu_start(Scanner sc) {
		
		MemberDTO member = null;
		
		String s_Choice="";
		
		do {
			String loginName = (member==null)?"":"["+member.getName()+" 로그인중..]";
			String login_logout = (member==null)?"로그인":"로그아웃"; 
			
			System.out.println("\n >>> ----- 시작메뉴 "+ loginName +" ----- <<< \n"
							 + "1.회원가입		2."+login_logout+"		3.프로그램종료\n"
							 + "-----------------------------\n");
			
			System.out.print("▷ 메뉴번호 선택 : ");
			s_Choice = sc.nextLine();
			
			switch (s_Choice) {
			case "1": // 회원가입
				memberRegister(sc);
				break;
				
			case "2": // 로그인 또는 로그아웃
				if("로그인".equals(login_logout))
					member = login(sc); // 로그인시도하기
				else
					member = null; // 로그아웃하기
					System.out.println(">>> 로그아웃 되었습니다. <<<");
				break;
				
			case "3": // 프로그램 종료
				
				break;
			default:
				System.out.println(">>> 메뉴에 없는 번호입니다. 다시 선택하세요. <<<");
				break;
			}// end of switch-----------------------------------------
			
		} while( !("3".equals(s_Choice)) );
		
	}// end of public void menu_start(Scanner sc)---------------------

	// **** 회원가입 **** //

	private void memberRegister(Scanner sc) {
		
		System.out.println("\n >>> ---- 회원가입 ---- <<<");
		
		System.out.print("1.아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("2.비밀번호 : ");
		String passwd = sc.nextLine();
		
		System.out.print("3.회원명 : ");
		String name = sc.nextLine();
		
		System.out.print("4.연락처(휴대폰) : ");
		String tel = sc.nextLine();
		
		MemberDTO member = new MemberDTO();
		member.setUserid(userid);
		member.setPasswd(passwd);
		member.setName(name);
		member.setMobile(tel);
		
		int n = mdao.memberRegister(member);
		
		if(n==1) {
			System.out.println("\n >>> 회원가입을 축하드립니다. <<<");
		}
	}// end of private void memberRegister(Scanner sc)----------------
		
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
		
	}// end of private void login(Scanner sc)------------ 


}
