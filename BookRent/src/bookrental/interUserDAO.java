package bookrental;

import java.util.*;


public interface interUserDAO {

	   // 회원가입(insert) 메소드
	   int memberRegister(userDTO user, Scanner sc);
	   
	   // 로그인처리(select) 메소드
	   userDTO login(userDTO udto, Map<String, String> paraMap);
	   
	   // 회원id가 존재하는지 체크하는 메소드
	   boolean checkUserid(String userid); 
	   
       // 회원탈퇴 메소드
       int leaveMember(Map<String, String> paraMap);
	   
	   
	}