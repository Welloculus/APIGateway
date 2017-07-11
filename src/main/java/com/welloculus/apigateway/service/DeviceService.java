package com.welloculus.apigateway.service;

import com.welloculus.apigateway.exception.WelloculusException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface DeviceService {

	public JSONArray getDevices() throws WelloculusException;

	public JSONObject getDeviceById(String deviceId);

	public void updateDeviceById(String deviceId, String deviceString);

	public void addDevice(String deviceString);

}
