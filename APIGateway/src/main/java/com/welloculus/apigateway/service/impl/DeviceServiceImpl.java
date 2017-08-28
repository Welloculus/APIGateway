package com.welloculus.apigateway.service.impl;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.welloculus.apigateway.db.MongoDBConnector;
import com.welloculus.apigateway.db.MongoDBUtils;
import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.model.UserRole;
import com.welloculus.apigateway.service.DeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("DeviceService")
public class DeviceServiceImpl implements DeviceService {

	private static final String COLLECTION_NAME = "devices";
	@Autowired
	MongoDBConnector mongoConnector;

	@Override
	public JSONArray getDevices(UserRole userRole, String userId) throws WelloculusException {
		MongoCollection<Document> devicesCollection = mongoConnector.getDatabase(userRole, userId)
				.getCollection(COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		FindIterable<Document> resultIterable = devicesCollection.find(whereQuery);
		return MongoDBUtils.toJSONArray(resultIterable);
	}

	@Override
	public JSONObject getDeviceById(UserRole userRole, String userId, String deviceId) {
		MongoCollection<Document> devicesCollection = mongoConnector.getDatabase(userRole, userId)
				.getCollection(COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id", new ObjectId(deviceId));
		FindIterable<Document> resultIterable = devicesCollection.find(whereQuery);
		return MongoDBUtils.toJSONObject(resultIterable);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addDevice(UserRole userRole, String userId, String deviceInfo) {
		MongoCollection<Document> devicesCollection = mongoConnector.getDatabase(userRole, userId)
				.getCollection(COLLECTION_NAME);
		Document device = new Document();
		device.putAll(JSONObject.fromObject(deviceInfo));
		devicesCollection.insertOne(device);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateDeviceById(UserRole userRole, String userId, String deviceId, String deviceInfo) {
		MongoCollection<Document> devicesCollection = mongoConnector.getDatabase(userRole, userId)
				.getCollection(COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id", new ObjectId(deviceId));
		Document device = new Document();
		device.putAll(JSONObject.fromObject(deviceInfo));

		UpdateResult result = devicesCollection.replaceOne(whereQuery, device);
		return result.getMatchedCount() > 0;
	}

	@Override
	public boolean deleteDeviceById(UserRole userRole, String userId, String deviceId) {
		MongoCollection<Document> devicesCollection = mongoConnector.getDatabase(userRole, userId)
				.getCollection(COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id", new ObjectId(deviceId));
		DeleteResult result = devicesCollection.deleteOne(whereQuery);
		return result.getDeletedCount() > 0;
	}

}
