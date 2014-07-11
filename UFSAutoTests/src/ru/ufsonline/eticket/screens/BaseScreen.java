package ru.ufsonline.eticket.screens;

import java.util.HashMap;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ru.ufsonline.eticket.common.AppiumSession;
import ru.ufsonline.eticket.logger.Logger;
import ru.ufsonline.eticket.logger.LoggerFactory;
import ru.ufsonline.eticket.utils.PropertiesUtil;

public class BaseScreen {
	
	protected AppiumDriver ad = null;
	
	protected PropertiesUtil uiMap = AppiumSession.uiMap;
	
	protected Logger logger = LoggerFactory.getLogger();	

	public BaseScreen(AppiumDriver ad) {
		this.ad = ad; 
	}	
	
	public void verifyText(WebElement element, String expectedText) {		
		Assert.assertEquals(element.getText(), expectedText);
	}
	
	public void waitForText(String locator, String text) {
		WebDriverWait wait = new WebDriverWait(ad, 15);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(locator), text));
	}	
	
	public void scroll(WebElement element, String direction) {
		JavascriptExecutor js = (JavascriptExecutor) ad;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", direction);
		scrollObject.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: scroll", scrollObject);
	}
	
}
