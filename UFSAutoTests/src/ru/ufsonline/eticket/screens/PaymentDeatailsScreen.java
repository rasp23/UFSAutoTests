package ru.ufsonline.eticket.screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ru.ufsonline.eticket.objects.PaymentInfo;
import ru.ufsonline.eticket.utils.TestObject;
import ru.ufsonline.eticket.utils.Utils;
import io.appium.java_client.AppiumDriver;

public class PaymentDeatailsScreen extends NavBarScreen {	
	
	private WebElement cardType;
	
	private WebElement cardNum;
	
	private WebElement cvn;
	
	private Select expMonth;
	
	private Select expYear;	
	
	private WebElement nextBtn;

	public PaymentDeatailsScreen(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
		setWebViewContext();
	}
	
	public PaymentDeatailsScreen selectCardType(String sCardType) {
		cardType = ad.findElementByXPath(uiMap.getProperty("payment.card_type").replace("CARD", sCardType));				
		cardType.click();
		return this;
	}
	
	public PaymentDeatailsScreen typeCardNumber(String sCardNum) {
		cardNum = ad.findElementByXPath(uiMap.getProperty("payment.card_num"));
		cardNum.sendKeys(sCardNum);
		return this;
	}

	public PaymentDeatailsScreen typeCvn(String sCvn) {
		cvn = ad.findElementByXPath(uiMap.getProperty("payment.cvn"));
		cvn.sendKeys(sCvn);
		return this;	
	}
	
	public PaymentDeatailsScreen selectExpMonth(String sExpMonth) {
		expMonth = new Select(ad.findElementByXPath(uiMap.getProperty("payment.month_btn")));		
		expMonth.selectByValue(sExpMonth);
		return this;
	}
	
	public PaymentDeatailsScreen selectExpYear(String sExpYear) {
		expYear = new Select(ad.findElementByXPath(uiMap.getProperty("payment.year_btn")));
		expYear.selectByValue(sExpYear);
		return this;
	}
	
	public ReviewOrderScreen clickNext() {
		nextBtn = ad.findElementByXPath(uiMap.getProperty("payment.next"));
		nextBtn.click();
		return new ReviewOrderScreen(ad);
	}
	
	public PaymentDeatailsScreen fillPaymentDetails(String sPaymentInfo) {
		PaymentInfo paymentInfo = new PaymentInfo(new TestObject(sPaymentInfo));
		selectCardType(paymentInfo.getCardType());
		typeCardNumber(paymentInfo.getCardNumber());
		typeCvn(paymentInfo.getCvn());
		selectExpMonth(Utils.getList(paymentInfo.getExpDate()).get(0));		
		selectExpYear(Utils.getList(paymentInfo.getExpDate()).get(1));		
		return this;
	}
}
