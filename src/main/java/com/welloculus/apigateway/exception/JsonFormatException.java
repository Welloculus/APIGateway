package com.welloculus.apigateway.exception;

public class JsonFormatException extends WelloculusException {

  private static final long serialVersionUID = 1L;
  static final WelloculusErrorCode ERROR_CODE = WelloculusErrorCode.JSON_FORMAT_EXCEPTION;

  public JsonFormatException(String errorMessage) {
    super(ERROR_CODE, errorMessage);
  }

  public JsonFormatException(Exception exception, String errorMessage) {
    super(exception, ERROR_CODE, errorMessage);
  }
}
