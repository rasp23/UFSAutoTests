package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

public class PopupDialog extends BaseScreen {
	
	private WebElement yesBtn;
	
	private WebElement noBtn;

	public PopupDialog(AppiumDriver ad) {
		super(ad);
	}

	public <T extends BaseScreen> T clickYes(Class<T> className)  {
		yesBtn = ad.findElementByXPath(uiMap.getProperty("popup.yes"));
		yesBtn.click();
		try {
			return className.getConstructor(AppiumDriver.class).newInstance(ad);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public void clickNo() {
		noBtn = ad.findElementByXPath(uiMap.getProperty("popup.no"));
		noBtn.click();
	}
	
}
