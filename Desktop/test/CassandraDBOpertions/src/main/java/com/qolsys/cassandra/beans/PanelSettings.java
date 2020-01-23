package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import java.util.Map;

/**
 * PanelSettings class corresponds to java bean class to table(panel_settings) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="panel_settings", keyspace="iqcloud")
public class PanelSettings {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@ClusteringColumn(0)
	@Column(name="settings_type")
	private String settingsType;

	@Column(name="settings")
	private Map<String,String> settings;


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
	 * @return settingsType 
	 */
	public String getSettingsType(){
		return settingsType;
	}

	/**
	 * @param settingsType Panel settings corresponding to particular module like arming, Zwave settings etc
	 */
	public void setSettingsType(String settingsType){
		this.settingsType = settingsType;
	}

	/**
	 * @return settings 
	 */
	public Map<String,String> getSettings(){
		return settings;
	}

	/**
	 * @param settings Setting type with DataType and Setting value
	 */
	public void setSettings(Map<String,String> settings){
		this.settings = settings;
	}

	/**
	 * String representation of Table(Entity) Object:PanelSettings 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", settingsType::"+settingsType+", settings::"+settings+"]";
	}

}