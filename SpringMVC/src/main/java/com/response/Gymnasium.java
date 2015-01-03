package com.response;

public class Gymnasium  {

	public Gymnasium(String name, String address, String phoneNumber,
			String website) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.website = website;
	}

	public Gymnasium() {
		// TODO Auto-generated constructor stub
	}

	private String name;
	
	private String address;
	
	private String phoneNumber;
	
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
	
	@Override
	public String toString() {
		return "Gymnasium [name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", website=" + website + "]";
	}
}
