package com.welloculus.apigateway.apiai;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.service.WellieService;

import ai.api.model.AIResponse;
import ai.api.model.Result;
import net.sf.json.JSONObject;

@Component
public class APIAIHandler {

	public static final float BMI_LOW = 18.5F;
	public static final float BMI_HIGH = 24.9F; 
	
    @Autowired
    WellieService wellieService;
    
    
    public String getResponse(AIResponse response){
		String responseString = null;
    	try {
    		if (response.getStatus().getCode() == 200) {
    			Result result = response.getResult();
    			Actions action = Actions.getEnum(result.getAction());
    			boolean isInComplete = result.isActionIncomplete();
				responseString = result.getFulfillment().getSpeech();
    			if(!isInComplete){
    				switch (action) {
    				case getFoodDetails:
    					responseString = getResponseWithFoodDetails(result);
    					break;
    				case getIdealWeight:
    					responseString = getIdealWeight(result);
    					break;
    				case getBMI:
    					responseString = getBMI(result);
    					break;
    				case defaultAction:
    				default:
    					break;
    				}
    			}
    			System.out.println("Response: "+responseString);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return responseString;
    }

	private String getBMI(Result result) {
		float height = UnitConverter.getHeightInMeters(result.getComplexParameter("height"));
		float weight = UnitConverter.getWeightInKG(result.getComplexParameter("weight"));
		float bmi = weight/(height*height);
		return String.format("Your BMI is %.2f",bmi);
	}

	private String getIdealWeight(Result result) {
		float height = UnitConverter.getHeightInMeters(result.getComplexParameter("height"));
		float weightRangeLow = BMI_LOW*height*height;
		float weightRangehigh = BMI_HIGH*height*height;
		return String.format("As per your height, your weight should be between %.2f KG. to %.2f KG.",weightRangeLow, weightRangehigh);
	}

	private String getResponseWithFoodDetails(Result result) {
		String detailType = "";
		String responseString = result.getFulfillment().getSpeech();
		Map<String, JsonElement> params = result.getParameters();
		try {
			if(responseString.contains("#calorie")){
				detailType = "calories";
				String food = params.get("Food").getAsString();			
				JSONObject foodDetails = wellieService.getFoodDetails(detailType);
				Object calorie = foodDetails.getJSONObject("details").getJSONObject(food).get("cal");
				String quantity = foodDetails.getJSONObject("details").getJSONObject(food).getString("quantity");
				responseString = responseString.replace("#calorie", calorie.toString()).replace("#quantity", quantity).replace("@Food", food);
			}			
		} catch (WelloculusException e) {
			e.printStackTrace();
		}
		
		return responseString;
	}
}
