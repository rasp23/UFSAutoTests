package ru.ufsonline.eticket.screens;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class SeatSelectionHintDialog extends BaseScreen {
	
	private WebElement hideHint;

	public SeatSelectionHintDialog(AppiumDriver ad) {
		super(ad);
	}
	
	public SeatScreen hideHint() {
		hideHint = ad.findElementByAccessibilityId(uiMap.getProperty("hint.hide"));
		hideHint.click();
		return new SeatScreen(ad);
	}

}
