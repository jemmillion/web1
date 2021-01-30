package my.day12.b.inheritance;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.util.MyUtil;

public class Member {

	// Gujikja 클래스와 Company 클래스가 공통으로 가져야 하는 field 생성 
	private String id;      // 아이디(5글자 이상 10글자 이하까지만 사용가능함)
	private String passwd;  // 암호 
	private String name;    // 이름(회사명, 개인성명)
	
	
	// Gujikja 클래스와 Company 클래스가 공통으로 가져야 하는 method 생성
//	public String getId() 
	
	protected String getId(){
		return id;
	}
	
	protected void setId(String id) {
		
		if(id != null && !id.trim().isEmpty() &&
		   5 <= id.length() && id.length() <= 10 )
		   this.id = id;
		else
		   System.out.println(">> 아이디는 공백만으로는 불가합니다. \n"
		   		            + " 또한 5글자 이상 10글자 이하까지만 가능합니다.<<");
		
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {

		boolean bool = MyUtil.isCheckPasswd(passwd);
		
		if(bool) 
			this.passwd = passwd;
		
		else 
			System.out.println(">> 암호는 8글자 이상 15글자 이하의 영대,소문자 및 숫자, 특수문자가 혼합되어야 합니다. <<");
	}
	
	
	public String getName() {
		return name;
	}
	
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
			this.name = name;
		
		else 
			System.out.println(">> 성명은 공백없이 한글로만 2글자 부터 5글자까지만 가능합니다. <<");
		
	}
	
	///////////////////////////////////////////
	public void setCompanName(String name) {
		this.name = name;
	}
	
	
	
}
