package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

public class BookingSummaryScreen extends NavBarScreen {
	
	private WebElement refuseBtn;
	
	private WebElement nextBtn;

	public BookingSummaryScreen(AppiumDriver ad) {
		super(ad);
	}
	
	public PopupDialog clickNext() {
		//avoiding early click
		do {
			nextBtn = ad.findElementByXPath(uiMap.getProperty("summary.next"));
			logger.debug(String.format("'Next' button coords: %s", nextBtn.getLocation().toString()));
		} while (nextBtn.getLocation().getX() == 0);
		
		nextBtn.click();
		return new PopupDialog(ad);
	}
	
	public void clickRefuse() {
		refuseBtn = ad.findElementByXPath(uiMap.getProperty("summary.refuse"));
		refuseBtn.click();
	}

}
