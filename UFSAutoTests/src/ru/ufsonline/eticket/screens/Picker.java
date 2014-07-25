package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class Picker extends BaseScreen {
	
	private WebElement wheel;
	
	private Toolbar toolbar;

	public Picker(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
	}
	
	public void pickItem(String item) {
		List<String> lItem = new ArrayList<String>();
		lItem.add(item);
		pickItem(lItem);
	}
	
	public void pickItem(List<String> item) {
		for (String s:item) {			
			wheel = ad.findElementByXPath(uiMap.getProperty("picker.wheel").
					replace("NUM", String.valueOf(item.indexOf(s) + 1)));
			wheel.sendKeys(s);
		}
		toolbar.ready();
 	}
}
