package ru.ufsonline.eticket.objects;

import ru.ufsonline.eticket.utils.TestObject;

public class PaymentInfo {
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String email;
	
	private String cardType;
	
	private String cardNumber;
	
	private String cvn;
	
	private String expDate;
	
	public PaymentInfo(TestObject paymentInfo) {
		this.firstName = paymentInfo.getProperty("firstName");
		this.lastName = paymentInfo.getProperty("lastName");
		this.phoneNumber = paymentInfo.getProperty("phoneNumber");
		this.email = paymentInfo.getProperty("email");
		this.cardType = paymentInfo.getProperty("cardType");
		this.cardNumber = paymentInfo.getProperty("cardNumber");
		this.cvn = paymentInfo.getProperty("cvn");
		this.expDate = paymentInfo.getProperty("expDate");			
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvn() {
		return cvn;
	}

	public void setCvn(String cvn) {
		this.cvn = cvn;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}	
	
}
