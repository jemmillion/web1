package my.day12.b.inheritance;

import java.util.Scanner;

public class GujikjaCompanyCtrl {

	// == 구직자(Gujikja) 신규 회원가입을 해주는 메소드 생성하기 ==  
	public Gujikja registerGujikja(Scanner sc, Gujikja[] guArr) {
		
		Gujikja gu = new Gujikja();
		
		do {
			System.out.print("1. 아이디 : ");
			String id = sc.nextLine(); 
			
			gu.setId(id);
			id = gu.getId();
			
			if(id != null) { 
                			
				// == 중복아이디 검사하기 == //
				boolean idFlag = false;
				
				for(int i=0; i<Gujikja.count; i++) {
					if( id.equals(guArr[i].getId()) ) {     
						System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
						idFlag = true;
						break;
					}
				}// end of for---------------------
				
				if(!idFlag)
					break;
			}
			
		} while (true);
		
		////////////////////////////////////////////
		
		do {
			System.out.print("2. 암호 : ");
			String passwd = sc.nextLine();
			
			gu.setPasswd(passwd);
			passwd = gu.getPasswd();
			
			if(passwd != null) 
				break;
			
		} while(true);
		
		////////////////////////////////////////////
		
		do {
			System.out.print("3. 성명 : ");
			String name = sc.nextLine();
			
			gu.setName(name);
			name = gu.getName();
			
			if(name != null)
			   break;
			
		} while(true);
		
		////////////////////////////////////////////
		
		do {
			System.out.print("4. 주민번호 앞의 7자리만 : ");
			String jubun = sc.nextLine();
			
			gu.setJubun(jubun);
			jubun = gu.getJubun();
			
			if(jubun != null) 
				break;
			
		} while(true);
		
		/////////////////////////////////////////////
		
		return gu;
		
	}// end of public Gujikja registerGujikja(Scanner sc, Gujikja[] guArr)-------
	
	
	// == 구인회사(Company) 신규 회원가입을 해주는 메소드 생성하기 ==  
	public Company registerCompany(Scanner sc, Company[] coArr) {
		
		Company co = new Company();
		
		do {
			System.out.print("1. 아이디 : ");
			String id = sc.nextLine(); 
			
			co.setId(id);
			id = co.getId();
			
			if(id != null) { 
                			
				// == 중복아이디 검사하기 == //
				boolean idFlag = false;
				
				for(int i=0; i<Company.count; i++) {
					if( id.equals(coArr[i].getId()) ) {     
						System.out.println(">> 이미 사용중인 아이디 입니다. <<\n");
						idFlag = true;
						break;
					}
				}// end of for---------------------
				
				if(!idFlag)
					break;
			}
			
		} while (true);
		
		////////////////////////////////////////////
		
		do {
			System.out.print("2. 암호 : ");
			String passwd = sc.nextLine();
			
			co.setPasswd(passwd);
			passwd = co.getPasswd();
			
			if(passwd != null) 
				break;
			
		} while(true);
		
		////////////////////////////////////////////
		
		do {
			System.out.print("3. 회사명 : ");
			String name = sc.nextLine();
			
			co.setName(name);
			name = co.getName();
			
			if(name != null)
			   break;
			
		} while(true);
		
		////////////////////////////////////////////
		
		do {
			System.out.print("4. 회사직종타입 : ");
			String jobType = sc.nextLine();
			
			co.setJobType(jobType);
			jobType = co.getJobType();
			
			if(jobType != null) 
				break;
			
		} while(true);
		
		/////////////////////////////////////////////
		
		do {
			System.out.print("5. 자본금 : ");
			String sseedMoneny = sc.nextLine();
			
			co.setSeedMoney(Long.parseLong(sseedMoneny));
			long seedMoneny = co.getSeedMoney();
			
			if(seedMoneny > 0) 
				break;
			
		} while(true);
		
		/////////////////////////////////////////////
		
		return co;
		
	}// end of public Company registerCompany(Scanner sc, Company[] coArr)--------
	
	
	// == 구직자의 정보를 모두 보여주는 메소드 생성하기 == 
	public void showAllGujikja(Gujikja[] guArr) {
		
		System.out.println("------------------------------------------------------");
		System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
		System.out.println("------------------------------------------------------");
		
		for(int i=0; i<Gujikja.count; i++) {
		
			guArr[i].viewInfo();
			
		}// end of for-----------------------
		
		System.out.print("\n");
		
	}// end of public void showAllGujikja(Gujikja[] guArr)----------
	
	
	// == 구직자의 정보를 모두 보여주는 메소드 생성하기 == 
	public void showAllCompany(Company[] coArr) {
		
		System.out.println("------------------------------------------------------");
		System.out.printf("%-10s\t%-15s\t%-4s\t%-4s\t%-10s\n","아이디","암호","회사명","업종","자본금");
		System.out.println("------------------------------------------------------");
		
		for(int i=0; i<Company.count; i++) {
		
			coArr[i].viewInfo();
			
		}// end of for-----------------------
		
		System.out.print("\n");
		
	}// end of public void showAllCompany(Company[] coArr)----------
	
	// == 구직자의 정보를 모두 보여주는 메소드 생성하기 == 
	public Gujikja loginByGujikja(Scanner sc,Gujikja[] guArr) {
		
		Gujikja gu=null;
		
		System.out.print("\n▷아이디 : ");
		String id=sc.nextLine();
		
		System.out.print("▷암호 : ");
		String passwd=sc.nextLine();
		
		for(int i=0;i<Gujikja.count;i++){
			
			if(id.equals(guArr[i].getId()) && passwd.equals(guArr[i].getPasswd())) {
				System.out.println(">> 로그인 성공!! <<\n");
				gu=guArr[i];
				
				break;
			}
			
		}// end of for-------------------------------------------
		if(gu==null)
			System.out.println(">> 로그인 실패!! <<");
			
		
		return gu;
	}// end of public Gujikja loginByGujikja(Scanner sc,Gujikja guArr)------------
	

	public Company loginByCompany(Scanner sc,Company[] coArr) {
		
		Company co=null;
		
		System.out.print("\n▷아이디 : ");
		String id=sc.nextLine();
		
		System.out.print("▷암호 : ");
		String passwd=sc.nextLine();
		
		for(int i=0;i<Company.count;i++) {
			
			if(id.equals(coArr[i].getId()) && passwd.equals(coArr[i].getPasswd())) {
				System.out.println(">> 로그인 성공!! <<\n");
				co=coArr[i];
				
				break;
			}
		}// end of for-----------------------------------------------
		if(co==null)
			System.out.println(">> 로그인 실패!! <<");
		
		return co;
	}// end of public Company loginByCompany(Scanner sc,Company[] coArr)--------------
	
	
	// ===== Method Overloading(메소드 오버로딩) ===== // 
	// 메소드의 이름은 같지만
	// 파라미터(매개변수)의 갯수 또는 파라미터의 종류 또는 파라미터의 순서가 다르면
	// 이름은 같지만 전혀 다른 메소드로 취급을 한다.
	// 이때 주의할 것은 리턴타입은 뭐가 오든 관계가 없다는 것이다.
	// 이러한 것을 "메소드의 오버로딩" 이라고 부른다.
	
	// == 구직자의 내정보 변경하기 메소드 생성 == //
	public Gujikja updateMyInfo(Scanner sc, Gujikja gu) {
		
		// 파라미터로 넘어온 Gujikja gu의 정보를 보여준다.
		// 즉, 로그인한 구직자의 정보를 보여준다.
		
		//System.out.println(gu); 또는
		System.out.println(gu);
		// my.day12.b.inheritance.Gujikja@7d4991ad
		
		System.out.println("\n ~~~~ 내정보 변경하기 ~~~~");
		
		do {
			System.out.print("▷암호변경=> ");
			String newPasswd=sc.nextLine(); // 변경된 암호 "12345"
											// 변경된 암호가 "super1234$A"
			
			gu.setPasswd(newPasswd);
			String passwd=gu.getPasswd();   // 암호정책에 맞지 않으면
										    // passwd는 변경전 암호이고, newPasswd는 암호정책에 맞지 않는 암호 
			
											// 변경된 암호가 "super1234$A"
											// 암호정책에 맞으면
											// passwd는 변경된 새 암호가 된다.
			if(newPasswd.equals(passwd))
				break;
				
		}while(true);
		
		do{
			System.out.print("▷성명 변경=> ");
			String newName=sc.nextLine();
			
			gu.setName(newName);
			String name=gu.getName();
			
			if(newName.equals(name))
				break;
		}while(true);
		
		
		do {
			System.out.print("▷주민번호 7자리 변경=> ");
			String newJubun=sc.nextLine();
			
			gu.setJubun(newJubun);
			String jubun=gu.getJubun();
			
			if(newJubun.equals(jubun))
				break;
		} while (true);
	
		System.out.println("");
		
	return gu;
	}
		
	
	// == 구인회사의 내정보 변경하기 메소드 생성 == //
	public Company updateMyInfo(Scanner sc, Company co) {
		
		// 파라미터로 넘어온 Company co의 정보를 보여준다.
		// 즉, 로그인한 구인회사의 정보를 보여준다.
		
		// System.out.println(co); 또는
		System.out.println(co);
		// my.day12.b.inheritance.Company@28d93b30
		System.out.println("~~~ 회사 정보 변경하기 ~~~");
	
		do{
			System.out.print("▷비밀번호 변경=> ");
			String newPasswd=sc.nextLine();
			
			co.setPasswd(newPasswd);
			String passwd=co.getPasswd();
			
			if(newPasswd.equals(passwd))
				break;
		}while(true);		
		
		do{
			System.out.print("▷회사이름 변경=> ");
			String newName=sc.nextLine();
			
			co.setName(newName);
			String name=co.getName();
			
			if(newName.equals(name))
				break;
		}while(true);
		
		
		do {
			System.out.print("▷직종 변경=> ");
			String newJobType=sc.nextLine();
			
			co.setJobType(newJobType);
			String jobType=co.getJobType();
			
			if(newJobType.equals(jobType))
				break;
		} while (true);
	
		do {
			System.out.print("▷자본금 변경=> ");
			long newSeedMoney=Long.parseLong(sc.nextLine());
			
			co.setSeedMoney(newSeedMoney);
			long seedMoney=co.getSeedMoney();
			
			if(seedMoney>0 && newSeedMoney == seedMoney) 
				break;
		} while (true);
	
		System.out.println("");
		
	return co;
	}
		
}	













