package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import java.util.Map;
import java.util.Date;

/**
 * SensorInfo class corresponds to java bean class to table(sensor_info) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="sensor_info", keyspace="iqcloud")
public class SensorInfo {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="protocol_type")
	private String protocolType;

	@ClusteringColumn(1)
	@Column(name="zone_id")
	private int zoneId;

	@ClusteringColumn(2)
	@Column(name="endpoint_id")
	private int endpointId;

	@Column(name="battery_status")
	private String batteryStatus;

	@Column(name="chime_type")
	private String chimeType;

	@Column(name="dealer_name")
	private String dealerName;

	@Column(name="device_properties")
	private Map<String,String> deviceProperties;

	@Column(name="installation_date")
	private Date installationDate;

	@Column(name="sensor_name")
	private String sensorName;

	@Column(name="sensor_state")
	private String sensorState;

	@Column(name="sensor_status")
	private String sensorStatus;

	@Column(name="sensor_tts")
	private String sensorTts;

	@Column(name="sensor_type")
	private String sensorType;

	@Column(name="sequence_number")
	private String sequenceNumber;

	@Column(name="updated_time")
	private Date updatedTime;

	@Column(name="vendor_name")
	private String vendorName;


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
	 * @return endpointId 
	 */
	public int getEndpointId(){
		return endpointId;
	}

	/**
	 * @param endpointId This parameter will act as a differentiating factor incase a single sensor is capable of exhibiting multiple functions. Currently Zwave supports being the examples from our inventory are SwitchStrip, SmartSocket etc
	 */
	public void setEndpointId(int endpointId){
		this.endpointId = endpointId;
	}

	/**
	 * @return batteryStatus 
	 */
	public String getBatteryStatus(){
		return batteryStatus;
	}

	/**
	 * @param batteryStatus Battery status that indicates either level(in %) or high/low.
	 */
	public void setBatteryStatus(String batteryStatus){
		this.batteryStatus = batteryStatus;
	}

	/**
	 * @return chimeType 
	 */
	public String getChimeType(){
		return chimeType;
	}

	/**
	 * @param chimeType Sensor notification sounds.
	 */
	public void setChimeType(String chimeType){
		this.chimeType = chimeType;
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
	 * @return deviceProperties 
	 */
	public Map<String,String> getDeviceProperties(){
		return deviceProperties;
	}

	/**
	 * @param deviceProperties Device specific and Futuristic additional sensor properties are all accommodated here
	 */
	public void setDeviceProperties(Map<String,String> deviceProperties){
		this.deviceProperties = deviceProperties;
	}

	/**
	 * @return installationDate 
	 */
	public Date getInstallationDate(){
		return installationDate;
	}

	/**
	 * @param installationDate Device installation date and time
	 */
	public void setInstallationDate(Date installationDate){
		this.installationDate = installationDate;
	}

	/**
	 * @return sensorName 
	 */
	public String getSensorName(){
		return sensorName;
	}

	/**
	 * @param sensorName Custom sensor name chosen by customer
	 */
	public void setSensorName(String sensorName){
		this.sensorName = sensorName;
	}

	/**
	 * @return sensorState 
	 */
	public String getSensorState(){
		return sensorState;
	}

	/**
	 * @param sensorState Status of sensor tripped/Open/Close/Tamper/Failure etc
	 */
	public void setSensorState(String sensorState){
		this.sensorState = sensorState;
	}

	/**
	 * @return sensorStatus 
	 */
	public String getSensorStatus(){
		return sensorStatus;
	}

	/**
	 * @param sensorStatus Active/Inactive
	 */
	public void setSensorStatus(String sensorStatus){
		this.sensorStatus = sensorStatus;
	}

	/**
	 * @return sensorTts 
	 */
	public String getSensorTts(){
		return sensorTts;
	}

	/**
	 * @param sensorTts TTS are the different speech messages based on event for particular sensor
	 */
	public void setSensorTts(String sensorTts){
		this.sensorTts = sensorTts;
	}

	/**
	 * @return sensorType 
	 */
	public String getSensorType(){
		return sensorType;
	}

	/**
	 * @param sensorType Categorization based on the uscase/purpose of the sensor like MainDoor/Thermostat etc
	 */
	public void setSensorType(String sensorType){
		this.sensorType = sensorType;
	}

	/**
	 * @return sequenceNumber 
	 */
	public String getSequenceNumber(){
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber Unique identifier of the sensor which manufacturer assign per batch. Some manufacturers even follow specification which may signifies some info in number itself
	 */
	public void setSequenceNumber(String sequenceNumber){
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return updatedTime 
	 */
	public Date getUpdatedTime(){
		return updatedTime;
	}

	/**
	 * @param updatedTime Last device information updated date and time
	 */
	public void setUpdatedTime(Date updatedTime){
		this.updatedTime = updatedTime;
	}

	/**
	 * @return vendorName 
	 */
	public String getVendorName(){
		return vendorName;
	}

	/**
	 * @param vendorName Sensor manufaturer/seller name
	 */
	public void setVendorName(String vendorName){
		this.vendorName = vendorName;
	}

	/**
	 * String representation of Table(Entity) Object:SensorInfo 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", protocolType::"+protocolType+", zoneId::"+zoneId+", endpointId::"+endpointId+
		", batteryStatus::"+batteryStatus+", chimeType::"+chimeType+", dealerName::"+dealerName+
		", deviceProperties::"+deviceProperties+", installationDate::"+installationDate+", sensorName::"+sensorName+
		", sensorState::"+sensorState+", sensorStatus::"+sensorStatus+", sensorTts::"+sensorTts+
		", sensorType::"+sensorType+", sequenceNumber::"+sequenceNumber+", updatedTime::"+updatedTime+
		", vendorName::"+vendorName+"]";
	}

}