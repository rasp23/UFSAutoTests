package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

public class CarScreen extends BaseScreen {
	
	private WebElement selectSeat;

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

}
