package bookrental;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		TotalController ctrl = new TotalController();
		Scanner sc = new Scanner(System.in);
		
		ctrl.menu_Start(sc);

		sc.close();
		System.out.println("~~~ 프로그램 종료 ~~~");
	}// end of public static void main(String[] args)--------

}
