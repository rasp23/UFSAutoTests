package ru.ufsonline.eticket.screens;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class NavBarScreen extends BaseScreen {
	
	private WebElement back;

	public NavBarScreen(AppiumDriver ad) {
		super(ad);				
	}
	
	public void back() {
		back = ad.findElementByAccessibilityId(uiMap.getProperty("navbar.back"));
		back.click();
	}

}
