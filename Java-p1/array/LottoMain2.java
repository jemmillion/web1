package my.day09.b.array;

import java.util.Random;

public class LottoMain2 {

	public static void main(String[] args) {
		
	/*
	 	int ball ==>   1,   2,   3,   4,   5,   6,   7, ....  43,  44,  45
	 	         ==> 101, 102, 103, 104, 105, 106, 107, .... 143, 144, 145
	 	int[] ballArr = new int[45];         
	 	---------------------------------------------------
	 	| 0 | 1 | 2 | 3 | 4 | 5 | 6 | .... | 42 | 43 | 44 |
	 	---------------------------------------------------         
	 */

		int[] ballArr = new int[45];  
		int[] tempArr = new int[5];
		// 기존에 뽑았던 방번호를 기억시켜두는 저장소
		
		/*
			tempArr
			--------------------------
	값 ==>	| -1 | -1 | -1 | -1 | -1 |
			--------------------------
		 */
		
		for(int i=0; i<ballArr.length; i++) {
			ballArr[i] = i+1;
			// 배열의 방마다 데이터값 입력하기
			// 데이터값은 101~145
		}
		
		for(int i=0; i<tempArr.length; i++) {
			tempArr[i] = -1;
		}
		/*
			공을 꺼내어 오면 공의 방번호(index번호)를 기억하는 곳으로 사용되는데
			꺼내온 공의 방번호(index번호)가 0(실제값은 1번볼)이 들어올 수 있으므로
			데이터값은 방번호(index번호)로 사용하지 않는 -1로 모두 (5개) 초기화한다. 
		 */
		// 공의 방번호(배열의 index)를 꺼내는 작업을 6번 반복
		// 0번 방부터 44번 방까지 랜덤하게 6번을 뽑아야 한다.
		// 즉, 0~44까지 난수를 발생.
		
		Random rnd = new Random();
		
		String result ="";
		
		outer: 
		for(int i=0; i<6; i++) {
			int idx=rnd.nextInt(44-0+1)+0;
			// 9  9  12  7  34  20
			
			// int[] tempArr <== 랜덤하게 뽑은 배열의 방번호를 기억하는 저장소
			/*
						-------------------------
				index	| 0 | 1 | 2 | 3 | 4 | 5 |
						-------------------------
				    값  	| -1| -1| -1| -1| -1| -1|
				        -------------------------
				    값		| 9 | 0 | 12| 7 | 34|   |
				    	-------------------------
			 */
			
			for(int j=0; j<tempArr.length; j++) {
				if(idx==tempArr[j]) {
					// 새로 뽑은 방번호가 기존에 뽑은 것과 같다면 다시 새로 뽑아야 한다.
					// 즉, 0~44 중에  또 새로 뽑아야 한다.
					i--;
					continue outer;
				}
			}// end of for------------------------
			
			if(i<5) {
				tempArr[i]=idx; // 뽑은 방번호(index 번호)를 저장시켜 둔다.
			}
			
			String comma = (i<5)?",":"";
			result+=ballArr[idx]+comma; // 10 1 12 13 8 35 21
			
		}// end of for-----------------------------

		System.out.println("\n로또 1등 당첨번호 : "+result);
	
	}
}
