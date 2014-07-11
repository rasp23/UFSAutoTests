package ru.ufsonline.eticket.tests;

import org.testng.annotations.Test;

import ru.ufsonline.eticket.screens.MainScreen.Language;
import ru.ufsonline.eticket.utils.GlobalProvider;

public class TestMainScreen extends TestBase {

	@Test(dataProvider="GlobalProvider", dataProviderClass=GlobalProvider.class)
	public void testSetLanguage(String language, String text) {
		mainScreen.setLanguage(Language.valueOf(language));
		mainScreen.verifyMyTicketsText(text);
	}	
}
