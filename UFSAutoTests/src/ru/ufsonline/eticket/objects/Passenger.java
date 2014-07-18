package ru.ufsonline.eticket.objects;

import ru.ufsonline.eticket.utils.TestObject;

public class Passenger {
	
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
	
	private boolean checkIn = true;
	
	public Passenger(TestObject passenger) {
		this.surname = passenger.getProperty("surname");
		this.name = passenger.getProperty("name");
		this.secondName = passenger.getProperty("secondName");
		this.idDocType = passenger.getProperty("idDocType");
		this.docNumber = passenger.getProperty("docNumber");
		this.gender = passenger.getProperty("gender");
		this.bithDate = passenger.getProperty("birthDate");
		this.countryOfDocIssue = passenger.getProperty("countryOfDocIssue");
		this.bithPlace = passenger.getProperty("bithPlace");
		this.email = passenger.getProperty("email");
		this.phone = passenger.getProperty("phone");
		String checkIn = passenger.getProperty("checkIn");
		if (checkIn != null) {
			this.checkIn = Boolean.valueOf(checkIn);
		}
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

	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}
	
}
