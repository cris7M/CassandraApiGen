package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import java.util.Map;

/**
 * EventHistoryAll class corresponds to java bean class to table(event_history_all) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="event_history_all", keyspace="iqcloud")
public class EventHistoryAll {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="event_time")
	private UUIDs eventTime;

	@Column(name="device_type")
	private String deviceType;

	@Column(name="event")
	private Map<String,String> event;

	@Column(name="event_name")
	private String eventName;

	@Column(name="protocol_type")
	private String protocolType;

	@Column(name="zone_node_id")
	private int zoneNodeId;


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
	 * @return zoneNodeId 
	 */
	public int getZoneNodeId(){
		return zoneNodeId;
	}

	/**
	 * @param zoneNodeId Zone id
	 */
	public void setZoneNodeId(int zoneNodeId){
		this.zoneNodeId = zoneNodeId;
	}

	/**
	 * String representation of Table(Entity) Object:EventHistoryAll 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", eventTime::"+eventTime+", deviceType::"+deviceType+", event::"+event+
		", eventName::"+eventName+", protocolType::"+protocolType+", zoneNodeId::"+zoneNodeId+"]";
	}

}