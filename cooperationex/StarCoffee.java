package cooperationex;

public class StarCoffee {

	int money;
	
	public String brewing(int money) {
		
		this.money+=money;
		if(money==Coffee.staramericano) {
			return "별 다방 아메리카노를 구입했습니다";
		}
		else if(money==Coffee.starlatte) {
			return "별 다방 라떼를 구입했습니다.";
		}
		else
			return null;
		
	}
	
}
