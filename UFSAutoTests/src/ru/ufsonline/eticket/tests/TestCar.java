package ru.ufsonline.eticket.tests;

import org.testng.annotations.Test;

import ru.ufsonline.eticket.objects.SearchProperties;
import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.TestObject;

public class TestCar extends TestBase {
	
	
	public void navigateTo (String sSearchProps, String expectedTrain, String carType) {	
		TestObject searchProps = new TestObject(sSearchProps);
		routeScreen = mainScreen.tapPurchase();
		routeScreen.fillSearchProperties(new SearchProperties(searchProps));		
		trainScreen = routeScreen.tapFind();
		String trainIndex = trainScreen.verifyTrainPresent(expectedTrain);	
		carScreen = trainScreen.selectCarType(trainIndex, carType);
	}
	//12
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testRates(String searchNum, String trainNum, String carType, String msg){
		navigateTo(searchNum, trainNum, carType);
		carScreen.tapRates();
		carScreen.verifyMessageApp(msg);
		carScreen.closeRates();
	}
	//13
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testServices(String searchNum, String trainNum, String carType, String cabinNum, String msg){
		navigateTo(searchNum, trainNum, carType);
		carScreen.tapServices();
		carScreen.verifyMessageApp(msg);
		carScreen.verifyCabin(cabinNum);
		carScreen.closeServices();
	}

}