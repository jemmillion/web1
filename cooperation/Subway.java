package cooperation;

public class Subway {
	
	int lineNumber;
	int passengerCount;
	int money;
	
	public Subway(int lineNumber) {
		this.lineNumber=lineNumber;
	}
	
	public void take(int money) { //½ÂÂ÷
		this.money+=money;
		passengerCount++;
	}
	
	public void showSubInfo() {
		System.out.println(lineNumber+"¹ø ÁöÇÏÃ¶ÀÇ ½Â°´Àº "+passengerCount+"¸íÀÌ°í, ¼öÀÔÀº "+money+"ÀÔ´Ï´Ù.");
	}
}
