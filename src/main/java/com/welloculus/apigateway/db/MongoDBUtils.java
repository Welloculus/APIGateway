package com.welloculus.apigateway.db;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MongoDBUtils {

	public static JSONArray toJSONArray(FindIterable<Document> resultIterable) {
		JSONArray result = new JSONArray();
		MongoCursor<Document> cursor = resultIterable.iterator();
		while (cursor.hasNext()) {
			result.add(cursor.next().toJson());
		}
		return result;
	}

	public static JSONObject toJSONObject(FindIterable<Document> resultIterable) {
		JSONObject result = null;
		MongoCursor<Document> cursor = resultIterable.iterator();
		while (cursor.hasNext()) {
			result = JSONObject.fromObject(cursor.next().toJson());
		}
		return result;
	}
	
	public static JSONArray mergeResultArrays(FindIterable<Document> resultIterable, String arrayName) {
		JSONArray result = new JSONArray();
		MongoCursor<Document> cursor = resultIterable.iterator();
		while (cursor.hasNext()) {
			JSONObject tempObject = JSONObject.fromObject(cursor.next().toJson());
			result.add(tempObject.get(arrayName));
		}
		return result;
	}
}
