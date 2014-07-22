package ru.ufsonline.eticket.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.objects.SearchProperties;
import ru.ufsonline.eticket.screens.PaymentDeatailsScreen;
import ru.ufsonline.eticket.screens.MainScreen.Language;
import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.TestObject;

public class TestPassengerData extends TestBase {
	
	public void navigateToPassData (String sSearchProps, String expectedTrain, String carType, String carNumber) {	
		TestObject searchProps = new TestObject(sSearchProps);

		route = main.tapPurchase();
		route.fillSearchProperties(new SearchProperties(searchProps));		
		train = route.tapFind();
		String trainIndex = train.verifyTrainPresent(expectedTrain);	
		car = train.selectCarType(trainIndex, carType);
		car.selectCar(carNumber);
		seat = car.selectSeat().hideHint();
		//seat.pickRange(seatFrom, seatTo);
		//seatScreen.pickLocationLevel(level);
		passengers = seat.clickPassengerData();

	}
	
	/*@BeforeMethod
	public void beforeMethod(Method m){
		if ((m.getName().equals("testDefaultRusDoc"))){
			mainScreen.setLanguage(Language.RUSSIAN);
			}
		}*/
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testDefaultEngDoc(String searchNum, String trainNum, String carType, String carNumber, String FirstText, String SecText){
		navigateToPassData(searchNum, trainNum, carType, carNumber);
		passengers.verifyMessageApp(FirstText);
		passengers.verifyMessageApp(SecText);
		passengers.verifyEregSwitchOn();
	}
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testPassInvalidDataMsg(String msg){
		//navigateToPassData(searchNum, trainNum, carType, carNumber);
		passengers.clickPaymentMsg();
		passengers.verifyMessageApp(msg);
	}
		
	
	
}