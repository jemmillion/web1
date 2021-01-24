package my.day08.b.DOWHILE;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrivateNumberMain {

	// === 소수란? ===
	// 소수란? 1과 자기 자신으로밖에 나누어지지 않는 1 이외의 정수
	// 예> 1부터 10까지의 소수를 나타내면
	//		2%2 ==> 0	2는 소수
	//		3%3 ==> 0	3은 소수
	//		4%2 ==> 0	4는 소수가 아님
	//		5%5 ==> 0	5는 소수
	//		6%2 ==> 0	6은 소수가 아님
	//		7%7 ==> 0	7은 소수
	//		8%2 ==> 0	8은 소수가 아님
	//		9%3 ==> 0	9는 소수가 아님
	//		10%2 ==> 0	10은 소수가 아님

	
	public static void main(String[] args) {

		/*
			
			== 실행 결과 ==
			▷시작 자연수 : 1엔터
			▷끝 자연수 : 20 엔터
			1부터 20까지의 소수는?
			2,3,5,7,11,13,17,19
			
			1부터 20까지의 소수의 개수? 8개
			
			1부터 20까지의 소수들의 합? 77
			
			=== 프로그램 종료 ===
		
		 */
		
		Scanner sc = new Scanner(System.in);
		int startNo=0, endNo;
		
		
		do {
		
			try{
				System.out.print("▷시작 자연수 : ");
				startNo = sc.nextInt(); // 1엔터 or 1.345엔터 or 똘똘이엔터
				sc.nextLine();
				
				System.out.print("▷끝 자연수 : ");
				endNo = sc.nextInt(); // 20엔터 or 2.345엔터 or 이순신엔터
				sc.nextLine();
				
				if(startNo<1 || endNo<1) {
					System.out.println(">> 입력하시는 값은 모두 자연수 이어야 합니다!! <<");
				}
				else {
					break;
				}
				
			} catch(InputMismatchException e) {
				System.out.println(">> 자연수만 입력하세요!! <<");
				sc.nextLine();
			}
		}while(true);
		
		////////////////////////////////////////////////////////////////////
		
		// startNo ==> 1		5
		// endNo ==> 20			20
		// 1부터 20까지의 소수를 구해야 한다.
		// 소수란? 1과 자기 자신의 수로만 나누었을때 나머지가 0인 1 이외의 정수를 말한다.
		
		String resultStr="";
		int cnt=0, sum=0;
		
		for(int i=startNo;i<=endNo;i++) {
			
			if(i==1)		// i가 소수인지 아닌지를 검사할 대상이다. 
				continue;	// 1은 소수여서 검사할 필요가 없으므로 continue;를 한다.
		
			/*
				나누기를 했을때 나머지가 얼마가 되는지 일일이 검사를 한다.
				만약에 i가 2라면 ==> 2는 소수이다.
				만약에 i가 3이라면 ==> 3%2 3은 소수이다.
				만약에 i가 4이라면 ==> 4%2 == 0 4%3(검사할 필요가 없다) 4는 소수가 아니다.
				만약에 i가 5이라면 ==> 5%2 5%3 5%4 5는 소수이다.
				만약에 i가 9이라면 ==> 9%2 9%3== 0 9%4(검사할 필요가 없다) 9는 소수가 아니다.
			 */
			
			boolean isSosu=true;
			for(int j=2;j<i;j++) { // j가 분모에 들어갈 값이다.
				if(i%j==0) { // 검사대상인 i는 소수가 아닌경우
					isSosu = false;
					break;
				}
				
			}// end of for---------------------------------
			
			if(isSosu) {// 검사대상인 i가 소수이라면
				cnt++;  // 소수의 개수
				sum+=i; // 소수들의 누적 합계
				
				//2,3,5,7,11,13,17,19
				
				String comma=(cnt>1)?",":""; // 두번ㅉ부터 나오는 소수부터는 소수 앞에 ,를 붙인다.
				resultStr += comma+i;
			}
			
		}// end of for-------------------------------------
		
		
		System.out.println(startNo+" 부터 "+endNo+" 까지의 소수는?\n"+resultStr);
		System.out.println(startNo+" 부터 "+endNo+" 까지의 소수의 개수?\n"+cnt+"개");
		System.out.println(startNo+" 부터 "+endNo+" 까지의 소수들의 합?\n"+sum);
		
		sc.close();
		System.out.println("=== 프로그램 종료 ===");
	}// end of main()----------------------

}
