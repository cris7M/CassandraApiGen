package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.core.LocalDate;

/**
 * DealerDevices class corresponds to java bean class to table(dealer_devices) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="dealer_devices", keyspace="iqcloud")
public class DealerDevices {

	@PartitionKey(0)
	@Column(name="dealer_id")
	private UUIDs dealerId;

	@PartitionKey(1)
	@Column(name="device_type")
	private String deviceType;

	@ClusteringColumn(0)
	@Column(name="batch_number")
	private String batchNumber;

	@Column(name="dealer_name")
	private String dealerName;

	@Column(name="device_id")
	private String deviceId;

	@Column(name="device_state")
	private String deviceState;

	@Column(name="induction_date")
	private LocalDate inductionDate;

	@Column(name="installation_date")
	private LocalDate installationDate;


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
	 * @return batchNumber 
	 */
	public String getBatchNumber(){
		return batchNumber;
	}

	/**
	 * @param batchNumber Batch number is specific to manufacturer, incase it is not available Anand suggested to use combination of month & Year or year alone based on the frequency of batches released.
	 */
	public void setBatchNumber(String batchNumber){
		this.batchNumber = batchNumber;
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
	 * @return deviceId 
	 */
	public String getDeviceId(){
		return deviceId;
	}

	/**
	 * @param deviceId Device id
	 */
	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	/**
	 * @return deviceState 
	 */
	public String getDeviceState(){
		return deviceState;
	}

	/**
	 * @param deviceState To track whether successfully installed in field or subjected to RMI
	 */
	public void setDeviceState(String deviceState){
		this.deviceState = deviceState;
	}

	/**
	 * @return inductionDate 
	 */
	public LocalDate getInductionDate(){
		return inductionDate;
	}

	/**
	 * @param inductionDate When the device is delivered to dealer
	 */
	public void setInductionDate(LocalDate inductionDate){
		this.inductionDate = inductionDate;
	}

	/**
	 * @return installationDate 
	 */
	public LocalDate getInstallationDate(){
		return installationDate;
	}

	/**
	 * @param installationDate Device installation date and time
	 */
	public void setInstallationDate(LocalDate installationDate){
		this.installationDate = installationDate;
	}

	/**
	 * String representation of Table(Entity) Object:DealerDevices 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[dealerId::"+dealerId+
		", deviceType::"+deviceType+", batchNumber::"+batchNumber+", dealerName::"+dealerName+
		", deviceId::"+deviceId+", deviceState::"+deviceState+", inductionDate::"+inductionDate+
		", installationDate::"+installationDate+"]";
	}

}