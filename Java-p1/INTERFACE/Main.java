package my.day15.c.INTERFACE;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		InterMemberCtrl ctrl=new MemberCtrl();
		Scanner sc=new Scanner(System.in);
		
		Member[] mbrArr = new Member[10];		
		
		Gujikja gu1 = new Gujikja();
		gu1.setId("eomjh");
		gu1.setPasswd("qwer1234$A");
		gu1.setName("엄정화");
		gu1.setJubun("9501252");
		
		if(ctrl.isUse(gu1)) {
			mbrArr[Member.count++]=gu1;
		}	
		
	//	System.out.println(gu1.getName());
	//	System.out.println("gu1.isUseGujikja() => " + gu1.isUseGujikja());
		
		Gujikja gu2 = new Gujikja();
		gu2.setId("leess");
		gu2.setPasswd("qwer1234$B");
		gu2.setName("이순신");
		gu2.setJubun("9709251");
		
		if(ctrl.isUse(gu2)) {
			mbrArr[Member.count++]=gu2;
		}
		
		////////////////////////////////////////////////////
		
		Company co1 = new Company();
		co1.setId("skcompany");
		co1.setPasswd("abcd1234$C");
		co1.setName("SK");
		co1.setJobType("IT");
		co1.setSeedMoney(20000000);
		
		if(ctrl.isUse(co1)) {
			mbrArr[Member.count++]=co1;
		}
		
	//	System.out.println(co1.getName());
	//	System.out.println("co1.isUseCompany() => " + co1.isUseCompany());
		
		Company co2 = new Company();
		co2.setId("ktcompany");
		co2.setPasswd("abcd1234$D");
		co2.setName("KT");
		co2.setJobType("IT");
		co2.setSeedMoney(30000000);
		
		if(ctrl.isUse(co2)) {
			mbrArr[Member.count++]=co2;
		}
		
		//System.out.println("Member.count => "+Member.count);
		
		Member mbr=null;
		String title="";
		String who="";
		
		do {
			if(mbr==null)
				title="====>> 메인메뉴 <<====";
			else
				who=(mbr instanceof Gujikja)?"구직자":"구인회사";
				title="====>> 메인메뉴 ["+who+" "+mbr.getName()+"] 로그인중.... <<====";
			
			
		}while(true);
		
		
		//////////////////////////////////////////////////
		/*
		 
		// 구직자로 로그인 시도
		Member mbr=ctrl.login(sc,mbrArr,1);
	
		// 구인회사로 로그인 시도
		Member mbr=ctrl.login(sc,mbrArr,2);
		 
		if(mbr!=null)
			System.out.print(">> 확인용 "+mbr.getName()+" 로그인 성공~~");
		else
			System.out.print(">> 확인용 로그인 실패~~");
		
		
		Member mbr=ctrl.register(sc, mbrArr, 1);
		
		if(mbr!=null)
			System.out.println(">> 회원가입 성공 <<");
		else
			System.out.println(">> 회원가입 실패 <<");
		*/

	}// end of main()---------------------------------------

}