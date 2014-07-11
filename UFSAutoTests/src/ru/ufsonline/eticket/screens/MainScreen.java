package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainScreen extends BaseScreen {	
	
	private WebElement englishButton;
	
	private WebElement russianButton;
	
	private WebElement germanButton;
	
	private WebElement purchaseButton;
	
	private WebElement myTicketsText;
	
	public enum Language {ENGLISH, RUSSIAN, GERMAN}

	public MainScreen(AppiumDriver ad) {
		super(ad);		
		englishButton = ad.findElement(By.xpath(uiMap.getProperty("main.english")));
		russianButton = ad.findElement(By.xpath(uiMap.getProperty("main.russian")));
		germanButton = ad.findElement(By.xpath(uiMap.getProperty("main.german")));
		purchaseButton = ad.findElement(By.xpath(uiMap.getProperty("main.purchase")));
		myTicketsText = ad.findElement(By.xpath(uiMap.getProperty("main.tickets_text")));
	}
	
	public void setLanguage(Language lang) {
		switch (lang) {
			case ENGLISH:
				englishButton.click();
				break;
			case RUSSIAN:
				russianButton.click();
				break;
			case GERMAN: 
				germanButton.click();
				break;
		}
	}
	
	public MainScreen verifyMyTicketsText(String text) {
		verifyText(myTicketsText, text);
		return this;
	}
	
	public RouteScreen tapPurchase() {
		purchaseButton.click();
		return new RouteScreen(ad);
	}	
}
