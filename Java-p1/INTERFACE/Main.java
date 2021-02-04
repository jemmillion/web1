package my.day15.c.INTERFACE;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		InterMemberCtrl ctrl = new MemberCtrl();
		Scanner sc = new Scanner(System.in);
		
		Member[] mbrArr = new Member[10];
		
		Gujikja gu1 = new Gujikja();
		gu1.setId("eomjh");
		gu1.setPasswd("qwer1234$A");
		gu1.setName("엄정화");
		gu1.setJubun("9501252");
		
		if(ctrl.isUse(gu1)) {
			mbrArr[Member.count++] = gu1;
		}	
		
		
		Gujikja gu2 = new Gujikja();
		gu2.setId("leess");
		gu2.setPasswd("qwer1234$B");
		gu2.setName("이순신");
		gu2.setJubun("9709251");
		
		if(ctrl.isUse(gu2)) {
			mbrArr[Member.count++] = gu2;
		}
		
		////////////////////////////////////////////////////
		
		Company co1 = new Company();
		co1.setId("skcompany");
		co1.setPasswd("abcd1234$C");
		co1.setName("SK");
		co1.setJobType("IT");
		co1.setSeedMoney(20000000);
		
		if(ctrl.isUse(co1)) {
			mbrArr[Member.count++] = co1;
		}
		
		
		Company co2 = new Company();
		co2.setId("ktcompany");
		co2.setPasswd("abcd1234$D");
		co2.setName("KT");
		co2.setJobType("IT");
		co2.setSeedMoney(30000000);
		
		if(ctrl.isUse(co2)) {
			mbrArr[Member.count++] = co2;
		}
		
	// System.out.println("확인용  Member.count => " + Member.count);
	///////////////////////////////////////////////////////////////
		
	
	/*	
	    // 구직자로 로그인 시도
		Member mbr = ctrl.login(sc, mbrArr, 1);
		
		// 구인회사로 로그인 시도
		Member mbr = ctrl.login(sc, mbrArr, 2);
		
		if(mbr != null)
			System.out.println(">> 확인용 "+mbr.getName()+"님 로그인 성공~~");
		else
			System.out.println(">> 확인용 로그인 실패~~");
	*/	
		
	/*	
		boolean bool = ctrl.duplicateIdGujikja("eomjh", mbrArr);
		System.out.println("확인용 bool => " + bool);
		// 확인용 bool => true
    */

		String smenuNo = "";
		
		Member mbr = null;
		String title = "";
		
		do {
			if(mbr == null)
			   title = "======  >>  메인메뉴  <<  ======== \n";
			
			else {
			    String who = (mbr instanceof Gujikja)?"구직자":"구인회사";
				title = "======  >>  메인메뉴["+who+" "+mbr.getName()+" 로그인중..]  <<  ======== \n";	
			}
			   
			System.out.println(title
					         + "1. 구직자 회원가입    2. 구인회사 회원가입 \n"
					         + "3. 구직자 로그인       4. 구인회사 로그인 \n"
					         + "5. 모든 구직자 출력   6. 모든 구인회사 출력 \n"
					         + "7. 로그아웃              8. 내정보 변경하기 \n"
					         + "9. 프로그램 종료\n");
			
			System.out.print("▷ 메뉴번호 선택 => "); 
			smenuNo = sc.nextLine();
			
			switch (smenuNo) {
				case "1": // 구직자 회원가입 
					boolean bool = ctrl.register(sc, mbrArr, 1);
					
					if(bool)
						System.out.println(">> 구직자 회원가입 성공!! \n");
					else
						System.out.println(">> 정원초과로 회원가입 실패!! \n");
						
					break;
	
				case "2": // 구인회사 회원가입
					bool = ctrl.register(sc, mbrArr, 2);
					
					if(bool)
						System.out.println(">> 구인회사 회원가입 성공!! \n");
					else
						System.out.println(">> 정원초과로 회원가입 실패!! \n");
						
					break;
					
				case "3": // 구직자 로그인
					if(mbr == null) {
						mbr = ctrl.login(sc, mbrArr, 1);
						if(mbr != null) 
							System.out.println(">> 로그인 성공!! << \n");
						else
							System.out.println(">> 로그인 실패!! << \n");
					}
					else if(mbr instanceof Gujikja)
						System.out.println(">> 현재 구직자로 로그인 되어진 상태 입니다!! << \n");
					
					else 
						System.out.println(">> 현재 구인회사로 로그인 되어진 상태 입니다!! << \n"); 
					
					break;
					
				case "4": // 구인회사 로그인
					if(mbr == null) {
						mbr = ctrl.login(sc, mbrArr, 2);
						if(mbr != null) 
							System.out.println(">> 로그인 성공!! << \n");
						else
							System.out.println(">> 로그인 실패!! << \n");
					}
					else if(mbr instanceof Company)
						System.out.println(">> 현재 구인회사로 로그인 되어진 상태 입니다!! << \n");
					
					else 
						System.out.println(">> 현재 구직자로 로그인 되어진 상태 입니다!! << \n");
					
					break;	
					
				case "5": // 모든 구직자 출력
					ctrl.viewInfoAll(mbrArr, 1);
					break;
					
				case "6": // 모든 구인회사 출력
					ctrl.viewInfoAll(mbrArr, 2);
					break;
					
				case "7": // 로그아웃
					mbr = null;
					System.out.println(">> 로그아웃 되었습니다. << \n");
					break;	
				
					
				case "8": // 내정보 변경하기
					if(mbr != null)
						mbr = ctrl.updateMemberInfo(sc, mbr); 
					    // 구직자 또는 구인회사로 로그인 되어진 상태 
					
					else
						System.out.println(">> 먼저 로그인 하셔야 합니다. << \n");
					
					break;
					
					
				default:
					break;
				}
			
		} while (!("9".equals(smenuNo)));

		sc.close();
		
		System.out.println("\n~~~~ 프로그램 종료 ~~~~");
		
	}// end of main()------------------------

}
