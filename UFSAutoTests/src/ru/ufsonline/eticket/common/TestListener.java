package ru.ufsonline.eticket.common;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import ru.ufsonline.eticket.logger.Logger;
import ru.ufsonline.eticket.logger.LoggerFactory;

public class TestListener extends TestListenerAdapter {
	
	private Logger logger = LoggerFactory.getLogger(); 
	
	@Override
    public void onStart(ITestContext tr) { 
    }
	
	@Override
    public void onTestStart(ITestResult tr) {		
		logger.info("==========================================");
		logger.info(tr.getName() + " Started");
		logger.info("==========================================");
    }	
	
	@Override
    public void onTestSuccess(ITestResult tr) { 
		logger.info("==========================================");
		logger.info(tr.getName() + " PASSED");
		logger.info("==========================================");
    }
	
	@Override
    public void onTestSkipped(ITestResult tr) { 
		logger.info("==========================================");
		logger.info(tr.getName() + " SKIPPED");
		logger.info("==========================================");
    }
	
	@Override
    public void onTestFailure(ITestResult tr) { 
		logger.info("==========================================");
		logger.info(tr.getName() + " FAILED");
		logger.info("==========================================");
    }
	
	@Override
    public void onFinish(ITestContext tr) { 
    }
	
}
