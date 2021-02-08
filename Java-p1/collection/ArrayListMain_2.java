package my.day17.a.collection;

import java.util.*;

/*
	== ArrayList의 특징 == 
	1. 출력시 저장된 순서대로 나온다.
	2. 중복된 데이터를 저장할 수 있다.
	3. 데이터를 읽어오는 속도는 ArrayList 가 LinkedList 보다 상대적으로 빠르다.
	4. 순차적으로 데이터를 추가/삭제하는 경우에는 ArrayList 가 LinkedList 보다 상대적으로 빠르다.
	5. 일반적인 데이터 추가/삭제는 데이터 중간 중간마다 발생하므로 이러한 경우에는 ArrayList 가 LinkedList 보다 상대적으로 느리다.
	6. 결과값은 ArrayList 를 사용하든지 LinkedList 를 사용하든지 동일한 결과값을 가진다.
	7. LinkedList 보다는 ArrayList 를 사용하도록 하자.
*/

public class ArrayListMain_2 {

	public static void main(String[] args) {
		
		// 1. Member 클래스의 객체만을 저장할 수 있는 ArrayList 객체 mbrList를 생성하시오.
		List<Member> mbrList = new ArrayList<>();
		
		// 2. Member 클래스의 객체 6개를 생성하여 mbrList 에 저장하세요.
		mbrList.add(new Member("youjs","qwer1234$","유재석"));
		mbrList.add(new Member("eom","qwer1234$","엄정화"));
	    mbrList.add(new Member("kanghd","qwer1234$","강호동"));
	    mbrList.add(new Member("leess","qwer1234$","이순신"));
	    mbrList.add(new Member("kimth","qwer1234$","김태희"));
	    mbrList.add(new Member("kangkc","qwer1234$","강감찬"));
		
	    mbrList.add(new Member());
	    mbrList.add(new Member("leess","qwer1234$","이순신"));
	    // ArrayList는 데이터 값이 동일한 객체를 저장할 수 있다.
	    
	    mbrList.add(new Member("leemh","qwer1234$","이민호"));
	    
		// 3. mbrList에 저장되어진 모든 회원들의 정보를 출력하도록  Member 클래스에 정의된 
	    //	  mbrInfo() 메소드를 실행하세요.
	    
	    // ArrayList에 저장되어진 데이터의 개수는 mbrList.size()로 알아온다.
	    System.out.println(mbrList.size()); // 9
	    
	    for(int i=0;i<mbrList.size();i++) {
	    	mbrList.get(i).infoPrint();
	    }
	    
	    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	    
	    for(Member mbr : mbrList) {// List.size()만큼 반복한다.
	    	mbr.infoPrint();
	    }
		
	    System.out.println("\n~~~~~~~~~[퀴즈1]~~~~~~~~~~\n");
	    
	    /*
	    	[퀴즈1]
	    	mbrList에 저장되어진 Member 객체들 중에서
	    	id 값이 "leess"인 회원만 그 회원의 정보를 출력하세요.
	     */
	    	
	    for(int i=0;i<mbrList.size();i++) {
	    	if("leess".equals(mbrList.get(i).id)) {
	    		mbrList.get(i).infoPrint();
	    	}
	    }
	    // 또는 
	    for(Member mbr : mbrList) {
	    	if("leess".equals(mbr.id))
	    		mbr.infoPrint();
	    }
	    
	    /*
	    	== 이순신님의 회원정보 ==
			1. 아이디 : leess
			2. 암호 : qwer1234$
			3. 성명 : 이순신
	     */
	    
	    System.out.println("\n~~~~~~~~~[퀴즈2]~~~~~~~~~~\n");

	    /*
    		[퀴즈2]
    		mbrList에 저장되어진 Member 객체들 중에서
    	 	name 값이 "이"씨인 회원만 그 회원의 정보를 출력하세요.
    	 	
    	 	name이 "서"씨인 회원만 그 회원의 정보를 출력하세요.
    	 	>> 회원중 "서"씨는 없습니다.
	     */

	    boolean flag=false;
	    String firstName="이";
	    // String firstName="서";
	    /*
	    for(int i=0; i<mbrList.size(); i++) {
	    	String name=mbrList.get(i).name;
	    	
	    	if(name!=null&&name.startsWith(firstName)) {
	    		flag=true;
	    		mbrList.get(i).infoPrint();
	    	}
	    }// end of for---------------------------------------------
	    
	    if(!flag) {
	    	System.out.println("회원중 \""+firstName+"\"씨는 없습니다.");
	    }
	    */
	    // 또는
	    for(Member mbr : mbrList) {
	    	String name=mbr.name;
	    	if(name!=null&&name.startsWith(firstName)) {
	    		flag=true;
	    		mbr.infoPrint();
	    	}
	    }// end of for---------------------------------------------
	    
	    if(!flag) {
	    	System.out.println("회원중 \""+firstName+"\"씨는 없습니다.");
	    }
	    
	    // *** ArrayList 타입인 mbrList에 새로운 Member 객체 추가시
	    //	   특정 index(위치)에 들어가도록 할 수 있다.
	    System.out.println("\n ~~~ 새로운 Member 객체 추가 ~~~ ");
	    
	    mbrList.add(new Member("seolh","qwer1234$","설현"));
	    // index 값이 없으면 mbrList의 맨 뒤에 추가된다.
	    
	    mbrList.add(3, new Member("chaew","qwer1234$","차은우"));
	    //			3이 특정 인덱스이다.
	    // 유재석(0) 엄정화(1) 강호동(2) 아순신(3) 으로 되어있는데
	    // 유재석(0) 엄정화(1) 강호동(2) 차은우(3) 이순신(4) 으로 된다.
	    
	    for(Member mbr : mbrList) {
	    	mbr.infoPrint();
	    }
	    
	 // *** ArrayList 타입인 mbrList에 저장되어진 Member 객체 삭제하기 ***
	    System.out.println("\n ~~~ ArrayList 타입인 mbrList에 저장되어진 Member 객체 삭제하기 ~~~ ");
	    System.out.println(">> 삭제하기 전 mbrList.size() => "+mbrList.size());
	    // >> 삭제하기 전 mbrList.size() => 11
	    mbrList.remove(3);
	    // mbrList.remove(삭제할 Member 객체의 인덱스번호);
	    
	    System.out.println(">> 삭제한 후 mbrList.size() => "+mbrList.size());
	    // >> 삭제한 후 mbrList.size() =<</
	    for(Member mbr:mbrList) {
	    	mbr.infoPrint();
	    }
	    
	    System.out.println("\n~~~~~~~~~[퀴즈3]~~~~~~~~~~\n");

	    /*
    		[퀴즈3]
    		mbrList에 저장되어진 Member 객체들 중에서
    	 	name 값이 "이"씨인 회원들을 삭제하고
    	 	삭제한 후 mbrList에 저장되어진 Member 객체들의 정보를 출력하세요.
	     */
	    
	    firstName="이";
	    
	    // >> 아래의 것은 틀린 풀이 
	    for(int i=mbrList.size()-1;i>=0;i--) {
	    	String name=mbrList.get(i).name;
	    	
	    	if(name!=null&&name.startsWith(firstName)) {
	    		System.out.println("i => "+i); // 8(이민호) 7(이순신) 6 5 4(김태희) 3(이순신) 2 1 0
 	    		mbrList.remove(i); // 8(이민호)를 삭제하면
 	    						   // 0 1 2 3(이순신) 4(김태희) 5 6 7(이순신) 으로 되어짐.
 	    		
 	    						   // 7(이순신)를 삭제하면
 	    						   // 0 1 2 3(이순신) 4(김태희) 5 6 으로 되어짐.
 	    		
 	    						   // 3(이순신)를 삭제하면
 	    						   // 0 1 2 3(김태희) 4 5 로 되어짐.
	    	}
	    }
	    for(Member mbr:mbrList) {
	    	mbr.infoPrint();
	    }
	    /*
	    for(Member mbr:mbrList) {
	    	mbr.infoPrint();
	    }
	   */
	    
	    System.out.println("\n~~~~~~~~~[퀴즈4]~~~~~~~~~~\n");

	    /*
    		[퀴즈4]
    		mbrList에 저장되어진 Member 객체들 중에서
    	 	특정한 조건에 만족하는 name이 "이"씨인 회원들을 삭제하고
    	 	삭제되어진 그 인덱스(위치) 자리에 새로운 Member 객체를 넣어주기
    	 	그런데 만약에 if가 null인 것이 없다라면 새로운 Member 객체는 맨 뒤에 넣어주기
	     */
	    flag=false;
	    
	    for(int i=mbrList.size()-1;i>=0;i--) {
	    	String id=mbrList.get(i).id;
	    	if(id==null) {			// 6 5(id가 null) 4 3 2 1 0
	    		mbrList.remove(i);	// 5(id가 null) 삭제
	    							// 0 1 2 3 4 5(설현) 으로 된다.
	    		mbrList.add(i, new Member("seokj","qwer1234$","서강준"));		// 0 1 2 3 4 5(서강준) 6(설현)
	    		flag=true;
	    		break;
	    	}
	    	if(!flag) {
	    		mbrList.add(new Member("seokj","qwer1234$","서강준"));
	    	}
	    }
	    for(Member mbr:mbrList) {
	    	mbr.infoPrint();
	    }	    
	    
	    System.out.println("\n === mbrList에 저장된 모든 객체 삭제하기 ===\n");
	    mbrList.clear();
	    
	    System.out.println(">> 삭제한 후 mbrList.size() => "+mbrList);
	    
	    
	    
	}// end of main()------------------------------

}
