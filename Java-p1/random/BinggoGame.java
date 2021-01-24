package my.day08.c.random;

import java.util.Random;
import java.util.Scanner;

public class BinggoGame {

	public static void main(String[] args) {
		
		System.out.println("== 빙고게임 ==\n");
		
		Random rnd = new Random();
		// int rndNum = rnd.nextInt(마지막수-처음수+1)+처음수;
		
		int rndNum = rnd.nextInt(100-1+1)+1;
		
		Scanner sc = new Scanner(System.in);
		
		int cnt=0;
		do {
			System.out.print("1부터 100 사이의 숫자입력 => ");
			int inputNum = Integer.parseInt(sc.nextLine());
			cnt++;
			
			if(inputNum<rndNum) {
				System.out.println(">> "+inputNum+" 보다 큰 수 입니다 <<");
			}
			else if(inputNum>rndNum){
				System.out.println(">> "+inputNum+" 보다 작은 수 입니다 <<");
			}
			else {
				System.out.println(">> 빙고!! "+cnt+"번만에 맞추었습니다. <<");
				sc.close();
				break;
			}
			
		}while(true);
		
		
	}// end of main()------------------------

}
