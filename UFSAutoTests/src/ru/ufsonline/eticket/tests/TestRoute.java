package ru.ufsonline.eticket.tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.screens.MainScreen.Language;
import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.Utils;

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
	public void testPickerDates(String expectedSize) {
		routeScreen.verifyPickerDates(routeScreen.getPickerDates(), Integer.valueOf(expectedSize));
	}
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSetTime(String time) {		
		routeScreen.setTime(time);
		routeScreen.verifyTime(time);
	}
	
	@AfterMethod
	public void afterMethod(Method m) {
		if ((m.getName().equals("testSetTime")) || m.getName().equals("testTextMessageSamePoints") || m.getName().equals("testTicketsCount")){
			routeScreen.back();	
			mainScreen.tapPurchase();
		}
	}
	//1
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testHintDeparture(String departureStation, String expectedDepartureStation){
		routeScreen.setDeparture(departureStation);
		routeScreen.verifyDepartureStation(expectedDepartureStation);
	}
	//1
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testHintDestination(String destinationStation, String expectedDestinationStation){
		routeScreen.setDestination(destinationStation);
		routeScreen.verifyDestinationStation(expectedDestinationStation);
	}
	
	
//	@AfterMethod
//	public void afterMethodHint(Method m) {
//		if ((m.getName().equals("testHintDestination")) || (m.getName().equals("testHintDeparture"))){
//			routeScreen.back();
//			mainScreen.setLanguage(Language.RUSSIAN);
//			mainScreen.tapPurchase();
//		} 
//	}
		
	//2	
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testSwapPoints(String departureStation, String destinationStation){
		routeScreen.typeDeparture(departureStation);
		routeScreen.typeDestination(destinationStation);
		routeScreen.verifyLocations();
	}
	//3,4,6
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void textMsgInvalidSearchFields(String departureStation, String destinationStation, String msg){
		routeScreen.typeDeparture(departureStation);
		routeScreen.typeDestination(destinationStation);
		routeScreen.tapFindWithMessage();
		Utils.sleep(2000,"Waiting for search results");
		routeScreen.verifyMessageApp(msg);
	}
	//5
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testEmptyFields(String msg){
		routeScreen.doEmpty();
		routeScreen.tapFindWithMessage();
		Utils.sleep(2000,"Waiting for search results");
		routeScreen.verifyMessageApp(msg);
	}
	//7,8
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testTicketsCount(String adultsNum, String childNum, String infantsNum, String msg){
		routeScreen.setAdults(adultsNum);
		routeScreen.setChildren(childNum);
		routeScreen.setInfants(infantsNum);
		routeScreen.verifyMessageApp(msg);
	}
}