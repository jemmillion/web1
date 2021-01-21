package cooperationex;

public class BeanCoffee {

	int money;
	
	public String brewing(int money) {
		
		this.money+=money;
		if(money==Coffee.beanamericano) {
			return "콩다방 아메리카노를 구입했습니다";
		}
		else if(money==Coffee.beanlatte) {
			return "콩다방 라떼를 구입했습니다.";
		}
		else
			return null;
		
	}
}
