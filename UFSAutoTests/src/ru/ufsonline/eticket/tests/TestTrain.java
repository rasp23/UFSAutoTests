package ru.ufsonline.eticket.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.utils.GlobalProvider;

public class TestTrain extends TestBase {
	
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		route = main.tapPurchase();
		train = route.tapFind();
	}
	//9
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSorting(){
		train.verifySorting();
	}
	//10
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testEreg(String msg){
		train.tapEreg();
		train.verifyMessageApp(msg);
	}
	//11
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testRoute(String trainNum, String trainRoute){
		train.verifyRouteApp(trainNum,trainRoute);
		train.closeRouteWin();
	}
}