package jdbc.day03;

import java.util.Map;

public interface InterMemberDAO {
	
	// 회원가입 메소드
	int memberRegister(MemberDTO member);

	// 로그인처리 메소드
	MemberDTO login(Map<String, String> paraMap);
	
}
