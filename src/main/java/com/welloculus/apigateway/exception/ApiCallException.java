package com.welloculus.apigateway.exception;

public class ApiCallException extends WelloculusException {

  private static final long serialVersionUID = 1L;
  static final WelloculusErrorCode ERROR_CODE = WelloculusErrorCode.API_CALL_FAILED;

  public ApiCallException(String errorMessage) {
    super(ERROR_CODE, errorMessage);
  }

  public ApiCallException(Exception exception, String errorMessage) {
    super(exception, ERROR_CODE, errorMessage);
  }
}
