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
		if ((m.getName().equals("testSetTime"))){
			routeScreen.back();	
			mainScreen.tapPurchase();
		}
	}
	
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testHintDeparture(String departureStation, String expectedDepartureStation){
		routeScreen.setDeparture(departureStation);
		List<String> expectedDepartureStationLst = new ArrayList<String>();
		expectedDepartureStationLst = Utils.getList(expectedDepartureStation);
		routeScreen.verifyDepartureStation(expectedDepartureStationLst);
	}
	
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testHintDestination(String destinationStation, String expectedDestinationStation){
		routeScreen.setDestination(destinationStation);
		List<String> expectedDestinationStationLst = new ArrayList<String>();
		expectedDestinationStationLst = Utils.getList(expectedDestinationStation);
		routeScreen.verifyDestinationStation(expectedDestinationStationLst);
	}
	
	
//	@AfterMethod
//	public void afterMethodHint(Method m) {
//		if ((m.getName().equals("testHintDestination")) || (m.getName().equals("testHintDeparture"))){
//			routeScreen.back();
//			mainScreen.setLanguage(Language.RUSSIAN);
//			mainScreen.tapPurchase();
//		} 
//	}
		
		
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testSwapPoints(String departureStation, String destinationStation){
		routeScreen.typeDeparture(departureStation);
		routeScreen.typeDestination(destinationStation);
		routeScreen.swapAndVerifyLocations();
	}
	
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testTextMessageSamePoints(String departureStation, String destinationStation){
		routeScreen.typeDeparture(departureStation);
		routeScreen.typeDestination(destinationStation);
		routeScreen.tapFindWithMessage();
		routeScreen.verifyMessageApp();
	}
}