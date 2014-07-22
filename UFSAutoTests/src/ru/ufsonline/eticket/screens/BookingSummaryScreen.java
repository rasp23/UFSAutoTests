package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingSummaryScreen extends NavBarScreen {
	
	private WebElement refuseBtn;
	
	private WebElement nextBtn;

	public BookingSummaryScreen(AppiumDriver ad) {
		super(ad);
	}
	
	public PopupDialog clickNext() {
		nextBtn = ad.findElementByXPath(uiMap.getProperty("summary.next"));
//		WebDriverWait wait = new WebDriverWait(ad, 15);
//		wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
//		nextBtn.click();
		Point point = nextBtn.getLocation();
		System.out.println(point.toString());
		ad.tap(1, 175, 475, 1);//170 384 | 279 471
		return new PopupDialog(ad);
	}
	
	public void clickRefuse() {
		refuseBtn = ad.findElementByXPath(uiMap.getProperty("summary.refuse"));
		refuseBtn.click();
	}

}
