package bookrental;

public class userDTO {

   private int userseq;      // 회원번호
   private String userid;       // 회원아이디
   private String passwd;       // 회원암호
   private String username;       // 회원명
   private String mobile;       // 연락처
   private int status;       // status 컬럼의 값이 1 이면 정상, 0 이면 탈퇴
   
   
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
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }
   public String getMobile() {
      return mobile;
   }
   public void setMobile(String mobile) {
      this.mobile = mobile;
   }
   public int getStatus() {
      return status;
   }
   public void setStatus(int status) {
      this.status = status;
   }
   
   
   
   
}