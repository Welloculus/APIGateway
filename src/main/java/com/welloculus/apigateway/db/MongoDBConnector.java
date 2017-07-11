package com.welloculus.apigateway.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.welloculus.apigateway.util.CustomLogger;

@Service("MongoDBConnector")
public class MongoDBConnector {

	@Value("${mongodb.host}")
	String dbHost;

	@Value("${mongodb.port}")
	String dbPort;

	@Value("${mongodb.facilitatordbname}")
	String facilitatorDbName;

	// TODO Anuj, add handling for provider id map
	@Value("${mongodb.providerdbname.provider1}") 
	String providerDBname;

	static CustomLogger logger = CustomLogger.getLogger();
	
	MongoClient mongo;

	private void init() {
		mongo = new MongoClient(dbHost, Integer.parseInt(dbPort));
	}

	public MongoDatabase getfacilitatorDB() {
		if(mongo==null){
			init();
		}
		return mongo.getDatabase(facilitatorDbName);
	}

	public MongoDatabase getProviderDB(String providerId) {
		if(mongo==null){
			init();
		}
		// String dbName = providerDBname.get(providerId);
		return mongo.getDatabase(providerDBname);
	}
}
