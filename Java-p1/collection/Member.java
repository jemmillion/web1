package my.day17.a.collection;

public class Member {

	
	String id;
	String passwd;
	String name;
	
	public Member() {}
	
	public Member(String id,String passwd,String name) {
		this.id=id;
		this.passwd=passwd;
		this.name=name;
	}

	public void infoPrint() {
		System.out.println("== "+name+"님의 회원정보 ==\n"
				+ "1. 아이디 : "+id+"\n"
				+ "2. 암호 : "+passwd+"\n"
				+ "3. 성명 : "+name+"\n");
	}

}

