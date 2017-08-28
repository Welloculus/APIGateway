package com.welloculus.apigateway.service;

import com.welloculus.apigateway.exception.WelloculusException;

import net.sf.json.JSONObject;

public interface WellieService {

	public JSONObject getFoodDetails(String detailType) throws WelloculusException;

}
