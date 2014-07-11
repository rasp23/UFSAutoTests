package ru.ufsonline.eticket.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.ufsonline.eticket.common.CommonConstants;

import com.applique.utils.TestConfigDocument.TestConfig.Test.Case;

public class GlobalProvider {
	
	private static Iterator<String> ids;	    
	
	@Test
	@DataProvider(name = "GlobalProvider")
	public static Iterator<Object[]> testApplique(Method m) {
		String xmlFile = m.getDeclaringClass().getName().substring( m.getDeclaringClass().getName().lastIndexOf(".") + 1) + ".xml";
		return commonDataProvider(CommonConstants.TEST_DATA + xmlFile, m.getName() );
	}
	
	public static String getCaseId() {
        if(ids!= null && ids.hasNext()) {
            return ids.next();
        } else {
            return "";
        }
    }
	
	public static Iterator<Object[]> commonDataProvider(String testConfigFile, String testName) {
		TestConfig testConfig = new TestConfig(testConfigFile);
		Case[] cases = testConfig.getCases(testName);		
		List<Object[]> casesParameters = new ArrayList<Object[]>();
		List<String> list = new ArrayList<String>();
		
		for(int i=0; i<cases.length; i++) {
			casesParameters.add(cases[i].getParameterArray());
			if(cases[i].getId() != null) {
                list.add(cases[i].getId());
            } else {
                list.add("");
            }        
            ids = list.iterator();
		}
		return casesParameters.iterator();
	}
	
}
