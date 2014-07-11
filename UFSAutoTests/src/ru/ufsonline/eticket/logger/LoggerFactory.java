package ru.ufsonline.eticket.logger;

import ru.ufsonline.eticket.common.CommonConstants;
import ru.ufsonline.eticket.utils.PropertiesUtil;

public class LoggerFactory {
	
	public static Logger getLogger() {
		Logger logger = Logger.getDefault();
		PropertiesUtil config = new PropertiesUtil(CommonConstants.CONFIG_FILE);		
		logger.setSeverity(Integer.parseInt(config.getProperty("log_level")));		
		return logger;
	}
}
