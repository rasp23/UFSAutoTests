package ru.ufsonline.eticket.screens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ru.ufsonline.eticket.objects.Passenger;
import ru.ufsonline.eticket.utils.TestObject;
import ru.ufsonline.eticket.utils.Utils;

public class PassengersScreen extends NavBarScreen {
	
	private WebElement surnameField;
	
	private WebElement nameField;
	
	private WebElement secondNameField;
	
	private WebElement idDocTypeBtn;
	
	private WebElement docNumberField;
	
	private WebElement genderBtn;
	
	private WebElement birthdayBtn;
	
	private WebElement docCountryBtn;
	
	private WebElement birthPlaceField;
	
	private WebElement emailField;
	
	private WebElement phoneField;
	
	private WebElement paymentBtn;
	
	private WebElement checkInSwitch;
	
	private WebElement eRegSwitch;
	
	private WebElement coupeGender;
	
	private Picker picker;

	public PassengersScreen(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
		picker = new Picker(ad);
	}
	
	public PassengersScreen typeSurname(String num, String surname) {
		surnameField = ad.findElementByXPath(uiMap.getProperty("passenger.surname").replace("NUM", num));
		surnameField.sendKeys(surname);
		toolbar.ready();
		return this;
	}
	
	public PassengersScreen typeName(String num, String name) {
		nameField = ad.findElementByXPath(uiMap.getProperty("passenger.name").replace("NUM", num));
		nameField.sendKeys(name);
		toolbar.ready();
		return this;
	}
	
	public PassengersScreen typeSecondName(String num, String secondName) {
		secondNameField = ad.findElementByXPath(uiMap.getProperty("passenger.second_name").replace("NUM", num));
		secondNameField.sendKeys(secondName);
		toolbar.ready();
		return this;
	}
	
	public PassengersScreen selectIdDocType(String num, String idDocType) {
		idDocTypeBtn = ad.findElementByXPath(uiMap.getProperty("passenger.doc_btn").replace("NUM", num));
		idDocTypeBtn.click();
		picker.pickItem(idDocType);
		return this;
	}
	
	public PassengersScreen typeDocNumber(String num, String docNum) {
		docNumberField = ad.findElementByXPath(uiMap.getProperty("passenger.doc_num").replace("NUM", num));
		docNumberField.sendKeys(docNum);
		toolbar.ready();
		return this;
	}
	
	public PassengersScreen selectGender(String num, String gender) {
		genderBtn = ad.findElementByXPath(uiMap.getProperty("passenger.gender").replace("NUM", num));
		genderBtn.click();
		picker.pickItem(gender);
		return this;
	}
	
	public PassengersScreen selectBirthDate(String num, String sBirthDate) {
		List<String> lBirthDate = Utils.getList(sBirthDate);
		birthdayBtn = ad.findElementByXPath(uiMap.getProperty("passenger.birthday_btn").replace("NUM", num));		
		birthdayBtn.click();		
		picker.pickItem(lBirthDate);
		return this;
	}
	
	public PassengersScreen selectDocCountry(String num, String docCountry) {
		if (docCountry != null) {
			docCountryBtn = ad.findElementByXPath(uiMap.getProperty("passenger.doc_country_btn").replace("NUM", num));
			docCountryBtn.click();
			picker.pickItem(docCountry);
		}
		return this;
	}
	
	public PassengersScreen typeBithPlace(String num, String birthPlace) {
		birthPlaceField = ad.findElementByXPath(uiMap.getProperty("passenger.birth_place").replace("NUM", num));
		birthPlaceField.sendKeys(birthPlace);
		toolbar.ready();
		return this;
	}
	
	public PassengersScreen typeEmail(String num, String email) {
		emailField = ad.findElementByXPath(uiMap.getProperty("passenger.email").replace("NUM", num));
		emailField.sendKeys(email);
		toolbar.ready();
		return this;
	}
	
	public PassengersScreen typePhone(String num, String phone) {
		phoneField = ad.findElementByXPath(uiMap.getProperty("passenger.phone").replace("NUM", num));
		phoneField.sendKeys(phone);
		toolbar.ready();
		return this;
	}
	
	public PassengersScreen selectGenderCoupe(String genderType) {
		coupeGender =ad.findElement(By.xpath(uiMap.getProperty("passenger.genderCoupeType")));
		coupeGender.click();
		picker.pickItem(genderType);
		return this;
		
	}
		
	public PassengersScreen enableCheckIn(String num) {
		checkInSwitch = ad.findElementByXPath(uiMap.getProperty("passenger.checkin").replace("NUM", num));
		if (checkInSwitch.getAttribute("value").equals("0")) {
			checkInSwitch.click();
		}
		return this;
	}
	
	public PopupDialog disableCheckIn(String num) {
		checkInSwitch = ad.findElementByXPath(uiMap.getProperty("passenger.checkin").replace("NUM", num));
		if (checkInSwitch.getAttribute("value").equals("1")){
			checkInSwitch.click();
		}		
		return new PopupDialog(ad);
	}
	
	public BookingSummaryScreen clickPayment() {
		paymentBtn = ad.findElementByXPath(uiMap.getProperty("passenger.payment"));
		paymentBtn.click();
		return new BookingSummaryScreen(ad);
	}
	
	public PassengersScreen clickPaymentMsg(){
		paymentBtn = ad.findElementByXPath(uiMap.getProperty("passenger.payment"));
		paymentBtn.click();
		return this;
	}
	
	public PassengersScreen fillPassengerData(String num, Passenger passenger) {
		typeSurname(num, passenger.getSurname());
		typeName(num, passenger.getName());
		typeSecondName(num, passenger.getSecondName());
		selectIdDocType(num, passenger.getIdDocType());
		typeDocNumber(num, passenger.getDocNumber());
		selectGender(num, passenger.getGender());
		selectBirthDate(num, passenger.getBithDate());
		selectDocCountry(num, passenger.getCountryOfDocIssue());
		typeBithPlace(num, passenger.getBithPlace());
		typeEmail(num, passenger.getEmail());
		typePhone(num, passenger.getPhone());
		if (passenger.isCheckIn()) {
			enableCheckIn(num);
		} else {
			disableCheckIn(num);
		}
		
		return this;
	}
	
	public PassengersScreen fillPassengersData(String sPassengers) {
		List<String> lPassengers = new ArrayList<String>();
		lPassengers = Utils.getList(sPassengers);
		for (String s:lPassengers) {
			fillPassengerData(String.valueOf(lPassengers.indexOf(s) + 1), new Passenger(new TestObject(s)));
		}
		
		return this;
	}
	
	public PassengersScreen verifyMessageApp(String expectedText) {
		String loc = "//UIAStaticText[contains(@label,\"TEXT\")]";
		//String debug = loc.replace("TEXT", expectedText);
		try {
			ad.findElementByXPath(loc.replace("TEXT", expectedText));			
		} catch(Exception e) {
			Assert.fail();
		}		
		
		return this;
	}

	public PassengersScreen verifyEregSwitchOn() {
		eRegSwitch = ad.findElement(By.xpath(uiMap.getProperty("passenger.checkin")));
		String switchOn = "1";
		//String debug  = eRegSwitch.getAttribute("value");
		Assert.assertTrue(eRegSwitch.getAttribute("value").equals(switchOn), "Electronic regisration is not turned on as a default!");
		return this;
	}

	public PassengersScreen setDate(String passNum, String date) {
		selectBirthDate(passNum,date);
		return this;
	}
	
	public PassengersScreen verifySetedDate(String date) {
		String birthdayDate = ad.findElement(By.xpath(uiMap.getProperty("passenger.birthday_date"))).getAttribute("value");
		Assert.assertNotEquals(birthdayDate, date, "There is an opportunity to select date < 1900!");
		return this;
	}

	public PassengersScreen setCurrDateMinus(String passNum, String minusYearNum, String minusDayNum) {
		List<String> lcurDate = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("E d MMMM, yyyy", Locale.US);
		String curDate = dateFormat.format(new Date());
		lcurDate = Utils.getList(curDate, " ");
		Integer curYear = Integer.valueOf(lcurDate.get(3));
		Integer curDay = Integer.valueOf(lcurDate.get(1));
		String curMonth = lcurDate.get(2);
		String neededYear = String.valueOf(curYear-Integer.valueOf(minusYearNum));
		String neededDay = String.valueOf(curDay-Integer.valueOf(minusDayNum));
		String neededDate = curMonth+neededDay+","+neededYear;
		selectBirthDate(passNum,neededDate);
		return this;
	}

	public PassengersScreen verifyDateAfterCur(String passNum, String minusYearNum, String minusDayNum) {
		setCurrDateMinus(passNum, "0", "0"); // Setting current date for second passenger
		String birthdayDateCur = ad.findElement(By.xpath(uiMap.getProperty("passenger.birthday_date"))).getAttribute("value");
		setCurrDateMinus(passNum, minusYearNum, minusDayNum);
		String birthdayDateAfter = ad.findElement(By.xpath(uiMap.getProperty("passenger.birthday_date"))).getAttribute("value");
		Assert.assertNotEquals(birthdayDateCur, birthdayDateAfter, "There is an opportunity to select date > current!");
		return this;

	}

	public PassengersScreen setCoupeNoGender() {
		coupeGender =ad.findElement(By.xpath(uiMap.getProperty("passenger.genderCoupeType")));
		coupeGender.click();
		return this;
	}

	public void verifyGenderCoupe(String expectedGenderType) {
		coupeGender =ad.findElement(By.xpath(uiMap.getProperty("passenger.genderCoupeType")));
		String actualGenderType = coupeGender.getAttribute("value");
		Assert.assertEquals(actualGenderType, expectedGenderType);
	}




}
