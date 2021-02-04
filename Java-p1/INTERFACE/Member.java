package my.day15.c.INTERFACE;

import my.util.MyUtil;

public abstract class Member implements InterMember {
// 	Member 클래스에서는 InterMember 인터페이스의 미완성메소드(추상메소드)를
//  재정의(메소드 오버라이딩)를 하지 않겠다는 말이므로 
//  Member 클래스는 미완성 클래스(==추상 클래스)가 되어야 한다.
	

	// Gujikja 클래스와 Company 클래스가 공통으로 가져야 하는 field 생성 
	private String id;      // 아이디(5글자 이상 10글자 이하까지만 사용가능함)
	private String passwd;  // 암호 
	private String name;    // 이름(회사명, 개인성명)
		
	static int count;  // 자식클래스인 Gujikja 객체(인스턴스) 
		               // 또는 자식클래스인 Company 객체(인스턴스) 의 개수를 알아오려는 용도
		
	// Gujikja 클래스와 Company 클래스가 공통으로 가져야 하는 method 생성
//		public String getId() {
		protected String getId() {	
			return id;
		}
		
//		public void setId(String id) {
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
		
		
		public void saveName(String name) {
			this.name = name;
		}
		
}
