package com.welloculus.apigateway.apiai;

import com.google.gson.JsonObject;

public class UnitConverter {

	public static final float LB_KG = 0.453592F;
	public static final float FT_M = 0.3048F;
	public static final float INCH_M = 0.0254F;
	public static final float CM_M = 0.01F;
	public static float getWeightInKG(JsonObject weightObject){
		float amount = weightObject.get("amount").getAsFloat();
		float amountInKg = amount;
		String unit = weightObject.get("unit").getAsString();
		if(unit.equals("lb")){
			amountInKg = amount*LB_KG;
		}else{
			amountInKg = amount*1;
		}
		return amountInKg;
	}
	
	public static float getHeightInMeters(JsonObject heightObject){
		float amount = heightObject.get("amount").getAsFloat();
		float amountInMeter = amount;
		String unit = heightObject.get("unit").getAsString();
		if(unit.equals("ft")){
			amountInMeter = amount*FT_M;
		}else if(unit.equals("inch")){
			amountInMeter = amount*INCH_M;
		}else if(unit.equals("cm")){
			amountInMeter = amount*CM_M;
		}else{
			amountInMeter = amount*1;
		}
		return amountInMeter;
	}
}
