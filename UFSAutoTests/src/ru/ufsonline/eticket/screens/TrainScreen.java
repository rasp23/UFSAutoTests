package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TrainScreen extends BaseScreen {
	
	private WebElement title; 
	
	public TrainScreen(AppiumDriver ad) {
		super(ad);
		title = ad.findElement(MobileBy.AccessibilityId(uiMap.getProperty("train.title")));		
	}
	
	public String verifyTrainPresent(String expectedTrain) {
		int trainsFound = ad.findElementsByXPath(uiMap.getProperty("train.record")).size();
		WebElement train = null;
		boolean isTrainPresent = false;
		int index = 0;
		
		for (int i=1; i<=trainsFound; i++) {
			train = ad.findElementByXPath(uiMap.getProperty("train.number").replace("NUM", String.valueOf(i)));
			isTrainPresent = train.getAttribute("label").contains(expectedTrain);
			if (isTrainPresent) {
				index = i;
				break;
			}
		}
		
		Assert.assertTrue(isTrainPresent);
		return String.valueOf(index);
	}
	
	public CarScreen selectCarType(String trainIndex, String sCarType) {
		sCarType = String.valueOf(Integer.valueOf(sCarType) + 2);
		String locator = uiMap.getProperty("train.type").replaceFirst("NUM", trainIndex).replace("NUM2", sCarType);
		WebElement carType = ad.findElementByXPath(locator);
		carType.click();
		return new CarScreen(ad);
	}
}
