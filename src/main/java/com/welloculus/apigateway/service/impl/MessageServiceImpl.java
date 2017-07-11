package com.welloculus.apigateway.service.impl;

import org.springframework.stereotype.Service;

import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.service.MessageService;

@Service("MessageService")
public class MessageServiceImpl implements MessageService {

	@Override
	public void handleMessage(String message) throws WelloculusException {
		// TODO Auto-generated method stub
		
	}
	
}
