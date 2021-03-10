package jdbc.day04;

public class BoardCommentDTO {  // BoardCommentDTO 가 오라클의 jdbc_comment(자식 테이블)에 해당함.

	private int commentno;      // 댓글번호 
	private String fk_boardno;  // 원글의 글번호 
	private String fk_userid;   // 사용자ID
	private String contents;    // 댓글내용 
	private String writeday;    // 작성일자	
	
	private MemberDTO member;   // select 용 (jdbc_member 테이블과 jdbc_comment 테이블의 JOIN). 글쓴이에 대한 모든 정보                 
                                // MemberDTO 가 오라클의 jdbc_member(부모 테이블)에 해당함.
	
	public int getCommentno() {
		return commentno;
	}
	
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	
	public String getFk_boardno() {
		return fk_boardno;
	}
	
	public void setFk_boardno(String fk_boardno) {
		this.fk_boardno = fk_boardno;
	}
	
	public String getFk_userid() {
		return fk_userid;
	}
	
	public void setFk_userid(String fk_userid) {
		this.fk_userid = fk_userid;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getWriteday() {
		return writeday;
	}
	
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	
	public MemberDTO getMember() {
		return member;
	}

	public void setMember(MemberDTO member) {
		this.member = member;
	}

	
	////////////////////////////////////////////////////
	
	public String viewInfo() {  // 댓글내용\t\t작성자\t작성일자 
		                        // contents\t\tmember.getName()\twriteday
		
		return contents+"\t\t"+member.getName()+"\t"+writeday;
	}
	
}
