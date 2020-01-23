package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Frozen;
import java.util.List;
import java.util.Map;
import com.datastax.driver.mapping.annotations.FrozenValue;
import java.util.Date;

/**
 * PanelDetails class corresponds to java bean class to table(panel_details) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="panel_details", keyspace="iqcloud")
public class PanelDetails {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="partition")
	private String partition;

	@Column(name="android_os_version")
	private String androidOsVersion;

	@Column(name="area_code")
	private String areaCode;

	@Column(name="arming_mode")
	private String armingMode;

	@Column(name="build_number")
	private String buildNumber;

	@Frozen
	@Column(name="carrier_details")
	private CarrierDetails carrierDetails;

	@Column(name="carrier_status")
	private String carrierStatus;

	@Column(name="celluar_connection")
	private String celluarConnection;

	@Column(name="cellular_signal_strength")
	private String cellularSignalStrength;

	@Column(name="city")
	private String city;

	@Column(name="country")
	private String country;

	@FrozenValue
	@Column(name="daughter_cards")
	private List<DaughterCardInfo> daughterCards;

	@Column(name="dealer_name")
	private String dealerName;

	@Column(name="hardware_version")
	private String hardwareVersion;

	@Column(name="installation_date")
	private Date installationDate;

	@Column(name="linux_version")
	private String linuxVersion;

	@Column(name="macaddress")
	private String macaddress;

	@Column(name="manufacture")
	private String manufacture;

	@Column(name="panel_properties")
	private Map<String,String> panelProperties;

	@Column(name="panel_status")
	private String panelStatus;

	@Column(name="part_number")
	private String partNumber;

	@Column(name="pca_serial_number")
	private String pcaSerialNumber;

	@Column(name="software_version")
	private String softwareVersion;

	@Column(name="state")
	private String state;

	@Column(name="updated_time")
	private Date updatedTime;

	@Column(name="zip_code")
	private String zipCode;


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
	 * @return partition 
	 */
	public String getPartition(){
		return partition;
	}

	/**
	 * @param partition Panel partition[This is intended to support future versions as of now all panels fall into partition-0]
	 */
	public void setPartition(String partition){
		this.partition = partition;
	}

	/**
	 * @return androidOsVersion 
	 */
	public String getAndroidOsVersion(){
		return androidOsVersion;
	}

	/**
	 * @param androidOsVersion Panel android os version
	 */
	public void setAndroidOsVersion(String androidOsVersion){
		this.androidOsVersion = androidOsVersion;
	}

	/**
	 * @return areaCode 
	 */
	public String getAreaCode(){
		return areaCode;
	}

	/**
	 * @param areaCode Area code
	 */
	public void setAreaCode(String areaCode){
		this.areaCode = areaCode;
	}

	/**
	 * @return armingMode 
	 */
	public String getArmingMode(){
		return armingMode;
	}

	/**
	 * @param armingMode As of now qolsys panel supports several modes of operation. They are 1.armaway 2.armstay & 3.disarm
	 */
	public void setArmingMode(String armingMode){
		this.armingMode = armingMode;
	}

	/**
	 * @return buildNumber 
	 */
	public String getBuildNumber(){
		return buildNumber;
	}

	/**
	 * @param buildNumber Panel build number
	 */
	public void setBuildNumber(String buildNumber){
		this.buildNumber = buildNumber;
	}

	/**
	 * @return carrierDetails 
	 */
	public CarrierDetails getCarrierDetails(){
		return carrierDetails;
	}

	/**
	 * @param carrierDetails Refer to corresponding UDT
	 */
	public void setCarrierDetails(CarrierDetails carrierDetails){
		this.carrierDetails = carrierDetails;
	}

	/**
	 * @return carrierStatus 
	 */
	public String getCarrierStatus(){
		return carrierStatus;
	}

	/**
	 * @param carrierStatus 
	 */
	public void setCarrierStatus(String carrierStatus){
		this.carrierStatus = carrierStatus;
	}

	/**
	 * @return celluarConnection 
	 */
	public String getCelluarConnection(){
		return celluarConnection;
	}

	/**
	 * @param celluarConnection Available/Not available
	 */
	public void setCelluarConnection(String celluarConnection){
		this.celluarConnection = celluarConnection;
	}

	/**
	 * @return cellularSignalStrength 
	 */
	public String getCellularSignalStrength(){
		return cellularSignalStrength;
	}

	/**
	 * @param cellularSignalStrength Number of signals shown
	 */
	public void setCellularSignalStrength(String cellularSignalStrength){
		this.cellularSignalStrength = cellularSignalStrength;
	}

	/**
	 * @return city 
	 */
	public String getCity(){
		return city;
	}

	/**
	 * @param city City name
	 */
	public void setCity(String city){
		this.city = city;
	}

	/**
	 * @return country 
	 */
	public String getCountry(){
		return country;
	}

	/**
	 * @param country Country [Can follow ISO-3166]
	 */
	public void setCountry(String country){
		this.country = country;
	}

	/**
	 * @return daughterCards 
	 */
	public List<DaughterCardInfo> getDaughterCards(){
		return daughterCards;
	}

	/**
	 * @param daughterCards Refer to corresponding UDT
	 */
	public void setDaughterCards(List<DaughterCardInfo> daughterCards){
		this.daughterCards = daughterCards;
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
	 * @return hardwareVersion 
	 */
	public String getHardwareVersion(){
		return hardwareVersion;
	}

	/**
	 * @param hardwareVersion Panel hardware revision
	 */
	public void setHardwareVersion(String hardwareVersion){
		this.hardwareVersion = hardwareVersion;
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
	 * @return linuxVersion 
	 */
	public String getLinuxVersion(){
		return linuxVersion;
	}

	/**
	 * @param linuxVersion Panel linux version 
	 */
	public void setLinuxVersion(String linuxVersion){
		this.linuxVersion = linuxVersion;
	}

	/**
	 * @return macaddress 
	 */
	public String getMacaddress(){
		return macaddress;
	}

	/**
	 * @param macaddress Panel macaddress[The universal format of Mac address in general are a six pair of hexadecimal number for example A0:23:DE:67:33:46]
	 */
	public void setMacaddress(String macaddress){
		this.macaddress = macaddress;
	}

	/**
	 * @return manufacture 
	 */
	public String getManufacture(){
		return manufacture;
	}

	/**
	 * @param manufacture Panel manufacturer name
	 */
	public void setManufacture(String manufacture){
		this.manufacture = manufacture;
	}

	/**
	 * @return panelProperties 
	 */
	public Map<String,String> getPanelProperties(){
		return panelProperties;
	}

	/**
	 * @param panelProperties Any additional properties of the panel will be accommodate here in form of Name/Value pairs
	 */
	public void setPanelProperties(Map<String,String> panelProperties){
		this.panelProperties = panelProperties;
	}

	/**
	 * @return panelStatus 
	 */
	public String getPanelStatus(){
		return panelStatus;
	}

	/**
	 * @param panelStatus Active/Inactive/any other states like alarm
	 */
	public void setPanelStatus(String panelStatus){
		this.panelStatus = panelStatus;
	}

	/**
	 * @return partNumber 
	 */
	public String getPartNumber(){
		return partNumber;
	}

	/**
	 * @param partNumber Panel part number [aka. P/N number]
	 */
	public void setPartNumber(String partNumber){
		this.partNumber = partNumber;
	}

	/**
	 * @return pcaSerialNumber 
	 */
	public String getPcaSerialNumber(){
		return pcaSerialNumber;
	}

	/**
	 * @param pcaSerialNumber Panel pca serial number
	 */
	public void setPcaSerialNumber(String pcaSerialNumber){
		this.pcaSerialNumber = pcaSerialNumber;
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
	 * @return state 
	 */
	public String getState(){
		return state;
	}

	/**
	 * @param state State name
	 */
	public void setState(String state){
		this.state = state;
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
	 * @return zipCode 
	 */
	public String getZipCode(){
		return zipCode;
	}

	/**
	 * @param zipCode Postal code
	 */
	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	/**
	 * String representation of Table(Entity) Object:PanelDetails 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", partition::"+partition+", androidOsVersion::"+androidOsVersion+", areaCode::"+areaCode+
		", armingMode::"+armingMode+", buildNumber::"+buildNumber+", carrierDetails::"+carrierDetails+
		", carrierStatus::"+carrierStatus+", celluarConnection::"+celluarConnection+", cellularSignalStrength::"+cellularSignalStrength+
		", city::"+city+", country::"+country+", daughterCards::"+daughterCards+
		", dealerName::"+dealerName+", hardwareVersion::"+hardwareVersion+", installationDate::"+installationDate+
		", linuxVersion::"+linuxVersion+", macaddress::"+macaddress+", manufacture::"+manufacture+
		", panelProperties::"+panelProperties+", panelStatus::"+panelStatus+", partNumber::"+partNumber+
		", pcaSerialNumber::"+pcaSerialNumber+", softwareVersion::"+softwareVersion+", state::"+state+
		", updatedTime::"+updatedTime+", zipCode::"+zipCode+"]";
	}

}