package com.database;

import java.net.UnknownHostException;
import com.mongodb.DB;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void CloseConnection() {
		client.close();
	}

	public  DB getConn() {
		return this.conn;
	}

 
}