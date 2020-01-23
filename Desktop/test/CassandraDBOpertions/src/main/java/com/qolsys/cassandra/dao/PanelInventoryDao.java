package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.PanelInventory;
import com.qolsys.cassandra.accessor.PanelInventoryAccessor;
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
 * PanelInventoryDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to panel_inventory 
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
public class PanelInventoryDao{

	private static Logger logger = LoggerFactory.getLogger(PanelInventoryDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelInventoryDao]
	* @exception Exception
	* @return List<PanelInventory>
	*/
	public List<PanelInventory> getAll(){
		List<PanelInventory> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelInventoryAccessor panelInventoryAccessor = mappingManager.createAccessor(PanelInventoryAccessor.class);
			Result<PanelInventory> result = panelInventoryAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of PanelInventoryDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelInventoryDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<PanelInventory>
	*/
	public List<PanelInventory> fetchPanelInventoryImpl(String meid){
		List<PanelInventory> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelInventoryAccessor panelInventoryAccessor = mappingManager.createAccessor(PanelInventoryAccessor.class);
			Result<PanelInventory> result = panelInventoryAccessor.fetchPanelInventory(meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelInventory(meid) of PanelInventoryDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelInventoryDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return List<PanelInventory>
	*/
	public List<PanelInventory> fetchPanelInventoryImpl(String meid, String protocolType){
		List<PanelInventory> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelInventoryAccessor panelInventoryAccessor = mappingManager.createAccessor(PanelInventoryAccessor.class);
			Result<PanelInventory> result = panelInventoryAccessor.fetchPanelInventory(meid, protocolType);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelInventory(meid, protocolType) of PanelInventoryDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelInventoryDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return PanelInventory
	*/
	public PanelInventory fetchPanelInventoryImpl(String meid, String protocolType, String deviceType){
		PanelInventory list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelInventoryAccessor panelInventoryAccessor = mappingManager.createAccessor(PanelInventoryAccessor.class);
			Result<PanelInventory> result = panelInventoryAccessor.fetchPanelInventory(meid, protocolType, deviceType);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelInventory(meid, protocolType, deviceType) of PanelInventoryDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean PanelInventory
	* @param panelInventory variable referencing the object corresponds to PanelInventory
	* @exception Exception
	* @return boolean
	*/
	public boolean savePanelInventory(PanelInventory panelInventory){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<PanelInventory> mapper = mappingManager.mapper(PanelInventory.class);
			mapper.save(panelInventory);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update PanelInventory Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table panel_inventory
	* @param json json representation for record that refers table panel_inventory
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByPanelInventoryJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into panel_inventory json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String PanelInventory Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean PanelInventory
	* @param list List of PanelInventory objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkPanelInventoryInsert(List<PanelInventory> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<PanelInventory> mapper = mappingManager.mapper(PanelInventory.class);
			BatchStatement batchStatement = new BatchStatement();
			for(PanelInventory bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution PanelInventory Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_inventory]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelInventoryImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_inventory where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_inventory table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_inventory]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelInventoryImpl(String meid, String protocolType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_inventory where meid='"+meid+"' and protocol_type='"+protocolType+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_inventory table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_inventory]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelInventoryImpl(String meid, String protocolType, String deviceType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_inventory where meid='"+meid+"' and protocol_type='"+protocolType+"' and device_type='"+deviceType+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_inventory table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_inventory]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelInventoryImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_inventory where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_inventory table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_inventory]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelInventoryImpl(String meid, String protocolType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_inventory where meid='"+meid+"' and protocol_type='"+protocolType+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_inventory table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_inventory]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelInventoryImpl(String meid, String protocolType, String deviceType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_inventory where meid='"+meid+"' and protocol_type='"+protocolType+"' and device_type='"+deviceType+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_inventory table", e);
		}
		return status;
	}

}