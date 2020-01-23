package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.ClusteringColumn;

/**
 * PanelsCountByParameter class corresponds to java bean class to table(panels_count_by_parameter) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="panels_count_by_parameter", keyspace="iqcloud")
public class PanelsCountByParameter {

	@PartitionKey(0)
	@Column(name="dealer_name")
	private String dealerName;

	@ClusteringColumn(0)
	@Column(name="id")
	private UUIDs id;

	@ClusteringColumn(1)
	@Column(name="param_type")
	private String paramType;

	@ClusteringColumn(2)
	@Column(name="param_name")
	private String paramName;

	@Column(name="count")
	private long count;


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
	 * @return id 
	 */
	public UUIDs getId(){
		return id;
	}

	/**
	 * @param id Random unique number that signifies unique device type
	 */
	public void setId(UUIDs id){
		this.id = id;
	}

	/**
	 * @return paramType 
	 */
	public String getParamType(){
		return paramType;
	}

	/**
	 * @param paramType Type of device
	 */
	public void setParamType(String paramType){
		this.paramType = paramType;
	}

	/**
	 * @return paramName 
	 */
	public String getParamName(){
		return paramName;
	}

	/**
	 * @param paramName Device name
	 */
	public void setParamName(String paramName){
		this.paramName = paramName;
	}

	/**
	 * @return count 
	 */
	public long getCount(){
		return count;
	}

	/**
	 * @param count Count of the particular device type
	 */
	public void setCount(long count){
		this.count = count;
	}

	/**
	 * String representation of Table(Entity) Object:PanelsCountByParameter 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[dealerName::"+dealerName+
		", id::"+id+", paramType::"+paramType+", paramName::"+paramName+
		", count::"+count+"]";
	}

}