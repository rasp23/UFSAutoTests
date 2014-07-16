package ru.ufsonline.eticket.screens;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class Toolbar extends BaseScreen {
	
	private WebElement ready;

	public Toolbar(AppiumDriver ad) {
		super(ad);
	}
	
	public void ready() {
		ready = ad.findElementByXPath(uiMap.getProperty("toolbar.ready"));
		ready.click();
	}

}
