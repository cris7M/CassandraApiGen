package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import java.util.Date;

/**
 * PanelsByPartnerDealer class corresponds to java bean class to table(panels_by_partner_dealer) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="panels_by_partner_dealer", keyspace="iqcloud")
public class PanelsByPartnerDealer {

	@PartitionKey(0)
	@Column(name="partner_name")
	private String partnerName;

	@ClusteringColumn(0)
	@Column(name="dealer_name")
	private String dealerName;

	@ClusteringColumn(1)
	@Column(name="meid")
	private String meid;

	@Column(name="registered_date")
	private Date registeredDate;


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
	 * @return registeredDate 
	 */
	public Date getRegisteredDate(){
		return registeredDate;
	}

	/**
	 * @param registeredDate Panel registrated date
	 */
	public void setRegisteredDate(Date registeredDate){
		this.registeredDate = registeredDate;
	}

	/**
	 * String representation of Table(Entity) Object:PanelsByPartnerDealer 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[partnerName::"+partnerName+
		", dealerName::"+dealerName+", meid::"+meid+", registeredDate::"+registeredDate+"]";
	}

}