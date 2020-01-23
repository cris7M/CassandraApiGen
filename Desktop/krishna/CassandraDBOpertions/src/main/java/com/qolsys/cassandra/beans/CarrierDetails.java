package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.UDT;
import com.datastax.driver.mapping.annotations.Field;

/**
 * CarrierDetails class corresponds to java bean class to User Defined Type(carrier_info) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@UDT(name="carrier_info", keyspace="iqcloud")
public class CarrierDetails {

	@Field(name="baseband_version")
	private String basebandVersion;
	@Field(name="iccid")
	private String iccid;
	@Field(name="imsi")
	private String imsi;
	@Field(name="imei")
	private String imei;

	/**
	 * @return basebandVersion 
	 */
	public String getBasebandVersion(){
		return basebandVersion;
	}

	/**
	 * @param basebandVersion Carrier baseband version defines range of frequency
	 */
	public void setBasebandVersion(String basebandVersion){
		this.basebandVersion = basebandVersion;
	}

	/**
	 * @return iccid 
	 */
	public String getIccid(){
		return iccid;
	}

	/**
	 * @param iccid It is 22 digit Integrated Circuit Card Identifier in general
	 */
	public void setIccid(String iccid){
		this.iccid = iccid;
	}

	/**
	 * @return imsi 
	 */
	public String getImsi(){
		return imsi;
	}

	/**
	 * @param imsi An International Mobile Subscriber Identity is up to 15 digits long. The first three digits[ISO-3166 n-3] represent the country code, followed by the the network code. The remaining digits, up to fifteen represents the unique subscriber number from within the network's customer base
	 */
	public void setImsi(String imsi){
		this.imsi = imsi;
	}

	/**
	 * @return imei 
	 */
	public String getImei(){
		return imei;
	}

	/**
	 * @param imei Every mobile phone, GSM modem or device with a built-in phone / modem has a unique 15 digit IMEI number. Based on this number, you can check some information about the device, eg brand or model.
	 */
	public void setImei(String imei){
		this.imei = imei;
	}

	/**
	 * String representation of UDT Object:CarrierDetails 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[basebandVersion::"+basebandVersion+
		", iccid::"+iccid+", imsi::"+imsi+", imei::"+imei+"]";
	}

}