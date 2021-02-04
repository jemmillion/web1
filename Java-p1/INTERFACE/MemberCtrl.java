package my.day15.c.INTERFACE;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;


public class MemberCtrl implements InterMemberCtrl {

	// 구직자 중복아이디 검사 
	@Override
	public boolean duplicateIdGujikja(String id, Member[] mbrArr) {
		
		boolean bool = false;
		
		for(int i=0; i<Member.count; i++) {
			if(mbrArr[i] instanceof Gujikja) {
				String storedId = mbrArr[i].getId();
				if(storedId.equals(id)) {
					bool = true;
					break;
				}
			}
		}// end of for----------------------------
		
		return bool;
	}

	// 구인회사 중복아이디 검사 
	@Override
	public boolean duplicateIdCompany(String id, Member[] mbrArr) {

		boolean bool = false;
		
		for(int i=0; i<Member.count; i++) {
			if(mbrArr[i] instanceof Company) {
				String storedId = mbrArr[i].getId();
				if(storedId.equals(id)) {
					bool = true;
					break;
				}
			}
		}// end of for----------------------------
		
		return bool;
	}

	// 회원가입(구직자 , 구인회사)
	@Override
	public boolean register(Scanner sc, Member[] mbrArr, int n) {
	// int n 은 n값이 1이면 구직자로 회원가입, n값이 2이면 구인회사로 회원가입.	
		
		boolean result = false;
		
		Member mbr = null;
		
		if(n==1) {
		 // 구직자로 회원가입
			mbr = new Gujikja();
			
			do {
				System.out.print("1. 구직자 아이디 : ");
				String id = sc.nextLine();
				
				mbr.setId(id);
				id = mbr.getId();
				
				if(id != null) {
					boolean bool = duplicateIdGujikja(id, mbrArr);
					
					if(bool)
						System.out.println(">> 이미 사용중인 아이디 입니다. << \n"); 
					else
						break;
				}
				
			} while(true);
			
			////////////////////////////////////////////
						
			do {
				System.out.print("2. 암호 : ");
				String passwd = sc.nextLine();
				
				mbr.setPasswd(passwd);
				passwd = mbr.getPasswd();
				
				if(passwd != null) 
					break;
			
			} while(true);
			
			////////////////////////////////////////////
						
			do {
				System.out.print("3. 성명 : ");
				String name = sc.nextLine();
				
				mbr.setName(name);
				name = mbr.getName();
				
				if(name != null)
					break;
			
			} while(true);
			
			////////////////////////////////////////////
			do {
				System.out.print("4. 주민번호 앞의 7자리만 : ");
				String jubun = sc.nextLine();
				
				((Gujikja) mbr).setJubun(jubun); 
				jubun = ((Gujikja) mbr).getJubun();
				
				if(jubun != null) 
					break;
				
			} while(true);
			////////////////////////////////////////////
			
		}// end of if(n==1)-----------------------------
		
		else if(n==2) {
		 // 구인회사로 회원가입	
			mbr = new Company();
			
			do {
				System.out.print("1. 구인회사 아이디 : ");
				String id = sc.nextLine();
				
				mbr.setId(id);
				id = mbr.getId();
				
				if(id != null) {
					boolean bool = duplicateIdCompany(id, mbrArr);
					
					if(bool)
						System.out.println(">> 이미 사용중인 아이디 입니다. << \n"); 
					else
						break;
				}
				
			} while(true);
			
			////////////////////////////////////////////
						
			do {
				System.out.print("2. 암호 : ");
				String passwd = sc.nextLine();
				
				mbr.setPasswd(passwd);
				passwd = mbr.getPasswd();
				
				if(passwd != null) 
					break;
			
			} while(true);
			
			////////////////////////////////////////////
			
			do {
				System.out.print("3. 회사명 : ");
				String name = sc.nextLine();
				
				mbr.setName(name);
				name = mbr.getName();
				
				if(name != null)
					break;
			
			} while(true);
			
			////////////////////////////////////////////
			
			do {
				System.out.print("4. 회사직종타입 : ");
				String jobType = sc.nextLine();
				
				((Company) mbr).setJobType(jobType);
				jobType = ((Company) mbr).getJobType(); 
				
				if(jobType != null) 
					break;
				
			} while(true);
			
			/////////////////////////////////////////////
			
			do {
				System.out.print("5. 자본금 : ");
				String sseedMoneny = sc.nextLine();
				
				((Company) mbr).setSeedMoney(Long.parseLong(sseedMoneny));
				long seedMoneny = ((Company) mbr).getSeedMoney();
				
				if(seedMoneny > 0) 
					break;
				
			} while(true);
			/////////////////////////////////////////////
			
		} // end of else if(n==2)-------------------------

		if(Member.count < mbrArr.length) {
			mbrArr[Member.count++] = mbr;
			result = true;
		}
		
		return result;
	}
	

	// 로그인(구직자 , 구인회사) 
	// int n 은 n값이 1이면 구직자로 로그인, n값이 2이면 구인회사로 로그인.
	@Override
	public Member login(Scanner sc, Member[] mbrArr, int n) {
		
		Member mbr = null;
		
		String str = (n==1)?"구직자":"구인회사";
		
		System.out.print("▷ "+str+" 아이디 : ");
		String id = sc.nextLine();
		
		System.out.print("▷ 암 호 : ");
		String passwd = sc.nextLine();
		
		if(n==1) {
			// 구직자로 로그인
			
			for(int i=0; i<Member.count; i++) {
				
				if(mbrArr[i] instanceof Gujikja) {
					if(id.equals(mbrArr[i].getId()) && 
					   passwd.equals(mbrArr[i].getPasswd())	) {
						
						mbr = mbrArr[i];	
					    break;	
					}
				}
				
			}// end of for----------------------
			
		}
		
		else if(n==2) {
			// 구인회사로 로그인

			for(int i=0; i<Member.count; i++) {
				
				if(mbrArr[i] instanceof Company) {
					if(id.equals(mbrArr[i].getId()) && 
					   passwd.equals(mbrArr[i].getPasswd())	) {
						
						mbr = mbrArr[i];	
					    break;	
					}
				}
				
			}// end of for----------------------

		}
		
		return mbr;
	}
	

	// 모든 회원정보 출력(구직자 , 구인회사)
	@Override
	public void viewInfoAll(Member[] mbrArr, int n) {
	// int n 은 n값이 1이면 구직자, n값이 2이면 구인회사.
		
		if(n==1) {
			// 모든 구직자들의 정보 출력
			
			System.out.println("------------------------------------------------------");
			System.out.printf("%-12s\t%-15s\t%-6s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
			System.out.println("------------------------------------------------------");
			
			for(int i=0; i<Member.count; i++) {
				if(mbrArr[i] instanceof Gujikja) {
					System.out.printf("%-12s\t%-15s\t%-6s\t%-4d\t%-2s\n",mbrArr[i].getId(), mbrArr[i].getPasswd(), mbrArr[i].getName(), ((Gujikja) mbrArr[i]).getAge(), ((Gujikja) mbrArr[i]).getGender()); 
				}
			}
			
		} // end of if(n==1)---------------------------
		
		else if(n==2) {
			// 모든 구인회사들의 정보 출력
			
			System.out.println("------------------------------------------------------");
			System.out.printf("%-12s\t%-15s\t%-6s\t%-6s\t%-10s\n","아이디","암호","회사명","업종","자본금");
			System.out.println("------------------------------------------------------");
			
			for(int i=0; i<Member.count; i++) {
				if(mbrArr[i] instanceof Company) {
					System.out.printf("%-12s\t%-15s\t%-6s\t%-6s\t%-10d\n",mbrArr[i].getId(), mbrArr[i].getPasswd(), mbrArr[i].getName(), ((Company) mbrArr[i]).getJobType(), ((Company) mbrArr[i]).getSeedMoney()); 
				}
			}
		} // end of else if(n==2)----------------------
		
		System.out.println("");
	}

	
	// 특정 회원 한명의 회원정보 출력(구직자 , 구인회사)
	@Override
	public String showInfo(Member mbr) {
		
		String info = "";
		
		if(mbr instanceof Gujikja) {
			// 성별
			// jubun.substring(6)  // "1"  "2"  "3"  "4" 
			
			String gender = "";
			String jubun = ((Gujikja) mbr).getJubun();
			
			switch ( jubun.substring(6)) {
				case "2":
				case "4":	
					gender = "여";
					break;
		
				default:
					gender = "남";
					break;
			}
			
			// 현재나이 = 현재년도 - (태어난년도) + 1
			
			// 현재년도 
			Calendar currentDate = Calendar.getInstance(); 
			// 현재날짜와 시간을 얻어온다.
			
			int currentYear = currentDate.get(Calendar.YEAR);
			
			// 태어난년도
			int birthYear = 0;
			
			switch ( jubun.substring(6)) {  // "1" "2"    "3" "4"
				case "1":
				case "2":	
					birthYear = 1900 + Integer.parseInt(jubun.substring(0,2)); 
					                        // 주민번호에서 앞의 2자리만 읽어오는 것 
					break;
		
				default:
					birthYear = 2000 + Integer.parseInt(jubun.substring(0,2)); 
	                // 주민번호에서 앞의 2자리만 읽어오는 것 
					break;
			}
			
			int age = currentYear - birthYear + 1;
			
			
			info = "1. 아이디 : "+mbr.getId()+"\n"
			     + "2. 암호 : "+mbr.getPasswd()+"\n"
			     + "3. 성명 : "+mbr.getName()+"\n"
			     + "4. 주민번호 7자리 : "+jubun+"\n"
			     + "5. 성별 : "+gender+"\n"
			     + "6. 현재나이 : "+age+"세\n";
	
		} // end of if(mbr instanceof Gujikja)---------
		
		else if(mbr instanceof Company) {
			DecimalFormat df = new DecimalFormat("#,###");
			
			info = "1. 아이디 : "+mbr.getId()+"\n"
				 + "2. 암호 : "+mbr.getPasswd()+"\n"
				 + "3. 성명 : "+mbr.getName()+"\n"
				 + "4. 업종타입 : "+((Company) mbr).getJobType()+"\n"
				 + "5. 자본금 : "+ df.format(((Company) mbr).getSeedMoney())+"원\n";
		} // end of else if(mbr instanceof Company)----
		
		return info;
	}

	
	// 회원(구직자 , 구인회사)으로 사용가능 한지 아닌지를 알아보는 것
	@Override
	public boolean isUse(Member mbr) {
		
		String id = mbr.getId();
		String passwd = mbr.getPasswd();
		String name = mbr.getName();
		
		if(mbr instanceof Gujikja) {
			String jubun = ((Gujikja) mbr).getJubun();
			
			if(id != null && passwd != null && name != null && jubun != null)    
				return true;
		}
		
		else if (mbr instanceof Company) {
		   String jobType = ((Company) mbr).getJobType();
		   long seedMoney = ((Company) mbr).getSeedMoney(); 
		   
		   if(id != null && passwd != null && name != null && jobType != null && seedMoney > 0 )    
				return true;
		}
		
		return false;
		
	}// end of public boolean isUse(Member mbr)----------------

	
	// 회원정보 변경(구직자 , 구인회사)
	@Override
	public Member updateMemberInfo(Scanner sc, Member mbr) {

		System.out.println("\n~~~~ 내정보 변경하기 ~~~~ \n");
		
		System.out.println(showInfo(mbr)); 
		
		if(mbr instanceof Gujikja) {
			// 구직자로 로그인 되어진 경우
			
			do {
				System.out.print("▷ 암호변경 => ");
				String newPasswd = sc.nextLine();  // 변경된 암호 "12345"
				                                   // 변경된 암호가 "super1234$A"
				
				mbr.setPasswd(newPasswd);           // 변경된 암호 "12345"
				String passwd = mbr.getPasswd();    // 암호정책에 맞지 않으면 
				                                    // passwd 는 변경전 암호이고, newPasswd 는 암호정책에 맞지 않는 암호 
				
				                                    // 변경된 암호가 "super1234$A"
				                                    // 암호정책에 맞으면 
				                                    // passwd 는 변경된 새암호가 된다.
				if(newPasswd.equals(passwd)) 
					break;
				
			} while(true);
			
			
			do {
				System.out.print("▷ 성명변경 => ");
				String newName = sc.nextLine();
				
				mbr.setName(newName);
				String name = mbr.getName();
				
				if(newName.equals(name))
				   break;
				
			} while(true);
			
			
			
			do {
				System.out.print("▷ 주민번호 7자리 변경 => ");
				String newJubun = sc.nextLine();
				
				((Gujikja) mbr).setJubun(newJubun);
				String jubun = ((Gujikja) mbr).getJubun();
				
				if(newJubun.equals(jubun)) 
					break;
				
			} while(true);
			
		} // end of if(mbr instanceof Gujikja)-----------------
		
		else {
			// 구인회사로 로그인 되어진 경우
		
			do {
				System.out.print("▷ 암호 변경 => ");
				String newPasswd = sc.nextLine();
				
				mbr.setPasswd(newPasswd);
				String passwd = mbr.getPasswd();
				
				if(newPasswd.equals(passwd)) 
					break;
				
			} while(true);
			
			
			do {
				System.out.print("▷ 회사명 변경 => ");
				String newName = sc.nextLine();
				
				mbr.setName(newName);
				String name = mbr.getName();
				
				if(newName.equals(name))
				   break;
				
			} while(true);
			
			
			do {
				System.out.print("▷ 회사직종타입 변경 => ");
				String newJobType = sc.nextLine();
				
				((Company) mbr).setJobType(newJobType);
				String jobType = ((Company) mbr).getJobType();
				
				if(newJobType.equals(jobType)) 
					break;
				
			} while(true);
			
			
			do {
				System.out.print("▷ 자본금 변경 => ");
				String newSseedMoneny = sc.nextLine();
				
				long newSeedMoneny = Long.parseLong(newSseedMoneny);
				
				((Company) mbr).setSeedMoney(newSeedMoneny);
				long seedMoneny = ((Company) mbr).getSeedMoney();
				
				if(seedMoneny > 0 && newSeedMoneny == seedMoneny) 
					break;
				
			} while(true);	
			
		} // end of else-----------------------------------------
		
		System.out.println("");  // 1줄 띄우기 
		
		return mbr;		
		
	}// end of public Member updateMemberInfo(Scanner sc, Member mbr)--------
	
}
