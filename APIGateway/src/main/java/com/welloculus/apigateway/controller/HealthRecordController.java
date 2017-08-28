package com.welloculus.apigateway.controller;

import static com.welloculus.apigateway.constants.Constants.JSON_CONTENT;
import static com.welloculus.apigateway.constants.Constants.ROLE;
import static com.welloculus.apigateway.constants.Constants.SUCCESS;
import static com.welloculus.apigateway.constants.Constants.USER_ID;
import static com.welloculus.apigateway.constants.Constants.ApiPath.GET_HEALTH_RECORDS;
import static com.welloculus.apigateway.constants.Constants.ApiPath.HEALTH_RECORDS;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.welloculus.apigateway.constants.Constants;
import com.welloculus.apigateway.model.UserRole;
import com.welloculus.apigateway.service.HealthRecordService;
import com.welloculus.apigateway.util.CustomLogger;

import net.sf.json.JSONObject;

@CrossOrigin
@RestController
@RequestMapping(HEALTH_RECORDS)
public class HealthRecordController {

	@Autowired
	HealthRecordService healthRecordService;

	static CustomLogger logger = CustomLogger.getLogger();

	@ResponseBody
	@RequestMapping(value = GET_HEALTH_RECORDS, method = RequestMethod.POST, consumes = JSON_CONTENT)
	public Map<String, Object> addHealthRecord(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role, @RequestBody String filterString) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String userId = headers.getFirst(USER_ID);
			JSONObject filter = JSONObject.fromObject(filterString);
			JSONObject content = healthRecordService.getRecords(userRole, userId, filter);
			response.put(Constants.RESPONSE_CONTENT, content);
			response.put(SUCCESS, true);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}
}
