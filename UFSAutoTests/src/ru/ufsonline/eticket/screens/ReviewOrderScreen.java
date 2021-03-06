package ru.ufsonline.eticket.screens;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class ReviewOrderScreen extends NavBarScreen {
	
	private WebElement payBtn;

	public ReviewOrderScreen(AppiumDriver ad) {
		super(ad);		
		setWebViewContext();
	}
	
	public void clickPay() {
		payBtn = ad.findElementByXPath(uiMap.getProperty("review.pay"));
		payBtn.click();
	}

}
