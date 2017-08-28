package com.welloculus.apigateway.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.welloculus.apigateway.model.UserRole;
import com.welloculus.apigateway.util.CustomLogger;

@Service("MongoDBConnector")
public class MongoDBConnector {

	@Value("${mongodb.host}")
	String dbHost;

	@Value("${mongodb.port}")
	String dbPort;

	@Value("${mongodb.facilitatordbname}")
	String facilitatorDbName;
	
	@Value("${mongodb.welliedbname}")
	String wellieDbName;

	static CustomLogger logger = CustomLogger.getLogger();
	
	MongoClient mongo;

	private void init() {
		mongo = new MongoClient(dbHost, Integer.parseInt(dbPort));
	}

	public MongoDatabase getDatabase(UserRole role, String userId){
		if(mongo==null){
			init();
		}
		MongoDatabase database = null;
		if(role.equals(UserRole.PROVIDER)){
			database = getProviderDB(userId);
		}else if(role.equals(UserRole.WELLIE)){
			database = getWellieDB();
		}else{
			database = getfacilitatorDB();
		}
		return database;
	}
	
	private MongoDatabase getWellieDB() {
		return mongo.getDatabase(wellieDbName);
	}

	private MongoDatabase getfacilitatorDB() {
		return mongo.getDatabase(facilitatorDbName);
	}

	private MongoDatabase getProviderDB(String providerId) {
		//TODO fetch db name using providerId
		// String dbName = providerDBname.get(providerId);
		String dbName = "provider";
		return mongo.getDatabase(dbName);
	}
}
