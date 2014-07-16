package ru.ufsonline.eticket.screens;

import java.util.List;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

import ru.ufsonline.eticket.objects.Passenger;
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
	
	private Picker picker;

	public PassengersScreen(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
		picker = new Picker(ad);
	}
	
	public PassengersScreen typeSurname(String surname) {
		surnameField = ad.findElementByXPath(uiMap.getProperty("passenger.surname"));
		surnameField.sendKeys(surname);
		return this;
	}
	
	public PassengersScreen typeName(String name) {
		nameField = ad.findElementByXPath(uiMap.getProperty("passenger.name"));
		nameField.sendKeys(name);
		return this;
	}
	
	public PassengersScreen typeSecondName(String secondName) {
		secondNameField = ad.findElementByXPath(uiMap.getProperty("passenger.second_name"));
		secondNameField.sendKeys(secondName);
		return this;
	}
	
	public PassengersScreen selectIdDocType(String idDocType) {
		idDocTypeBtn = ad.findElementByXPath(uiMap.getProperty("passenger.doc_btn"));
		idDocTypeBtn.click();
		picker.pickItem("1",idDocType);
		return this;
	}
	
	public PassengersScreen typeDocNumber(String docNum) {
		docNumberField = ad.findElementByXPath(uiMap.getProperty("passenger.doc_num"));
		docNumberField.sendKeys(docNum);
		return this;
	}
	
	public PassengersScreen selectGender(String gender) {
		genderBtn = ad.findElementByXPath(uiMap.getProperty("passenger.gender"));
		genderBtn.click();
		picker.pickItem("1", gender);
		return this;
	}
	
	public PassengersScreen selectBirthDate(String sBirthDate) {
		List<String> lBirthDate = Utils.getList(sBirthDate);
		birthdayBtn = ad.findElementByXPath(uiMap.getProperty("passenger.birthday_btn"));		
		birthdayBtn.click();		
		picker.pickItem("1", lBirthDate.get(0));
		picker.pickItem("2", lBirthDate.get(1));
		picker.pickItem("3", lBirthDate.get(2));
		return this;
	}
	
	public PassengersScreen selectDocCountry(String docCountry) {
		docCountryBtn = ad.findElementByXPath(uiMap.getProperty("passenger.doc_country_btn"));
		docCountryBtn.click();
		picker.pickItem("1", docCountry);
		return this;
	}
	
	public PassengersScreen typeBithPlace(String birthPlace) {
		birthPlaceField = ad.findElementByXPath(uiMap.getProperty("passenger.birth_place"));
		birthPlaceField.sendKeys(birthPlace);
		return this;
	}
	
	public PassengersScreen typeEmail(String email) {
		emailField = ad.findElementByXPath(uiMap.getProperty("passenger.email"));
		emailField.sendKeys(email);
		return this;
	}
	
	public PassengersScreen typePhone(String phone) {
		phoneField = ad.findElementByXPath(uiMap.getProperty("passenger.phone"));
		phoneField.sendKeys(phone);
		return this;
	}
	
	public PaymentScreen clickPayment() {
		paymentBtn = ad.findElementByXPath(uiMap.getProperty("passenger.payment"));
		paymentBtn.click();
		return new PaymentScreen(ad);
	}
	
	public PassengersScreen fillPassengerData(Passenger passenger) {
		typeSurname(passenger.getSurname());
		typeName(passenger.getName());
		typeSecondName(passenger.getSecondName());
		selectIdDocType(passenger.getIdDocType());
		typeDocNumber(passenger.getDocNumber());
		selectGender(passenger.getGender());
		selectBirthDate(passenger.getBithDate());
		selectDocCountry(passenger.getCountryOfDocIssue());
		typeBithPlace(passenger.getBithPlace());
		typeEmail(passenger.getEmail());
		typePhone(passenger.getPhone());
		
		return this;
	}
	
}
