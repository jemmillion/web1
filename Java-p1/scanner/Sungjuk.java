package my.day04.b.scanner;

public class Sungjuk {

	String hakbun; // "0930434"
	String name;
	byte kor;
	byte eng;
	byte math;
	
	short age; // short -32768 ~ 32767   20 ~ 50 로 제한

	
	// public Sungjuk() {} 기본생성자
	
	boolean checkJumsu(byte jumsu) {
		if(0 <= jumsu && jumsu <= 100) {
			return true;
		}
		else { 
			System.out.println("## 점수 입력은 0 이상 100 까지만 가능합니다. ##");
			return false;
		}
		}
		
	boolean checkAge(short age) {
		if(20 <= age && age <= 50) {
				return true;
		}
		else { 
			System.out.println("=== 나이 입력은 20 이상 50 까지만 가능합니다. ===");
			return false;
		}
		}	
		

	void showInfo() {
		short total = (short)(kor+eng+math);
		float avg = Math.round(total/3.0F*10)/10.0F;
				// Math.round()는 my.day05.b.math의 MathMain.java를 참조할 것!!
		
		String hakjum = "";
		
		if( avg >= 90 ) {
			hakjum = "A";
		}
		
		else if( avg >= 80 ) {
			hakjum = "B";
		}
		
		else if( avg >= 70 ) {
			hakjum = "C";
		}
		
		else if( avg >= 60 ) {
			hakjum = "D";
		}
		
		else {
			hakjum = "F";
		}
		
		System.out.println("=== "+name+"님의 성적결과 === \n"
						+ "1. 학번 : "+hakbun+"\n"
						+ "2. 성명 : "+name+"\n"
						+ "3. 국어 : "+kor+"\n"
						+ "4. 영어 : "+eng+"\n"
						+ "5. 수학 : "+math+"\n"
						+ "6. 총점 : "+total+"\n"
						+ "7. 평균 : "+avg+"\n"
						+ "8. 학점 : "+hakjum+"\n"
						+ "9. 나이 : "+age+"세");
		
		
	}
}
