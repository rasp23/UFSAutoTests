package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainScreen extends NavBarScreen {	
	
	private WebElement englishButton;
	
	private WebElement russianButton;
	
	private WebElement germanButton;
	
	private WebElement purchaseButton;
	
	private WebElement myTicketsText;
	
	public enum Language {ENGLISH, RUSSIAN, GERMAN}

	public MainScreen(AppiumDriver ad) {
		super(ad);		
	}
	
	public void setLanguage(Language lang) {
		switch (lang) {
			case ENGLISH:
				englishButton = ad.findElement(By.xpath(uiMap.getProperty("main.english")));				
				englishButton.click();
				logger.info("Set English language");
				break;
			case RUSSIAN:
				russianButton = ad.findElement(By.xpath(uiMap.getProperty("main.russian")));
				russianButton.click();
				logger.info("Set Russian language");
				break;
			case GERMAN:
				germanButton = ad.findElement(By.xpath(uiMap.getProperty("main.german")));
				germanButton.click();
				logger.info("Set German language");
				break;
		}
	}
	
	public MainScreen verifyMyTicketsText(String text) {
		myTicketsText = ad.findElement(By.xpath(uiMap.getProperty("main.tickets_text")));
		verifyText(myTicketsText, text);		
		return this;
	}
	
	public RouteScreen tapPurchase() {
		purchaseButton = ad.findElement(By.xpath(uiMap.getProperty("main.purchase")));
		purchaseButton.click();
		logger.info("Tapped 'Purchase' button");
		return new RouteScreen(ad);
	}	
}
