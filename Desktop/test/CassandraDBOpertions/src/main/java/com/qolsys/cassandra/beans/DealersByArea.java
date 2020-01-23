package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.ClusteringColumn;

/**
 * DealersByArea class corresponds to java bean class to table(dealers_by_area) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="dealers_by_area", keyspace="iqcloud")
public class DealersByArea {

	@PartitionKey(0)
	@Column(name="area_code")
	private String areaCode;

	@ClusteringColumn(0)
	@Column(name="dealer_id")
	private UUIDs dealerId;

	@Column(name="dealer_name")
	private String dealerName;


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
	 * String representation of Table(Entity) Object:DealersByArea 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[areaCode::"+areaCode+
		", dealerId::"+dealerId+", dealerName::"+dealerName+"]";
	}

}