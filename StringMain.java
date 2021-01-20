package my.day10.d.string;

import java.text.DecimalFormat;

import my.util.MyUtil;

public class StringMain {

	public static void main(String[] args) {
		
		// === 1. "문자열".charAt(int index) ===
		//		  "안녕하세요".charAt(2)==> '하'
		// index => 01234
		
		char ch = "안녕하세요".charAt(2);
		System.out.println("ch => " + ch);
		// ch => 하
		
		String str = "안녕하세요";
		//	index => 01234
		String result="";
		
		for(int i=str.length()-1;i>=0;i--) {
			result+=str.charAt(i);
		}
		
		System.out.println(result);
		//요세하녕안
		
		// === 2. "문자열".toCharArray() ===
		// "안녕하세요".toCharArray() => char 타입의 배열로 만들어준다.
		// -----------------------
		// |'안'|'녕'|'하'|'세'|'요'|
		// -----------------------
		// 	 0	  1	  2	  3    4	<=index
		
		char[] chArr = "안녕하세요".toCharArray();
		result="";
		
		for(int i=chArr.length-1;i>=0;i--) {
			result+=chArr[i];
		}
		
		System.out.println(result);
		
		// === 3."문자열".substring(int 시작인덱스, int 끝인덱스) ===
		// "안녕하세요".substring(1, 4); ==> 1번 인덱스인 "녕"부터 4번 인덱스 앞에까지인 "세"까지만 뽑아온다.
		// 01234

		str="안녕하세요".substring(1, 4);
		System.out.println(str);
		// 녕하세
		
		int len="안녕하세요".length(); //len=>5
		str="안녕하세요".substring(2, len);
		System.out.println(str);
		// 하세요
		
		// === 4."문자열".substring(int 시작인덱스) ===
		// "안녕하세요".substring(2); ==> 2번 인덱스인 "하"부터 끝까지 뽑아온다.
		// 01234
		str="안녕하세요".substring(2);
		System.out.println(str);
		
		// === 5. "문자열".indexOf("찾을문자열") ===
		//		  "문자열"에서 최초로 나오는 "찾을문자열"의 인덱스값(int)를 알려준다.
		int index="시작하라 안녕하세요 건강하세요".indexOf("하");
				// 012(최초로 "하"가 나온 인덱스값)
		System.out.println(index); //2
		
		index="시작하라 안녕하세요 건강하세요".indexOf("하세요");
			 // 01234567
		System.out.println(index); //7
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~\n");
		
		// ==== [퀴즈] ==== //
				
		String[] pathFileNameArr = {"C:/mydocument/resume/나의이력서.hwp",
								 "D:/mymusic.mp3",
								 "C:/photo/내얼굴.jpg"};
		
		for(int i=0;i<pathFileNameArr.length;i++) {
			System.out.println(pathFileNameArr[i]);
		}
		/*
			C:/mydocument/resume/나의이력서.hwp
			D:/mymusic.mp3
			C:/photo/내얼굴.jpg
		 */
		
		System.out.println("\n=== 파일명만 뽑아온 결과물 ===\n");
		for(int i=0;i<pathFileNameArr.length;i++) {
			
			String pathFileName=pathFileNameArr[i];
			
			char[] chrArr=pathFileName.toCharArray();
			
			String strReverse="";
			for(int j=chrArr.length-1;j>=0;j--) {
				strReverse+=chrArr[j];
			}
			// System.out.println(strReverse);
			
			// 최초로 /가 나오는 인덱스값
			int idxSlash=strReverse.indexOf("/");
			strReverse=strReverse.substring(0,idxSlash);
			
			//System.out.println(strReverse);
			
			chrArr=strReverse.toCharArray();
			
			String fileName="";
			for(int j=chrArr.length-1;j>=0;j--) {
				fileName+=chrArr[j];
			}
			
			System.out.println(fileName);
			
			/*
			나의이력서.hwp
			mymusic.mp3
			내얼굴.jpg
			*/
			
		}// end of for--------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		// === 6. "문자열".lastindexOf("찾을문자열") ===
		//		  "문자열"에서 마지막으로 나오는 "찾을문자열"의 인덱스값(int)를 알려준다.
			index="시작하라 안녕하세요 건강하세요".lastIndexOf("하");
					// 012(마지막으로 "하"가 나온 인덱스값)
			System.out.println(index); //13
			
			index="시작하라 안녕하세요 건강하세요".lastIndexOf("하세요");
				 // 01234567
			System.out.println(index); //13
	
			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~\n");
			
		//=== [퀴즈] ===//
		System.out.println("\n=== 파일명만 뽑아온 결과물 ===\n");
		
		for(int i=0;i<pathFileNameArr.length;i++) {
			
			int idx= pathFileNameArr[i].lastIndexOf("/");// 마지막으로 나오는 / 의 인덱스 값
			
			System.out.println(pathFileNameArr[i].substring(idx+1));
		}

		System.out.println("\n~~~~~~~~~~~~~~~~~~~\n");
		
		// === 퀴즈 === //

		String money="2,000,000";
		
		int commaIndex=money.indexOf(",");
		
		System.out.println(commaIndex); //1
		
		System.out.println(money.substring(0,commaIndex)); //2
		
		System.out.println(money.substring(0,commaIndex+1)); // "000,000"

		money=money.substring(0,commaIndex)+money.substring(commaIndex+1);
		//2+"000,000"
		
		System.out.println(money); // "2000,000"
		
		commaIndex=money.indexOf(","); //4
		
		money=money.substring(0, commaIndex) + money.substring(commaIndex+1);
		// "2000"+"000"
		
		System.out.println(money);
		// "2000000"
		
		commaIndex=money.indexOf(","); // -1
		// 어떤 문자열에서 찾고자 하는 문자가 없으면 -1을 리턴시켜준다.
		
		System.out.println(commaIndex); // -1
			
		int sum=MyUtil.delComma("2,000,000")+MyUtil.delComma("5,000");
		// 1000000+5000;
		// 1005000
		
		DecimalFormat df= new DecimalFormat("#,###");
		System.out.println(df.format(sum));
		// "2,005,000"
		
		// === 7. "문자열.split("구분자") ===
		//		  "문자열"을 "구분자"로 잘라서 String 타입의 배열로 돌려주는 것이다.
		System.out.println("\n 1. ==================== \n");
		
		String food = "파스타,국밥,볶음밥,고구마,계란말이";
		
		String[] foodArr=food.split(",");
		// {"파스타,국밥,볶음밥,고구마,계란말이"}
		
		for(int i=0;i<foodArr.length;i++) {
			System.out.println(foodArr[i]);
		}
		/*
			파스타
			국밥
			볶음밥	
			고구마
			계란말이
		 */
		
		System.out.println("\n 2. ==================== \n");
		
		food = "파스타	국밥	볶음밥	고구마	계란말이";
		
		foodArr=food.split("\t");
		// {"파스타,국밥,볶음밥,고구마,계란말이"}
		
		for(int i=0;i<foodArr.length;i++) {
			System.out.println(foodArr[i]);
		}
		/*
			파스타
			국밥
			볶음밥	
			고구마
			계란말이
		 */		
		
		System.out.println("\n 3. ==== split 사용시 구분자로 . | / 등 특수문자를 사용하려고 하는 경우 ==== \n");
		
		food = "파스타.국밥.볶음밥.고구마.계란말이";
		
		foodArr=food.split(".");
		System.out.println("foodArr.length => "+foodArr.length);
		// foodArr.length => 0
		// ==> 0 이 나옴. 즉 . 단독만으로는 구분자로 인식을 못함.
	    // split 사용시 구분자로 . | / 등 특수문자를 사용하려고 할 경우에는 구분자로 인식을 못할 경우가 많으므로
	    // 구분자 앞에 \\ 를 붙이거나 구분자를 [] 로 씌워주면 된다. 즉, [구분자] 이렇게 말이다.
		
		foodArr=food.split("\\.");
	 // 또는 foodArr=food.split("[.]");
		// {"파스타,국밥,볶음밥,고구마,계란말이"}
		
		for(int i=0;i<foodArr.length;i++) {
			System.out.println(foodArr[i]);
		}
		/*
			파스타
			국밥
			볶음밥	
			고구마
			계란말이
		 */		
		
		System.out.println("\n 4. ==================== \n");
		
		food = "파스타|국밥|볶음밥|고구마|계란말이";
		
		foodArr=food.split("[|]");
		// {"파스타,국밥,볶음밥,고구마,계란말이"}
		
		for(int i=0;i<foodArr.length;i++) {
			System.out.println(foodArr[i]);
		}
		/*
			파스타
			국밥
			볶음밥	
			고구마
			계란말이
		 */
			
		System.out.println("\n 5. ==================== \n");
		
		food = "파스타,국밥,볶음밥,고구마,계란말이";
		
		foodArr=food.split("[,]");
	// 또는 foodArr=food.split("\\,");
		// {"파스타,국밥,볶음밥,고구마,계란말이"}
		
		for(int i=0;i<foodArr.length;i++) {
			System.out.println(foodArr[i]);
		}
		/*
			파스타
			국밥
			볶음밥	
			고구마
			계란말이
		 */
		
		System.out.println("\n 6. ==================== \n");
		
		food = "파스타,국밥.볶음밥	고구마,계란말이";
		
	//	foodArr=food.split("\\,|\\.|\\t");
		foodArr=food.split("[,.\t]");
		// {"파스타,국밥,볶음밥,고구마,계란말이"}
		
		for(int i=0;i<foodArr.length;i++) {
			System.out.println(foodArr[i]);
		}
		/*
			파스타
			국밥
			볶음밥	
			고구마
			계란말이
		 */
		
		System.out.println("\n 7. ==================== \n");
		
		food = "파스타1국밥2볶음밥3고구마4계란말이";
		
		foodArr=food.split("\\d"); // \\d에서 d는 정규표현식의 하나로써 숫자를 의미한다.
								   // 즉, 숫자가 구분자가 되는 것이다.
		// {"파스타,국밥,볶음밥,고구마,계란말이"}
		
		for(int i=0;i<foodArr.length;i++) {
			System.out.println(foodArr[i]);
		}
		/*
			파스타
			국밥
			볶음밥	
			고구마
			계란말이
		 */
	
		System.out.println("\n 8. ==================== \n");
		
		food = "파스타1국밥2볶음밥3고구마4계란말이";
		
		foodArr=food.split("\\D"); // \\D에서 D는 정규표현식의 하나로써 숫자가 아닌것을 의미한다.
								   // 즉, 숫자가 아닌것이 구분자가 되는 것이다.
		// {"1,2,3,4"}
		
		for(int i=0;i<foodArr.length;i++) {
			System.out.println(foodArr[i]);
		}
		/*
			1
			
			2
			
			
			3
			
			
			4
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		// == 참고 : \를 escape 문자라고 부른다. ==
		System.out.println("나의 이름은 \"이순신\" 입니다.");
		// 나의 이름은 "이순신" 입니다.
		
		System.out.println("C:\\movie\\koreamovie\\경이로운소문.mp4");
		// C:\movie\koreamovie\경이로운소문.mp4
		
		System.out.println("나의 이름은 '엄정화' 입니다.");
		// 나의 이름은 '엄정화' 입니다.
		
		
		// === 8. String.join("구분자", 문자열배열명) ===
		// "	    문자열 배열을 "구분자"로 합쳐서 String 타입으로 돌려주는 것이다.
		String[] nameArr = {"한석규","두석규","세석규","네석규","오석규"};
		String names=String.join("-",nameArr);
		System.out.println(names);
		// "한석규-두석규-세석규-네석규-오석규"
		
		// === 9. "문자열".replaceAll("변경대상문자열","새로이변경될문자열") ===
		// 		  "문자열"에서 "변경대상문자열"을 모두 "새로이변경될문자열"로 교체해서 변경한다.
		names = names.replaceAll("석규", "SK");
		System.out.println(names);
		// "한SK-두SK-세SK-네SK-오SK"
		
		// === 10. "문자열".replaceFirst("변경대상문자열","새로이변경될문자열") ===
		// 		  "문자열"에서 "변경대상문자열"을 첫번째만 "새로이변경될문자열"로
		names = names.replaceFirst("SK","석규");
		System.out.println(names);
		// "한SK-두SK-세SK-네SK-오SK"		
		
		// === 퀴즈 === //
		names="한SK-두SK-세SK-네SK-오SK";
		
		for(int i=0;i<3;i++) {
			names = names.replaceFirst("SK","석규");
			// "한석규-두SK-세SK-네SK-오SK"
			// "한석규-두석규-세SK-네SK-오SK"
			// "한석규-두석규-세석규-네SK-오SK"
		}
		
		System.out.println(names);
		// "한석규-두석규-세석규-네SK-오SK"
		
		
		names="한SK-두SK-세SK-네SK-오SK";
	
		for(int i=0;i<3;i++) {
			names = names.replaceFirst("SK","석규");
		}
			names = names.replaceFirst("석규","SK");
		
		
		System.out.println(names);
		// "한SK-두석규-세석규-네SK-오SK"
		
		System.out.println("\n ===== 퀴즈 ===== \n");
		
		String[] contents = {"호호안녕하세요","건강하세요","행복하세요 또봐요","즐겁고 건강한 하루되세요"}; 
	       
	    // "건강" 이라는 단어가 포함된것을 출력하세요.
		
		for(int i=0; i<contents.length; i++) {
	          int idx = contents[i].indexOf("건강");
	          if(idx != -1)
	             System.out.println(contents[i]);
	       }
		
		/*
			"건강하세요"
		 	"즐겁고 건강한 하루되세요"
		 */
		System.out.println("===========================");
		
		// "건강" 이라는 단어로 시작하는 것만 출력하세요.
		for(int i=0; i<contents.length; i++) {
	          int idx = contents[i].indexOf("건강");
	          if(idx == 0)
	             System.out.println(contents[i]);
	       }
		
		// 또는 아래와 같이 할수도 있습니다.
	    // === 11. "문자열".startsWith("찾고자하는문자열")  ===
	    //         "문자열" 에서 "찾고자하는문자열"이 맨첫번째에 나오면 true 를 반환.
       	//         "문자열" 에서 "찾고자하는문자열"이 맨첫번째에 나오지 않으면 false 를 반환.
       
        // "건강" 이라는 단어로 시작하는 것만 출력하세요.
		
		for(int i=0; i<contents.length; i++) {
	          if(contents[i].startsWith("건강"))
	             System.out.println(contents[i]);
	       }
		// "건강하세요"
		
		System.out.println("\n =========================== \n");
		
	    // === 12. "문자열".endsWith("찾고자하는문자열")  ===
	    //         "문자열" 에서 "찾고자하는문자열"로 끝나면 true 를 반환.
       	//         "문자열" 에서 "찾고자하는문자열"이 끝나지 않으면 false 를 반환.
       
        // "하세요" 라는 단어로 끝나는 것만 출력하세요.
		
		for(int i=0; i<contents.length; i++) {
	          if(contents[i].endsWith("하세요"))
	             System.out.println(contents[i]);
	       }
		
		// === 13. "문자열".trim() ===
		//		   "문자열".trim()은 "문자열"의 좌,우에 공백이 있으면 공백을 모두 제거한다.
		String insa = "			수고		많으셨습니다 			";
		System.out.println("하하하"+insa+"내일 뵐게요~~");
		//하하하			수고		많으셨습니다 			내일 뵐게요~~
		
		System.out.println("하하하"+insa.trim()+"내일 뵐게요~~");
		//하하하수고		많으셨습니다내일 뵐게요~~
		
		// === 14. "문자열".isEmpty() ===
		// 		   "문자열"이 아무것도 없으면 true를 반환해주고,
		//		   "문자열"이 뭔가 있으면 false를 반환해준다.
		String str1="", str2="abc", str3="		";
		
		System.out.println(str.isEmpty()); 	//true
		System.out.println(str2.isEmpty());	//false
		System.out.println(str3.isEmpty());	//false
		System.out.println(str3.trim().isEmpty());	//true
		//					"".isEmpty();
		
		// === 15. "문자열".toUpperCase() ===
		// 		   "문자열"에서 소문자가 있으면 모두 대문자로 변경해서 반환.
		String words = "My Name is Tom 입니다.";
		System.out.println(words.toUpperCase());
		// MY NAME IS TOM 입니다.
		
		// === 16. "문자열".toLowerCase() ===
		// 		   "문자열"에서 소문자가 있으면 모두 대문자로 변경해서 반환.
		words = "My Name is Tom 입니다.";
		System.out.println(words.toLowerCase());
		// my name is tom 입니다.
		
		// === 17. "문자열".equals("비교대상문자열") ===
		//		       대문자와 소문자를 구분하면서 
		// 		   "문자열"과 "비교대상문자열"의 값이 일치하면 true를 반환.
		//		   "문자열"과 "비교대상문자열"의 값이 일치하지 않으면 false를 반환.
		
		// === 18. "문자열".equalsIgnoreCase("비교대상문자열") ===
		//		       대문자와 소문자를 구분하지 않으면서
		// 		   "문자열"과 "비교대상문자열"의 값이 일치하면 true를 반환.
		//		   "문자열"과 "비교대상문자열"의 값이 일치하지 않으면 false를 반환.	
		
		String[] strArr = {"korea"," seoul "," Korea 대한민국", "서울 kOrEA 만세",null};
		
		// 검색어를 "korea"
		for(String s : strArr) {
			if("korea".equals(s)) {
				System.out.println(s);
			}
		}// end of for--------------------------------
		//korea
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		// 검색어를 "korea","KOREA","kOReA"와 같이 3개 중에 아무거나 입력
		// "korea"," korea seoul ","Korea 대한민국", "서울 kOrEA 만세"
		
		String search = "koReA"; // "KOREA" "korea"
		
		for(String s:strArr) {
			search=search.toLowerCase(); 
			// "kOReA" ==> "korea"
			
			if(s!=null&&s.toLowerCase().indexOf(search)!=-1){
				// s.toLowerCase()은 "korea"," seoul "," korea seoul ","korea 대한민국", "서울 korea 만세" 으로 되어진 상태이다.
				System.out.println(s);
			}
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
	}
}