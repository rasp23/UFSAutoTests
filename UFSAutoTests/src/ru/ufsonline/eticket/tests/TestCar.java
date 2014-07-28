package ru.ufsonline.eticket.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.objects.SearchProperties;
import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.TestObject;

public class TestCar extends TestBase {
	
	
	public void navigateTo (String sSearchProps, String expectedTrain, String carType) {	
		TestObject searchProps = new TestObject(sSearchProps);
		route = main.tapPurchase();
		route.fillSearchProperties(new SearchProperties(searchProps));		
		train = route.tapFind();
		String trainIndex = train.verifyTrainPresent(expectedTrain);	
		car = train.selectCarType(trainIndex, carType);
	}
	
/*	@AfterMethod
	public void afterMethod(Method m) {
			car.back();
			train.back();
			route.back();
		}*/
		
	//12
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testRates(String searchNum, String trainNum, String carType, String msg){
		navigateTo(searchNum, trainNum, carType);
		car.tapRates();
		car.verifyMessageApp(msg);
		car.closeRates();
	}
	//13
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testServices(String cabinNum, String msg){
		//navigateTo(searchNum, trainNum, carType);
		car.tapServices();
		car.verifyMessageApp(msg);
		car.verifyCabin(cabinNum);
		car.closeServices();
	}

}