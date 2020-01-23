package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;

/**
 * DealerCustomerDetails class corresponds to java bean class to table(dealer_customer_details) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="dealer_customer_details", keyspace="iqcloud")
public class DealerCustomerDetails {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@Column(name="address1")
	private String address1;

	@Column(name="address2")
	private String address2;

	@Column(name="city")
	private String city;

	@Column(name="country")
	private String country;

	@Column(name="dealer_name")
	private String dealerName;

	@Column(name="email")
	private String email;

	@Column(name="home_contact")
	private String homeContact;

	@Column(name="state")
	private String state;

	@Column(name="work_contact")
	private String workContact;

	@Column(name="zipcode")
	private String zipcode;


	/**
	 * @return meid 
	 */
	public String getMeid(){
		return meid;
	}

	/**
	 * @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	 */
	public void setMeid(String meid){
		this.meid = meid;
	}

	/**
	 * @return address1 
	 */
	public String getAddress1(){
		return address1;
	}

	/**
	 * @param address1 Address description stating building number and name
	 */
	public void setAddress1(String address1){
		this.address1 = address1;
	}

	/**
	 * @return address2 
	 */
	public String getAddress2(){
		return address2;
	}

	/**
	 * @param address2 Address description that has street and locality information
	 */
	public void setAddress2(String address2){
		this.address2 = address2;
	}

	/**
	 * @return city 
	 */
	public String getCity(){
		return city;
	}

	/**
	 * @param city City name
	 */
	public void setCity(String city){
		this.city = city;
	}

	/**
	 * @return country 
	 */
	public String getCountry(){
		return country;
	}

	/**
	 * @param country Country [Can follow ISO-3166]
	 */
	public void setCountry(String country){
		this.country = country;
	}

	/**
	 * @return dealerName 
	 */
	public String getDealerName(){
		return dealerName;
	}

	/**
	 * @param dealerName Name of the dealer customer associated with the panel
	 */
	public void setDealerName(String dealerName){
		this.dealerName = dealerName;
	}

	/**
	 * @return email 
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * @param email Email address
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * @return homeContact 
	 */
	public String getHomeContact(){
		return homeContact;
	}

	/**
	 * @param homeContact Primary Mobile number (Notification purpose via mobile like SMS alerts)
	 */
	public void setHomeContact(String homeContact){
		this.homeContact = homeContact;
	}

	/**
	 * @return state 
	 */
	public String getState(){
		return state;
	}

	/**
	 * @param state State name
	 */
	public void setState(String state){
		this.state = state;
	}

	/**
	 * @return workContact 
	 */
	public String getWorkContact(){
		return workContact;
	}

	/**
	 * @param workContact Secondary Mobile number(Alternative number or office number)
	 */
	public void setWorkContact(String workContact){
		this.workContact = workContact;
	}

	/**
	 * @return zipcode 
	 */
	public String getZipcode(){
		return zipcode;
	}

	/**
	 * @param zipcode Postal or Zip codes
	 */
	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}

	/**
	 * String representation of Table(Entity) Object:DealerCustomerDetails 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", address1::"+address1+", address2::"+address2+", city::"+city+
		", country::"+country+", dealerName::"+dealerName+", email::"+email+
		", homeContact::"+homeContact+", state::"+state+", workContact::"+workContact+
		", zipcode::"+zipcode+"]";
	}

}