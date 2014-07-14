package ru.ufsonline.eticket.tests;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.utils.GlobalProvider;

public class TestRoute extends TestBase {
	
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		routeScreen = mainScreen.tapPurchase();
	}
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testDefaultDate() {		
		routeScreen.verifyDefaultDate();
	}	
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testPickerDates(String expectedSize, String listOfItems) {		
		routeScreen.verifyPickerDates(routeScreen.getPickerDates(), Integer.valueOf(expectedSize));
	}
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSetTime(String time) {		
		routeScreen.setTime(time);
		routeScreen.verifyTime(time);
	}
	
	@AfterMethod
	public void afterMethod(Method m) {
		if (m.getName().equals("testSetTime")) {
			routeScreen.back();	
			mainScreen.tapPurchase();
		}
	}
	
}
