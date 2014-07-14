package ru.ufsonline.eticket.screens;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class SeatScreen extends BaseScreen {
	
	private WebElement rangeButton;
	
	private WebElement rangePickerFrom;
	
	private WebElement rangePickerTo;
	
	private Toolbar toolbar;

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
	
	public void pickRange(String from, String to) {
		invokeRangePicker();
//		if (!from.equals("1")) {
//			from = " " + from; 
//		}
//		if (!to.equals("1")) {
//			to = " " + to; 
//		}
//		String tmp = rangePickerFrom.getAttribute("values");
//		String ttmp = rangePickerFrom.getAttribute("value");
//		rangePickerFrom.sendKeys("from");
		rangePickerFrom.sendKeys(from);
		toolbar.ready();
	}
}
