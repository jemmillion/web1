package my.day08.c.random;

import java.util.Random;
import java.util.Scanner;

public class RandomMain2 {

	public static void main(String[] args) {

		// === 랜덤한 정수를 뽑아본다 === //
		
		// 보안상 Math.random() 보다는 new Random(); 을 사용하는 것이 더 안전하다.
				
		Random rnd = new Random();
		// int rndNum = rnd.nextInt(마지막수-처음수+1)+처음수;
		
		
		int rand1 = rnd.nextInt(10-1+1)+1;
		System.out.println("1부터 10까지 중 랜덤하게 발생한 값 : "+rand1);
		
		int rand2 = rnd.nextInt(7-3+1)+3;	
		System.out.println("3부터 7까지 중 랜덤하게 발생한 값 : "+rand2);
		
		int rand3 = rnd.nextInt('z'-'a'+1)+'a';
		System.out.println("a부터 z까지 중 랜덤하게 소문자 : "+(char)rand3);
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		// 인증키는 랜덤한 숫자3개소문자4개로 만든다.
		// 예:>103qdtq  020avat
		
		String key = "";
		for(int i=0;i<3;i++) {
			int num=rnd.nextInt(9-0+1)+0;
			key+=num;
		}
		for(int i=0;i<4;i++) {
			int num=rnd.nextInt('z'-'a'+1)+'a';
			key+=(char)num;
		}
		System.out.println("인증키 => "+key); 
		
		/*
		int randKey1 = (int)(Math.random()*(9-1+1))+1;
		int randKey2 = (int)(Math.random()*('z'-'a'+1))+'a';
		String result="";
		
		for(int i=0;i<4;i++) {
				
		System.out.println("인증키는 "+(randKey1+(char)randKey2)+"입니다.");
		*/
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("선택[1:홀수/0:짝수] => ");
		String choice = sc.nextLine();  // "0" 	    "1"
		char ch = choice.charAt(0);     // '0' 	 	'1'
										// 48       49 
		                                // 48-'0'  	49-'0'
										// 48-48	49-48
		                                //  0        1
		int choiceNo = ch-'0'; // 0 또는 1
		
		int randNo = rnd.nextInt(10-1+1)+1;
		
		String result="";
		if(choiceNo == randNo%2) {
			result="맞추었습니다.";
		}
		else {
			result="틀렸습니다.";
		}
		
		
		System.out.println(result+"랜덤하게 나온 수는 "+randNo+"입니다.");
		
		//rand1==> 짝수인지 홀수인지
		
		sc.close();
	}

}
