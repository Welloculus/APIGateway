package com.welloculus.apigateway.controller;

import static com.welloculus.apigateway.constants.Constants.JSON_CONTENT;
import static com.welloculus.apigateway.constants.Constants.SUCCESS;
import static com.welloculus.apigateway.constants.Constants.ApiPath.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.welloculus.apigateway.constants.Constants;
import com.welloculus.apigateway.model.UserRole;
import com.welloculus.apigateway.service.PatientService;
import com.welloculus.apigateway.util.CustomLogger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping(PATIENTS)
public class PatientController {

	@Autowired
	PatientService patientService;

	static CustomLogger logger = CustomLogger.getLogger();

	/**
	 * Fetch Events Controller
	 */
	@ResponseBody
	@RequestMapping(value = GET_PATIENTS, method = RequestMethod.GET)
	public Map<String, Object> getPatients(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String userId = headers.getFirst(USER_ID);
			JSONArray content = patientService.getPatients(userRole, userId);
			response.put(Constants.RESPONSE_CONTENT, content);
			response.put(SUCCESS, true);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}

	/**
	 * Fetch Events Controller
	 */
	@ResponseBody
	@RequestMapping(value = GET_PATIENT_BY_ID, method = RequestMethod.GET)
	public Map<String, Object> getPatientById(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String patientId = headers.getFirst(PATIENT_ID);
			String userId = headers.getFirst(USER_ID);
			JSONObject content = patientService.getPatientById(userRole, userId, patientId);
			response.put(Constants.RESPONSE_CONTENT, content);
			response.put(SUCCESS, true);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}

	@ResponseBody
	@RequestMapping(value = UPDATE_PATIENT_BY_ID, method = RequestMethod.POST, consumes = JSON_CONTENT)
	public Map<String, Object> updatePatientById(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role, @RequestBody String patientString) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String patientId = headers.getFirst(PATIENT_ID);
			String userId = headers.getFirst(USER_ID);
			patientService.updatePatientById(userRole, userId, patientId, patientString);
			response.put(SUCCESS, true);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = ADD_PATIENT, method = RequestMethod.POST, consumes = JSON_CONTENT)
	public Map<String, Object> addPatient(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role, @RequestBody String patientString) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String userId = headers.getFirst(USER_ID);
			patientService.addPatient(userRole, userId, patientString);
			response.put(SUCCESS, true);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}
}
