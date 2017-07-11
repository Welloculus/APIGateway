package com.welloculus.apigateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.welloculus.apigateway.db.MongoDBConnector;
import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.service.PatientService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("PatientService")
public class PatientServiceImpl implements PatientService {

	@Autowired
	MongoDBConnector mongoUtils;
	
	@Override
	public JSONArray getPatients() throws WelloculusException {
		String patients = "[{\"city\":\"Chicago\",\"patient_dob\":\"606675957\",\"contact\":\"9785412648\",\"patient_gender\":\"M\",\"email\":\"test\",\"patient_username\":\"testuser\",\"patient_lastname\":\"LName\",\"patient_firstname\":\"Fname\"},{\"city\":\"Chicago\",\"email\":\"prachi.jain@impetus.co.in\",\"patient_username\":\"jain\",\"patient_lastname\":\"Jain\",\"patient_firstname\":\"Prachi\",\"contact\":\"9981947785\"},{\"city\":\"Indore\",\"email\":\"prachi.jain@impetus.co.in\",\"patient_firstname\":\"Prachi\",\"patient_lastname\":\"Jain\",\"patient_username\":\"prachi\",\"contact\":\"9981947785\"}]";
		return JSONArray.fromObject(patients);
	}
//	
//	public JSONArray getPatients_mongo() throws WelloculusException {
//		DBCollection usersCollection = mongoUtils.getfacilitatorDB().getCollection("users");
//		DBCursor cursor = usersCollection.find();
//		return CursorUtils.toJSONArray(cursor);
//	}

	@Override
	public JSONObject getPatientById(String patientId) {
		String pateint = "{\"city\":\"Chicago\",\"patient_dob\":\"606675957\",\"contact\":\"9785412648\",\"patient_gender\":\"M\",\"email\":\"test\",\"patient_username\":\"testuser\",\"patient_lastname\":\"LName\",\"patient_firstname\":\"Fname\"}";
		return JSONObject.fromObject(pateint);
	}
	
//	public JSONObject getPatientById_mongo() throws WelloculusException {
//		DBCollection usersCollection = mongoUtils.getfacilitatorDB().getCollection("users");
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put("patient_username", "testuser");
//		DBCursor cursor = usersCollection.find(whereQuery);
//		return CursorUtils.toJSONObject(cursor);
//	}

	@Override
	public void updatePatientById(String patientId, String patientString) {
		// TODO Auto-generated method stub

	}

}
