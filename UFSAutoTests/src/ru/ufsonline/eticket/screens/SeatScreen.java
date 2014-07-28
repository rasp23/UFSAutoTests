package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

public class SeatScreen extends NavBarScreen {
	
	private WebElement rangeButton;
	
//	private WebElement rangePickerFrom;
	
//	private WebElement rangePickerTo;
	
	private WebElement levelButton;
	
	private WebElement levelPicker;
	
	private WebElement passengerData;

	public SeatScreen(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
	}
	
	public void invokeRangePicker() {
		rangeButton = ad.findElementByXPath(uiMap.getProperty("seat.range_btn"));
		rangeButton.click();
//		rangePickerFrom = ad.findElementByXPath(uiMap.getProperty("seat.range_from"));
//		rangePickerTo = ad.findElementByXPath(uiMap.getProperty("seat.range_to"));
	}
	
	public void invokeLocationLevelPicker() {
		levelButton = ad.findElementByXPath(uiMap.getProperty("seat.level_btn"));
		levelButton.click();
		levelPicker = ad.findElementByXPath(uiMap.getProperty("seat.level_picker"));
	}
	
	public void pickRange(String from, String to) {
		invokeRangePicker();	
		
		/*
		unable to select from this picker due to the UIA issue 
		https://groups.google.com/forum/appium-discuss/uYdRTdQDvpU
		
	 	using tap to specified coords as a workaround 
		 
		rangePickerFrom.sendKeys(from);
		rangePickerTo.sendKeys(to);
		*/
		
		for (int i=1; i<=Integer.valueOf(from) - 1; i++) {
			ad.tap(1, 90, 455, 1);
		}
		for (int i=1; i<=38 - Integer.valueOf(to); i++) {
			ad.tap(1, 238, 371, 1);
		}

		toolbar.ready();
	}
	
	public void pickLocationLevel(String sLocationLevel) {
		invokeLocationLevelPicker();
		levelPicker.sendKeys(sLocationLevel);
		toolbar.ready();
	}
	
	public PassengersScreen clickPassengerData() {
		passengerData = ad.findElementByXPath(uiMap.getProperty("seat.passenger_data"));
		passengerData.click();
		return new PassengersScreen(ad);
	}
}
