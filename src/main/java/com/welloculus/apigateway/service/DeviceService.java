package com.welloculus.apigateway.service;

import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.model.UserRole;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface DeviceService {

	public JSONArray getDevices(UserRole userRole, String userId) throws WelloculusException;

	public JSONObject getDeviceById(UserRole userRole, String userId, String deviceId);

	public void updateDeviceById(UserRole userRole, String userId, String deviceId, String deviceString);

	public void addDevice(UserRole userRole, String userId, String deviceInfo);

}
