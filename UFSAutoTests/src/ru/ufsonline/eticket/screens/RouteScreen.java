package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ru.ufsonline.eticket.objects.SearchProperties;
import ru.ufsonline.eticket.utils.TestObject;
import ru.ufsonline.eticket.utils.Utils;

public class RouteScreen extends NavBarScreen {
	
	private WebElement departure;
	
	private WebElement destination;
	
	private WebElement swapBtn;
	
	private WebElement clearField;
	
	private WebElement dateField;
	
	private WebElement dateButton;
	
	private WebElement datePicker;
	
	private WebElement fromTime;
	
	private WebElement tillTime;
	
	private WebElement time;
	
	private WebElement adult1;
	
	private WebElement adult2;
	
	private WebElement adult3;
	
	private WebElement adult4;
	
	private WebElement child1;
	
	private WebElement child2;
	
	private WebElement child3;
	
	private WebElement infant1;
	
	private WebElement infant2;
	
	private WebElement find;
	
	private WebElement clearBtn;
	
	private final String DATE_PATTERN = "E d MMMM, yyyy"; 
	
	private Toolbar toolbar;
	
	private Picker picker;
	
	private WebElement key;
	
	public RouteScreen(AppiumDriver ad) {
		super(ad);		
		toolbar = new Toolbar(ad);
		picker = new Picker(ad);
	}

	public void typeDeparture(String station) {
		departure = ad.findElement(By.xpath(uiMap.getProperty("route.departure")));
		departure.click();
		sendEnglish(departure, station);
		logger.info(String.format("Typed '%s' in 'Departure station' field", station));
		toolbar.ready();		
	}
	
	public void typeDestination(String station) {
		destination = ad.findElement(By.xpath(uiMap.getProperty("route.destination")));
		destination.click();
		sendEnglish(destination, station);
		logger.info(String.format("Typed '%s' in 'Destination station' field", station));
		toolbar.ready();		
	}
	
	public void invokePicker() {
		dateButton = ad.findElement(By.xpath(uiMap.getProperty("route.date_btn")));	
		dateButton.click();		
		logger.info("Opened date picker");
	}
	
	public void pickDate(int shift) {	
		Assert.assertTrue((shift >= -1) && (shift <= 58), "Verify test data: '-1<=shift<=58' !");
		invokePicker();		
		picker.pickItem(getRequiredDate(shift));
	}
	
	public String getPickerDates() {
		invokePicker();
		String dates = datePicker.getAttribute("values");
		logger.info("Picker items are: " + dates);
		return dates;
	}
	
	public void setFromTime(int hours) {	
		fromTime = ad.findElement(By.xpath(uiMap.getProperty("route.from_time")));
		time = ad.findElement(By.xpath(uiMap.getProperty("route.time")));
		Point point;		
		while (Integer.valueOf(time.getText().substring(0, 2)) != hours) {
			point = fromTime.getLocation();
			ad.swipe(point.x, point.y, point.x + 5, point.y, 500);	
		}
		logger.info(String.format("From value set to %s hours", hours));
	}
	
	public void setTillTime(int hours) {
		tillTime = ad.findElement(By.xpath(uiMap.getProperty("route.till_time")));
		time = ad.findElement(By.xpath(uiMap.getProperty("route.time")));
		Point point;
		while (Integer.valueOf(time.getText().substring(8, 10)) != hours) {
			point = tillTime.getLocation();	
			ad.swipe(point.x, point.y, point.x - 5, point.y, 500);	
		}
		logger.info(String.format("Till value set to %s hours", hours));
	}
	
	public void setTime(String time) {
		setFromTime(Integer.valueOf(time.substring(0, time.indexOf(":"))));
		setTillTime(Integer.valueOf(time.substring(time.lastIndexOf(":") - 2, time.length() - 3)));
	}
	
	public void setAdults(String number) {
		adult1 = ad.findElement(By.xpath(uiMap.getProperty("route.adult1")));
		adult2 = ad.findElement(By.xpath(uiMap.getProperty("route.adult2")));
		adult3 = ad.findElement(By.xpath(uiMap.getProperty("route.adult3")));
		adult4 = ad.findElement(By.xpath(uiMap.getProperty("route.adult4")));
		
		switch (Integer.valueOf(number)) { 
			case 1: adult1.click();
					logger.info("Clicked one adult");
					break;
			case 2: adult2.click();
					logger.info("Clicked two adults");
					break;
			case 3: adult3.click();
					logger.info("Clicked three adults");
					break;
			case 4: adult4.click();
					logger.info("Clicked four adults");
					break;					
			default:
					logger.error(String.format("Impossible to select %s adults. Check your test data!", number));
					break;
		}
	}
	
	public void setChildren(String number) {
		child1 = ad.findElement(By.xpath(uiMap.getProperty("route.child1")));
		child2 = ad.findElement(By.xpath(uiMap.getProperty("route.child2")));
		child3 = ad.findElement(By.xpath(uiMap.getProperty("route.child3")));
		
		switch (Integer.valueOf(number)) {
			case 0: logger.info("No children selected");
					break;
			case 1: child1.click();
					logger.info("Clicked one child");
					break;
			case 2: child2.click();
					logger.info("Clicked two children");
					break;
			case 3: child3.click();
					logger.info("Clicked three children");
					break;
			default:
					logger.error(String.format("Impossible to select %s children. Check your test data!", number));
					break;
		}
	}
 
	public void setInfants(String number) {
		infant1 = ad.findElement(By.xpath(uiMap.getProperty("route.infant1")));
		infant2 = ad.findElement(By.xpath(uiMap.getProperty("route.infant2")));
		
		switch (Integer.valueOf(number)) {
			case 0: logger.info("No infants selected");
					break;
			case 1: infant1.click();
					logger.info("Clicked one infant");
					break;
			case 2: infant2.click();
					logger.info("Clicked two infants");
					break;
			default:
					logger.error(String.format("Impossible to select %s infants. Check your test data!", number));
					break;
		}
 	}
	
	public TrainScreen tapFind() {
		find = ad.findElement(By.xpath(uiMap.getProperty("route.search")));
		find.click();
		logger.info("Tapped 'Find' button");
		return new TrainScreen(ad);
	}	
	
	/**
	 * Returning a date using shift from current date and format pattern
	 * 
	 * @param shift
	 * @return date of the specified format 
	 */
	private String getRequiredDate(int shift) {
		Calendar dateToPick = new GregorianCalendar();
		dateToPick.add(Calendar.DAY_OF_YEAR, shift);
		SimpleDateFormat formatPattern = new SimpleDateFormat(DATE_PATTERN, Locale.US);
		StringBuilder formattedDate = new StringBuilder(formatPattern.format(dateToPick.getTime()));
		return formattedDate.replace(2, 3, "").toString();
	}	
	
	public void fillSearchProperties(String sSearchProps){
		SearchProperties searchProps = new SearchProperties(new TestObject(sSearchProps));
		
		typeDeparture(searchProps.getDeparture());
		typeDestination(searchProps.getDestination());
		pickDate(searchProps.getDepartureDate());
		setTime(searchProps.getDepartureTime());
		setAdults(searchProps.getAdults());
		setChildren(searchProps.getChildren());
		setInfants(searchProps.getInfants());
	}

	public void clearDeparture() {
		departure = ad.findElement(By.xpath(uiMap.getProperty("route.departure")));
		departure.click();
		logger.info("Clicked 'Departure station' field");
		clearField = ad.findElement(By.xpath(uiMap.getProperty("route.departureClearField")));
		clearField.click();
		logger.info("'Departure station' field cleared");
	}
	
	public void  clearDestination() {
		destination = ad.findElement(By.xpath(uiMap.getProperty("route.destination")));
		destination.click();
		logger.info("Clicked 'Destination station' field");
		clearField = ad.findElement(By.xpath(uiMap.getProperty("route.destinationClearField")));
		clearField.click();
		logger.info("'Destination station' field cleared");
	}
	
	public void setDeparture(String station){
		departure = ad.findElement(By.xpath(uiMap.getProperty("route.departure")));
		departure.click();
		departure.sendKeys(station);
	}
	
	public void setDestination(String station){
		destination = ad.findElement(By.xpath(uiMap.getProperty("route.destination")));
		destination.click();
		//Utils.sleep(3000, "Avoiding JS error");
		destination.sendKeys(station);
	}
	
	public RouteScreen verifyDefaultDate() {
		dateField = ad.findElement(By.xpath(uiMap.getProperty("route.date_field")));
		verifyText(dateField, getRequiredDate(1));		
		return this;
	}
	
	public RouteScreen verifyPickerDates(String sDates, int expectedSize) {
		StringBuilder sbDates = new StringBuilder(sDates); 
		List<String> lDates = new ArrayList<String>();
		
		//parse dates string, add dates in list 
		while (sbDates.length() != 0) {
			int index = sbDates.indexOf(",", 1+ sbDates.indexOf(","));
			if (index < 0) { 
				index = sbDates.length() - 1;
			}
			String date = sbDates.substring(0, index);
			sbDates.delete(0, index + 2);
			lDates.add(date);			
		}		
		
		Assert.assertEquals(lDates.size(), expectedSize, "The number of dates in picker are incorrect!");
		
		for (int i = -1; i > expectedSize - 2; i++) {
			Assert.assertEquals(lDates.get(i + 1), getRequiredDate(i), "Picker date is not as expected!");
		}
		logger.info("Picker dates are as expected");		
		return this;
	}
	
	public RouteScreen verifyTime(String expectedTime) {
		Assert.assertEquals(time.getText(), expectedTime, "Time is not as expected!");		
		logger.info("Found expected time " + expectedTime);
		return this;
	}
	
	public RouteScreen verifyDepartureStation(String expectedDepartureStation) {
		List<String> hintDepartureLst = new ArrayList<String>();
		List<String> expectedDepartureStationLst = new ArrayList<String>();
		expectedDepartureStationLst = Utils.getList(expectedDepartureStation);
		int hintNum = 3;
		
		for (int i = 1; i <= hintNum; i++){
			String hintLoc = uiMap.getProperty("route.departureHint").replace("NUM", String.valueOf(i));
			String hintDeparture = ad.findElement(By.xpath(hintLoc)).getAttribute("name");
			hintDepartureLst.add(hintDeparture);			
		}
		logger.info("Departure station hints are: " + hintDepartureLst.toString());
		
		for (int i = 0; i <hintNum-1; i++) {
			Assert.assertEquals(hintDepartureLst.get(i), expectedDepartureStationLst.get(i),
					"Departure hints are not as expected!");
		
		}
		return this;
	}
	
	public RouteScreen verifyDestinationStation(String expectedDestinationStation) {
		List<String> hintDestinationLst = new ArrayList<String>();
		List<String> expectedDestinationStationLst = new ArrayList<String>();
		expectedDestinationStationLst = Utils.getList(expectedDestinationStation);
		int hintNum = 3;
		
		for (int i = 1; i <= hintNum; i++){
			String hintLoc = uiMap.getProperty("route.destinationHint").replace("NUM", String.valueOf(i));
			String hintDestination = ad.findElement(By.xpath(hintLoc)).getAttribute("name");
			hintDestinationLst.add(hintDestination);			
		}
		logger.info("Destination station hints are: " + hintDestinationLst.toString());
		
		for (int i = 0; i <hintNum-1; i++) {
			Assert.assertEquals(hintDestinationLst.get(i), expectedDestinationStationLst.get(i),
					"Destination hints are not as expected!");
		}
		return this;
	}
	

	public RouteScreen swapLocations(){
		swapBtn = ad.findElement(By.name(uiMap.getProperty("route.swapButton")));
		swapBtn.click();
		logger.info("Tapped swap locations button");
		return this;
	}
	
	public RouteScreen verifySwapLocations() {
		String departureTxt = departure.getText();
		String destinationTxt = destination.getText();
		swapLocations();		
		Assert.assertEquals(ad.findElement(By.xpath(uiMap.getProperty("route.departure"))).getText(),
				destinationTxt, "Swap isn't working properly");
		logger.info("Found correct text in 'Departure station' field: " + destinationTxt);
		Assert.assertEquals(ad.findElement(By.xpath(uiMap.getProperty("route.destination"))).getText(),
				departureTxt, "Swap isn't working properly");
		logger.info("Found correct text in 'Destination station' field: " + departureTxt);		
		return this;
	}
	
	public RouteScreen tapFindWithMessage() {
		find = ad.findElement(By.xpath(uiMap.getProperty("route.search")));
		find.click();
		logger.info("Tapped 'Find' button");
		return this;
	}

	public RouteScreen verifyMessageApp(String expectedText) {
		try {
			ad.findElementByXPath(uiMap.getProperty("error.msg").replace("TEXT", expectedText));	
			logger.error("Found expected error message: " + expectedText);
		} catch(Exception e) {
			Assert.fail(String.format("Error message '%s' was not found!", expectedText));
		}		
		
		return this;
	}

	public RouteScreen doEmpty() {
		departure = ad.findElement(By.xpath(uiMap.getProperty("route.departure")));
		departure.click();
		logger.info("Taped departure station");
		String locDep = uiMap.getProperty("route.clearDeparture");
		String locDest=  uiMap.getProperty("route.clearDestination");
		clearBtn = ad.findElement(By.xpath(locDep));
		ad.tap(1, clearBtn, 3);
		logger.info("Taped clear button");
		destination = ad.findElement(By.xpath(uiMap.getProperty("route.destination")));
		destination.click();
		logger.info("Taped destination station");
		clearBtn = ad.findElement(By.xpath(locDest));
		ad.tap(1, clearBtn, 3);
		toolbar.ready();
		return this;
	}

	public void setDepartureRus(String depStation) {
		doEmpty();
		departure = ad.findElement(By.xpath(uiMap.getProperty("route.departure")));
		departure.click();
		goToRusKeyboard();
		String loc = uiMap.getProperty("keyboard.key");
		for (int i =0; i < depStation.length();i++){
			key = ad.findElement(By.xpath(loc.replace("KEY", String.valueOf(depStation.charAt(i)))));
			key.click();
			//ad.tap(1, key, 2);
		}
	}
	
	public void setDestinationRus(String desStation) {
		doEmpty();
		destination = ad.findElement(By.xpath(uiMap.getProperty("route.destination")));
		destination.click();
		goToRusKeyboard();
		String loc = uiMap.getProperty("keyboard.key");
		for (int i =0; i < desStation.length();i++){
			key = ad.findElement(By.xpath(loc.replace("KEY", String.valueOf(desStation.charAt(i)))));
			key.click();
			//ad.tap(1, key, 2);
		}
	}
}