package ru.ufsonline.eticket.screens;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class BookingSummaryScreen extends NavBarScreen {
	
	private WebElement refuseBtn;
	
	private WebElement nextBtn;

	public BookingSummaryScreen(AppiumDriver ad) {
		super(ad);
	}
	
	public PopupDialog clickNext() {
		nextBtn = ad.findElementByXPath(uiMap.getProperty("summary.next"));
		nextBtn.click();
		return new PopupDialog(ad);
	}
	
	public void clickRefuse() {
		refuseBtn = ad.findElementByXPath(uiMap.getProperty("summary.refuse"));
		refuseBtn.click();
	}

}
