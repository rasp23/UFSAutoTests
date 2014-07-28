package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

import ru.ufsonline.eticket.objects.PaymentInfo;
import ru.ufsonline.eticket.utils.TestObject;

public class BillingInfoScreen extends NavBarScreen {
	
	private WebElement firstNameField;
	
	private WebElement lastNameField;	
	
	private WebElement phoneField;
	
	private WebElement emailField;
	
	private WebElement nextBtn;

	public BillingInfoScreen(AppiumDriver ad) {
		super(ad);		
		toolbar = new Toolbar(ad);		
		waitForText(uiMap.getProperty("navbar.title"), "Payment");
		setWebViewContext();
	}

	public BillingInfoScreen typeFirstName(String firstName) {
		firstNameField = ad.findElementByXPath(uiMap.getProperty("billing.first_name"));
		firstNameField.sendKeys(firstName);
		return this;
	}
	
	public BillingInfoScreen typeLastName(String lastName) {
		lastNameField = ad.findElementByXPath(uiMap.getProperty("billing.last_name"));
		lastNameField.sendKeys(lastName);
		return this;
	}
	
	public BillingInfoScreen typePhone(String phone) {
		phoneField = ad.findElementByXPath(uiMap.getProperty("billing.phone"));
		phoneField.sendKeys(phone);
		return this;
	}
	
	public BillingInfoScreen typeEmail(String email) {
		emailField = ad.findElementByXPath(uiMap.getProperty("billing.email"));
		emailField.sendKeys(email);
		return this;
	}
	
	public PaymentDeatailsScreen clickNext() {
		nextBtn = ad.findElementByXPath(uiMap.getProperty("billing.next"));
		nextBtn.click();
		return new PaymentDeatailsScreen(ad);
	}
	
	public BillingInfoScreen fillBillingInfo(String sPaymentInfo) {
		PaymentInfo paymentInfo = new PaymentInfo(new TestObject(sPaymentInfo));
		typeFirstName(paymentInfo.getFirstName());
		typeLastName(paymentInfo.getLastName());
		typePhone(paymentInfo.getPhoneNumber());
		typeEmail(paymentInfo.getEmail());
		
		return this;
	}
	
	
}
