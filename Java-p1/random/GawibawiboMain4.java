package my.day08.c.random;

import java.util.Random;
import java.util.Scanner;

public class GawibawiboMain4 {

	public static void main(String[] args) {

		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);
		
		int userNum=0;
		
		do {
			
			System.out.println("=========== 메뉴 ===========");
			System.out.println("1.가위\t2.바위\t3.보\t4.게임종료");
			System.out.println("==========================");
			System.out.print(">> 선택하세요=> ");
			
			try {
				userNum=Integer.parseInt(sc.nextLine()); // "똘똘이"  4  5
				if(!(1<=userNum && userNum<=4)) {
					System.out.println("▷메뉴에 존재하지 않는 번호입니다.\n");
					continue;
				}
				
				if(userNum!=4) {
					// 사용자가 1 또는 2 또는 3을 낸 경우
					
					// PC도 1 또는 2 또는 3 중에 하나를 랜덤하게 내야 한다.
					
					int pcNum = rnd.nextInt(3-1+1)+1;
					
					String msg="";
					// 사용자가 이긴경우
					if((pcNum==1 && userNum==2)||
						(pcNum==2 && userNum==3)||
						(pcNum==3 && userNum==1)) {
						msg=">>> 사용자님이 이겼습니다!!\n";
					}
					// PC가 이긴경우
					else if((pcNum==1 && userNum==2)||
							(pcNum==2 && userNum==3)||
							(pcNum==3 && userNum==1)) {
						msg=">>> 사용자님이 졌습니다!!\n";
					}
					// 사용자와 PC가 비긴경우
					else {
						msg=">>> 비겼습니다!!\n";
					}
					
					System.out.println(msg);
				}
				
				
			}catch(NumberFormatException e) {
				System.out.println("▷숫자로만 입력하세요!!\n");
			}
			
		}while(!(userNum==4));
		//end of do~while --------------------------------------------
		
		sc.close();
		System.out.println("\n프로그램 종료\n");
	}

}
