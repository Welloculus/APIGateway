package com.welloculus.apigateway.controller;

import static com.welloculus.apigateway.constants.Constants.DEVICE_ID;
import static com.welloculus.apigateway.constants.Constants.JSON_CONTENT;
import static com.welloculus.apigateway.constants.Constants.ROLE;
import static com.welloculus.apigateway.constants.Constants.SUCCESS;
import static com.welloculus.apigateway.constants.Constants.USER_ID;
import static com.welloculus.apigateway.constants.Constants.ApiPath.ADD_DEVICE;
import static com.welloculus.apigateway.constants.Constants.ApiPath.DELETE_DEVICE_BY_ID;
import static com.welloculus.apigateway.constants.Constants.ApiPath.DEVICES;
import static com.welloculus.apigateway.constants.Constants.ApiPath.GET_DEVICES;
import static com.welloculus.apigateway.constants.Constants.ApiPath.GET_DEVICE_BY_ID;
import static com.welloculus.apigateway.constants.Constants.ApiPath.UPDATE_DEVICE_BY_ID;

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
import com.welloculus.apigateway.service.DeviceService;
import com.welloculus.apigateway.util.CustomLogger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@CrossOrigin
@RestController
@RequestMapping(DEVICES)
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	static CustomLogger logger = CustomLogger.getLogger();

	/**
	 * Fetch Events Controller
	 */
	@ResponseBody
	@RequestMapping(value = GET_DEVICES, method = RequestMethod.GET)
	public Map<String, Object> getDevices(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String userId = headers.getFirst(USER_ID);
			JSONArray content = deviceService.getDevices(userRole, userId);
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
	@RequestMapping(value = GET_DEVICE_BY_ID, method = RequestMethod.GET)
	public Map<String, Object> getDeviceById(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String deviceId = headers.getFirst(DEVICE_ID);
			String userId = headers.getFirst(USER_ID);
			JSONObject content = deviceService.getDeviceById(userRole, userId, deviceId);
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
	@RequestMapping(value = ADD_DEVICE, method = RequestMethod.POST, consumes = JSON_CONTENT)
	public Map<String, Object> addDevice(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role,
			@RequestBody String deviceInfo) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String userId = headers.getFirst(USER_ID);
			deviceService.addDevice(userRole, userId, deviceInfo);
			response.put(SUCCESS, true);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}

	@ResponseBody
	@RequestMapping(value = UPDATE_DEVICE_BY_ID, method = RequestMethod.PUT, consumes = JSON_CONTENT)
	public Map<String, Object> updateDeviceById(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role,
			@RequestBody String deviceInfo) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String deviceId = headers.getFirst(DEVICE_ID);
			String userId = headers.getFirst(USER_ID);
			boolean isUpdated = deviceService.updateDeviceById(userRole, userId, deviceId, deviceInfo);
			response.put(SUCCESS, isUpdated);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}

	@ResponseBody
	@RequestMapping(value = DELETE_DEVICE_BY_ID, method = RequestMethod.DELETE)
	public Map<String, Object> deleteDeviceById(@RequestHeader HttpHeaders headers, @PathVariable(ROLE) String role) {
		logger.debugStartOfMethod();
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			UserRole userRole = UserRole.getEnum(role);
			String deviceId = headers.getFirst(DEVICE_ID);
			String userId = headers.getFirst(USER_ID);
			boolean isDeleted = deviceService.deleteDeviceById(userRole, userId, deviceId);
			response.put(SUCCESS, isDeleted);
		} catch (Exception e) {
			response.put(SUCCESS, false);
			logger.error(e);
		}
		logger.debugEndOfMethod();
		return response;
	}
}
