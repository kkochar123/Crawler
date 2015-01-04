package com.crawler;


import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.database.MongoDBConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
 
public class Crawler {
 
	public static MongoDBConnection db = new MongoDBConnection();
	private static int count = 0;
	public static void main(String[] args) throws IOException {
 
		System.out.println("Enter city Name : ");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String city = bufferRead.readLine();
		
		processMainPage("http://www.justdial.com/" + city + "/Gyms", -1);
		try {
			db.CloseConnection();
		} catch (Throwable e) {
			}
	}
 
	public static void processMainPage(String URL, int previousPageNo) throws IOException {
 
			Document mainPage = null;
					
			try {
			mainPage = Jsoup.connect(URL).get();
			} catch (IOException e1) {
				return;
			}
			
			Elements subsequentLinks = mainPage.getElementsByClass("comp_name_text").select("a[href]");
			for(Element link: subsequentLinks){
					processSubsequentPage(link.attr("abs:href"));					
			}

			Elements nextLinks = mainPage.getElementsByClass("pager").select("a[href]");
			for(Element link: nextLinks){
				String url = link.attr("abs:href");
				if(url.contains("page=")) {
					int pageStartIndex = url.indexOf("page=") + 5;
					int pageEndIndex = url.indexOf("page=") + 6;
					if(url.charAt(pageEndIndex ) != '&')
						pageEndIndex = pageEndIndex + 1;
					int pageNo = Integer.parseInt(url.substring(pageStartIndex , pageEndIndex));
					if(pageNo > previousPageNo )
						processMainPage(link.attr("abs:href"), pageNo );	
				}
			}
 
	}

	
	private static void processSubsequentPage(String URL) {
		HashMap<String,String> map = new HashMap<String, String>();
		Document subsequentPage = null;
		try {
			subsequentPage = Jsoup.connect(URL).get();
		} catch (IOException e1) {
			return;
		}

		String city = "";
		if(URL.contains("&city=") && URL.contains("&area=")) {
			int pageStartIndex = URL.indexOf("&city=") + 6;
			int pageEndIndex = URL.indexOf("&area=");
			city = URL.substring(pageStartIndex , pageEndIndex);
		}
		
		String name = subsequentPage.body().getElementsByClass("pro_tit").text();
		if(name != null && !name.isEmpty()) {
			System.out.println(name);
		}
		String details  = subsequentPage.body().getElementsByClass("dtls_addr").text();
		if(details != null && !details.isEmpty()) {
			String[] detailsList = details.split(" ");
			int size = detailsList.length;
			String contactNumber = detailsList[size - 1];
			String website = detailsList[size -2];
			String address = null;
			if(website.startsWith("www.") || website.startsWith("http")) {
				address = details.substring(0, details.indexOf(website));
			}
			else {
				website = null;
				address = details.substring(0, details.indexOf(contactNumber));
			}
			if(map.containsKey(contactNumber)) 
				return;
			else {
				map.put(contactNumber, website);
				insertIntoDatabase(name, contactNumber, website, address, city);
			}
		}
	}

	private static void insertIntoDatabase(String name, String contactNumber,
			String website, String address, String city) {
		DBCollection gyms = db.getConn().getCollection("gymnasium");

		BasicDBObject gym = new BasicDBObject();
        gym.put("name", name);
        gym.put("address", address);
        gym.put("phoneNumber", contactNumber);
        gym.put("website", website);
        gym.put("city", city.toLowerCase());
        gyms.insert(gym);
		System.out.println("Record Inserted " + ++count);	
	}
}