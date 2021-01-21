package cooperationex;

public class Customer {

	String customerName;
	int money;
	
	public Customer(String customerName,int money) {
		this.customerName=customerName;
		this.money=money;
	}
	
	public void buyStarCoffee(StarCoffee sCoffee,int money) {
		String message=sCoffee.brewing(4000);
		if(message!=null) {
			this.money-=money;
			System.out.println(customerName+"님이 "+money+"원으로 "+message);		
		}
	}
	
	public void buyBeanCoffee(BeanCoffee bCoffee,int money) {
		String message=bCoffee.brewing(4500);
		if(message!=null) {
			this.money-=money;
			System.out.println(customerName+"님이 "+money+"원으로 "+message);		
		}
	}
	
}
