package my.day12.b.inheritance;

import java.util.Scanner;

public class GujikjaCompanyMain {

	public static void main(String[] args) {
		
		Gujikja[] guArr = new Gujikja[5];
		Company[] coArr = new Company[5];
		
		Gujikja gu1 = new Gujikja();
		gu1.setId("eomjh");
		gu1.setPasswd("qwer1234$A");
		gu1.setName("엄정화");
		gu1.setJubun("9501252");
		
		if(gu1.isUseGujikja()) {
			guArr[Gujikja.count++] = gu1;
		}	
		
	//	System.out.println(gu1.getName());
	//	System.out.println("gu1.isUseGujikja() => " + gu1.isUseGujikja());
		
		Gujikja gu2 = new Gujikja();
		gu2.setId("leess");
		gu2.setPasswd("qwer1234$B");
		gu2.setName("이순신");
		gu2.setJubun("9709251");
		
		if(gu2.isUseGujikja()) {
			guArr[Gujikja.count++] = gu2;
		}
		
		////////////////////////////////////////////////////
		
		Company co1 = new Company();
		co1.setId("skcompany");
		co1.setPasswd("abcd1234$C");
		co1.setName("SK");
		co1.setJobType("IT");
		co1.setSeedMoney(20000000);
		
		if(co1.isUseCompany()) {
			coArr[Company.count++] = co1;
		}
		
	//	System.out.println(co1.getName());
	//	System.out.println("co1.isUseCompany() => " + co1.isUseCompany());
		
		Company co2 = new Company();
		co2.setId("ktcompany");
		co2.setPasswd("abcd1234$D");
		co2.setName("KT");
		co2.setJobType("IT");
		co2.setSeedMoney(30000000);
		
		if(co2.isUseCompany()) {
			coArr[Company.count++] = co2;
		}
		
		//////////////////////////////////////////////////
		
		GujikjaCompanyCtrl ctrl = new GujikjaCompanyCtrl();
		
		Scanner sc = new Scanner(System.in);
	    
		String smenuNo = "";
		Gujikja gu=null;
		Company co=null;
		String title="";
		
		do {
			if(gu==null && co==null)
				title = "======  >>  메인메뉴  <<  ======== \n";
			else if(co==null && gu!=null)
				title = "======  >>  메인메뉴[구직자 "+gu.getName()+"] 로그인중... <<  ======== \n";
			else
				title = "======  >>  메인메뉴[구인회사 "+co.getName()+"] 로그인중... <<  ======== \n";
			
			System.out.println(title
					         + "1. 구직자 회원가입    2. 구인회사 회원가입 \n"
					         + "3. 구직자 로그인       4. 구인회사 로그인 \n"
					         + "5. 모든 구직자 출력   6. 모든 구인회사 출력 \n"
					         + "7. 로그아웃              8. 내 정보 변경하기\n"
					         + "9. 프로그램 종료\n");
			
			System.out.print("▷ 메뉴번호 선택 => "); 
			smenuNo = sc.nextLine();
			
			switch (smenuNo) {
				case "1": // 구직자 회원가입 
					Gujikja gujikja = ctrl.registerGujikja(sc, guArr);
					guArr[Gujikja.count++] = gujikja;
					System.out.println(">> 구직자 회원가입 성공!! \n");
					
					break;
	
				case "2": // 구인회사 회원가입
					Company company = ctrl.registerCompany(sc, coArr);
					coArr[Company.count++] = company;
					System.out.println(">> 구인회사 회원가입 성공!! \n");
					
					break;
					
				case "3": // 구직자 로그인
					if(gu==null && co==null) {
						gu = ctrl.loginByGujikja(sc,guArr);
					}
					else if(gu!=null && co==null)
						System.out.println(">>현재 구직자로 로그인되어진 상태입니다!<<");
					else
						System.out.println(">>현재 구인회사로 로그인되어진 상태입니다!<<");
					break;
					
				case "4": // 구인회사 로그인
					if(gu==null && co==null) {
						co=ctrl.loginByCompany(sc,coArr);
					}
					else if(gu==null && co!=null)
						System.out.println(">>현재 구인회사로 로그인되어진 상태입니다!<<");
					else
						System.out.println(">>현재 구직자로 로그인되어진 상태입니다!<<");
					break;
				case "5": // 모든 구직자 출력
					ctrl.showAllGujikja(guArr);
					break;
					
				case "6": // 모든 구인회사 출력
					ctrl.showAllCompany(coArr);
					break;
					
				case "7": // 로그아웃
					gu=null;
					System.out.println(">> 로그아웃 되었습니다. <<\n");
					
					break;	
					
				case "8": // 내 정보 변경하기
					if(gu!=null && co==null)
						gu=ctrl.updateMyInfo(sc,gu);// 구직자로 로그인 되어진 상태
					else if(co!=null && gu==null)
						co=ctrl.updateMyInfo(sc,co);// 구인회사로 로그인 되어진 상태
					else
						System.out.println("먼저 로그인 하셔야 합니다. \n");
					
				default:
					break;
				}
			
		} while (!("9".equals(smenuNo)));

		sc.close();
		
		System.out.println("\n~~~~ 프로그램 종료 ~~~~");
		
	}// end of main()-----------------------------

}

