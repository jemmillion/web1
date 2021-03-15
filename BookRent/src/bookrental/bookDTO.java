package bookrental;

public class bookDTO {

	
	// book_list
	private String bookseq;       
	private String bookisbn;      
	private String bookid;        
	private String bookctg;       
	private String booktitle;    
	private String authorname;    
	private String publishcom;    
	private String price;         
	
	private String status;
	private String bookqty;
	
	
	// book_rent
	private String rentseq;       //대여번호    
	private String usersid;       //  회원번호        
	private String rentday;       // 대여일자
	private String returnday;     // 반납예정일
	private String rentstatus ;   // 1이면 미반납, 0이면 반납
	
	private String delayFee ;   // 연체료
	
	private userDTO user;
	
	
	
	
	// getter and setter
	public String getBookseq() {
		return bookseq;
	}
	public void setBookseq(String bookseq) {
		this.bookseq = bookseq;
	}
	public String getBookisbn() {
		return bookisbn;
	}
	public void setBookisbn(String bookisbn) {
		this.bookisbn = bookisbn;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getBookctg() {
		return bookctg;
	}
	public void setBookctg(String bookctg) {
		this.bookctg = bookctg;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public String getPublishcom() {
		return publishcom;
	}
	public void setPublishcom(String publishcom) {
		this.publishcom = publishcom;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}  
	
	
	
	
	
	public String getRentseq() {
		return rentseq;
	}
	public void setRentseq(String rentseq) {
		this.rentseq = rentseq;
	}
	public String getUserid() {
		return usersid;
	}
	public void setUserid(String usersid) {
		this.usersid = usersid;
	}
	public String getRentday() {
		return rentday;
	}
	public void setRentday(String rentday) {
		this.rentday = rentday;
	}
	public String getReturnday() {
		return returnday;
	}
	public void setReturnday(String returnday) {
		this.returnday = returnday;
	}
	public String getRentstatus() {
		return rentstatus;
	}
	public void setRentstatus(String rentstatus) {
		this.rentstatus = rentstatus;
	}
	
	public userDTO getUser() {
		return user;
	}
	public void setUser(userDTO user) {
		this.user = user;
	}
	
	public String getBookqty() {
		return bookqty;
	}
	public void setBookqty(String bookqty) {
		this.bookqty = bookqty;
	}
	public String getUsersid() {
		return usersid;
	}
	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}
	
	
	
	public String getDelayFee() {
		return delayFee;
	}
	public void setDelayFee(String delayFee) {
		this.delayFee = delayFee;
	}
	
}
