package ru.ufsonline.eticket.screens;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

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
	
	private Picker picker;

	public PassengersScreen(AppiumDriver ad) {
		super(ad);
		toolbar = new Toolbar(ad);
		picker = new Picker(ad);
	}
	
	public void typeSurname(String surname) {
		surnameField = ad.findElementByXPath(uiMap.getProperty("passenger.surname"));
		surnameField.sendKeys(surname);
	}
	
	public void typeName(String name) {
		nameField = ad.findElementByXPath(uiMap.getProperty("passenger.name"));
		nameField.sendKeys(name);
	}
	
	public void typeSecondName(String secondName) {
		secondNameField = ad.findElementByXPath(uiMap.getProperty("passenger.second_name"));
		secondNameField.sendKeys(secondName);
	}
	
	public void selectIdDocType(String idDocType) {
		idDocTypeBtn = ad.findElementByXPath(uiMap.getProperty("passenger.doc_btn"));
		idDocTypeBtn.click();
		picker.pickItem("1",idDocType);
	}
	
	public void typeDocNumber(String docNum) {
		docNumberField = ad.findElementByXPath(uiMap.getProperty("passenger.doc_num"));
		docNumberField.sendKeys(docNum);
	}
	
	public void selectGender(String gender) {
		genderBtn = ad.findElementByXPath(uiMap.getProperty("passenger.gender"));
		genderBtn.click();
		picker.pickItem("1", gender);
	}
}
