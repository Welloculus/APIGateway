package com.welloculus.apigateway.apiai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

@Component
public class APIAIHandler {
	
    AIConfiguration configuration;

    AIDataService dataService;
    
    @Autowired
    public APIAIHandler(@Value("${apiai.token}") final String token) {
        System.out.println("token: "+token);
    	configuration = new AIConfiguration(token);
        
        dataService = new AIDataService(configuration);
  
	}

    public String getResponse(String queryString){

		String responseString = null;
    	try {			
    		AIRequest request = new AIRequest(queryString);
    		AIResponse response = dataService.request(request);
    		if (response.getStatus().getCode() == 200) {
    			responseString = response.getResult().getFulfillment().getSpeech();
    			System.out.println("Query: "+queryString+"\nResponse: "+responseString);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return responseString;
    }
}
