package ru.ufsonline.eticket.tests;

import org.testng.annotations.Test;

import ru.ufsonline.eticket.objects.Passenger;
import ru.ufsonline.eticket.objects.SearchProperties;
import ru.ufsonline.eticket.screens.BillingInfoScreen;
import ru.ufsonline.eticket.utils.GlobalProvider;
import ru.ufsonline.eticket.utils.TestObject;

public class TestBuyTicket extends TestBase {
	
	@Test
	public void testBuyTicket() {		
	} 
	
	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSuccessfullSearch(String sSearchProps, String expectedTrain, String carType, String carNumber, String seatFrom, String seatTo, 
			String level, String sPassengersData, String sPaymentInfo) {	
		TestObject searchProps = new TestObject(sSearchProps);
		
		route = main.tapPurchase();
//		routeScreen.fillSearchProperties(new SearchProperties(searchProps));		
		train = route.tapFind();
		String trainIndex = train.verifyTrainPresent(expectedTrain);	
		car = train.selectCarType(trainIndex, carType);		
		car.selectCar(carNumber);
		seat = car.selectSeat().hideHint();
//		seatScreen.pickRange(seatFrom, seatTo);
		seat.pickLocationLevel(level);
		passengers = seat.clickPassengerData();
		passengers.fillPassengersData(sPassengersData);
		billingInfo = passengers.clickPayment().clickNext().clickYes(BillingInfoScreen.class);
		billingInfo.fillBillingInfo(sPaymentInfo);
		paymentDetails = billingInfo.clickNext();
		paymentDetails.fillPaymentDetails(sPaymentInfo);
		reviewOrder = paymentDetails.clickNext();
		reviewOrder.clickPay();
	}	 
}
