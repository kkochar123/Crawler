package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;


import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.database.MongoDBConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.response.Gymnasium;

@Controller
@RequestMapping("/v1/gym-info")
public class RetreiveGymDetails {

	@SuppressWarnings("deprecation")
	@WebMethod
	@RequestMapping(value="/{cityName}", method = RequestMethod.GET)
	public ModelAndView  getGymDetails(@PathVariable String cityName) {
		List<String> gymLists = new ArrayList<String>();
		gymLists = fetchRecordsFromDatabase(cityName);
		
		ModelAndView model = new ModelAndView("list");
		model.addObject("lists", gymLists);
		
		return model;

	}
	
	/*public static void main(String[] args) {
		fetchRecordsFromDatabase("Rohtak");
	}*/
	private List<String> fetchRecordsFromDatabase(String city) {
		MongoDBConnection db = new MongoDBConnection();
		DBCollection gyms = db.getConn().getCollection("gymnasium");
		BasicDBObject findQuery = new BasicDBObject("city", new BasicDBObject("$eq",city));
		List<String> gymList = new ArrayList<String>();
        DBCursor docs = gyms.find(findQuery);

        while(docs.hasNext()){
            DBObject doc = docs.next();
            Gymnasium gym = new Gymnasium();
			gym.setAddress(String.valueOf(doc.get("address")));
			gym.setName(String.valueOf(doc.get("name")));
			gym.setPhoneNumber(String.valueOf(doc.get("phoneNumber")));;
			gym.setWebsite(String.valueOf(doc.get("website")));;
            
			gymList.add(gym.toString());
        }
		db.CloseConnection();
		
		return gymList;
	}
	
}