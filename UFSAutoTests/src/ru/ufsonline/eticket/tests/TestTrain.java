package ru.ufsonline.eticket.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.utils.GlobalProvider;

public class TestTrain extends TestBase {
	
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		routeScreen = mainScreen.tapPurchase();
		trainScreen = routeScreen.tapFind();
	}
	//9
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSorting(){
		trainScreen.verifySorting();
	}
	//
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testEreg(String msg){
		trainScreen.tapEreg();
		trainScreen.verifyMessageApp(msg);
	}
	
}