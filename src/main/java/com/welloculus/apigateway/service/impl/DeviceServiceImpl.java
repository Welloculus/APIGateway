package com.welloculus.apigateway.service.impl;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.welloculus.apigateway.db.MongoDBUtils;
import com.welloculus.apigateway.db.MongoDBConnector;
import com.welloculus.apigateway.exception.WelloculusException;
import com.welloculus.apigateway.service.DeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("DeviceService")
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	MongoDBConnector mongoConnector;
	
	@Override
	public JSONArray getDevices() throws WelloculusException {
		MongoCollection<Document> devicesCollection = mongoConnector.getfacilitatorDB().getCollection("devices");
		BasicDBObject whereQuery = new BasicDBObject();
		FindIterable<Document> resultIterable = devicesCollection.find(whereQuery);
		return MongoDBUtils.toJSONArray(resultIterable);
	}
	
	@Override
	public JSONObject getDeviceById(String deviceId) {
//		String pateint = "{\"contact\":\"9826012461\",\"patient_gender\":\"M\",\"device_udi\":\"28-051691b6b4ff\",\"is_available\":true,\"device_name\":\"Temperature_Sensor\",\"supplier_username\":\"saurav\",\"supplier_email\":\"saurav.kumar@impetus.co.in\",\"supplier_city\":\"India\",\"supplier_contact\":\"8447541999\",\"device_state\":true,\"device_type\":\"IOT\"}";
//		return JSONObject.fromObject(pateint);
		

		MongoCollection<Document> devicesCollection = mongoConnector.getfacilitatorDB().getCollection("devices");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("device_id", deviceId);
		FindIterable<Document> resultIterable = devicesCollection.find(whereQuery);
		return MongoDBUtils.toJSONObject(resultIterable);
	}
	
	public JSONObject getDeviceById_mongo() throws WelloculusException {
		MongoCollection<Document> usersCollection= mongoConnector.getfacilitatorDB().getCollection("users");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("device_username", "testuser");
		FindIterable<Document> resultIterable = usersCollection.find(whereQuery);
		return MongoDBUtils.toJSONObject(resultIterable);
	}

	@Override
	public void updateDeviceById(String deviceId, String deviceString) {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addDevice(String deviceString) {
		MongoCollection<Document> devicesCollection = mongoConnector.getfacilitatorDB().getCollection("devices");
        Document device = new Document();
        device.putAll(JSONObject.fromObject(deviceString));
        devicesCollection.insertOne(device);
	}

}
