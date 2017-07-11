package com.welloculus.apigateway.controller;

import static com.welloculus.apigateway.constants.Constants.JSON_CONTENT;
import static com.welloculus.apigateway.constants.Constants.SUCCESS;
import static com.welloculus.apigateway.constants.Constants.ApiPath.GET_PATIENTS;
import static com.welloculus.apigateway.constants.Constants.ApiPath.GET_PATIENT_BY_ID;
import static com.welloculus.apigateway.constants.Constants.ApiPath.ID;
import static com.welloculus.apigateway.constants.Constants.ApiPath.PATIENTS;
import static com.welloculus.apigateway.constants.Constants.ApiPath.UPDATE_PATIENT_BY_ID;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.welloculus.apigateway.constants.Constants;
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
	public Map<String, Object> getPatients() {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			JSONArray content = patientService.getPatients();
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
	@RequestMapping(value = GET_PATIENT_BY_ID, method = RequestMethod.GET, consumes = JSON_CONTENT)
	public Map<String, Object> getPatientById(@PathVariable(ID) String patientId) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			JSONObject content = patientService.getPatientById(patientId);
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
	public Map<String, Object> updatePatientById(@PathVariable(ID) String patientId,
			@RequestBody String patientString) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			patientService.updatePatientById(patientId, patientString);
			response.put(SUCCESS, true);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}
}
