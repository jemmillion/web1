package my.day16.e.lambda;

public class MainLambda_2 {

	public static void main(String[] args) {
		
		System.out.println("=== 람다식(Lambda)릉 사용하지 않은 것 ===");

		InterAreaFunctional_1 obj1= new InterAreaFunctional_1(){
			@Override
			public double area(double x, double y){
				return x*y;
			}
		};
		double areaSize1=obj1.area(10.5, 5.5);
		System.out.println("가로 10.5, 세로 5.5인 면적은 : "+areaSize1);
		//가로 10.5, 세로 5.5인 면적은 : 57.75
		
		System.out.println("\n~~~~~~~~~ 람다식(Lambda) 표기 방법 ~~~~~~~~~");
		
		InterAreaFunctional_3 obj = new InterAreaFunctional_3(){
			@Override
			public void smile(String state) {
				System.out.println("웃음소리 : "+state);
			}
		};
		
		obj.smile("하하하~~~~");
		
		// 1. 람다식(Lambda) 표기 기본방법
		InterAreaFunctional_3 objA = (String state) -> {System.out.println("웃음소리1 : "+state);};
		objA.smile("낄깔깔~~~~");
		
		// 2. 실행명령문이 1개 이라면 { }를 생략할 수 있다 
		InterAreaFunctional_3 objB = (String state) -> System.out.println("웃음소리2 : "+state);
		objB.smile("헤헤헤~~~~");
		
		// 3. 파라미터 의 타입은 생략할 수 있다.
		InterAreaFunctional_3 objC = (state) -> System.out.println("웃음소리3 : "+state);
		objC.smile("호호호~~~~");
		
		// 4. 파라미터의 개수가 1개일 때는 파라미터를 감싸는 괄호 ( )를 생략할 수 있다.  
		InterAreaFunctional_3 objD = state -> System.out.println("웃음소리4 : "+state);
		objD.smile("히히히~~~~");
		
		// 5. 파라미터가 없는 람다식은 무조건 ( ) 괄호를 기재해야 한다.
		InterAreaFunctional_4 objE = () -> System.out.println("안녕하세요!~~");
		objE.write();
		
		// 6. 파라미터가 2개 이상 일때는 무조건 ( ) 괄호를 기재해야 한다.
		InterAreaFunctional_1 objF = (x,y) -> {return x+y;};
		double db1=objF.area(10.5,20.5);
		System.out.println(db1);
		// 31.0
		
		// 7. return이 있을 경우에는 { } 를 생략할 수 없다.
		// InterAreaFunctional_1 objG = (x,y) -> return x+y; // 오류
		InterAreaFunctional_1 objG = (x,y) -> {return x-y;};
		double db2=objG.area(30.5,20.5);
		System.out.println(db2);
		// 10.0
				
		// 8. return문만 있는 단일 코드인 경우에는 { } 및 return을 생략할 수 있다.
		InterAreaFunctional_1 objH = (x,y) -> x+y;
		double db3=objH.area(30.5,20.5);
		System.out.println(db3);
		// 51.0		
		
		
		System.out.println("=== 람다식(Lambda)를 사용한 것 ===");
		/*
	             람다(Lambda) 함수는 프로그래밍 언어에서 사용되는 개념으로 익명 함수(Anonymous functions)를 지칭하는 용어이다.
	             현재 사용되고 있는 람다의 근간은 수학과 기초 컴퓨터과학 분야에서의 람다 대수이다. 
	             람다 대수는 간단히 말하자면 수학에서 사용하는 함수를 보다 단순하게 표현하는 방법이다.
	 
	             람다 대수는 이름을 가질 필요가 없다. 즉, 익명 함수 (Anonymous functions)이다.
	             람다식(Lambda)은 익명함수(anonymous function)을 생성하기 위한 식으로서 객체 지향 언어보다 함수 지향 언어에 가깝다.
	        
	             자바에서 람다식의 사용 목적은 인터페이스에 정의된 메소드를 구현시 코딩양을 확 줄여서 간편하게 사용하는 것이 목적이다.  
		              
	             자바에서는 함수 단독으로는 사용할 수 없고 객체를 통해서만 사용이 가능한 형태이므로 
	             자바에서 람다를 실행하려면 전제조건으로 먼저 FunctionalInterface(함수형 인터페이스)를 구현한 익명(무명)클래스 객체가 생성되어져 있어야만 한다. 
	             람다는 FunctionalInterface(함수형 인터페이스)를 구현한 익명(무명)클래스 객체의 메소드로 동작하게 된다.  
	             여기서 주의할 점은 FunctionalInterface(함수형 인터페이스)는 오로지 딱 한개만의 추상메소드로 이루어져야 한다는 것이다. 
	             만약에 FunctionalInterface(함수형 인터페이스)에 두 개 이상의 메소드가 선언되면 자바 컴파일러는 오류를 발생시킨다.  
	             함수형 인터페이스를 만드려면  @FunctionalInterface 어노테이션을 사용하면 된다.
     */
		InterAreaFunctional_1 obj2 = (x,y) -> {return x*y;}; 
		double areaSize2 = obj2.area(10.5, 5.5);
		
		System.out.println("가로 10.5, 세로 5.5인 면적은 : "+areaSize2);
		//가로 10.5, 세로 5.5인 면적은 : 57.75
	}// end of main()---------------------------------

}
