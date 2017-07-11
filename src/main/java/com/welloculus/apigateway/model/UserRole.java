package com.welloculus.apigateway.model;

import com.welloculus.apigateway.exception.WelloculusErrorCode;
import com.welloculus.apigateway.exception.WelloculusException;

public enum UserRole {

	PROVIDER("provider"), FACILITATOR("facilitator"), SUPPLIER("supplier");

	String key;

	UserRole(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static UserRole getEnum(String key) throws WelloculusException {
		UserRole result = null;
		for (UserRole api : values()) {
			if (api.getKey().equals(key)) {
				result = api;
				break;
			}
		}
		if(result==null){
			throw new WelloculusException(WelloculusErrorCode.UNDEFINED_USER_ROLE, "invalid user role "+key);
		}
		return result;
	}
}
