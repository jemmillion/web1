package my.day16.d.annonymousClass;

public class MainAnnonymous {

	public static void main(String[] args) {

		System.out.println("\n== 1. 일반 클래스 사용시 ==");
		
		InterArea area1 = new Area();
		double areaSize1 = area1.area(10.5, 5.5);
		System.out.println("가로 10.5, 세로 5.5인 면적은 : "+areaSize1);
		// 가로 10.5, 세로 5.5인 면적은 : 57.75
		
		InterArea area2 = new Area();
		double areaSize2 = area2.area(20.5, 15.5);
		System.out.println("가로 20.5, 세로 15.5인 면적은 : "+areaSize2);
		// 가로 20.5, 세로 15.5인 면적은 : 317.75
		
		System.out.println("\n== 2. 익명(==무명,annonymous) 클래스 사용시 ==");
		/*
		        익명(무명) 클래스는 말 그대로 이름이 없는 클래스이다.
		        인터페이스를 구현한 클래스가 특정한 한곳에서만 사용되고 다른 곳에서는 재사용되지 않는 경우이라면
		        이럴 경우에는 굳이 클래스 파일로 만들 필요 없이 익명(무명) 클래스로 사용하면 된다.
		        
		        익명(무명) 클래스의 객체는 반드시 부모 클래스를 상속받는 클래스 이거나 인터페이스를 구현한 클래스이어야 한다.
		        왜냐하면 클래스의 이름은 없지만 저장받는 타입은 존재해야 하기 때문이다.
		        그래서 익명(무명) 클래스의 객체는 부모 클래스 또는 인터페이스로 받게 되어있다.   
		                  
		        익명(무명) 클래스를 사용하는 가장 큰 목적은 
		        부모 클래스를 상속받는 자식 클래스를 생성하지 않고도  객체를 만들어서 부모 클래스에 정의된 메소드를 재정의 할 수 있다는 것이다. 
		        익명(무명) 클래스는 일반적으로 Graphic 프로그래밍(GUI 프로그래밍)을 할 때 리스너 인터페이스를 만들 때 주로 사용한다.
		        익명(무명) 클래스는 추상클래스와 인터페이스로부터 만들 수 있다.    
	   */
		InterArea areaObj = new InterArea() {
			@Override
			public double area(double x, double y){
				return x*y;
			}
		};
		// Interface를 사용함과 동시에 객체화?
		
		double areaSize3 = areaObj.area(10.5, 5.5);
		System.out.println("가로 10.5, 세로 5.5인 면적은 : "+areaSize3);
		
	}// end of main()-------------------------------------

}
