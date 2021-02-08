package my.day17.a.collection;

import java.util.*;


public class PropertiesMain_6 {

	/*
    Properties 는 HashMap의 구버전인 Hashtable을 상속받아 구현한 것으로,
    Hashtable 은 키와 값(Object, Object)의 형태로 저장하는데 비해서
    Properties 는 (String 키, String 밸류값)의 형태로 저장하는 단순화된 컬렉션 클래스이다.
        키는 고유해야 한다. 즉, 중복을 허락하지 않는다. 중복된 값을 넣으면 마지막에 넣은 값으로 덮어씌운다.
        주로 어플리케이션의 환경설정과 관련된 속성(property)을 저장하는데 사용되며, 
        데이터를 파일로 부터 읽고 쓰는 편리한 기능을 제공한다.    
	 */
	
	public static void main(String[] args) {
		
		Properties prop =new Properties();
		
		prop.setProperty("jdk", "http://www.oracle.com/technetwork/java/javase/downloads/index.html");
	    prop.setProperty("eclipse", "http://www.sist.co.kr");
	    prop.setProperty("eclipse", "http://www.eclipse.org/downloads/eclipse-packages/");
	    prop.setProperty("oracle", "http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html");
	
	    String url=prop.getProperty("eclipse");
	    System.out.println(url);
	    // http://www.eclipse.org/downloads/eclipse-packages/
	    
	    System.out.println("\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n");
	    
	    // === prop에 저장되어진 모든 value값들을 출력하고자 한다. ===
	    // 먼저 prop에 저장되어진 키목록을 알아와야 한다.
	    // 키목록은 아래와 같이 하면 된다.
	    
	    // Enumeration<?> en=prop.propertyNames();
	    // 제네릭에서 <?> 의 뜻은 ?는 아무거나를 뜻하는 것이므로  Object와 같은 의미이다.
	    
	    @SuppressWarnings("unchecked") // 경고 표시를 하지 말라는 뜻
		Enumeration<String> en=(Enumeration<String>)prop.propertyNames();
	    // Properties prop에서 키목록은 Enumeration<String> 형태로 반환시켜준다.
	    
	    while(en.hasMoreElements()) {
	    	
	    	String key=en.nextElement();
	    	System.out.print(key);
	    	System.out.print("=");
	    	System.out.println(prop.getProperty(key)); // 해당 key에 일치하는 밸류값을 리턴시켜준다.
	    
	    }// end of while----------------------------
	}// end of main()-------------------------------------------------
	

}
