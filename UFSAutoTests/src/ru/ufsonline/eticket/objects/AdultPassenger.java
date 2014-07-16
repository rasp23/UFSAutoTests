package ru.ufsonline.eticket.objects;

import ru.ufsonline.eticket.utils.TestObject;

public class AdultPassenger {
	
	private String surname;
	
	private String name;
	
	private String secondName;
	
	private String idDocType;
	
	private String docNumber;
	
	private String gender;
	
	private String bithDate;
	
	private String countryOfDocIssue;
	
	private String bithPlace;
	
	private String email;
	
	private String phone;
	
	private boolean onlineCheckin;
	
	public AdultPassenger(TestObject passengerInfo) {
		this.surname = passengerInfo.getProperty("surname");
		this.name = passengerInfo.getProperty("name");
		this.secondName = passengerInfo.getProperty("secondName");
		this.idDocType = passengerInfo.getProperty("idDocType");
		this.docNumber = passengerInfo.getProperty("docNumber");
		this.gender = passengerInfo.getProperty("gender");
		this.bithDate = passengerInfo.getProperty("bithDate");
		this.countryOfDocIssue = passengerInfo.getProperty("countryOfDocIssue");
		this.bithPlace = passengerInfo.getProperty("bithPlace");
		this.email = passengerInfo.getProperty("email");
		this.phone = passengerInfo.getProperty("phone");
		this.onlineCheckin = Boolean.valueOf(passengerInfo.getProperty("onlineCheckin"));
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getIdDocType() {
		return idDocType;
	}

	public void setIdDocType(String idDocType) {
		this.idDocType = idDocType;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBithDate() {
		return bithDate;
	}

	public void setBithDate(String bithDate) {
		this.bithDate = bithDate;
	}

	public String getCountryOfDocIssue() {
		return countryOfDocIssue;
	}

	public void setCountryOfDocIssue(String countryOfDocIssue) {
		this.countryOfDocIssue = countryOfDocIssue;
	}

	public String getBithPlace() {
		return bithPlace;
	}

	public void setBithPlace(String bithPlace) {
		this.bithPlace = bithPlace;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isOnlineCheckin() {
		return onlineCheckin;
	}

	public void setOnlineCheckin(boolean onlineCheckin) {
		this.onlineCheckin = onlineCheckin;
	}
	
}
