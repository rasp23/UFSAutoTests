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
import ru.ufsonline.eticket.screens.CarScreen;
import ru.ufsonline.eticket.screens.MainScreen;
import ru.ufsonline.eticket.screens.PassengersScreen;
import ru.ufsonline.eticket.screens.RouteScreen;
import ru.ufsonline.eticket.screens.SeatScreen;
import ru.ufsonline.eticket.screens.TrainScreen;
import ru.ufsonline.eticket.screens.UserAgreementDialog;
import ru.ufsonline.eticket.utils.PropertiesUtil;
import ru.ufsonline.eticket.utils.Utils;

public class TestBase {
	
	private AppiumDriver ad = null;
	
	protected UserAgreementDialog agreementDialog = null;
	
	protected MainScreen mainScreen = null;
	
	protected RouteScreen routeScreen = null;
	
	protected TrainScreen trainScreen = null;
	
	protected CarScreen carScreen = null;
	
	protected SeatScreen seatScreen = null;
	
	protected PassengersScreen passengersScreen;
	
	@BeforeClass
	public void beforeClass() {		
		PropertiesUtil config = new PropertiesUtil(CommonConstants.CONFIG_FILE);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.VERSION, config.getProperty("version"));
		capabilities.setCapability(CapabilityType.PLATFORM, config.getProperty("platform"));
		capabilities.setCapability("device", config.getProperty("device"));
		capabilities.setCapability("app", config.getProperty("app_path"));
		AppiumSession.uiMap = new PropertiesUtil(CommonConstants.UIMAP);	
		AppiumSession.timeout = Long.valueOf(config.getProperty("timeout"));
		
		try {
			ad = new AppiumDriver(new URL(config.getProperty("appium_server")), capabilities);			
			Utils.sleep(1000);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		ad.manage().timeouts().implicitlyWait(AppiumSession.timeout/1000, TimeUnit.SECONDS);			
		agreementDialog = new UserAgreementDialog(ad);
		mainScreen = agreementDialog.acceptUserAgreement();		
	}
	
	@AfterClass
	public void afterClass() {
		ad.quit();
	}
}