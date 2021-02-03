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
	// == 메소드의 재정의(method overriding 메소드 오버라이딩) == //
	// ==> InterMember 인터페이스에 정의된 추상메소드(미완성메소드)를 재정의 한 것임.
	   @Override
	   public void setName(String name) {
	      
	      if(name != null && !name.trim().isEmpty() &&
	         2<=name.length() && name.length()<=20 ) {
	         super.saveName(name);   
	      }
	      
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

	
	@Override // 메소드의 오버라이딩(메소드 재정의)
	public String toString(){
		
		String info="1. 아이디 : "+getId()+"\n"
				+"2. 암호 : "+getPasswd()+"\n"
				+"3. 성명 : "+getName()+"\n"
				+"4. 주민번호 7자리 : "+jubun+"\n";
		
		return info;
	}
	
		// 현재년도
		
		Calendar currentYear = Calendar.getInstance(); // 현재날짜와 시간을 얻어온다.
			
		int year = currentYear.get(Calendar.YEAR);
				
}
