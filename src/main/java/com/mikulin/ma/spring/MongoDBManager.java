package com.mikulin.ma.spring;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

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

	private static final String MONGO_DB_PROPERITES_FILE = "/WEB-INF/ma.properties";
	

	@Resource(name="dbProperties")
	private Properties dbProperties;

//	public MongoDBManager(ServletContext context) {
//		try {
//			readProperties(context);
//		} catch (IOException ex) {
//			LOGGER.error("Exception while reading Mongo db properties", ex);
//		}
//	}

	public void addLogRecord(String user, String input, String result) {
//		readProperties();
		String host = dbProperties.getProperty("host");
		String dbname = dbProperties.getProperty("dbname");
		String dbUser = dbProperties.getProperty("user");
		String dbPassword = dbProperties.getProperty("password");

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

//	private Properties readProperties(ServletContext context) throws IOException {
//		if (dbProperties == null) {
//			dbProperties = new Properties();
//			dbProperties.load(context.getResourceAsStream(MONGO_DB_PROPERITES_FILE));
//		}
//		return properties;
//
//	}
}
