package com.welloculus.apigateway.controller;

import static com.welloculus.apigateway.constants.Constants.JSON_CONTENT;
import static com.welloculus.apigateway.constants.Constants.SUCCESS;
import static com.welloculus.apigateway.constants.Constants.ApiPath.WELLIE;
import static com.welloculus.apigateway.constants.Constants.ApiPath.WELLIE_QUERY;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.welloculus.apigateway.apiai.APIAIService;
import com.welloculus.apigateway.constants.Constants;
import com.welloculus.apigateway.util.CustomLogger;

import net.sf.json.JSONObject;

@CrossOrigin
@RestController
@RequestMapping(WELLIE)
public class WellieController {

	@Autowired
	APIAIService apiaiService;

	static CustomLogger logger = CustomLogger.getLogger();

	@ResponseBody
	@RequestMapping(value = WELLIE_QUERY, method = RequestMethod.POST, consumes = JSON_CONTENT)
	public Map<String, Object> addHealthRecord(@RequestBody String queryJson) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			
			JSONObject queryObject = JSONObject.fromObject(queryJson);
			String responseString = apiaiService.getResponse(queryObject);
			if(responseString!=null){				
				response.put(Constants.RESPONSE_CONTENT, responseString);
				response.put(SUCCESS, true);
			}
			else{
				response.put(SUCCESS, false);
			}
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}
}
