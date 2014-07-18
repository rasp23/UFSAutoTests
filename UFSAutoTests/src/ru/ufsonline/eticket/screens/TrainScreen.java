package ru.ufsonline.eticket.screens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ru.ufsonline.eticket.utils.Utils;

public class TrainScreen extends BaseScreen {
	
	private WebElement title; 
	
	private WebElement sortByNumBtn;
	
	private WebElement eRegBtn;
	
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
	
	public void verifySorting() {
		int trainsFound = ad.findElementsByXPath(uiMap.getProperty("train.record")).size();
		List<String> temp_lst = new ArrayList<String>();
		List<String> trainNumbersBefore= new ArrayList<String>();
		List<String> trainNumbersAfter= new ArrayList<String>();
		String loc = uiMap.getProperty("train.number");
		int neededNum = 6; // needed elem from list
		for (int i = 1; i<=trainsFound; i++){
			WebElement textBeforeSort = ad.findElement(By.xpath(loc.replace("NUM", String.valueOf(i))));
			String text = textBeforeSort.getAttribute("name");
			temp_lst = Utils.getList(text);
			String tmp = temp_lst.get(neededNum);
			trainNumbersBefore.add(tmp);
			logger.info("Calculating actual nums");
		}
		sortByNumBtn = ad.findElement(By.xpath(uiMap.getProperty("train.sortNum")));
		
		for (int i =0; i<2; i++){
		ad.tap(1, sortByNumBtn, 2);
		logger.info("Taped sort button");
		Utils.sleep(1000,"Waiting for sorting");
		}
		
		for (int i = 1; i <= trainsFound; i++){
			String textAfterSort = ad.findElement(By.xpath(uiMap.getProperty("train.number").replace("NUM", String.valueOf(i)))).getAttribute("name");
			temp_lst = Utils.getList(textAfterSort);
			trainNumbersAfter.add(temp_lst.get(neededNum));
			logger.info("Calculating expected nums");
		}
		Collections.sort(trainNumbersBefore);
		Assert.assertEquals(trainNumbersBefore, trainNumbersAfter, "Sorting is not working properly!");
	}
	
	public TrainScreen verifyMessageApp(String expectedText) {
		String loc = "//UIAStaticText[contains(@label,'TEXT')]";
		//String text = "Departure and destination stations are the same";
		String debug = ad.findElementByXPath("//UIAWindow[1]/UIAScrollView[1]/UIAStaticText[7]").getText();
		try {
			ad.findElementByXPath(loc.replace("TEXT", expectedText));			
		} catch(Exception e) {
			Assert.fail();
		}		
		
		return this;
	}
	public TrainScreen tapEreg(){
		eRegBtn = ad.findElement(By.xpath(uiMap.getProperty("train.eRegBtn")));
		eRegBtn.click();
		logger.info("Taped eReg button");
		return this;
	}
	
}
