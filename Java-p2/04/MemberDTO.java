package jdbc.day04;

// === DTO(Data Transfer Object, 데이터전송(운반)객체 )
//     쉽게 말해서 DTO는 테이블의 1개 행(ROW)을 말한다.
//     어떤 테이블에 데이터를 insert 하고자 할때 DTO에 담아서 보낸다.
//     또한 어떤 테이블에서 데이터를 select 하고자 할때도 DTO에 담아서 읽어온다.

public class MemberDTO {

	// attribute, property, 속성을 정의한다. 
	private int userseq;        // 회원번호
	private String userid;      // 회원아이디
	private String passwd;      // 회원암호
	private String name;        // 회원명
	private String mobile;      // 연락처
	private int point;          // 포인트
	private String registerday; // 가입일자 
	private int status;         // status 컬럼의 값이 1 이면 정상, 0 이면 탈퇴 	
	
	public int getUserseq() {
		return userseq;
	}
	
	public void setUserseq(int userseq) {
		this.userseq = userseq;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public String getRegisterday() {
		return registerday;
	}
	
	public void setRegisterday(String registerday) {
		this.registerday = registerday;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		
		return "1.회원번호 : "+ userseq +"\n"
			 + "2.회원아이디: "+ userid +"\n"
			 + "3.회원명 : "+ name +"\n"
			 + "4.연락처 :  "+ mobile +"\n"
			 + "5.포인트 : "+ point +"\n"
			 + "6.가입일자 : "+ registerday +"\n";
			 
	}
	
}
