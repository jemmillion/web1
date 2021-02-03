package my.day15.c.INTERFACE;

import java.util.Scanner;

public class MemberCtrl implements InterMemberCtrl {

	// 구직자 중복아이디 검사
	@Override
	public boolean duplicateIdGujikja(String id, Member[] mbrArr) {
		
		boolean bool=false;
		
		for(int i=0;i<Member.count;i++) {
			if(mbrArr[i] instanceof Gujikja) {
				String storeId=mbrArr[i].getId();
				if(storeId.contentEquals(id)) {
					bool=true;
					break;
				}
			}	
		}
		return false;
	}
	
	// 구인회사 중복아이디 검사
	@Override
	public boolean duplicateIdCompany(String id, Member[] mbrArr) {
		
		return false;
	}

	// 회원가입(구직자, 구인회사)
	   @Override
	   public Member register(Scanner sc, Member[] mbrArr, int n) {
	   // int n 은 n값이 1이면 구직자로 회원가입, n값이 2이면 구인회사로 회원가입.   

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
	      
	      }

	      return mbr;
	   }

		
	//=================================================================================//

	// 로그인(구직자, 구인회사)
	// int n은 n값이 1이면 구직자로 로그인, n값이 2이면 구인회사로 로그인.
	@Override
	public Member login(Scanner sc, Member[] mbrArr, int n) {
		
		Member mbr=null;
		
		String str=(n==1)?"구직자":"구인회사";
		
		System.out.print("▷"+str+" 아이디 : ");
		String id=sc.nextLine();
		
		System.out.print("▷ 암호 : ");
		String passwd=sc.nextLine();
		
		if(n==1) {
			// 구직자로 로그인
			
			for(int i=0;i<Member.count;i++) {
				
				if(mbrArr[i] instanceof Gujikja) {
					if(id.equals(mbrArr[i].getId()) && 
							passwd.equals(mbrArr[i].getPasswd())){
							
						mbr=mbrArr[i];
						break;
							}
				}
			}// end of for()--------------------------------------------
			
		}
		else if(n==2) {
			// 구인회사로 로그인
			
			for(int i=0;i<Member.count;i++) {
				
				if(mbrArr[i] instanceof Company) {
					if(id.equals(mbrArr[i].getId()) && 
							passwd.equals(mbrArr[i].getPasswd())){
							
						mbr=mbrArr[i];
						break;
							}
				}
			}// end of for()--------------------------------------------
		}
		return mbr;
	}

	//모든 회원정보 출력(구직자, 구인회사)
	@Override
	public void viewInfoAll(Member[] mbArr, int n) {
		// int n 값이 1이면 구직자, n 값이 2이면 구인회사.
		
	}
	
	// 특정 회원 한명의 회원정보 출력(구직자, 구인회사)
	@Override
	public String showInfo(Member mbr) {
		
		return null;
	}
	
	// 회원(구직자, 구인회사)으로 사용가능 한지 아닌지를 알아보는 것
	@Override
	public boolean isUse(Member mbr) {
		
		String id = mbr.getId();
		String passwd=mbr.getPasswd();
		String name=mbr.getName();
		
		if(mbr instanceof Gujikja) {
			String jubun=((Gujikja)mbr).getJubun();
			
			if(id!=null&&passwd!=null&&name!=null&&jubun!=null) {
				return true;
			}
				
		}
		else if(mbr instanceof Company) {
			String jobType=((Company)mbr).getJobType();
			long seedMoney=((Company)mbr).getSeedMoney();
			
			if(id!=null&&passwd!=null&&name!=null&&jobType!=null) {
				return true;
			}
		}
		return false;
		
	}// end of public boolean isUse(Member mbr)----------------------------- 
	
	// 회원정보 변경(구직자, 구인회사)
	@Override
	public Member updateMemberInfo(Scanner sc, Member mbr) {
		
		return null;
	}




}

