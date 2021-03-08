package jdbc.day03;

import java.util.Scanner;

public class Member_Main {

	public static void main(String[] args) {
		
		MemberCtrl mctrl = new MemberCtrl();		
		Scanner sc = new Scanner(System.in);
		
		mctrl.menu_start(sc);
		
		sc.close();
		System.out.println("~~~ 프로그램 종료 ~~~");
		
	}// end of main()----------------------------------------

}
