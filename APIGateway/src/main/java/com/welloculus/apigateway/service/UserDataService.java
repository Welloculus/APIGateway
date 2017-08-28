package com.welloculus.apigateway.service;

import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.model.UserRole;

import net.sf.json.JSONArray;

public interface UserDataService {

	public JSONArray getUserData(UserRole userRole, String userId) throws WelloculusException;

	public void addUserData(UserRole userRole, String userId, String userdataInfo);
}
