package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import java.util.Date;
import java.util.Map;

/**
 * PanelAudit class corresponds to java bean class to table(panel_audit) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="panel_audit", keyspace="iqcloud")
public class PanelAudit {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="session_id")
	private UUIDs sessionId;

	@Column(name="end_time")
	private Date endTime;

	@Column(name="panel_session_stop_properties")
	private Map<String,String> panelSessionStopProperties;

	@Column(name="panel_startup_properties")
	private Map<String,String> panelStartupProperties;

	@Column(name="session_status")
	private String sessionStatus;

	@Column(name="session_termission_reason")
	private String sessionTermissionReason;

	@Column(name="session_type")
	private String sessionType;

	@Column(name="start_time")
	private Date startTime;

	@Column(name="up_time")
	private String upTime;


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
	 * @return sessionId 
	 */
	public UUIDs getSessionId(){
		return sessionId;
	}

	/**
	 * @param sessionId Session id along with unique time which is an UUID[Universal Unique id] based out of time
	 */
	public void setSessionId(UUIDs sessionId){
		this.sessionId = sessionId;
	}

	/**
	 * @return endTime 
	 */
	public Date getEndTime(){
		return endTime;
	}

	/**
	 * @param endTime Endtime will be updated only on graceful shutdown else will be updated on next successful startup of the panel.
	 */
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}

	/**
	 * @return panelSessionStopProperties 
	 */
	public Map<String,String> getPanelSessionStopProperties(){
		return panelSessionStopProperties;
	}

	/**
	 * @param panelSessionStopProperties lists out properties on session terminate, this will be recorded in case of only gracefulshutdown
	 */
	public void setPanelSessionStopProperties(Map<String,String> panelSessionStopProperties){
		this.panelSessionStopProperties = panelSessionStopProperties;
	}

	/**
	 * @return panelStartupProperties 
	 */
	public Map<String,String> getPanelStartupProperties(){
		return panelStartupProperties;
	}

	/**
	 * @param panelStartupProperties Description of all high priority or important properties at the panel startup
	 */
	public void setPanelStartupProperties(Map<String,String> panelStartupProperties){
		this.panelStartupProperties = panelStartupProperties;
	}

	/**
	 * @return sessionStatus 
	 */
	public String getSessionStatus(){
		return sessionStatus;
	}

	/**
	 * @param sessionStatus whether it is active or expired session
	 */
	public void setSessionStatus(String sessionStatus){
		this.sessionStatus = sessionStatus;
	}

	/**
	 * @return sessionTermissionReason 
	 */
	public String getSessionTermissionReason(){
		return sessionTermissionReason;
	}

	/**
	 * @param sessionTermissionReason detailed reason for the session termination like crash/tombstones etc
	 */
	public void setSessionTermissionReason(String sessionTermissionReason){
		this.sessionTermissionReason = sessionTermissionReason;
	}

	/**
	 * @return sessionType 
	 */
	public String getSessionType(){
		return sessionType;
	}

	/**
	 * @param sessionType Type of session and it can be also source of session initiation
	 */
	public void setSessionType(String sessionType){
		this.sessionType = sessionType;
	}

	/**
	 * @return startTime 
	 */
	public Date getStartTime(){
		return startTime;
	}

	/**
	 * @param startTime Start time of the panel
	 */
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}

	/**
	 * @return upTime 
	 */
	public String getUpTime(){
		return upTime;
	}

	/**
	 * @param upTime total uptime of the panel
	 */
	public void setUpTime(String upTime){
		this.upTime = upTime;
	}

	/**
	 * String representation of Table(Entity) Object:PanelAudit 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", sessionId::"+sessionId+", endTime::"+endTime+", panelSessionStopProperties::"+panelSessionStopProperties+
		", panelStartupProperties::"+panelStartupProperties+", sessionStatus::"+sessionStatus+", sessionTermissionReason::"+sessionTermissionReason+
		", sessionType::"+sessionType+", startTime::"+startTime+", upTime::"+upTime+"]";
	}

}