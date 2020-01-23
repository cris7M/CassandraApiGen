package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;

/**
 * PanelInventory class corresponds to java bean class to table(panel_inventory) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="panel_inventory", keyspace="iqcloud")
public class PanelInventory {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="protocol_type")
	private String protocolType;

	@ClusteringColumn(1)
	@Column(name="device_type")
	private String deviceType;

	@Column(name="count")
	private long count;


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
	 * @return protocolType 
	 */
	public String getProtocolType(){
		return protocolType;
	}

	/**
	 * @param protocolType Protocols supported by devices like Zwave, SRF etc
	 */
	public void setProtocolType(String protocolType){
		this.protocolType = protocolType;
	}

	/**
	 * @return deviceType 
	 */
	public String getDeviceType(){
		return deviceType;
	}

	/**
	 * @param deviceType device type indicates panels, sensor types etc
	 */
	public void setDeviceType(String deviceType){
		this.deviceType = deviceType;
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
	 * String representation of Table(Entity) Object:PanelInventory 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", protocolType::"+protocolType+", deviceType::"+deviceType+", count::"+count+"]";
	}

}