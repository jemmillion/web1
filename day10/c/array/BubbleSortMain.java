package my.day10.c.array;

import java.util.Arrays;

public class BubbleSortMain {
	
	/*
	   == 입사문제 ==
	   >> 버블 정렬(Bubble Sort)은 
	    정렬 대상 리스트(배열)의 항목을 수평방향으로 나열했다고 가정했을 때, 
	    왼쪽 끝에서부터 시작해서 인접하는 두 항목의 값을 비교하여 
	    올바른 순서(오름차순 또는 내림차순)로 되어있지 않으면 서로 위치를 교환하는 정렬방법이다.
	    
	    이렇게 인접하는 항목의 값을 비교해서 위치를 교환하는 과정을 
	    리스트(배열)의 마지막 항목까지 반복해서 제일 큰(또는 작은) 값이 끝에 오도록 한다.
	    
	    각 회전(Pass)과정이 끝날때마다 정렬은 뒤에서부터 하나씩 완료된다. 
	*/
	
	public static void main(String[] args) {

		int[] numArr= {9,7,3,5,1};
	/*
	 	numArr[0]=>9
	 	numArr[1]=>7
	 	numArr[2]=>3
	 	numArr[3]=>5
	 	numArr[4]=>1
	 */
		System.out.println("=== 정렬하기 전 ===");
		for(int i=0;i<numArr.length;i++) {
			String str = (i<numArr.length-1)?",":"\n";
			System.out.print(numArr[i]+str);
		}// end of for--------------------------------------------
		/*
		=== 정렬하기 전 ===
		9,7,3,5,1
				
				-----------
		데이터값=>	|9|7|3|5|1|
				-----------
		index=>  0 1 2 3 4
		*/
		
		// === 오름차순 정렬하기 === //
		
		for(int i=0;i<numArr.length-1;i++) {
			// 비교대상을 추출할 개수==> 9 7 3 5 은 몇개? 4개
		// (int i=0;i<4;i++)
			for(int j=0;j<numArr.length-1-i;j++) { // i=0; ==> 조건식이 j<4; 되어야만 4번 비교함.
												 // i=1; ==> 조건식이 j<3; 되어야만 3번 비교함.
											 	 // i=2; ==> 조건식이 j<2; 되어야만 2번 비교함.
												 // i=3; ==> 조건식이 j<1; 되어야만 1번 비교함.
				if(numArr[j]>numArr[j+1]) {
				///numArr[0]>numArr[j+1]
				//	  9          7
					
				/*
				    int temp=numArr[0];		//9;
				    numArr[0]=numArr[1];	//numArr[0]<=7
				    numArr[1]=temp;			//numArr[1]<=9
				   
				   	int temp=numArr[1];
				   	numArr[1]=numArr[2];
				   	numArr[2]=temp;
				   	
				   	int temp=numArr[2];
				   	numArr[2]=numArr[3];
				   	numArr[3]=temp;
				 */
					
					int temp=numArr[j];    
					numArr[j]=numArr[j+1]; 
					numArr[j+1]=temp;	   
						
				}
			}// end of for---------------------------------------
		}// end of for---------------------------------------
		
		System.out.println("=== 오름차순 정렬한 후 ===");
		for(int i=0;i<numArr.length;i++) {
			String str = (i<numArr.length-1)?",":"\n";
			System.out.print(numArr[i]+str);
		}// end of for--------------------------------------------


		// === 내림차순 정렬하기 === //
		int[] numArr2= {9,7,3,5,1};
		
		for(int i=0;i<numArr2.length-1;i++) {
			
			for(int j=0;j<numArr2.length-1-i;j++) {
				
				if(numArr2[j]<numArr2[j+1]) {
					
					int temp=numArr2[j];    
					numArr2[j]=numArr2[j+1]; 
					numArr2[j+1]=temp;	
					
				}
			}
			System.out.println("=== 내림차순 정렬한 후 ===");
			for(i=0;i<numArr2.length;i++) {
				String str = (i<numArr2.length-1)?",":"\n";
				System.out.print(numArr2[i]+str);
			}
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		int[] numArr3= {9,7,3,5,1};
		
		System.out.println("=== 정렬하기 전 ===");
		for(int i=0;i<numArr3.length;i++) {
			String str = (i<numArr3.length-1)?",":"\n";
			System.out.print(numArr3[i]+str);
		}

		Arrays.sort(numArr3);  // 오름차순 정렬
		
		System.out.println("=== Arrays.sort를 사용하여 정렬한 후 ===");
		for(int i=0;i<numArr3.length;i++) {
			String str = (i<numArr3.length-1)?",":"\n";
			System.out.print(numArr3[i]+str);
		}
		
		
	} // end of main()--------------------------------------------

}
