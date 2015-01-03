package com.database;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
 
public class MongoDBConnection {
 
	public MongoClient client = null;
	private DB conn = null;
 
	public MongoDBConnection() {
		try {
			MongoClientURI uri  = new MongoClientURI("mongodb://admin:admin123@ds029801.mongolab.com:29801/mongodb"); 
	        client = new MongoClient(uri);
	        conn = client.getDB(uri.getDatabase());
		}
		catch (UnknownHostException e) {
			System.out.println("Unable to Create Connection to database : " + e.getMessage());
		}
	}
	
	public void CloseConnection() {
		client.close();
	}

	public  DB getConn() {
		return this.conn;
	}

 
}