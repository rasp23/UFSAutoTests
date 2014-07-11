package ru.ufsonline.eticket.screens;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class SeatScreen extends BaseScreen {
	
	private WebElement rangeButton;
	
	private WebElement rangePickerFrom;
	
	private WebElement rangePickerTill;
	
	private Toolbar toolbar;

	public SeatScreen(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
	}
	
	public void invokeRangePicker() {
		rangeButton = ad.findElementByXPath(uiMap.getProperty("seat.range_btn"));
		rangeButton.click();
		rangePickerFrom = ad.findElementByXPath(uiMap.getProperty("seat.range_from"));
		rangePickerTill = ad.findElementByXPath(uiMap.getProperty("seat.range_till"));
	}
	
	public void pickRange(String from, String till) {
		invokeRangePicker();
		String tmpp = rangePickerFrom.getAttribute("values");
		String tmp = rangePickerFrom.getAttribute("value");
		String tmppp = rangePickerTill.getAttribute("values");
		rangePickerFrom.sendKeys(from);
		rangePickerTill.sendKeys(till);
		toolbar.ready();
	}
}
