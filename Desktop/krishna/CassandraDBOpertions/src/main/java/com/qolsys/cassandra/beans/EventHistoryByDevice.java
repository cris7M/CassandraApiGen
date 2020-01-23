package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.core.utils.UUIDs;
import java.util.Map;

/**
 * EventHistoryByDevice class corresponds to java bean class to table(event_history_by_device) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="event_history_by_device", keyspace="iqcloud")
public class EventHistoryByDevice {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="protocol_type")
	private String protocolType;

	@ClusteringColumn(1)
	@Column(name="device_type")
	private String deviceType;

	@ClusteringColumn(2)
	@Column(name="zone_id")
	private int zoneId;

	@ClusteringColumn(3)
	@Column(name="event_time")
	private UUIDs eventTime;

	@Column(name="event")
	private Map<String,String> event;

	@Column(name="event_name")
	private String eventName;


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
	 * @return zoneId 
	 */
	public int getZoneId(){
		return zoneId;
	}

	/**
	 * @param zoneId This is refered as zoneId or NodeId based on device category
	 */
	public void setZoneId(int zoneId){
		this.zoneId = zoneId;
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
	 * @return eventName 
	 */
	public String getEventName(){
		return eventName;
	}

	/**
	 * @param eventName Name of the event
	 */
	public void setEventName(String eventName){
		this.eventName = eventName;
	}

	/**
	 * String representation of Table(Entity) Object:EventHistoryByDevice 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", protocolType::"+protocolType+", deviceType::"+deviceType+", zoneId::"+zoneId+
		", eventTime::"+eventTime+", event::"+event+", eventName::"+eventName+"]";
	}

}