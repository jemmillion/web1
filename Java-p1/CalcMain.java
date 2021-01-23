package my.day05.c.IF;

import java.util.Scanner;

public class CalcMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("첫번째 정수 입력 => ");
			int num1 = Integer.parseInt(sc.nextLine());
	
			System.out.print("두번째 정수 입력 => ");
			int num2 = Integer.parseInt(sc.nextLine());
			
			System.out.print("사칙연산자 선택(+ - * /) => ");
			String operator = sc.nextLine(); // "+" "-" "*" "/"
			
			String result = "";
			if( "+".equals(operator) ) {
				result = String.valueOf(num1 + num2);
			}
			else if( "-".equals(operator) ) {
				result = String.valueOf(num1 - num2);
			}
			else if( "*".equals(operator) ) {
				result = String.valueOf(num1 * num2);
			}
			else if( "/".equals(operator) ) {
				if(num2==0) {
					result = ">> 분모에는 0이 올 수 없습니다!! <<";
				}
				else {
					result = String.valueOf((double)num1 / num2);
				}
			}
			else {
				System.out.println(">> 사칙연산(+ - * /)만 선택하세요!! <<");
				sc.close();
				return;
			}
			
			//삼항연산자
			result = ("/".equals(operator)&&num2==0) ? result:num1+operator+num2+"="+result;
			
			System.out.println(result);
			
		} catch(NumberFormatException e){
			System.out.println(">> 정수만 입력하세요!! <<");
		}
		sc.close();
	}// end of main()----------------------------------
	}

