package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import java.util.Map;

/**
 * EventHistoryByDeviceType class corresponds to java bean class to table(event_history_by_device_type) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="event_history_by_device_type", keyspace="iqcloud")
public class EventHistoryByDeviceType {

	@PartitionKey(0)
	@Column(name="device_type")
	private String deviceType;

	@PartitionKey(1)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="event_time")
	private UUIDs eventTime;

	@Column(name="event")
	private Map<String,String> event;


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
	 * @return eventTime 
	 */
	public UUIDs getEventTime(){
		return eventTime;
	}

	/**
	 * @param eventTime Event unique time
	 */
	public void setEventTime(UUIDs eventTime){
		this.eventTime = eventTime;
	}

	/**
	 * @return event 
	 */
	public Map<String,String> getEvent(){
		return event;
	}

	/**
	 * @param event Additional event related properties as key/value pairs
	 */
	public void setEvent(Map<String,String> event){
		this.event = event;
	}

	/**
	 * String representation of Table(Entity) Object:EventHistoryByDeviceType 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[deviceType::"+deviceType+
		", meid::"+meid+", eventTime::"+eventTime+", event::"+event+"]";
	}

}