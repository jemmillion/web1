package my.day08.c.random;

import java.util.Scanner;

public class MathRandomMain {

	public static void main(String[] args) {

		// === 랜덤한 정수를 뽑아본다 === //
		
		double random=Math.random();
		System.out.println("random => "+random);
		// random => 6.437128685458093E-4
		// random => 0.8174716885933764
		// random => 0.022325715848525274
		
		/*
			java.lang.Math.random() 메소드는
			0.0 이상 1.0 미만의 실수(double)값을 랜덤하게 나타내어주는 메소드이다.
			즉, 0.0 <= 임의의 난수(실수) < 1.0
			
			1부터 10까지 중 랜덤한 정수를 얻어와 본다.
			
			랜덤한 정수 = (int)(Math.random()*구간범위)+시작값;
			
			0.0		  (int)(0.0*10-1+1))+1			==>	1
			0.23346438(int)(0.23346438*(10-1+1))+1	==> 3
			0.67835431(int)(0.67835431*(10-1+1))+1	==> 7
			0.99999999(int)(0.99999999*(10-1+1))+1  ==> 10
			
			3부터 7까지 중 랜덤한 정수를 얻어와 본다.
			
			0.0		  (int)(0.0*7-3+1))+3			==>	3
			0.23346438(int)(0.23346438*(7-3+1))+3	==> 4
			0.67835431(int)(0.67835431*(7-3+1))+3	==> 6
			0.99999999(int)(0.99999999*(7-3+1))+3   ==> 7
			
		 */
		
		int rand1 = (int)(Math.random()*(10-1+1))+1;
		System.out.println("1부터 10까지 중 랜덤하게 발생한 값 : "+rand1);
		
		int rand2 = (int)(Math.random()*(7-3+1))+3;
		System.out.println("3부터 7까지 중 랜덤하게 발생한 값 : "+rand2);
		
		int rand3 = (int)(Math.random()*('z'-'a'+1))+'a';
		System.out.println("a부터 z까지 중 랜덤하게 소문자 : "+(char)rand3);
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		// 인증키는 랜덤한 숫자3개소문자4개로 만든다.
		// 예:>103qdtq  020avat
		
		String key = "";
		for(int i=0;i<3;i++) {
			int num=(int)(Math.random()*(9-0+1))+0;
			key+=num;
		}
		for(int i=0;i<4;i++) {
			int num=(int)(Math.random()*('z'-'a'+1))+'a';
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
		
		int randNo = (int)(Math.random()*(10-1+1))+1;
		
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
