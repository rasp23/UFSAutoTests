package ru.ufsonline.eticket.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.objects.Passenger;
//import ru.ufsonline.eticket.screens.PaymentDeatailsScreen;
//import ru.ufsonline.eticket.screens.MainScreen.Language;
import ru.ufsonline.eticket.utils.GlobalProvider;

public class TestPassengerData extends TestBase {
	
	private final String expectedTrain = "020У";
	
	private final String carType = "2";
	
	private final String carNumber = "3";
	
	private final String childrenNum = "1";
	
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		route = main.tapPurchase();
		route.setChildren(childrenNum);
		train = route.tapFind();
		String trainIndex = train.verifyTrainPresent(expectedTrain);
		car = train.selectCarType(trainIndex, carType);
		car.selectCar(carNumber);
		seat = car.selectSeat().hideHint();
		passengers = seat.clickPassengerData();
	}

	//14
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testDefaultEngDoc(String text){
		passengers.verifyMessageApp(text);
	}
	//14
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testEreg (){
		passengers.verifyEregSwitchOn();
	}
	//
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
	//21
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testAllGenderCoupe(String numOne, String numTwo, String sBirthDate, String genderOne, String genderTwo, String genderType){
		passengers.selectGender(numOne, genderOne);
		passengers.selectGender(numTwo, genderTwo);
		passengers.selectBirthDate(numTwo, sBirthDate);
		passengers.selectGenderCoupe(genderType);
		passengers.verifyGenderCoupe(genderType);

	}
	//22
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testDigitsSurname(String passNum, String digit, String msg){
		passengers.typeSurname(passNum, digit);
		passengers.verifyMessageApp(msg);
	}
	//23
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testPassDataByStep(String num_1, String num_2, Passenger passenger_1, Passenger passenger_2, String msg){
		passengers.fillPassengerData(num_1, passenger_1);
		passengers.fillPassengerData(num_2, passenger_2);
		passengers.clickPaymentMsg();
		passengers.verifyMessageApp(msg);
		
	}
	
	@AfterMethod
	public void afterMethod(Method m) {
		passengers.back();
		seat.clickPassengerData();
	}
	
}