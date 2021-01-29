package my.day12.a.encapsulation;

public class GujikjaMain {

	public static void main(String[] args) {

		Gujikja[] guArr=new Gujikja[5];
		
		Gujikja gu1=new Gujikja();
		
		//gu1.setUserid("fgghfghjrdrjrghy");
		//>>> 아이디는 5글자 이상 10글자 이하여야합니다.
		
		gu1.setUserid("honggd");
		gu1.setPasswd("qwee1234@A");
		gu1.setName("홍길동");
		gu1.setJubun("9501221");
		
		if(gu1.isUseGujikja())
			guArr[0]=gu1;
		
		Gujikja gu2=new Gujikja();
		gu2.setUserid("eomjh");
		gu2.setPasswd("qwee1234@A");
		gu2.setName("엄정화");
		gu2.setJubun("9701122");
		
		if(gu2.isUseGujikja())
			guArr[1]=gu2;
		
		for(int i=0;i<guArr.length;i++) {
			if(guArr[i]!=null)
				System.out.println(guArr[i].getUserid());
		}
		
	}// end of main()-----------------------------
}
