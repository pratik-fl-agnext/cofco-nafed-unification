package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class UserCSVModel.
 */

@JsonIgnoreProperties
@JsonInclude(content = Include.NON_NULL)
public class UserCSVModel {

	@JsonProperty("first_name")
	private String firstname; 
	
	@JsonProperty("last_name")
	private String lastname; 
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("contact_number")
	private String contactnumber;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("pincode")
	private String pincode;
	
	@JsonProperty("state_admin_email")
	private String stateadminemail;
	
	@JsonProperty("location_name")
	private String locationname;
	
	@JsonProperty("warehouse_name")
	private String warehouseName;
	
	@JsonProperty("device_serial_no")
	private String deviceserialno;
	
	@JsonProperty("device_type")
	private String devicetype;
	
	@JsonProperty("category_name")
	private String categoryname;
	
	@JsonProperty("commodity_name")
	private String commodityname;
	
	@JsonProperty("location_code")
	private String locationcode;
	
	@JsonProperty("customer_email")
	private String customerEmail;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStateadminemail() {
		return stateadminemail;
	}

	public void setStateadminemail(String stateadminemail) {
		this.stateadminemail = stateadminemail;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getDeviceserialno() {
		return deviceserialno;
	}

	public void setDeviceserialno(String deviceserialno) {
		this.deviceserialno = deviceserialno;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCommodityname() {
		return commodityname;
	}

	public void setCommodityname(String commodityname) {
		this.commodityname = commodityname;
	}

	public String getLocationcode() {
		return locationcode;
	}

	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	@Override
	public String toString() {
		return "UserCSVModel [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", contactnumber=" + contactnumber + ", role=" + role + ", address=" + address
				+ ", country=" + country + ", state=" + state + ", city=" + city + ", pincode=" + pincode
				+ ", stateadminemail=" + stateadminemail + ", locationname=" + locationname + ", warehouseName="
				+ warehouseName + ", deviceserialno=" + deviceserialno + ", devicetype=" + devicetype
				+ ", categoryname=" + categoryname + ", commodityname=" + commodityname + ", locationcode="
				+ locationcode + ", customerEmail=" + customerEmail + "]";
	}

	public UserCSVModel(String firstname, String lastname, String email, String password, String contactnumber,
			String role, String address, String country, String state, String city, String pincode,
			String stateadminemail, String locationname, String warehouseName, String deviceserialno, String devicetype,
			String categoryname, String commodityname, String locationcode, String customerEmail) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.contactnumber = contactnumber;
		this.role = role;
		this.address = address;
		this.country = country;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.stateadminemail = stateadminemail;
		this.locationname = locationname;
		this.warehouseName = warehouseName;
		this.deviceserialno = deviceserialno;
		this.devicetype = devicetype;
		this.categoryname = categoryname;
		this.commodityname = commodityname;
		this.locationcode = locationcode;
		this.customerEmail = customerEmail;
	}

	public UserCSVModel() {
		super();
	}
	
	

	

}
