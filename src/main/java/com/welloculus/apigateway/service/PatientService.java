package com.welloculus.apigateway.service;

import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.model.UserRole;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface PatientService {

	public JSONArray getPatients(UserRole userRole, String userId) throws WelloculusException;

	public JSONObject getPatientById(UserRole userRole, String userId, String patientId);

	public void updatePatientById(UserRole userRole, String userId, String patientId, String patientString);

	public void addPatient(UserRole userRole, String userId, String patientInfo);

}
