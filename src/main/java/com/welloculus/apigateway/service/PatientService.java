package com.welloculus.apigateway.service;

import com.welloculus.apigateway.exception.WelloculusException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface PatientService {

	public JSONArray getPatients() throws WelloculusException;

	public JSONObject getPatientById(String patientId);

	public void updatePatientById(String patientId, String patientString);

}
