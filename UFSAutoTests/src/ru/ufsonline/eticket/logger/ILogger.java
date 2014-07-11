package ru.ufsonline.eticket.logger;

public interface ILogger {
	
	public static interface Severity {
		
		final static int ERROR = 1;
		
		final static int WARNING = 2;

		final static int INFO = 3;			

		final static int DEBUG = 4;
	}

	public interface IEntry {
		
		int getSeverity();

		String getMessage();

		Throwable getException();
	}

	void setSeverity(int severity);

	void log(IEntry entry);
}
