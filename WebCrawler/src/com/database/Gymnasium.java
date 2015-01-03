package com.database;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Gymnasium")
//@JsonRootName(value = "Gymnasium")
//@JsonIgnoreProperties(ignoreUnknown = true)

public class Gymnasium  {

	@XmlElement(name = "name")
	//@JsonProperty("name")
	private String name;
	
	@XmlElement(name = "address")
	//@JsonProperty("address")
	private String address;
	
	@XmlElement(name = "phoneNumber")
	//@JsonProperty("phoneNumber")
	private String phoneNumber;
	
	@XmlElement(name = "website")
	//@JsonProperty("website")
	private String website;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
