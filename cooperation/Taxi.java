package cooperation;

public class Taxi {

	int taxiNumber;
	int passengerCount;
	int money;
	
	public Taxi(int taxiNumber) {
		this.taxiNumber=taxiNumber;
	}
	
	public void take(int money){
		this.money+=money;
		passengerCount++;
	}
	
	public void showTaxiInfo() {
		System.out.println(taxiNumber+"번의 승객은 "+passengerCount+"명이고, 수입은 "+money+"입니다.");
	}
	
}
