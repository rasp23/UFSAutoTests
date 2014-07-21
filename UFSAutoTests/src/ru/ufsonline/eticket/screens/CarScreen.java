package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CarScreen extends BaseScreen {
	
	private WebElement selectSeat;
	
	private WebElement rateBtn;
	
	private WebElement closeRatesBtn;
	
	private WebElement servicesBtn;
	
	private WebElement closeServicesBtn;

	public CarScreen(AppiumDriver ad) {
		super(ad);
	}
	
	public void selectCar(String carNumber) {
		String locator = uiMap.getProperty("car.number").replace("NUM", carNumber);
		WebElement car = ad.findElementByXPath(locator);
		scroll(car, "right");
	}
	
	public SeatSelectionHintDialog selectSeat() {
		selectSeat = ad.findElementByAccessibilityId(uiMap.getProperty("car.selectseat"));
		selectSeat.click();
		return new SeatSelectionHintDialog(ad);
	}
	
	public CarScreen verifyMessageApp(String expectedText) {
		String loc = "//UIAStaticText[contains(@label,'TEXT')]";
		//String debug = ad.findElementByXPath("//UIAWindow[1]/UIAScrollView[1]/UIAStaticText[7]").getText();
		try {
			ad.findElementByXPath(loc.replace("TEXT", expectedText));			
		} catch(Exception e) {
			Assert.fail();
		}		
		
		return this;
	}

	public CarScreen tapRates() {
		rateBtn = ad.findElement(By.xpath(uiMap.getProperty("car.ratesBtn")));
		rateBtn.click();
	    //ad.tap(1, rateBtn, 2);
		logger.info("Taped rates button");
		return this;
	}

	public CarScreen closeRates() {
		closeRatesBtn = ad.findElement(By.xpath(uiMap.getProperty("car.ratesWindowClose")));
		closeRatesBtn.click();
		logger.info("Taped close rates button");
		return this;
	}

	public CarScreen tapServices() {
		servicesBtn = ad.findElement(By.xpath(uiMap.getProperty("car.servicesBtn")));
		servicesBtn.click();
		logger.info("Taped services button");
		return this;
	}

	public CarScreen verifyCabin(String cabinNum) {
		try {
			ad.findElementByXPath(uiMap.getProperty("car.cabinNum").replace("NUM", cabinNum));			
		} catch(Exception e) {
			Assert.fail();
		}		
		
		return this;
	}

	public CarScreen closeServices() {
		closeServicesBtn = ad.findElement(By.xpath(uiMap.getProperty("car.sericesWindowClose")));
		closeServicesBtn.click();
		logger.info("Taped close services button");
		return this;
	}
	

}