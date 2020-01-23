package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.core.LocalDate;
import java.util.Map;

/**
 * PanelDailyAnalytics class corresponds to java bean class to table(panel_daily_analytics) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="panel_daily_analytics", keyspace="iqcloud")
public class PanelDailyAnalytics {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@PartitionKey(1)
	@Column(name="panel_date")
	private LocalDate panelDate;

	@Column(name="alarm_counter")
	private Map<String,Integer> alarmCounter;

	@Column(name="device_wakeup_time")
	private String deviceWakeupTime;

	@Column(name="events_counter")
	private Map<String,Integer> eventsCounter;

	@Column(name="exception_counter")
	private Map<String,Integer> exceptionCounter;

	@Column(name="software_version")
	private String softwareVersion;


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
	 * @return panelDate 
	 */
	public LocalDate getPanelDate(){
		return panelDate;
	}

	/**
	 * @param panelDate Specific date of the panel to get summary of activity on critical events base
	 */
	public void setPanelDate(LocalDate panelDate){
		this.panelDate = panelDate;
	}

	/**
	 * @return alarmCounter 
	 */
	public Map<String,Integer> getAlarmCounter(){
		return alarmCounter;
	}

	/**
	 * @param alarmCounter Alarm type and corresponding count per day
	 */
	public void setAlarmCounter(Map<String,Integer> alarmCounter){
		this.alarmCounter = alarmCounter;
	}

	/**
	 * @return deviceWakeupTime 
	 */
	public String getDeviceWakeupTime(){
		return deviceWakeupTime;
	}

	/**
	 * @param deviceWakeupTime Device wake up time (update it every 24 hours)
	 */
	public void setDeviceWakeupTime(String deviceWakeupTime){
		this.deviceWakeupTime = deviceWakeupTime;
	}

	/**
	 * @return eventsCounter 
	 */
	public Map<String,Integer> getEventsCounter(){
		return eventsCounter;
	}

	/**
	 * @param eventsCounter Events reported on daily basis with count
	 */
	public void setEventsCounter(Map<String,Integer> eventsCounter){
		this.eventsCounter = eventsCounter;
	}

	/**
	 * @return exceptionCounter 
	 */
	public Map<String,Integer> getExceptionCounter(){
		return exceptionCounter;
	}

	/**
	 * @param exceptionCounter Exceptions occured on daily basis with count
	 */
	public void setExceptionCounter(Map<String,Integer> exceptionCounter){
		this.exceptionCounter = exceptionCounter;
	}

	/**
	 * @return softwareVersion 
	 */
	public String getSoftwareVersion(){
		return softwareVersion;
	}

	/**
	 * @param softwareVersion Panel software version 
	 */
	public void setSoftwareVersion(String softwareVersion){
		this.softwareVersion = softwareVersion;
	}

	/**
	 * String representation of Table(Entity) Object:PanelDailyAnalytics 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", panelDate::"+panelDate+", alarmCounter::"+alarmCounter+", deviceWakeupTime::"+deviceWakeupTime+
		", eventsCounter::"+eventsCounter+", exceptionCounter::"+exceptionCounter+", softwareVersion::"+softwareVersion+"]";
	}

}