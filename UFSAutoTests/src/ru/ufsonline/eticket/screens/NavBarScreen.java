package ru.ufsonline.eticket.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class NavBarScreen extends BaseScreen {
	
	private WebElement back;
	
	private WebElement title;

	public NavBarScreen(AppiumDriver ad) {
		super(ad);				
	}
	
	public void back() {
		back = ad.findElement(By.xpath((uiMap.getProperty("navbar.back"))));
		back.click();
	}
	
	public String getTitle(){
		title = ad.findElement(By.xpath(uiMap.getProperty("toolbar.title")));
		return title.getText();
	}

}
