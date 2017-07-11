package com.welloculus.apigateway.service.impl;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.welloculus.apigateway.db.MongoDBUtils;
import com.welloculus.apigateway.db.MongoDBConnector;
import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.model.UserRole;
import com.welloculus.apigateway.service.PatientService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("PatientService")
public class PatientServiceImpl implements PatientService {

	private static final String COLLECTION_NAME = "users";
	@Autowired
	MongoDBConnector mongoConnector;
	
	@Override
	public JSONArray getPatients(UserRole userRole, String userId) throws WelloculusException {
		MongoCollection<Document> patientsCollection = mongoConnector.getDatabase(userRole, userId).getCollection(COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		FindIterable<Document> resultIterable = patientsCollection.find(whereQuery);
		return MongoDBUtils.toJSONArray(resultIterable);
	}
	
	@Override
	public JSONObject getPatientById(UserRole userRole, String userId, String patientId) {
		MongoCollection<Document> patientsCollection = mongoConnector.getDatabase(userRole, userId).getCollection(COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id",new ObjectId(patientId));
		FindIterable<Document> resultIterable = patientsCollection.find(whereQuery);
		return MongoDBUtils.toJSONObject(resultIterable);
	}
	
	@Override
	public void updatePatientById(UserRole userRole, String userId, String patientId, String patientString) {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addPatient(UserRole userRole, String userId, String patientInfo) {
		MongoCollection<Document> patientsCollection = mongoConnector.getDatabase(userRole, userId).getCollection(COLLECTION_NAME);
        Document patient = new Document();
        patient.putAll(JSONObject.fromObject(patientInfo));
        patientsCollection.insertOne(patient);
	}

}
