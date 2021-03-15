package bookrental;

import java.util.*;

public interface interLibrarianDAO {

	// 사서 회원가입
	int librarianRegister(librarianDTO ldto, Scanner sc);

	// 로그인
	librarianDTO login(librarianDTO ldto, Map<String, String> paraMap);

}
