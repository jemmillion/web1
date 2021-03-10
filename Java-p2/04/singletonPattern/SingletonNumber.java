package jdbc.day04.singletonPattern;

public class SingletonNumber {

/*
    !!! === singleton 패턴에서 중요한 것은 다음의 3가지 이다  === !!!
    
    == 첫번째,
       private 변수로 자기 자신의 클래스 인스턴스를 가지도록 해야 한다.
              접근제한자가 private 이므로 외부 클래스에서는 직접적으로 접근이 불가하다.
              또한 static 변수로 지정하여 SingletonNumber 클래스를 사용할 때 
              객체생성은 딱 1번만 생성되도록 해야 한다.  
*/	
	
	// field (첫번째)
	// --> static 변수  첫번째로 호출(작동) <-- //
	private static SingletonNumber singleton = null;
	
	private int cnt = 0; // 인스턴스 변수
	
	// --> static 초기화 블럭 두번째로 호출(작동) <-- //
	static {
		// 중요한 사실은 static 초기화 블럭은 해당 클래스가 객체로 생성되기전에 먼저 실행되어지며,
		// 딱 1번만 호출되어지고 다음번에 새로운 객체(인스턴스)를 매번 생성하더라도
		// static 초기화 블럭은 호출이 안되어진다.
		
		singleton = new SingletonNumber();
		
		System.out.println(">>> SingletonNumber 클래스의 static 초기화 블럭 호출  <<<");
	}
	
	
 //	== 두번째,
 // 생성자에 접근제한자를 private 으로 지정하여, 외부에서 절대로 인스턴스를 생성하지 못하도록 막아버린다.
	private SingletonNumber() { }

 //	== 세번째,
 // static 메소드를 생성[지금은 getInstance()] 하여 외부에서 해당 클래스의 객체를 사용할 수 있도록 해준다.	
    public static SingletonNumber getInstance() { // --> 세번째로 호출(작동) static 메소드 <-- //
    	return singleton;
    }
    
    
    /////////////////////////////////////////////////////////
    public int getNextNumber() { // 인스턴스 메소드
		return ++cnt;  // 인스턴스 변수
	}
	
}




