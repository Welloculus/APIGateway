package com.welloculus.apigateway.util;


import org.slf4j.LoggerFactory;

import com.welloculus.apigateway.exception.WelloculusException;

import org.slf4j.Logger;

/**
 * This is a kind of wrapper class over slf4j logger class. This contains the
 * same functionality, but the only difference is that it encrypts the logs
 * before logging them.
 * 
 */
public class CustomLogger {

	private static final String LOGGER_NAME = "API_GATEWAY";
	
	private static CustomLogger customLogger = null;
	Logger logger = null;
	
	private CustomLogger(Logger basicLogger) {
		this.logger = basicLogger;
	}
	
	public static CustomLogger getLogger() {
		if (customLogger == null) {
			customLogger = new CustomLogger((Logger)LoggerFactory.getLogger(LOGGER_NAME));
		}
		return customLogger;
	}

	public void error(Exception e){
		error(e.getMessage());
		debug(e);
	}
	public void error(String message) {
		message = getCaller() + message;
		logger.error(message);
	}

	public void debug(String message) {
		message = getCaller() + message;
		logger.debug(message);
	}
	
	public void debug(String message, Exception e) {
		message = getCaller() + message;
		if(e instanceof WelloculusException){		
			WelloculusException ctsmException = (WelloculusException) e;
			message += " Exception: - "+ctsmException.getErrorCode().name()+" : "+ctsmException.getErrorMessage();
		}
		logger.debug(message);
	}

	public void debug(Exception e) {
		String message = getCaller();
		if(e instanceof WelloculusException){		
			WelloculusException ctsmException = (WelloculusException) e;
			message += " Exception: - "+ctsmException.getErrorCode().name()+" : "+ctsmException.getErrorMessage();
			if(ctsmException.getException()!=null){
			  ctsmException.getException().printStackTrace();
			}
		}
		logger.debug(message);
		e.printStackTrace();
	}

	/**
	 * Call this method when an information message is required. Use this method
	 * carefully such that no data is printed as a part of it. Also avoid more
	 * usage of this. Instead use debug method to print more frequently
	 * occurring messages.
	 */
	public void info(String message) {
		message = getCaller() + message;
		logger.info(message);
	}

	/**
	 * Call this method when a debug message for indicating a start of method is
	 * required
	 */
	public void debugStartOfMethod() {
		// Although start of method info logs are converted to debug level, but
		// we need not encrypt these due to high occurrence frequency and
		// absence of any data at any point of time.
		String message = getCaller();
		message += "Start of Method";
		logger.debug(message);
	}

	/**
	 * Call this method when a debug message for indicating an end of method is
	 * required
	 */
	public void debugEndOfMethod() {
		// Although end of method info logs are converted to debug level, but we
		// need not encrypt these due to high occurrence frequency and absence
		// of any data at any point of time. 
		String message = getCaller();
		message += "End of Method";
		logger.debug(message);
	}

	public static String getCaller() {
		String tag = "";
		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			tag = (stackTraceElements[3].getFileName()).replaceAll(".java", ".")+ stackTraceElements[3].getMethodName() + "()";
		} catch (Exception e) {
			//do nothing
		}
		return tag+": ";
	}
}
