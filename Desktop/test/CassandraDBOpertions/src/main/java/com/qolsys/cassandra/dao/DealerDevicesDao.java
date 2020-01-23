package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.DealerDevices;
import com.qolsys.cassandra.accessor.DealerDevicesAccessor;
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
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.core.BatchStatement;

/**
 * DealerDevicesDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to dealer_devices 
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
public class DealerDevicesDao{

	private static Logger logger = LoggerFactory.getLogger(DealerDevicesDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealerDevicesDao]
	* @exception Exception
	* @return List<DealerDevices>
	*/
	public List<DealerDevices> getAll(){
		List<DealerDevices> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealerDevicesAccessor dealerDevicesAccessor = mappingManager.createAccessor(DealerDevicesAccessor.class);
			Result<DealerDevices> result = dealerDevicesAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of DealerDevicesDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealerDevicesDao]
	* @param dealerId Dealer unique id
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return List<DealerDevices>
	*/
	public List<DealerDevices> fetchDealerDevicesImpl(UUIDs dealerId, String deviceType){
		List<DealerDevices> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealerDevicesAccessor dealerDevicesAccessor = mappingManager.createAccessor(DealerDevicesAccessor.class);
			Result<DealerDevices> result = dealerDevicesAccessor.fetchDealerDevices(dealerId, deviceType);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchDealerDevices(dealerId, deviceType) of DealerDevicesDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealerDevicesDao]
	* @param dealerId Dealer unique id
	* @param deviceType device type indicates panels, sensor types etc
	* @param batchNumber Batch number is specific to manufacturer, incase it is not available Anand suggested to use combination of month & Year or year alone based on the frequency of batches released.
	* @exception Exception
	* @return DealerDevices
	*/
	public DealerDevices fetchDealerDevicesImpl(UUIDs dealerId, String deviceType, String batchNumber){
		DealerDevices list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealerDevicesAccessor dealerDevicesAccessor = mappingManager.createAccessor(DealerDevicesAccessor.class);
			Result<DealerDevices> result = dealerDevicesAccessor.fetchDealerDevices(dealerId, deviceType, batchNumber);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchDealerDevices(dealerId, deviceType, batchNumber) of DealerDevicesDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean DealerDevices
	* @param dealerDevices variable referencing the object corresponds to DealerDevices
	* @exception Exception
	* @return boolean
	*/
	public boolean saveDealerDevices(DealerDevices dealerDevices){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<DealerDevices> mapper = mappingManager.mapper(DealerDevices.class);
			mapper.save(dealerDevices);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update DealerDevices Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table dealer_devices
	* @param json json representation for record that refers table dealer_devices
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByDealerDevicesJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into dealer_devices json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String DealerDevices Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean DealerDevices
	* @param list List of DealerDevices objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkDealerDevicesInsert(List<DealerDevices> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<DealerDevices> mapper = mappingManager.mapper(DealerDevices.class);
			BatchStatement batchStatement = new BatchStatement();
			for(DealerDevices bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution DealerDevices Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [dealer_devices]
	* @param dealerId Dealer unique id
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonDealerDevicesImpl(UUIDs dealerId, String deviceType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from dealer_devices where dealer_id='"+dealerId+"' and device_type='"+deviceType+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_devices table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [dealer_devices]
	* @param dealerId Dealer unique id
	* @param deviceType device type indicates panels, sensor types etc
	* @param batchNumber Batch number is specific to manufacturer, incase it is not available Anand suggested to use combination of month & Year or year alone based on the frequency of batches released.
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonDealerDevicesImpl(UUIDs dealerId, String deviceType, String batchNumber){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from dealer_devices where dealer_id='"+dealerId+"' and device_type='"+deviceType+"' and batch_number='"+batchNumber+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_devices table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [dealer_devices]
	* @param dealerId Dealer unique id
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteDealerDevicesImpl(UUIDs dealerId, String deviceType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from dealer_devices where dealer_id='"+dealerId+"' and device_type='"+deviceType+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_devices table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [dealer_devices]
	* @param dealerId Dealer unique id
	* @param deviceType device type indicates panels, sensor types etc
	* @param batchNumber Batch number is specific to manufacturer, incase it is not available Anand suggested to use combination of month & Year or year alone based on the frequency of batches released.
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteDealerDevicesImpl(UUIDs dealerId, String deviceType, String batchNumber){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from dealer_devices where dealer_id='"+dealerId+"' and device_type='"+deviceType+"' and batch_number='"+batchNumber+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_devices table", e);
		}
		return status;
	}

}