package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.PanelSettings;
import com.qolsys.cassandra.accessor.PanelSettingsAccessor;
import com.qolsys.cassandra.constants.Constants;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Result;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.BatchStatement;

/**
 * PanelSettingsDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to panel_settings 
 * table in database. It contains following features as described below:<br>
 * 1.Fetch pojos based on search criteria<br>
 * 2.Fetch records as an array of Json Strings<br>
 * 3.Save or Update a record by pojo or Json<br>
 * 4.Insert multiple records via batch statements<br>
 * 5.Delete records based on recommended criteria
 *
 * @author cassandraIDC
 * 
 */
public class PanelSettingsDao{

	private static Logger logger = LoggerFactory.getLogger(PanelSettingsDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelSettingsDao]
	* @exception Exception
	* @return List<PanelSettings>
	*/
	public List<PanelSettings> getAll(){
		List<PanelSettings> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelSettingsAccessor panelSettingsAccessor = mappingManager.createAccessor(PanelSettingsAccessor.class);
			Result<PanelSettings> result = panelSettingsAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of PanelSettingsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelSettingsDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<PanelSettings>
	*/
	public List<PanelSettings> fetchPanelSettingsImpl(String meid){
		List<PanelSettings> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelSettingsAccessor panelSettingsAccessor = mappingManager.createAccessor(PanelSettingsAccessor.class);
			Result<PanelSettings> result = panelSettingsAccessor.fetchPanelSettings(meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelSettings(meid) of PanelSettingsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelSettingsDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param settingsType Panel settings corresponding to particular module like arming, Zwave settings etc
	* @exception Exception
	* @return PanelSettings
	*/
	public PanelSettings fetchPanelSettingsImpl(String meid, String settingsType){
		PanelSettings list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelSettingsAccessor panelSettingsAccessor = mappingManager.createAccessor(PanelSettingsAccessor.class);
			Result<PanelSettings> result = panelSettingsAccessor.fetchPanelSettings(meid, settingsType);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelSettings(meid, settingsType) of PanelSettingsDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean PanelSettings
	* @param panelSettings variable referencing the object corresponds to PanelSettings
	* @exception Exception
	* @return boolean
	*/
	public boolean savePanelSettings(PanelSettings panelSettings){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<PanelSettings> mapper = mappingManager.mapper(PanelSettings.class);
			mapper.save(panelSettings);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update PanelSettings Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table panel_settings
	* @param json json representation for record that refers table panel_settings
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByPanelSettingsJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into panel_settings json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String PanelSettings Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean PanelSettings
	* @param list List of PanelSettings objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkPanelSettingsInsert(List<PanelSettings> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<PanelSettings> mapper = mappingManager.mapper(PanelSettings.class);
			BatchStatement batchStatement = new BatchStatement();
			for(PanelSettings bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution PanelSettings Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_settings]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelSettingsImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_settings where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_settings table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_settings]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param settingsType Panel settings corresponding to particular module like arming, Zwave settings etc
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelSettingsImpl(String meid, String settingsType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_settings where meid='"+meid+"' and settings_type='"+settingsType+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_settings table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_settings]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelSettingsImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_settings where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_settings table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_settings]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param settingsType Panel settings corresponding to particular module like arming, Zwave settings etc
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelSettingsImpl(String meid, String settingsType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_settings where meid='"+meid+"' and settings_type='"+settingsType+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_settings table", e);
		}
		return status;
	}

}