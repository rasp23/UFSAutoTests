package ru.ufsonline.eticket.screens;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class Picker extends BaseScreen {
	
	private WebElement wheel;
	
	private Toolbar toolbar;

	public Picker(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
	}
	
	public void pickItem(String wheelNum, String item) {
		wheel = ad.findElementByXPath(uiMap.getProperty("picker.wheel").replace("NUM", wheelNum));
		wheel.sendKeys(item);
		toolbar.ready();
	}
}
