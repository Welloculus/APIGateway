package com.welloculus.apigateway.service;

import com.welloculus.apigateway.exception.WelloculusException;

public interface MessageService {

  public void handleMessage(String message) throws WelloculusException;

}
