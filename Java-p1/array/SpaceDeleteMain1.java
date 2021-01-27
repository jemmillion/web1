package my.day09.b.array;

public class SpaceDeleteMain1 {

	public static void main(String[] args) {

		// === 입사문제 : 문자열 중 공백 제거하기 === //
		String str = "  korea   seou l 쌍용 강북 교육센터  ";
		System.out.println(str);
		
		char[] chArr = str.toCharArray();
		/*
			-----------------------------------------------------------------------
		 	|' '|' '|'k'|'o'|'r'|'e'|'a'|' '|' '|' '|'s'|'e'|'o'|'u'|' '|'l'|' '|'쌍'|'용'|' '|'강'|'북'|' '|'교'|'육'|'센'|'터'|' '|' '|
		 	-----------------------------------------------------------------------	
		 */
		
		int len = 0;
		for(int i=0; i<chArr.length; i++) {
			if(chArr[i] != ' ') {
				len++;
			}
		}// end of for---------------------------
		
	//	System.out.println(len); // 18
		
		char[] resultchArr=new char[len];
		//--------------------------------------------------------------------------
		//|'k'|'o'|'r'|'e'|'a'|'s'|'e'|'o'|'u'|'l'|'쌍'|'용'|'강'|'북'|'교'|'육'|'센'|'터'|
		//--------------------------------------------------------------------------
		for(int i=0,j=0; i<chArr.length; i++,j++) {// chArr.length==>29
			if(chArr[i] != ' ') {
				resultchArr[j] = chArr[i];
				// resultchArr[0]~resultchArr[17]
				// java.lang.ArrayIndexOutOfBoundsException ==> 배열크기를 초과한 경우에 발생하는 오류!!
			}
			else {
				j--;
			}
		}// end of for------------------------------------
		
		System.out.println(String.valueOf(resultchArr));
	//  "koreaseoul쌍용강북교육센터";

		
		
	//	"koreaseoul쌍용강북교육센터";

	}// end of main()----------------------------

}
