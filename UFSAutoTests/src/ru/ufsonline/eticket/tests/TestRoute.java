package ru.ufsonline.eticket.tests;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.Utils;

public class TestRoute extends TestBase {
	
	@BeforeClass
	public void beforeClass() {
		super.beforeClass();
		route = main.tapPurchase();
	}
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testDefaultDate() {		
		route.verifyDefaultDate();
	}	
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testPickerDates(String expectedSize) {
		route.verifyPickerDates(route.getPickerDates(), Integer.valueOf(expectedSize));
	}
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSetTime(String time) {		
		route.setTime(time);
		route.verifyTime(time);
	}
	
	@AfterMethod
	public void afterMethod(Method m) {
		if (m.getName().equals("testSetTime") || m.getName().equals("testTextMessageSamePoints") ||
		 	m.getName().equals("testTicketsCount")){
			
			route.back();	
			main.tapPurchase();
		}
	}
	//1
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testHintDeparture(String departureStation, String expectedDepartureStation){
		route.setDeparture(departureStation);
		route.verifyDepartureStation(expectedDepartureStation);
	}
	//1
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testHintDestination(String destinationStation, String expectedDestinationStation){
		route.setDestination(destinationStation);
		route.verifyDestinationStation(expectedDestinationStation);
	}	
		
	//2	
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testSwapPoints(String departureStation, String destinationStation){
		route.typeDeparture(departureStation);
		route.typeDestination(destinationStation);
		route.verifyLocations();
	}
	//3,4,6
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void textMsgInvalidSearchFields(String departureStation, String destinationStation, String msg){
		route.typeDeparture(departureStation);
		route.typeDestination(destinationStation);
		route.tapFindWithMessage();
		Utils.sleep(2000,"Waiting for search results");
		route.verifyMessageApp(msg);
	}
	//5
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testEmptyFields(String msg){
		route.doEmpty();
		route.tapFindWithMessage();
		Utils.sleep(2000,"Waiting for search results");
		route.verifyMessageApp(msg);
	}
	//7,8
	@Test(dataProvider = "GlobalProvider", dataProviderClass = GlobalProvider.class)
	public void testTicketsCount(String adultsNum, String childNum, String infantsNum, String msg){
		route.setAdults(adultsNum);
		route.setChildren(childNum);
		route.setInfants(infantsNum);
		route.verifyMessageApp(msg);
	}
}