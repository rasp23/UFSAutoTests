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

public class RouteScreen extends NavBarScreen {
	
	private WebElement departure;
	
	private WebElement destination;
	
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
	
	private WebElement ready;
	
	private final String DATE_PATTERN = "E d MMMM, yyyy"; 

	public RouteScreen(AppiumDriver ad) {
		super(ad);		
	}
	
	public void typeDeparture(String station) {
		departure = ad.findElement(By.xpath(uiMap.getProperty("route.departure")));
		departure.sendKeys(station);
		ready = ad.findElementByAccessibilityId((uiMap.getProperty("ready")));
		ready.click();
	}
	
	public void typeDestination(String station) {
		destination = ad.findElement(By.xpath(uiMap.getProperty("route.destination")));
		destination.sendKeys(station);
	}
	
	public void invokePicker() {
		dateButton = ad.findElement(By.xpath(uiMap.getProperty("route.date_btn")));	
		dateButton.click();				
		datePicker = ad.findElement(By.xpath(uiMap.getProperty("route.date_picker")));		
	}
	
	public void pickDate(int shift) {	
		Assert.assertTrue((shift >= -1) && (shift <= 58), "Verify test data: '-1<=shift<=58' !");
		invokePicker();
		datePicker.sendKeys(getRequiredDate(shift));
		ready = ad.findElementByAccessibilityId((uiMap.getProperty("ready")));
		ready.click();
	}
	
	public String getPickerDates() {
		invokePicker();
		return datePicker.getAttribute("values");
	}
	
	public void setFromTime(int hours) {	
		fromTime = ad.findElement(By.xpath(uiMap.getProperty("route.from_time")));
		time = ad.findElement(By.xpath(uiMap.getProperty("route.time")));
		Point point;		
		while (Integer.valueOf(time.getText().substring(0, 2)) != hours) {
			point = fromTime.getLocation();
			ad.swipe(point.x, point.y, point.x + 5, point.y, 500);	
		}
	}
	
	public void setTillTime(int hours) {
		tillTime = ad.findElement(By.xpath(uiMap.getProperty("route.till_time")));
		time = ad.findElement(By.xpath(uiMap.getProperty("route.time")));
		Point point;
		while (Integer.valueOf(time.getText().substring(8, 10)) != hours) {
			point = tillTime.getLocation();	
			ad.swipe(point.x, point.y, point.x - 5, point.y, 500);	
		}
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
		return new TrainScreen(ad);
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
		
		return this;
	}
	
	public RouteScreen verifyTime(String expectedTime) {
		Assert.assertEquals(time.getText(), expectedTime, "Time is not as expected!");		
		return this;
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
	
	public void fillSearchProperties(SearchProperties searchProps){
		typeDeparture(searchProps.getDeparture());
		typeDestination(searchProps.getDestination());
		pickDate(searchProps.getDepartureDate());
		setTime(searchProps.getDepartureTime());
		setAdults(searchProps.getAdults());
		setChildren(searchProps.getChildren());
		setInfants(searchProps.getInfants());
	}
}