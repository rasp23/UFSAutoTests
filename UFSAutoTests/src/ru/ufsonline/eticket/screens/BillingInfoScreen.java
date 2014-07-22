package ru.ufsonline.eticket.screens;

import java.util.Set;

import org.openqa.selenium.WebElement;

import ru.ufsonline.eticket.objects.PaymentInfo;
import ru.ufsonline.eticket.utils.TestObject;
import ru.ufsonline.eticket.utils.Utils;
import io.appium.java_client.AppiumDriver;

public class BillingInfoScreen extends BaseScreen {
	
	private WebElement firstNameField;
	
	private WebElement lastNameField;	
	
	private WebElement phoneField;
	
	private WebElement emailField;
	
	private WebElement nextBtn;

	public BillingInfoScreen(AppiumDriver ad) {
		super(ad);		
		toolbar = new Toolbar(ad);		
	}

	public BillingInfoScreen typeFirstName(String firstName) {
		Utils.sleep(10000);
		Set<String> contextNames = ad.getContextHandles();
		String newContext = String.valueOf(contextNames.toArray()[1]); 
		ad.context(newContext);
		firstNameField = ad.findElementByXPath("//UIATextField[1]");
		firstNameField.sendKeys(firstName);
		toolbar.ready();
		return this;
	}
	
	public BillingInfoScreen typeLastName(String lastName) {
		lastNameField = ad.findElementByXPath(uiMap.getProperty("billing.last_name"));
		lastNameField.sendKeys(lastName);
		toolbar.ready();
		return this;
	}
	
	public BillingInfoScreen typePhone(String phone) {
		phoneField = ad.findElementByXPath(uiMap.getProperty("billing.phone"));
		phoneField.sendKeys(phone);
		toolbar.ready();
		return this;
	}
	
	public BillingInfoScreen typeEmail(String email) {
		emailField = ad.findElementByXPath(uiMap.getProperty("billing.email"));
		emailField.sendKeys(email);
		toolbar.ready();
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
