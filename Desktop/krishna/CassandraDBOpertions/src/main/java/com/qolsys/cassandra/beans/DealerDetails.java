package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import java.util.Map;

/**
 * DealerDetails class corresponds to java bean class to table(dealer_details) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="dealer_details", keyspace="iqcloud")
public class DealerDetails {

	@PartitionKey(0)
	@Column(name="dealer_id")
	private UUIDs dealerId;

	@ClusteringColumn(0)
	@Column(name="partner_name")
	private String partnerName;

	@Column(name="area_code")
	private String areaCode;

	@Column(name="city")
	private String city;

	@Column(name="country")
	private String country;

	@Column(name="dealer_name")
	private String dealerName;

	@Column(name="parent_dealer_id")
	private UUIDs parentDealerId;

	@Column(name="partner_details")
	private Map<String,String> partnerDetails;

	@Column(name="primary_contact")
	private String primaryContact;

	@Column(name="primary_email")
	private String primaryEmail;

	@Column(name="secondary_contact")
	private String secondaryContact;

	@Column(name="state")
	private String state;

	@Column(name="zipcode")
	private String zipcode;


	/**
	 * @return dealerId 
	 */
	public UUIDs getDealerId(){
		return dealerId;
	}

	/**
	 * @param dealerId Dealer unique id
	 */
	public void setDealerId(UUIDs dealerId){
		this.dealerId = dealerId;
	}

	/**
	 * @return partnerName 
	 */
	public String getPartnerName(){
		return partnerName;
	}

	/**
	 * @param partnerName Name of the partner where dealer is associated
	 */
	public void setPartnerName(String partnerName){
		this.partnerName = partnerName;
	}

	/**
	 * @return areaCode 
	 */
	public String getAreaCode(){
		return areaCode;
	}

	/**
	 * @param areaCode Area code
	 */
	public void setAreaCode(String areaCode){
		this.areaCode = areaCode;
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
	 * @return parentDealerId 
	 */
	public UUIDs getParentDealerId(){
		return parentDealerId;
	}

	/**
	 * @param parentDealerId This field is intended to support sub-dealer concept
	 */
	public void setParentDealerId(UUIDs parentDealerId){
		this.parentDealerId = parentDealerId;
	}

	/**
	 * @return partnerDetails 
	 */
	public Map<String,String> getPartnerDetails(){
		return partnerDetails;
	}

	/**
	 * @param partnerDetails If dealer is attached to any partner as of now its qolsys however in future it is ADT for ADT Dealers
	 */
	public void setPartnerDetails(Map<String,String> partnerDetails){
		this.partnerDetails = partnerDetails;
	}

	/**
	 * @return primaryContact 
	 */
	public String getPrimaryContact(){
		return primaryContact;
	}

	/**
	 * @param primaryContact Primary mobile number
	 */
	public void setPrimaryContact(String primaryContact){
		this.primaryContact = primaryContact;
	}

	/**
	 * @return primaryEmail 
	 */
	public String getPrimaryEmail(){
		return primaryEmail;
	}

	/**
	 * @param primaryEmail Primary email address
	 */
	public void setPrimaryEmail(String primaryEmail){
		this.primaryEmail = primaryEmail;
	}

	/**
	 * @return secondaryContact 
	 */
	public String getSecondaryContact(){
		return secondaryContact;
	}

	/**
	 * @param secondaryContact Alternative contact
	 */
	public void setSecondaryContact(String secondaryContact){
		this.secondaryContact = secondaryContact;
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
	 * String representation of Table(Entity) Object:DealerDetails 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[dealerId::"+dealerId+
		", partnerName::"+partnerName+", areaCode::"+areaCode+", city::"+city+
		", country::"+country+", dealerName::"+dealerName+", parentDealerId::"+parentDealerId+
		", partnerDetails::"+partnerDetails+", primaryContact::"+primaryContact+", primaryEmail::"+primaryEmail+
		", secondaryContact::"+secondaryContact+", state::"+state+", zipcode::"+zipcode+"]";
	}

}