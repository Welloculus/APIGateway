package com.welloculus.apigateway.service.impl;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.welloculus.apigateway.db.MongoDBConnector;
import com.welloculus.apigateway.db.MongoDBUtils;
import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.model.UserRole;
import com.welloculus.apigateway.service.UserDataService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("UserDataService")
public class UserDataServiceImpl implements UserDataService {

	private static final String COLLECTION_NAME = "userdatas";
	@Autowired
	MongoDBConnector mongoConnector;
	
	@Override
	public JSONArray getUserData(UserRole userRole, String userId) throws WelloculusException {
		MongoCollection<Document> userdatasCollection = mongoConnector.getDatabase(userRole, userId).getCollection(COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		FindIterable<Document> resultIterable = userdatasCollection.find(whereQuery);
		return MongoDBUtils.toJSONArray(resultIterable);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addUserData(UserRole userRole, String userId, String userdataString) {
		MongoCollection<Document> userdatasCollection = mongoConnector.getDatabase(userRole, userId).getCollection(COLLECTION_NAME);
        Document userdata = new Document();
        userdata.putAll(JSONObject.fromObject(userdataString));
        userdatasCollection.insertOne(userdata);
	}

}
