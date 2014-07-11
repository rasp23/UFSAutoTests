package ru.ufsonline.eticket.tests;

import org.testng.annotations.Test;

import ru.ufsonline.eticket.properties.SearchProperties;
import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.TestObject;

public class TestBuyTicket extends TestBase {
	
	@Test
	public void testBuyTicket() {		
	} 
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSuccessfullSearch(String paramSearchProps, String expectedTrain, String carType, String carNumber, String seatFrom, String seatTill) {	
		TestObject searchProps = new TestObject(paramSearchProps);
		
		routeScreen = mainScreen.tapPurchase();
//		routeScreen.fillSearchProperties(new SearchProperties(searchProps));		
		trainScreen = routeScreen.tapFind();
		String trainIndex = trainScreen.verifyTrainPresent(expectedTrain);	
		carScreen = trainScreen.selectCarType(trainIndex, carType);
		carScreen.selectCar(carNumber);
		seatScreen = carScreen.selectSeat().hideHint();
		seatScreen.pickRange(seatFrom, seatTill);
	}
}
