package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserAgreementDialog extends BaseScreen {
	
	private WebElement acceptButton;

	public UserAgreementDialog(AppiumDriver ad) {
		super(ad);
		acceptButton = ad.findElement(By.xpath(uiMap.getProperty("main.accept")));
	}
	
	public MainScreen acceptUserAgreement() {
		acceptButton.click();
		ad.switchTo().alert().accept();
		return new MainScreen(ad);
	}

}
