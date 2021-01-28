package my.day11.b.abstraction;

import java.util.Scanner;

import my.util.MyUtil;

public class GujikjaMain {
	
	public static void main(String[] args) {

		Gujikja[] guArr=new Gujikja[5];
		
		Gujikja gu1 = new Gujikja();
		gu1.userid = "eomjh";
		gu1.passwd = "qwer1234$A";
		gu1.name = "엄정화";
			
		//boolean bool=MyUtil.isCheckJubun(null);
		//boolean bool=MyUtil.isCheckJubun("950115");
		//boolean bool=MyUtil.isCheckJubun("95011512");
		//boolean bool=MyUtil.isCheckJubun("9501t51");
		//boolean bool=MyUtil.isCheckJubun("9501155");
		//boolean bool=MyUtil.isCheckJubun("9513155");
		//boolean bool=MyUtil.isCheckJubun("9501321");
		//boolean bool=MyUtil.isCheckJubun("9502301");
		
		String jubun="9501211";
		
		boolean bool=MyUtil.isCheckJubun(jubun);
		if(bool) {
			gu1.jubun=jubun;	
			guArr[0]=gu1;
			
			Gujikja.count++;
		}

		// === 아래는 정규표현식을 사용하여 검사한 것이다. ===
		//boolean bool=MyUtil.isCheckJubun2(null);
		//boolean bool=MyUtil.isCheckJubun2("950115");
		//boolean bool=MyUtil.isCheckJubun2("95011512");
		//boolean bool=MyUtil.isCheckJubun2("9501t51");
		//boolean bool=MyUtil.isCheckJubun2("9501155");
		//boolean bool=MyUtil.isCheckJubun2("9513155");
		//boolean bool=MyUtil.isCheckJubun2("9501321");
		//boolean bool=MyUtil.isCheckJubun2("9502301");	
		
		//System.out.println(bool);
		
		//System.out.println("성별 : "+gu.getGender());
		//System.out.println("나이 : "+gu.getAge());
		
		Gujikja gu2 = new Gujikja();
		gu2.userid = "leess";
		gu2.passwd = "qwer1234$B";
		gu2.name = "이순신";
		gu2.jubun = "9710201";
		
		guArr[1]=gu2;
		Gujikja.count++;
		
		Gujikja gu3 = new Gujikja();
		gu3.userid="yooks";
		gu3.passwd="qwer1234@A";
		gu3.name="유관순";
		gu3.jubun="8510202";
		
		guArr[2]=gu3;
		Gujikja.count++;
				
		//////////////////////////////////////////////////////////////////////////////
		GujikjaCtrl ctrl = new GujikjaCtrl();
		
		Scanner sc=new Scanner(System.in);
		
		String smenuNo = "";
		
		do {
			
			System.out.println(" === 메인메뉴 === \n"
							+ "1.구직자 회원가입  2.구직자 모두 보기  3.검색  4.프로그램 종료 \n");
			System.out.print("▷ 메뉴번호 선택 => ");
			smenuNo = sc.nextLine();
			
			switch (smenuNo) {
				case "1": // 구직자 회원가입
					Gujikja gujikja=ctrl.register(sc,guArr);
					guArr[Gujikja.count++]=gujikja;
					System.out.println(">> 회원가입 성공!! \n");
					
					break;
				
				case "2": // 구직자 모두 보기
					ctrl.showAll(guArr);
					break;
					
				case "3": // 검색메뉴를 보여주도록 호출
					searchMenu(sc,ctrl,guArr);
					break;
					
				default:
					break;
			}
			
		} while (!("4".equals(smenuNo)));
		
		sc.close();
		
		System.out.println("\n~~~프로그램 종료~~~\n");
	}// end of main()--------------------------


	static void searchMenu(Scanner sc,GujikjaCtrl ctrl,Gujikja[] guArr) {
		
		String sMenuNo="";
		do {
			System.out.println("=== 검색메뉴 === \n"
							 + "1.연령대검색  2.성별검색  3.연령대및성별검색  4.메인으로 돌아가기 \n");
			
			System.out.println("▷검색메뉴번호 선택 => ");
			sMenuNo=sc.nextLine();
			
			switch (sMenuNo) {
				case "1": // 연령대검색
					System.out.println("▷연령대 => "); // 30
					String sageline = sc.nextLine();
					int ageline=Integer.parseInt(sageline); // 20
					
					ctrl.showByAgeline(guArr,Gujikja.count,ageline);
					
					break;
					
				case "2": // 성별검색
					System.out.print("▷ 성별[남/여] => "); 
					String gender = sc.nextLine();
										
					ctrl.showByGender(guArr, Gujikja.count, gender);
					break;
					
				case "3":
					System.out.print("▷ 연령대 => "); 
					sageline = sc.nextLine();
					
					System.out.print("▷ 성별[남/여] => "); 
					gender = sc.nextLine();
					
					ageline = Integer.parseInt(sageline); 
					
					ctrl.showByAgelineGender(guArr, Gujikja.count, ageline, gender);
					
					break;
					
				default:
					break;
					
			}// end of switch(key)----------------------------
		
		}while(!("4".equals(sMenuNo)));
		
	}// end of void searchMenu()---------------------
	

}
