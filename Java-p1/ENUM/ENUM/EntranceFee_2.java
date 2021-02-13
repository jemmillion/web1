package my.day18.d.ENUM;

// 아래는 조금전 my.day18.c.ENUM.EntranceFee_2 클래스를 enum을 사용하여 변경한 것이다.

//>> === enum(열거형)에 멤버(field, method)추가하기 === << //
//모든 enum(열거형)은 추상 클래스 Enum 의 자손이다. 그러므로 field 및  생성자와 method 를 만들 수 있는 것이다.
public enum EntranceFee_2 {

	CHILD(0), TEENAGER(150), ADULT(300), OLD(100); // 끝에 ; 을 붙여야 한다.
	// !!! 사실은 열거형 상수 CHILD, TEENAGER, ADULT, OLD 하나 하나가 EntranceFee_2 객체라는 것이다.!!!
	
	private final int FEE;
	// enum(열거형)의 인스턴스 변수는 반드시 final 이어야 한다는 규칙은 없지만 
	// fee는 열거형 상수값을 저장하기 위한 용도이므로 final 을 붙인 것이다.
	
	EntranceFee_2(int fee) {
	// enum(열거형)의 생성자는 접근제한자가 private 이 생략되어져 있는 것이다.
		this.FEE = fee;
	}

	public int getFee() {
		return FEE;
	}
}
