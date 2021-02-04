package my.day15.c.INTERFACE;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.util.MyUtil;

public class Gujikja extends Member {

// 	Gujikja 클래스는 Member 클래스의 자식 클래스가 되어진다.
//  그러므로 자식 클래스인 Gujikja 에서는 부모 클래스인 Member 에서 선언되어진 것들을 
//  상속받아 사용할 수 있게 된다.	
	
	// 구직자 
	
	// field 
	private String jubun;   // 주민번호 앞의 7자리 까지만   
	                        // 예: "9501151"  남자   "0105103"  남자
	                        // 예: "9501152"  여자   "0105104"  여자
	
	// method 
	// == 메소드의 재정의(method overriding  메소드 오버라이딩) == //
	// ==> InterMember 인터페이스에 정의된 추상메소드(미완성메소드)를 재정의 한 것임. 
	@Override
	public void setName(String name) {
		
		// name 에는 한글만 허용하겠다.
		
		if(name == null) {
			System.out.println(">> 성명은 공백없이 한글로만 2글자 부터 5글자까지만 가능합니다. <<");
		    return;
		}
		
		// 정규표현식(Regular Expression)이란?
		// ==> 특정한 규칙을 가진 문자열의 집합을 표현하기 위해 쓰이는 형식언어
		
		// == 1. 정규표현식(Regular Expression) 패턴을 작성한다. == //
		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		
		// == 2. 문자열이 주어진 정규식 패턴과 일치하는지 판별하는 객체를 생성한다. == //
		Matcher m = p.matcher(name);
		
		// == 3. 판별하도록 한다. == //
		// 주어진 정규식 패턴과 일치하면  true, 일치하지 않으면 false 를 리턴시켜준다.
		boolean b = m.matches();
		
		if(b) 
			super.saveName(name);
		
		else 
			System.out.println(">> 성명은 공백없이 한글로만 2글자 부터 5글자까지만 가능합니다. <<");
		
	}
	
	
	
	public void setJubun(String jubun) {
		
	   boolean bool = MyUtil.isCheckJubun(jubun);
	   
	   if(bool)
		   this.jubun = jubun;
		
	   else 
		   System.out.println(">> 주민번호 앞의 7자리가 올바르지 않습니다. <<");
	}
	
	public String getJubun() {
		return jubun;
	}
	
	// == 구직자의 성별을 알아오는 메소드 생성 == 
	public String getGender() {
		
		String gender = ""; 
		
		switch (jubun.substring(6)) {
			case "1":
			case "3":	
				gender = "남";
				break;
	
			case "2":
			case "4":	
				gender = "여";
				break;
		}// end of switch-------------------
		
		return gender;
		
	}// end of String getGender()-------------
		
		
	// == 구직자의 현재나이 알아오는 메소드 생성 == 
	public int getAge() {
		//  "9501151"   "0105104"
		
		// 현재나이 = 현재년도 - 태어난년도 + 1 
		
		Calendar currentDate = Calendar.getInstance(); 
		// 현재날짜와 시간을 얻어온다.
		
		int currentYear = currentDate.get(Calendar.YEAR);
		
		String sgenderNum = jubun.substring(6);  
		// "1" 또는 "2" 또는 "3" 또는 "4" 
		
		String sbirthYear = jubun.substring(0, 2); 
		// "95" 또는 "01"
		
		int nbirthYear = Integer.parseInt(sbirthYear); 
		// 95 또는 1
		
		if("1".equals(sgenderNum) || "2".equals(sgenderNum)) {
			// 구직자가 1900년대 생
			return currentYear - (1900 + nbirthYear) + 1;
		}
		else {
			// 구직자가 2000년대 생
			return currentYear - (2000 + nbirthYear) + 1;
		}
		
	}// end of int getAge()------------------- 
	
	
	@Override   // 메소드 오버라이딩(메소드 재정의)
	public String toString() {
		
		String info = "1. 아이디 : "+getId()+"\n"
				    + "2. 암호 : "+getPasswd()+"\n"
				    + "3. 성명 : "+getName()+"\n"
				    + "4. 주민번호 7자리 : "+jubun+"\n";
		
		return info;
	}

	///////////////////////////////////////////////////////
	
	
}// end of public class Gujikja extends Member----------
