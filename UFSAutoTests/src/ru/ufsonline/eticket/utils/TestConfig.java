package ru.ufsonline.eticket.utils;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import com.applique.utils.TestConfigDocument;
import com.applique.utils.TestConfigDocument.TestConfig.SetUp.Parameter;

public class TestConfig {
	
	private TestConfigDocument.TestConfig.SetUp setUp;
	private TestConfigDocument.TestConfig.Test[] tests;
	
	public TestConfig(String fileName) {
		TestConfigDocument doc = TestConfigDocument.Factory.newInstance();
		doc.setTestConfig(TestConfigDocument.TestConfig.Factory.newInstance());
		doc.getTestConfig().setTestArray(new TestConfigDocument.TestConfig.Test[]{});
		doc.getTestConfig().setSetUp(TestConfigDocument.TestConfig.SetUp.Factory.newInstance());
		try {
			doc = TestConfigDocument.Factory.parse(new File(fileName));
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tests = doc.getTestConfig().getTestArray();
		setUp = doc.getTestConfig().getSetUp();
	}
	
	public TestConfigDocument.TestConfig.Test.Case[] getCases(String name) {
		for(int i=0; i<tests.length; i++) {
			if(tests[i].getName().equals(name)) {
				return tests[i].getCaseArray();
			}
		}
		return new TestConfigDocument.TestConfig.Test.Case[]{};
	}
	
	public String getParameter(String name) {
		if (name != null) {
			Parameter[] lsParams = setUp.getParameterArray();
			for (int i = 0; i < lsParams.length; i++) {
				if (name.equals(lsParams[i].getName())) {
					return lsParams[i].getStringValue();
				}
			}
		} //else Logger.getDefault().error("Parameter was passed as NULL!!!");
		return "";
	}	
	
	public String[] getSetUpParameters() {
		Parameter[] parameterArray = setUp.getParameterArray();
		String[] parameters = new String[parameterArray.length];
		for(int i = 0; i<parameterArray.length; i++) {
			parameters[i] = parameterArray[i].getStringValue();
		}
		return parameters;
	}
}
