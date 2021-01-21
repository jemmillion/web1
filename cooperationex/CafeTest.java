package cooperationex;

public class CafeTest {

	public static void main(String[] args) {
		
		Customer kim=new Customer("Kim",10000);
		Customer lee=new Customer("Lee",10000);
		
		StarCoffee starCoffee=new StarCoffee();
		BeanCoffee beanCoffee=new BeanCoffee();
		
		kim.buyStarCoffee(starCoffee, Coffee.staramericano);
		lee.buyBeanCoffee(beanCoffee, Coffee.beanlatte);
		
		

	}

}
