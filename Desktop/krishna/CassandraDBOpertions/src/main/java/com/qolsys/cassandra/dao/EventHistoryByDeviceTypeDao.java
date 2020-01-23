package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.EventHistoryByDeviceType;
import com.qolsys.cassandra.accessor.EventHistoryByDeviceTypeAccessor;
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
 * EventHistoryByDeviceTypeDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to event_history_by_device_type 
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
public class EventHistoryByDeviceTypeDao{

	private static Logger logger = LoggerFactory.getLogger(EventHistoryByDeviceTypeDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceTypeDao]
	* @exception Exception
	* @return List<EventHistoryByDeviceType>
	*/
	public List<EventHistoryByDeviceType> getAll(){
		List<EventHistoryByDeviceType> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceTypeAccessor eventHistoryByDeviceTypeAccessor = mappingManager.createAccessor(EventHistoryByDeviceTypeAccessor.class);
			Result<EventHistoryByDeviceType> result = eventHistoryByDeviceTypeAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of EventHistoryByDeviceTypeDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceTypeDao]
	* @param deviceType device type indicates panels, sensor types etc
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<EventHistoryByDeviceType>
	*/
	public List<EventHistoryByDeviceType> fetchEventHistoryByDeviceTypeImpl(String deviceType, String meid){
		List<EventHistoryByDeviceType> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceTypeAccessor eventHistoryByDeviceTypeAccessor = mappingManager.createAccessor(EventHistoryByDeviceTypeAccessor.class);
			Result<EventHistoryByDeviceType> result = eventHistoryByDeviceTypeAccessor.fetchEventHistoryByDeviceType(deviceType, meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryByDeviceType(deviceType, meid) of EventHistoryByDeviceTypeDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceTypeDao]
	* @param deviceType device type indicates panels, sensor types etc
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param eventTime Event unique time
	* @exception Exception
	* @return EventHistoryByDeviceType
	*/
	public EventHistoryByDeviceType fetchEventHistoryByDeviceTypeImpl(String deviceType, String meid, UUIDs eventTime){
		EventHistoryByDeviceType list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceTypeAccessor eventHistoryByDeviceTypeAccessor = mappingManager.createAccessor(EventHistoryByDeviceTypeAccessor.class);
			Result<EventHistoryByDeviceType> result = eventHistoryByDeviceTypeAccessor.fetchEventHistoryByDeviceType(deviceType, meid, eventTime);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryByDeviceType(deviceType, meid, eventTime) of EventHistoryByDeviceTypeDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean EventHistoryByDeviceType
	* @param eventHistoryByDeviceType variable referencing the object corresponds to EventHistoryByDeviceType
	* @exception Exception
	* @return boolean
	*/
	public boolean saveEventHistoryByDeviceType(EventHistoryByDeviceType eventHistoryByDeviceType){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<EventHistoryByDeviceType> mapper = mappingManager.mapper(EventHistoryByDeviceType.class);
			mapper.save(eventHistoryByDeviceType);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update EventHistoryByDeviceType Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table event_history_by_device_type
	* @param json json representation for record that refers table event_history_by_device_type
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByEventHistoryByDeviceTypeJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into event_history_by_device_type json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String EventHistoryByDeviceType Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean EventHistoryByDeviceType
	* @param list List of EventHistoryByDeviceType objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkEventHistoryByDeviceTypeInsert(List<EventHistoryByDeviceType> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<EventHistoryByDeviceType> mapper = mappingManager.mapper(EventHistoryByDeviceType.class);
			BatchStatement batchStatement = new BatchStatement();
			for(EventHistoryByDeviceType bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution EventHistoryByDeviceType Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_by_device_type]
	* @param deviceType device type indicates panels, sensor types etc
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryByDeviceTypeImpl(String deviceType, String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_by_device_type where device_type='"+deviceType+"' and meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device_type table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_by_device_type]
	* @param deviceType device type indicates panels, sensor types etc
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param eventTime Event unique time
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryByDeviceTypeImpl(String deviceType, String meid, UUIDs eventTime){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_by_device_type where device_type='"+deviceType+"' and meid='"+meid+"' and event_time='"+eventTime+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device_type table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_by_device_type]
	* @param deviceType device type indicates panels, sensor types etc
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryByDeviceTypeImpl(String deviceType, String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_by_device_type where device_type='"+deviceType+"' and meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device_type table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_by_device_type]
	* @param deviceType device type indicates panels, sensor types etc
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param eventTime Event unique time
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryByDeviceTypeImpl(String deviceType, String meid, UUIDs eventTime){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_by_device_type where device_type='"+deviceType+"' and meid='"+meid+"' and event_time='"+eventTime+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device_type table", e);
		}
		return status;
	}

}