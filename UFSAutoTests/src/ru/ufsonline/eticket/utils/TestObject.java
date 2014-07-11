package ru.ufsonline.eticket.utils;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import ru.ufsonline.eticket.common.CommonConstants;

import com.applique.utils.TestDataDocument;

public class TestObject {
	
	private TestDataDocument.TestData.Data data = TestDataDocument.TestData.Data.Factory.newInstance();
	public static final String NULL_STRING = "null";
	
	public TestObject(String name) {		
		data.addNewProperties();
		TestDataDocument doc = TestDataDocument.Factory.newInstance();
		try {
			doc = TestDataDocument.Factory.parse(new File(CommonConstants.TEST_DATA_FILE));
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TestDataDocument.TestData.Data[] dataArray = doc.getTestData().getDataArray();
		for(int i=0; i<dataArray.length; i++) {
			if(dataArray[i].getName().equals(name)) {
				data = dataArray[i];
			}
		}
	}
	
	public String getProperty(String name) {		
		TestDataDocument.TestData.Data.Properties.Property[] properties = data.getProperties().getPropertyArray();
		for(int i=0; i<properties.length; i++) {
			if(properties[i].getName().equals(name)) {
				return properties[i].getStringValue();
			}
		}
		return null;
	}
	
	public TestDataDocument.TestData.Data.Properties.Property[] getProperties() {
		return data.getProperties().getPropertyArray();
	}
}