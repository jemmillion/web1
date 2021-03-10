package jdbc.day04;

import java.util.*;

public interface InterBoardDAO {

	int write(BoardDTO bdto); // 게시판 글쓰기
	
	List<BoardDTO> boardList(); // 글목록보기 
	
	BoardDTO viewContents(Map<String, String> paraMap); // 글내용보기

	void updateViewCount(String boardno); // 조회수 1증가 시키기 

	int writeComment(BoardCommentDTO cmdto, Scanner sc); // 댓글쓰기(jdbc_comment 테이블에 insert) 

	List<BoardCommentDTO> commentList(String boardno); // 원글에 대한 댓글을 가져오는 것 (특정 게시글 글번호에 대한 jdbc_comment 테이블과 jdbc_member 테이블을 JOIN 해서 보여준다)  

	int updateBoard(Map<String, String> paraMap); // 글 수정하기

	int deleteBoard(Map<String, String> paraMap); // 글 삭제하기

	Map<String, Integer> statisticsByWeek(); // 최근 1주일간에 대해 select 되어져 나온 결과물

	List<Map<String, String>> statisticsByCurrentMonth(); // 이번달 일자별 게시글 작성건수
	
	
	
	
}
