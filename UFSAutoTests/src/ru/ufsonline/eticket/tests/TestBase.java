package ru.ufsonline.eticket.tests;
import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import ru.ufsonline.eticket.common.AppiumSession;
import ru.ufsonline.eticket.common.CommonConstants;
import ru.ufsonline.eticket.logger.Logger;
import ru.ufsonline.eticket.logger.LoggerFactory;
import ru.ufsonline.eticket.screens.BillingInfoScreen;
import ru.ufsonline.eticket.screens.CarScreen;
import ru.ufsonline.eticket.screens.MainScreen;
import ru.ufsonline.eticket.screens.PassengersScreen;
import ru.ufsonline.eticket.screens.PaymentDeatailsScreen;
import ru.ufsonline.eticket.screens.ReviewOrderScreen;
import ru.ufsonline.eticket.screens.RouteScreen;
import ru.ufsonline.eticket.screens.SeatScreen;
import ru.ufsonline.eticket.screens.TrainScreen;
import ru.ufsonline.eticket.screens.UserAgreementDialog;
import ru.ufsonline.eticket.utils.PropertiesUtil;
import ru.ufsonline.eticket.utils.Utils;

public class TestBase {
	
	private AppiumDriver ad = null;
	
	protected UserAgreementDialog agreement = null;
	
	protected MainScreen main = null;
	
	protected RouteScreen route = null;
	
	protected TrainScreen train = null;
	
	protected CarScreen car = null;
	
	protected SeatScreen seat = null;
	
	protected PassengersScreen passengers = null;
	
	protected BillingInfoScreen billingInfo = null;
	
	protected PaymentDeatailsScreen paymentDetails = null;
	
	protected ReviewOrderScreen reviewOrder = null;
	
	private Logger logger = LoggerFactory.getLogger(); 
	
	@BeforeClass
	public void beforeClass() {		
		logger.info("==========================================");
		logger.info(this.getClass().getName());
		logger.info("==========================================");
		
		PropertiesUtil config = new PropertiesUtil(CommonConstants.CONFIG_FILE);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.VERSION, config.getProperty("version"));
		capabilities.setCapability(CapabilityType.PLATFORM, config.getProperty("platform"));
		capabilities.setCapability("device", config.getProperty("device"));
		capabilities.setCapability("app", config.getProperty("app_path"));
		capabilities.setCapability("newCommandTimeout", AppiumSession.timeout/6000);
		
		AppiumSession.uiMap = new PropertiesUtil(CommonConstants.UIMAP);	
		AppiumSession.timeout = Long.valueOf(config.getProperty("timeout"));	
		
		try {
			ad = new AppiumDriver(new URL(config.getProperty("appium_server")), capabilities);			
			Utils.sleep(AppiumSession.timeout/30);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		ad.manage().timeouts().implicitlyWait(AppiumSession.timeout/500, TimeUnit.SECONDS);			
		agreement = new UserAgreementDialog(ad);
		main = agreement.acceptUserAgreement();	
	}
	
	@AfterClass
	public void afterClass() {
		ad.quit();		
	}
}