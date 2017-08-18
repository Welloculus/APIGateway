package com.welloculus.apigateway.apiai;

import static com.welloculus.apigateway.constants.Constants.QUERY;
import static com.welloculus.apigateway.constants.Constants.SESSION_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceContext;
import ai.api.AIServiceContextBuilder;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import net.sf.json.JSONObject;

@Component
public class APIAIService {
	
	AIConfiguration configuration;

	@Autowired
	APIAIHandler apiaiHandler;

	@Autowired
	public APIAIService(@Value("${apiai.token}") final String token) {
		System.out.println("token: " + token);
		configuration = new AIConfiguration(token);
	}

	public String getResponse(JSONObject queryObject) {
		try {
			String queryString = queryObject.getString(QUERY);
			String sessionId;
			if (queryObject.containsKey(SESSION_ID)) {
				sessionId = queryObject.getString(SESSION_ID);
			} else {
				sessionId = "defaultSession";
			}
			AIServiceContext context = AIServiceContextBuilder.buildFromSessionId(sessionId);
			AIDataService dataService = new AIDataService(configuration, context);
			AIRequest request = new AIRequest(queryString);
			AIResponse response = dataService.request(request);
			return apiaiHandler.getResponse(response);
		} catch (Exception e) {
			return "Sorry, something went wrong, My bad!!";
		}
	}
}
