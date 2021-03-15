package bookrental;

import java.util.*;

public interface interBookDAO {

	// 일반회원 도서대여현황 조회
	List<bookDTO> checkRentalInfo(userDTO udto);

    // 도서등록
   	int bookInfoInput(bookDTO book, Scanner sc);
   
	// 대여중 도서목록
	List<bookDTO> checkOutInfo();

	// 도서 대여하기
	boolean checkRentStatus(String bookid, int no, String userid);

	// 도서 반납하기
	boolean returnBook(String bookid);
	
	// 일반회원 도서검색
	List<bookDTO> showBookInfo(String category, String booktitle, String authorName, String publisherName);

	// 개별도서정보입력   
	int registerBookid(bookDTO newbook, Scanner sc);

  	// 등록도서 삭제
	int deleteBook(Map<String, String> paraMap);
	
	// 삭제 도서 제목, 작가 확인
	bookDTO viewContents(Map<String, String> paraMap);

	// 도서가 대여중인지 확인
	boolean checkBookRent(String bookid);

	//bookisbn 이 존재하는지 체크하는 메소드
	public boolean checkBookisbn(String isbn_input);

	//현재 연체된 회원의 도서목록
	String showDelayFeeList(String answer);
	

	
}
