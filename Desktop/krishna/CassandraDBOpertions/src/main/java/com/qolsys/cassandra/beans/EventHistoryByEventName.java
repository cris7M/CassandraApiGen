package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.core.utils.UUIDs;
import java.util.Map;

/**
 * EventHistoryByEventName class corresponds to java bean class to table(event_history_by_event_name) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="event_history_by_event_name", keyspace="iqcloud")
public class EventHistoryByEventName {

	@PartitionKey(0)
	@Column(name="event_name")
	private String eventName;

	@PartitionKey(1)
	@Column(name="event_time")
	private UUIDs eventTime;

	@Column(name="event")
	private Map<String,String> event;

	@Column(name="meid")
	private String meid;


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
	 * String representation of Table(Entity) Object:EventHistoryByEventName 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[eventName::"+eventName+
		", eventTime::"+eventTime+", event::"+event+", meid::"+meid+"]";
	}

}