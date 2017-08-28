package com.welloculus.apigateway.exception;

public class WelloculusException extends Exception {

  private static final long serialVersionUID = 1L;
  private WelloculusErrorCode errorCode;
  private String errorMessage;
  private Exception exception;

  public WelloculusException(WelloculusErrorCode errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public WelloculusException(Exception exception, WelloculusErrorCode errorCode, String errorMessage) {
    this.exception = exception;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public WelloculusErrorCode getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Exception getException() {
    return exception;
  }

  public String getMessage() {
    String message = getErrorCode().name() + " : " + getErrorMessage();
    return message;
  }
}
