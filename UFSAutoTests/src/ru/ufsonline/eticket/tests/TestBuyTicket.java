package ru.ufsonline.eticket.tests;

import org.testng.annotations.Test;

import ru.ufsonline.eticket.objects.Passenger;
import ru.ufsonline.eticket.objects.SearchProperties;
import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.TestObject;

public class TestBuyTicket extends TestBase {
	
	@Test
	public void testBuyTicket() {		
	} 
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSuccessfullSearch(String sSearchProps, String expectedTrain, String carType, String carNumber, String seatFrom, String seatTo, 
			String level, String sPassengerData) {	
		TestObject searchProps = new TestObject(sSearchProps);
		TestObject passengerData = new TestObject(sPassengerData);
		
		routeScreen = mainScreen.tapPurchase();
//		routeScreen.fillSearchProperties(new SearchProperties(searchProps));		
		trainScreen = routeScreen.tapFind();
		String trainIndex = trainScreen.verifyTrainPresent(expectedTrain);	
		carScreen = trainScreen.selectCarType(trainIndex, carType);
		carScreen.selectCar(carNumber);
		seatScreen = carScreen.selectSeat().hideHint();
//		seatScreen.pickRan(seatFrom, seatTo);
		seatScreen.pickLocationLevel(level);
		passengersScreen = seatScreen.clickPassengerData();
		passengersScreen.fillPassengerData(new Passenger(passengerData));
	}
}
