package cooperation;

public class TakeTransTest {

	public static void main(String[] args) {

		Student studentJ = new Student("Jane",5000);
		Student studentT = new Student("Team",10000);
		Student studentE = new Student("Edward",50000);
		
		Bus bus100 = new Bus(100);
		Bus bus500 = new Bus(500);
		Subway subwayGreen = new Subway(2);
		Subway subwayBlue = new Subway(4);
		Taxi taxi3356 = new Taxi(3356);
		
		
		studentJ.takeBus(bus100);
		studentT.takeSub(subwayGreen);
		studentE.takeTaxi(taxi3356);
		
		studentJ.showInfo();
		studentT.showInfo();
		studentE.showInfo();
		
		bus100.showBusInfo();
		bus500.showBusInfo();
		
		subwayGreen.showSubInfo();
		
		taxi3356.showTaxiInfo();

		//Edward는 늦게 일어나 학교에 지각을 했습니다. 택시를 타고 10000원을 지불하였습니다. 이 상황을 코드에 추가
		
		//
		
	}

}
