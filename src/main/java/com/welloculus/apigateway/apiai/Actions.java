package com.welloculus.apigateway.apiai;

public enum Actions {
	getFoodDetails, getIdealWeight, getBMI, defaultAction;
	
	public static Actions getEnum(String name){
		for (Actions action : values()) {
			if(action.name().equals(name)){
				return action;
			}
		}
		return defaultAction;
	}
	
}
