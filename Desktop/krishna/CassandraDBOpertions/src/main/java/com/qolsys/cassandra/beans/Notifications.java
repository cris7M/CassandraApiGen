package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;

/**
 * Notifications class corresponds to java bean class to table(notifications) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="notifications", keyspace="iqcloud")
public class Notifications {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="notification_type")
	private int notificationType;

	@Column(name="description")
	private String description;

	@Column(name="notified_status")
	private boolean notifiedStatus;


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
	 * @return notificationType 
	 */
	public int getNotificationType(){
		return notificationType;
	}

	/**
	 * @param notificationType Type of notification
	 */
	public void setNotificationType(int notificationType){
		this.notificationType = notificationType;
	}

	/**
	 * @return description 
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * @param description Description of notification
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * @return notifiedStatus 
	 */
	public boolean getNotifiedStatus(){
		return notifiedStatus;
	}

	/**
	 * @param notifiedStatus Nofied/Not notified
	 */
	public void setNotifiedStatus(boolean notifiedStatus){
		this.notifiedStatus = notifiedStatus;
	}

	/**
	 * String representation of Table(Entity) Object:Notifications 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", notificationType::"+notificationType+", description::"+description+", notifiedStatus::"+notifiedStatus+"]";
	}

}