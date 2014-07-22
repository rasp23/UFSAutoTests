package ru.ufsonline.eticket.screens;

import org.openqa.selenium.WebElement;

import ru.ufsonline.eticket.objects.PaymentInfo;
import ru.ufsonline.eticket.utils.TestObject;
import ru.ufsonline.eticket.utils.Utils;
import io.appium.java_client.AppiumDriver;

public class PaymentDeatailsScreen extends BaseScreen {	
	
	private WebElement cardType;
	
	private WebElement cardNum;
	
	private WebElement cvn;
	
	private WebElement expMonth;
	
	private WebElement expYear;	
	
	private WebElement nextBtn;

	public PaymentDeatailsScreen(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
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
		expMonth = ad.findElementByXPath(uiMap.getProperty("payment.month_btn"));
		expMonth.sendKeys(sExpMonth);
		return this;
	}
	
	public PaymentDeatailsScreen selectExpYear(String sExpYear) {
		expYear = ad.findElementByXPath(uiMap.getProperty("payment.year_btn"));
		expYear.sendKeys(sExpYear);
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
