package com.welloculus.apigateway.service.impl;

import static com.welloculus.apigateway.constants.Constants.DATA_TYPE;
import static com.welloculus.apigateway.constants.Constants.END_DATE;
import static com.welloculus.apigateway.constants.Constants.START_DATE;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.welloculus.apigateway.db.MongoDBConnector;
import com.welloculus.apigateway.db.MongoDBUtils;
import com.welloculus.apigateway.model.UserRole;
import com.welloculus.apigateway.service.HealthRecordService;

import net.sf.json.JSONObject;

@Service("HealthRecordService")
public class HealthRecordServiceImpl implements HealthRecordService {


	private static final String COLLECTION_NAME = "data";
	@Autowired
	MongoDBConnector mongoConnector;
	
	@SuppressWarnings("unchecked")
	@Override
	public void addRecord(String data) {
		JSONObject dataJson = JSONObject.fromObject(data);
		if(!dataJson.containsKey("provider_id")){
			return;
		}
		String providerId = dataJson.getString("provider_id");
		MongoCollection<Document> userDataCollection = mongoConnector.getDatabase(UserRole.PROVIDER, providerId)
				.getCollection(COLLECTION_NAME);
		Document document = new Document();
		document.putAll(dataJson);
		userDataCollection.insertOne(document);
	}

	@Override
	public JSONObject getRecords(UserRole userRole, String userId, JSONObject filter) {
		MongoCollection<Document> userDataCollection = mongoConnector.getDatabase(userRole, userId)
				.getCollection(COLLECTION_NAME);
		String dataType = null;
		if(filter.containsKey(DATA_TYPE)){
			dataType = filter.getString(DATA_TYPE);
		}
		String startDate = filter.getString(START_DATE);
		String endDate = filter.getString(END_DATE);
		
		BasicDBObject query = new BasicDBObject();

		query.put("data_type",new BasicDBObject("$eq", dataType));
		query.put("date", new BasicDBObject("$gte", startDate).append("$lte", endDate));
		
		FindIterable<Document> resultIterable = userDataCollection.find(query).limit(1000);
		JSONObject resultObject = new JSONObject();
		resultObject.put(dataType, MongoDBUtils.mergeResultArrays(resultIterable));
		return resultObject;
	}

}
