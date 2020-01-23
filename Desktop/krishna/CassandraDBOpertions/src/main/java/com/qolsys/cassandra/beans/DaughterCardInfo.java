package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.UDT;
import com.datastax.driver.mapping.annotations.Field;
import java.util.Map;

/**
 * DaughterCardInfo class corresponds to java bean class to User Defined Type(daughter_card_info) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@UDT(name="daughter_card_info", keyspace="iqcloud")
public class DaughterCardInfo {

	@Field(name="name")
	private String name;
	@Field(name="protocol_type")
	private String protocolType;
	@Field(name="version")
	private String version;
	@Field(name="firmware_version")
	private String firmwareVersion;
	@Field(name="additional_properties")
	private Map<String,String> additionalProperties;

	/**
	 * @return name 
	 */
	public String getName(){
		return name;
	}

	/**
	 * @param name Daughter card name it may be complete model info
	 */
	public void setName(String name){
		this.name = name;
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
	 * @return version 
	 */
	public String getVersion(){
		return version;
	}

	/**
	 * @param version Revision of the release
	 */
	public void setVersion(String version){
		this.version = version;
	}

	/**
	 * @return firmwareVersion 
	 */
	public String getFirmwareVersion(){
		return firmwareVersion;
	}

	/**
	 * @param firmwareVersion Firmware version details
	 */
	public void setFirmwareVersion(String firmwareVersion){
		this.firmwareVersion = firmwareVersion;
	}

	/**
	 * @return additionalProperties 
	 */
	public Map<String,String> getAdditionalProperties(){
		return additionalProperties;
	}

	/**
	 * @param additionalProperties Any additional properties related to daughter card will be accommodated in the form of Key/Value pairs
	 */
	public void setAdditionalProperties(Map<String,String> additionalProperties){
		this.additionalProperties = additionalProperties;
	}

	/**
	 * String representation of UDT Object:DaughterCardInfo 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[name::"+name+
		", protocolType::"+protocolType+", version::"+version+", firmwareVersion::"+firmwareVersion+
		", additionalProperties::"+additionalProperties+"]";
	}

}