package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.EventHistoryByDevice;
import com.qolsys.cassandra.accessor.EventHistoryByDeviceAccessor;
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
 * EventHistoryByDeviceDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to event_history_by_device 
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
public class EventHistoryByDeviceDao{

	private static Logger logger = LoggerFactory.getLogger(EventHistoryByDeviceDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceDao]
	* @exception Exception
	* @return List<EventHistoryByDevice>
	*/
	public List<EventHistoryByDevice> getAll(){
		List<EventHistoryByDevice> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceAccessor eventHistoryByDeviceAccessor = mappingManager.createAccessor(EventHistoryByDeviceAccessor.class);
			Result<EventHistoryByDevice> result = eventHistoryByDeviceAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of EventHistoryByDeviceDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<EventHistoryByDevice>
	*/
	public List<EventHistoryByDevice> fetchEventHistoryByDeviceImpl(String meid){
		List<EventHistoryByDevice> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceAccessor eventHistoryByDeviceAccessor = mappingManager.createAccessor(EventHistoryByDeviceAccessor.class);
			Result<EventHistoryByDevice> result = eventHistoryByDeviceAccessor.fetchEventHistoryByDevice(meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryByDevice(meid) of EventHistoryByDeviceDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return List<EventHistoryByDevice>
	*/
	public List<EventHistoryByDevice> fetchEventHistoryByDeviceImpl(String meid, String protocolType){
		List<EventHistoryByDevice> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceAccessor eventHistoryByDeviceAccessor = mappingManager.createAccessor(EventHistoryByDeviceAccessor.class);
			Result<EventHistoryByDevice> result = eventHistoryByDeviceAccessor.fetchEventHistoryByDevice(meid, protocolType);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryByDevice(meid, protocolType) of EventHistoryByDeviceDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return List<EventHistoryByDevice>
	*/
	public List<EventHistoryByDevice> fetchEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType){
		List<EventHistoryByDevice> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceAccessor eventHistoryByDeviceAccessor = mappingManager.createAccessor(EventHistoryByDeviceAccessor.class);
			Result<EventHistoryByDevice> result = eventHistoryByDeviceAccessor.fetchEventHistoryByDevice(meid, protocolType, deviceType);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryByDevice(meid, protocolType, deviceType) of EventHistoryByDeviceDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @exception Exception
	* @return List<EventHistoryByDevice>
	*/
	public List<EventHistoryByDevice> fetchEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType, int zoneId){
		List<EventHistoryByDevice> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceAccessor eventHistoryByDeviceAccessor = mappingManager.createAccessor(EventHistoryByDeviceAccessor.class);
			Result<EventHistoryByDevice> result = eventHistoryByDeviceAccessor.fetchEventHistoryByDevice(meid, protocolType, deviceType, zoneId);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryByDevice(meid, protocolType, deviceType, zoneId) of EventHistoryByDeviceDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByDeviceDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @param eventTime Event unique time
	* @exception Exception
	* @return EventHistoryByDevice
	*/
	public EventHistoryByDevice fetchEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType, int zoneId, UUIDs eventTime){
		EventHistoryByDevice list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByDeviceAccessor eventHistoryByDeviceAccessor = mappingManager.createAccessor(EventHistoryByDeviceAccessor.class);
			Result<EventHistoryByDevice> result = eventHistoryByDeviceAccessor.fetchEventHistoryByDevice(meid, protocolType, deviceType, zoneId, eventTime);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryByDevice(meid, protocolType, deviceType, zoneId, eventTime) of EventHistoryByDeviceDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean EventHistoryByDevice
	* @param eventHistoryByDevice variable referencing the object corresponds to EventHistoryByDevice
	* @exception Exception
	* @return boolean
	*/
	public boolean saveEventHistoryByDevice(EventHistoryByDevice eventHistoryByDevice){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<EventHistoryByDevice> mapper = mappingManager.mapper(EventHistoryByDevice.class);
			mapper.save(eventHistoryByDevice);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update EventHistoryByDevice Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table event_history_by_device
	* @param json json representation for record that refers table event_history_by_device
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByEventHistoryByDeviceJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into event_history_by_device json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String EventHistoryByDevice Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean EventHistoryByDevice
	* @param list List of EventHistoryByDevice objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkEventHistoryByDeviceInsert(List<EventHistoryByDevice> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<EventHistoryByDevice> mapper = mappingManager.mapper(EventHistoryByDevice.class);
			BatchStatement batchStatement = new BatchStatement();
			for(EventHistoryByDevice bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution EventHistoryByDevice Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryByDeviceImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_by_device where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryByDeviceImpl(String meid, String protocolType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_by_device where meid='"+meid+"' and protocol_type='"+protocolType+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_by_device where meid='"+meid+"' and protocol_type='"+protocolType+"' and device_type='"+deviceType+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType, int zoneId){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_by_device where meid='"+meid+"' and protocol_type='"+protocolType+"' and device_type='"+deviceType+"' and zone_id="+zoneId+"";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @param eventTime Event unique time
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType, int zoneId, UUIDs eventTime){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_by_device where meid='"+meid+"' and protocol_type='"+protocolType+"' and device_type='"+deviceType+"' and zone_id="+zoneId+" and event_time='"+eventTime+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryByDeviceImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_by_device where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryByDeviceImpl(String meid, String protocolType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_by_device where meid='"+meid+"' and protocol_type='"+protocolType+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_by_device where meid='"+meid+"' and protocol_type='"+protocolType+"' and device_type='"+deviceType+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType, int zoneId){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_by_device where meid='"+meid+"' and protocol_type='"+protocolType+"' and device_type='"+deviceType+"' and zone_id="+zoneId+"";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_by_device]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param deviceType device type indicates panels, sensor types etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @param eventTime Event unique time
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryByDeviceImpl(String meid, String protocolType, String deviceType, int zoneId, UUIDs eventTime){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_by_device where meid='"+meid+"' and protocol_type='"+protocolType+"' and device_type='"+deviceType+"' and zone_id="+zoneId+" and event_time='"+eventTime+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_device table", e);
		}
		return status;
	}

}