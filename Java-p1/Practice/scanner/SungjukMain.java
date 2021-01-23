package my.day04.b.scanner;

import java.util.Scanner;

public class SungjukMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Sungjuk sj = new Sungjuk(); // 기본생성자
		
		int inputType = 0;
		try {
		
			System.out.print("1. 학번 : ");
			sj.hakbun = sc.nextLine(); // "091234"
			
			System.out.print("2. 성명 : ");
			sj.name = sc.nextLine(); // "이순신"
	
			// === *** 유효성 검사하기(올바른 데이터인지 틀린 데이터인지 검사하는 것) *** ===
			// Integer.parseInt(String Str)
	        // Byte.parseByte(String Str)
			// Short.parseShort(String Str)
			
			inputType = 1; // inputType 값이 1이라면 국어,영어,수학를 입력하는 중이다 라고 본다.
			System.out.print("3. 국어 : ");
			byte kor = Byte.parseByte(sc.nextLine());
			// "똘똘이" "2000" "-50"
			// 0~100
			// byte : -128~127	
			
		// boolean bool = sj.checkJumsu(kor); 
			// kor 값이 0~100 이라면 true, 아니라면(0 미만 또는 100보다 크다라면) false
			
		// if(!bool) {
			if(!sj.checkJumsu(kor)) {
				sc.close();
				return;
			}
			else {
				sj.kor = kor;
			}
			
			System.out.print("4. 영어 : ");
			byte eng = Byte.parseByte(sc.nextLine()); 
								// "똘똘이" "2000" "120" "80"
		// bool = sj.checkJumsu(eng);
			if(!sj.checkJumsu(eng)) {
				sc.close();
				return;
			}
			else {
				sj.eng = eng;
			}
			
			System.out.print("5. 수학 : ");
			byte math = Byte.parseByte(sc.nextLine()); 
									// "안녕" "3000" "-90" "70"
		// bool = sj.checkJumsu(math);
			
			if(!sj.checkJumsu(math)) {
				sc.close();
				return;
			}
			else {
				sj.math = math;
			}
			
			inputType = 2; // inputType 값이 2라면 나이를 입력하는 중이다 라고 본다.
			System.out.print("6. 나이 : ");
			short age = Short.parseShort(sc.nextLine()); 
										// "행복하세요" "900000" "15" "25"
			
			if(!sj.checkAge(age)) {
				sc.close();
				return;
			}
			else {
				sj.age = age;
			}
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			sj.showInfo();
			/*
			  === 이순신님의 성적결과 ===
			  1. 학번 : 091234
			  2. 성명 : 이순신
			  3. 국어 : 90
			  4. 영어 : 80
			  5. 수학 : 78
			  6. 총점 : 248
			  7. 평균 : 82.6666666667
			  8. 학점 : B
			  9. 나이 : 20
			 */
			
			
			}catch(NumberFormatException e) {
				if(inputType == 1)
					System.out.println(">> 점수 입력은 0이상 100 까지만 가능합니다. <<");
				else if(inputType == 2)
					System.out.println(">> 나이 입력은 20이상 50 까지만 가능합니다. <<");
				
				e.printStackTrace();
			}
		
		
			
		sc.close();
	}// end of main()------------------

		
}
