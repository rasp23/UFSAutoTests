package ru.ufsonline.eticket.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Reporter;

public class Logger implements ILogger {

	private final int defaultLogLevel = Severity.INFO;	

	protected static Logger defaultLogger = new Logger();
	
	protected int severity;

	public class Entry implements IEntry {
		
		protected int severity;

		protected String message;

		protected Throwable exception;

		public Entry(int severity, String message, Throwable exception) {
			this.severity = severity;
			this.message = message;
			this.exception = exception;
		}

		public int getSeverity() {
			return severity;
		}

		public String getMessage() {
			return message;
		}

		public Throwable getException() {
			return exception;
		}

		public String toString() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
			Date time = Calendar.getInstance().getTime();
			StringBuffer buffer = new StringBuffer(dateFormat.format(time));
			
			buffer.append(severityToString(severity)).append(": ");

			if (message != null) {
				buffer.append(message);
			}

			if (exception != null) {
				if (message != null) {
					buffer.append(", ");
				}
				buffer.append(exception);
			}

			return buffer.toString();
		}
	}
	
	public static String severityToString(int severity) {
		switch (severity) {
		case Severity.ERROR:
			return "ERROR";		
		case Severity.INFO:
			return "INFO";		
		case Severity.WARNING:
			return "WARNING";
		case Severity.DEBUG:
			return "DEBUG";
		}

		return null;
	}

	public static Logger getDefault() {
		return Logger.defaultLogger;
	}

	private Logger() {
		this.severity = defaultLogLevel;		
	}
	
	public void log(IEntry entry) {
		//Using TestNG reporter since we are going to move to TestNG 
		Reporter.log(entry.toString());
		//Output to console
		System.out.println(entry.toString());
	}

	public void log(int severity, String message, Throwable exception) {
		if (this.severity < severity)
			return;		
		log(new Entry(severity, message, exception));
	}

	public void log(int severity, String message) {
		log(severity, message, null);
	}

	public void log(int severity, Throwable exception) {
		log(severity, null, exception);
	}

	public void error(String message, Throwable exception) {
		log(Severity.ERROR, message, exception);
	}
	
	public void warning(String message, Throwable exception) {
		log(Severity.WARNING, message, exception);
	}

	public void info(String message, Throwable exception) {
		log(Severity.INFO, message, exception);
	}	

	public void debug(String message, Throwable exception) {
		log(Severity.DEBUG, message, exception);
	}

	public void error(String message) {
		error(message, null);
	}
	
	public void warning(String message) {
		warning(message, null);
	}

	public void info(String message) {
		info(message, null);
	}
	
	public void debug(String message) {
		debug(message, null);
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}
}
