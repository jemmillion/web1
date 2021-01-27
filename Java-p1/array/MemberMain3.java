package my.day09.a.array;

import java.util.Scanner;

import my.util.MyUtil;

public class MemberMain3 {

	public static void main(String[] args) {
		
		Member2[] mbrArr=new Member2[3];
		
		for(int i=0;i<mbrArr.length;i++) {
			System.out.println(mbrArr[i]);
		}
		/*
			null
			null
			null
		 */
		
		Scanner sc=new Scanner(System.in);
		
		int menuNo=0;
		
		do {
			System.out.println("\n======= >> 메뉴 << =======");
			System.out.println("1.회원가입  2.모든회원조회 3.프로그램종료");
			System.out.print("▷선택하세요 => ");
			
			try {
				menuNo = Integer.parseInt(sc.nextLine());
				// "똘똘이"  3  5
				
				if(!(1<=menuNo && menuNo<=3)) {
					System.out.println(">> 메뉴에 없는 번호입니다.\n");
					continue;
				}
				
				switch (menuNo) {
					case 1: // 회원가입
					
					Member2 mbr = new Member2();
					System.out.print("\n1.아이디 : ");
					mbr.id=sc.nextLine();
					
					do {	
						System.out.print("2.비밀번호 : ");
						String passwd=sc.nextLine();
						
						boolean bool = MyUtil.isCheckPasswd(passwd);
						
						if(bool) {
							mbr.passwd = passwd;
							break;
						}
						else {
							System.out.println(">> 암호는 8글자 이상 15글자 이하의 영문 대,소문자,숫자 및 특수문자가 혼합되어야 합니다!!\n");
						}
					}while(true);
					
					System.out.print("3.성명 : ");
					mbr.name=sc.nextLine();
					
					for(int i=0;i<mbrArr.length;i++) {
						if(mbrArr[i] == null) {
							mbrArr[i]=mbr;
							break;
						}
					}// end of for--------------------
					// mbrArr[0] <== "이순신 객체"
					// mbrArr[1] <== "엄정화 객체"
					// mbrArr[2] <== "서강준 객체"
					
				case 2: // 모든회원조회
					
					int nullCount=0;
					
					for(int i=0;i<mbrArr.length;i++) {
						if(mbrArr[i]!=null) {
							System.out.println(mbrArr[i].showInfo()+"\n");
						}
						else {
							nullCount++; // 1 
						}
					}// end of for------------------
					
					if(nullCount == mbrArr.length) {
						System.out.println(">> 가입된 회원이 0명입니다. <<");
					}
					
					break;
					
				}//end of switch--------------
			}catch(NumberFormatException e) {
				System.out.println(">> 정수만 입력하세요!! <<\n");
			}
			
			
		}while(!(menuNo == 3));
		
		sc.close();
		System.out.println(">> 프로그램 종료 <<");
	}//end of main()-------------------------

}
