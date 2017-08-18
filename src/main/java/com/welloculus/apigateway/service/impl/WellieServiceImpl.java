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
import com.welloculus.apigateway.service.WellieService;

import net.sf.json.JSONObject;

@Service("WellieService")
public class WellieServiceImpl implements WellieService {

	private static final String FOOD_DETAILS_COLLECTION_NAME = "food_details";
	@Autowired
	MongoDBConnector mongoConnector;


	@Override
	public JSONObject getFoodDetails(String detailType) throws WelloculusException {
		MongoCollection<Document> foodDetailsCollection = mongoConnector.getDatabase(UserRole.WELLIE, null)
				.getCollection(FOOD_DETAILS_COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("detail_type", detailType);
		FindIterable<Document> resultIterable = foodDetailsCollection.find(whereQuery);
		return MongoDBUtils.toJSONObject(resultIterable);
	}

}
