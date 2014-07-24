package ru.ufsonline.eticket.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.objects.SearchProperties;
//import ru.ufsonline.eticket.screens.PaymentDeatailsScreen;
//import ru.ufsonline.eticket.screens.MainScreen.Language;
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

	
	@BeforeMethod
	public void beforeMethod(Method m){
		passengers.back();
		passengers = seat.clickPassengerData();
		}

	//14
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testDefaultEngDoc(String searchNum, String trainNum, String carType, String carNumber, String FirstText, String SecText){
		navigateToPassData(searchNum, trainNum, carType, carNumber);
		passengers.verifyMessageApp(FirstText);
		passengers.verifyMessageApp(SecText);
		passengers.verifyEregSwitchOn();
	}
	//15
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testPassInvalidDataMsg(String msg){
		//navigateToPassData(searchNum, trainNum, carType, carNumber);
		passengers.clickPaymentMsg();
		passengers.verifyMessageApp(msg);
	}
	//16
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testPassBirthYear(String passNum, String date){
		passengers.setDate(passNum, date);
		passengers.verifySetedDate(date);
	}
	//17
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testPassCurrBirth(String passNum, String minusYearNum, String minusDayNum, String msg){
		passengers.setCurrDateMinus(passNum, minusYearNum, minusDayNum); 
		passengers.clickPaymentMsg();
		passengers.verifyMessageApp(msg);
	}
	//18
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testPassBirthDay(String passNum, String minusYearNum, String minusDayNum){
		passengers.verifyDateAfterCur(passNum, minusYearNum, minusDayNum);
	}
	//19
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testNoGenderCoupe (String msg){
		passengers.setCoupeNoGender();
		passengers.verifyMessageApp(msg);
	}
	//20
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testOneGenderCoupe(String num, String gender, String msg){
		passengers.selectGender(num, gender);
		passengers.setCoupeNoGender();
		passengers.verifyMessageApp(msg);
	}
	//21,22,23
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testAllGenderCoupe(String numOne, String numTwo, String sBirthDate, String genderOne, String genderTwo, String genderType, String msg, String msgPay){
		passengers.selectGender(numOne, genderOne);
		passengers.selectGender(numTwo, genderTwo);
		passengers.selectBirthDate(numTwo, sBirthDate);
		passengers.clickPaymentMsg();
		passengers.verifyMessageApp(msg);
		passengers.selectGenderCoupe(genderType);
		passengers.verifyGenderCoupe(genderType);
		passengers.clickPaymentMsg();
		passengers.verifyMessageApp(msgPay);
	}
	//22
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testDigitsSurname(String passNum, String digit, String msg){
		passengers.typeSurname(passNum, digit);
		passengers.verifyMessageApp(msg);
	}
}