package com.mikulin.ma.db;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mikulin.ma.Constants;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * Mongo database manager.
 *
 */
public class MongoDBManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBManager.class);

	@Resource(name = "dbProperties")
	private Properties dbProperties;

	public void addLogRecord(String user, String input, String result) {
		String active = dbProperties.getProperty("db.mongo.active");
		if (!Boolean.parseBoolean(active)) {
			LOGGER.debug("Adding record to the Mongo DB is skipped");
			return;
		}
		String host = dbProperties.getProperty("db.mongo.host");
		String dbname = dbProperties.getProperty("db.mongo.dbname");
		String dbUser = dbProperties.getProperty("db.mongo.user");
		String dbPassword = dbProperties.getProperty("db.mongo.password");

		//Mongo pool?
		MongoCredential credential = MongoCredential.createCredential(dbUser, dbname, dbPassword.toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress(host), Arrays.asList(credential));

		MongoDatabase db = mongoClient.getDatabase(dbname);

		try {
			Document document = new Document(Constants.DATE, new Date());
			document.append(Constants.USER, user).append(Constants.INPUT, input).append(Constants.RESULT, result);
			db.getCollection(dbProperties.getProperty("collection")).insertOne(document);
		} finally {
			mongoClient.close();
		}
	}

}
