package my.day09.b.array;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MoneyCommaMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("▷금액을 입력하세요(정수로만) => ");
		
			
		long money=Long.parseLong(sc.nextLine());
		
		DecimalFormat df = new DecimalFormat("#,###");
		String smoney=df.format(money);
		
		System.out.println(smoney);
	
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.print("▶금액을 입력하세요(정수로만) => ");
		String inputStr=sc.nextLine();	 // 123456789 		 23456789 
		
		int len = inputStr.length();  	 // len=> 9 		 len=> 8
		int commaCount=len/3;			 // commaCount=> 3   commaCount=> 2
		
		commaCount=(len%3==0)?commaCount-1:commaCount;
		 								 // commaCount=> 3-1 commaCount=> 2
		// if(len%3==0) commaCount-=1;과 동일
		
		char[] inputChArr = inputStr.toCharArray();
		/*
		   -------------------		-----------------
  데이터값=>   |1|2|3|4|5|6|7|8|9|		|2|3|4|5|6|7|8|9|
		   -------------------		-----------------
 index=>	0 1 2 3 4 5 6 7 8 		 0 1 2 3 4 5 6 7 
		 */
		
		char[] outputChArr = new char[len+commaCount];	// new char[9+2];	new char[8+2];
		/*
		   -----------------------		---------------------
		   |1|2|3|,|4|5|6|,|7|8|9|		|2|3|,|4|5|6|,|7|8|9|
		   -----------------------		---------------------
	index	0 1 2 3 4 5 6 7 8 9 10		 0 1 2 3 4 5 6 7 8 9 
	
	콤마들어올=> 7번  3번						 6번  2번
	index
		 */
		
		int cnt=0;  // 반복하는 횟수
		for(int i=outputChArr.length-1, j=inputChArr.length-1;i>=0;i--,j--) {
			cnt++;  // 1 2 3 4 5 6 7 8 9 10 11	
					// 1 2 3 4 5 6 7 8 9 10
			
			if(cnt%4!=0) {
				// 값을 넣어주는 것이다.
				outputChArr[i] = inputChArr[j];   // outputChArr[10] = inputChArr[8];
												  // outputChArr[9] = inputChArr[7];
												  // outputChArr[8] = inputChArr[6];
												  
												  // outputChArr[6] = inputChArr[5];
												  // outputChArr[5] = inputChArr[4];
												  // outputChArr[4] = inputChArr[3];
								
												  // outputChArr[2] = inputChArr[2];
												  // outputChArr[1] = inputChArr[1];
											  	  // outputChArr[0] = inputChArr[0];
			}
			
			else { // 콤마(,)를 넣어주는 것이다.
				outputChArr[i] = ',';		  	// outputChArr[7] = ',';  j는 5+1
				j++;		  					// outputChArr[3] = ',';  j는 2+1
			}			
		}// end of for------------------------------------------
		
		String result="";
		for(int i=0;i<outputChArr.length;i++) {
			result+=outputChArr[i];
		}
		
		System.out.println(result);
		
		sc.close();
			
		}

	}
