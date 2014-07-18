package ru.ufsonline.eticket.screens;

import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

import ru.ufsonline.eticket.objects.Passenger;
import ru.ufsonline.eticket.utils.TestObject;
import ru.ufsonline.eticket.utils.Utils;

public class PassengersScreen extends BaseScreen {
	
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
	
}
