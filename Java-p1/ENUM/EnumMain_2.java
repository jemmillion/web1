package my.day18.b.ENUM;

public class EnumMain_2 {

	public static void main(String[] args) {
		
		// 모든 enum(열거형)은 추상 클래스 Enum 의 자손이다. 
	    // 즉, enum(열거형)의 조상은 java.lang.Enum 클래스이다.
	    // 그러므로 enum(열거형)타입은 클래스이며 java.lang.Enum 클래스에서 정의되어진 메소드들을 사용할 수 있게 된다.
		
		SeasonType_1[] seasonArr = SeasonType_1.values();
		// values() 메소드는 enum(열거형)의 모든 상수를 배열에 담아 반환해주는 것이다.
		
		System.out.println("1.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for(SeasonType_1 season : seasonArr) {
			String seasonName = season.name();
			// name() 메소드는 enum(열거형) 상수의 값을 문자열(String)로 반환해주는 것이다.
			
			System.out.println(seasonName);
		}
		/*
			봄
			여름
			가을
			겨울
		 */
		
		System.out.println("\n 2.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for(SeasonType_1 season : seasonArr) {
			int order = season.ordinal();
			// ordinal() 메소드는 enum(열거형) 상수가 정의된 순서를 반환해주는 것이다.
	         // 순서는 0 부터 시작한다.
			String seasonName = season.name();
			
			System.out.println(order+". "+seasonName);
		}
		/*
			0. 봄
			1. 여름
			2. 가을
			3. 겨울
		 */
		
		System.out.println("\n 3.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		try {
			SeasonType_1 season = SeasonType_1.valueOf("여름");
			// valueOf()메소드는 열거형 SeasonType 에서 문자열 "여름" 과 일치하는 열거형 상수를 반환해주는 것이다.
			
			System.out.println(season);
			// 여름
			
			season = SeasonType_1.valueOf("한겨울");
			System.out.println(season);
		} catch(IllegalArgumentException e) {
			System.out.println(">> 열거형 상수에 없습니다. <<");
		}
	}// end of main()--------------------------------

}
