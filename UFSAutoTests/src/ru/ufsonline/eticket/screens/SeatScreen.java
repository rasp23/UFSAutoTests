package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

public class SeatScreen extends BaseScreen {
	
	private WebElement rangeButton;
	
	private WebElement rangePickerFrom;
	
	private WebElement rangePickerTo;
	
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
		rangePickerFrom = ad.findElementByXPath(uiMap.getProperty("seat.range_from"));
		rangePickerTo = ad.findElementByXPath(uiMap.getProperty("seat.range_to"));
	}
	
	public void invokeLocationLevelPicker() {
		levelButton = ad.findElementByXPath(uiMap.getProperty("seat.level_btn"));
		levelButton.click();
		levelPicker = ad.findElementByXPath(uiMap.getProperty("seat.level_picker"));
	}
	
	public void pickRange(String from, String to) {
		invokeRangePicker();		
		rangePickerFrom.sendKeys(from);
		rangePickerTo.sendKeys(to);
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
