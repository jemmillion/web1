package my.day15.c.INTERFACE;

import java.util.Scanner;

public interface InterMemberCtrl {

	// 구직자 중복아이디 검사
	boolean duplicateIdGujikja(String id, Member[] mbrArr);
	// 아이디가 이미 존재한다라면 true를 리턴.
	// 아이디가 존재하지 않는다라면 false를 리턴.
	
	// 구인회사 중복아이디 검사
	boolean duplicateIdCompany(String id, Member[] mbrArr);
	
	// 회원가입(구직자, 구인회사)
	Member register(Scanner sc, Member[] mbrArr, int n);
	// int n은 n값이 1이면 구직자로 회원가입, n값이 2이면 구인회사로 회원가입.
	
	// 로그인
	Member login(Scanner sc, Member[] mbrArr, int n);
	// int n은 n값이 1이면 구직자로 로그인, n값이 2이면 구인회사로 로그인.
	
	// 모든 회원정보 출력(구직자, 구인회사)
	void viewInfoAll(Member[] mbArr, int n);
	// int n은 n값이 1이면 구직자, n값이 2이면 구인회사.
	
	// 특정 회원 한 명의 회원정보 출력(구직자, 구인회사)
	String showInfo(Member mbr);
	
	// 회원(구직자, 구인회사)으로 사용가능 한지 아닌지를 알아보는 것
	boolean isUse(Member mbr);
	
	// 회원정보 변경(구직자, 구인회사)
	Member updateMemberInfo(Scanner sc,Member mbr);


	
}
