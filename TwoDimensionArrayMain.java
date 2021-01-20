package my.day10.c.array;

public class TwoDimensionArrayMain {

	public static void main(String[] args) {

		// === 1차원 배열 ===
		int[] subjectArr = new int[5]; // 1행 5열
		
		/*
			---------------------
index=>		| 0 | 1 | 2 | 3 | 4 |   1행 5열
			--------------------- 
		 */

		// === 2차원 배열 ===
		int[][] pointArr = new int[4][3]; // 4행 3열
				
		/*
			----------------------------
index=>		| [0][0] | [0][1] | [0][2] |
			----------------------------
			| [1][0] | [1][1] | [1][2] |   4행 3열
			----------------------------
			| [2][0] | [2][1] | [2][2] |
			----------------------------
			| [3][0] | [3][1] | [3][2] |
			----------------------------
			
		 */
		pointArr[0][0]=10;
		pointArr[0][1]=20;
		pointArr[0][2]=30;
		
		pointArr[1][0]=40;
		pointArr[1][1]=50;
		pointArr[1][2]=60;
		
		pointArr[2][0]=70;
		pointArr[2][1]=80;
		pointArr[2][2]=90;
	/*	
		pointArr[3][0]=100;
		pointArr[3][1]=110;
		pointArr[3][2]=120;
	*/	
		
		System.out.println("pointArr.length => "+pointArr.length);
		// pointArr.length => 4
		// 이차원배열명.length => 행의 길이가 나온다.
		
		System.out.println("pointArr[0].length => "+pointArr[0].length);
		// pointArr[0].length => 3
		// 이차원배열명[행의인덱스].length => 그 행에 존재하는 열의  길이가 나온다.
		
		System.out.println("pointArr[1].length => "+pointArr[1].length);
		// pointArr[1].length => 3
		// 이차원배열명[행의인덱스].length => 그 행에 존재하는 열의  길이가 나온다.
		
		System.out.println("pointArr[2].length => "+pointArr[2].length);
		// pointArr[2].length => 3
		// 이차원배열명[행의인덱스].length => 그 행에 존재하는 열의  길이가 나온다.
		
		System.out.println("pointArr[3].length => "+pointArr[3].length);
		// pointArr[3].length => 3
		// 이차원배열명[행의인덱스].length => 그 행에 존재하는 열의  길이가 나온다.
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for(int i=0;i<pointArr.length;i++){// 행
			
			for(int j=0;j<pointArr[i].length;j++) {// 열
				String str=(j<pointArr[i].length-1)?",":"";
				System.out.printf("%02d%s",pointArr[i][j],str);
			}// end of for-----------------------------------------------
			System.out.print("\n");
		}// end of for---------------------------------------------------
		
		System.out.println("\n==========성적 결과===========\n");
		
		 				  //국어 영어 수학
		int[][] jumsuArr = {{90,80,70},	//이순신
							{80,85,76},	//엄정화
							{85,70,90},	//서강준
							{60,80,50}};	//이혜리 
		//4행 3열(학생 4명, 과목 3개)
		
		int[] totalArr=new int[jumsuArr.length]; 
		// 각 학생들의 총점을 저장시켜두는 곳이다.
		
		int[] subjectTotalArr=new int[3]; 
		// 각 과목별 총점을 저장시켜두는 곳이다.
		// subjectTotalArr[0]=0	국어총점 90+80+85+60
		// subjectTotalArr[1]=0	영어총점 80+85+70+80
		// subjectTotalArr[2]=0	수학총점 70+76+90+50
		
		
		// 1. 각 학생별로 총점을 나타내어 보자.
		for(int i=0;i<jumsuArr.length;i++) {
			
			int sum=0;
			for(int j=0;j<jumsuArr[i].length;j++) {
				sum+=jumsuArr[i][j];
			}// end of for---------------------------
			
			totalArr[i]=sum;
		}
		
		for(int total : totalArr) {
			System.out.println(total);
		}
		/*
		    240
			241
			245
			190
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		// 2. 각 과목별로 총점을 나타내어 보자.
		
		for(int i=0;i<jumsuArr[0].length;i++) {
			int sum=0;
			for(int j=0;j<jumsuArr.length;j++) {
				sum+=jumsuArr[j][i];
			}
			subjectTotalArr[i]=sum;
		}	
			
		for(int total : subjectTotalArr) {
			System.out.println(total);
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.println("=== 각 과목별 평균 ===");
		
		System.out.println("----------------------");
		System.out.println("국어	영어	수학");
		System.out.println("----------------------");
		
		for(int i=0;i<jumsuArr[0].length;i++) {
			int sum=0;
			for(int j=0;j<jumsuArr.length;j++) {
				sum+=jumsuArr[j][i];
			}
			subjectTotalArr[i]=sum;
		}	
			
		String result="";
		for(int total : subjectTotalArr) {
			double avg=Math.round(((double)total/jumsuArr.length)*10)/10.0;
			result+=avg+"\t";	
		}
		System.out.println(result);
	
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
	
		int[][] numArr = new int[4][]; // 4행 3열
					
		/*
		numArr[0][0]=10;  // 열의 크기를 설정하지 않았으므로 NullPointerException이 발생한다.
		numArr[0][1]=20;
		numArr[0][2]=30;
		*/
		numArr[0]=new int[3];
		numArr[1]=new int[2];
		numArr[2]=new int[4];
		numArr[3]=new int[3];
		
		numArr[0][0]=10;
		numArr[0][1]=20;
		numArr[0][2]=30;
		
		numArr[1][0]=40;
		numArr[1][1]=50;
		//numArr[1][2]=60;	// ArrayIndexOutOfBoundsException 발생함.
		
		numArr[2][0]=70;
		numArr[2][2]=90;	
		
		for(int i=0;i<numArr.length;i++) {// 행
			
			for(int j=0;j<numArr[i].length;j++) {// 열
				String str=(j<numArr[i].length-1)?",":"";
				System.out.printf("%2d%s",numArr[i][j],str);
			}
			
			System.out.print("\n");
			
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		int[][] numArr2 = {{10,20,30},
						   {40,50},
						   {70,0,90,0},
						   {0,0,0}};
		
		for(int i=0;i<numArr2.length;i++) {// 행
			
			for(int j=0;j<numArr2[i].length;j++) {// 열
				String str=(j<numArr2[i].length-1)?",":"";
				System.out.printf("%2d%s",numArr2[i][j],str);
			}
			
			System.out.print("\n");
			
		}
		
	}// end of main()----------------------------------------------------

}
