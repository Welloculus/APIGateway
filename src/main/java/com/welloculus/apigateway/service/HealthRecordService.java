package com.welloculus.apigateway.service;

import com.welloculus.apigateway.model.UserRole;

import net.sf.json.JSONObject;

public interface HealthRecordService {

	void addRecord(String data);

	JSONObject getRecords(UserRole userRole, String userId, JSONObject filter);
	
}
