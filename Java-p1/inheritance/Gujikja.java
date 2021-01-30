package my.day12.b.inheritance;

import java.util.Calendar;

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
	
	static int count;  // Gujikja 객체(인스턴스)의 개수를 알아오려는 용도
	
	
	// method 
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
	
	
	// (userid, passwd, name, jubun) field 의  값이 모두 null 아니라면 
	// true 를 리턴해주고, 적어도 1개 이상이 null 이라면 false 를 리턴해주는
	// 메소드를 생성한다.
	public boolean isUseGujikja() {
		
		if(super.getId() != null &&
		   this.getPasswd() != null &&
		   getName() != null &&
		   jubun != null) 
			return true;
		
		else 
			return false;
	
	}// end of public boolean isUseGujikja()------------------
	
	
	public void viewInfo() {
		System.out.printf("%-10s\t%-15s\t%-8s\t%3d\t%-2s\n",getId(),getPasswd(),getName(),getAge(),getGender());
	}// end of public void viewInfo()-------------------
	
	@Override // 메소드의 오버라이딩(메소드 재정의)
	public String toString(){
		
		String info="1. 아이디 : "+getId()+"\n"
				+"2. 암호 : "+getPasswd()+"\n"
				+"3. 성명 : "+getName()+"\n"
				+"4. 주민번호 7자리 : "+jubun+"\n";
		
		return info;
	}
	
} // end of public class Gujikja-----------------





