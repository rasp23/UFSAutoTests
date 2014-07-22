package ru.ufsonline.eticket.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.objects.SearchProperties;
import ru.ufsonline.eticket.screens.PaymentScreen;
import ru.ufsonline.eticket.screens.MainScreen.Language;
import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.TestObject;

public class TestPassengerData extends TestBase {
	
	public void navigateToPassData (String sSearchProps, String expectedTrain, String carType, String carNumber, String seatFrom, String seatTo) {	
		TestObject searchProps = new TestObject(sSearchProps);

		routeScreen = mainScreen.tapPurchase();
		routeScreen.fillSearchProperties(new SearchProperties(searchProps));		
		trainScreen = routeScreen.tapFind();
		String trainIndex = trainScreen.verifyTrainPresent(expectedTrain);	
		carScreen = trainScreen.selectCarType(trainIndex, carType);
		carScreen.selectCar(carNumber);
		seatScreen = carScreen.selectSeat().hideHint();
		seatScreen.pickRange(seatFrom, seatTo);
		//seatScreen.pickLocationLevel(level);
		passengersScreen = seatScreen.clickPassengerData();

	}
	
	/*@BeforeMethod
	public void beforeMethod(Method m){
		if ((m.getName().equals("testDefaultRusDoc"))){
			mainScreen.setLanguage(Language.RUSSIAN);
			}
		}*/
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testDefaultRusDoc(String searchNum, String trainNum, String carType, String carNumber, String seatFrom, String seatTo, String FirstText, String SecText){
		navigateToPassData(searchNum, trainNum, carType, carNumber,seatFrom, seatTo);
		passengersScreen.verifyMessageApp(FirstText);
		passengersScreen.verifyMessageApp(SecText);
		passengersScreen.verifyEregSwitchOn();
	}
		
	
	
}