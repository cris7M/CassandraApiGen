package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.SensorInfo;
import com.qolsys.cassandra.accessor.SensorInfoAccessor;
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
 * SensorInfoDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to sensor_info 
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
public class SensorInfoDao{

	private static Logger logger = LoggerFactory.getLogger(SensorInfoDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [SensorInfoDao]
	* @exception Exception
	* @return List<SensorInfo>
	*/
	public List<SensorInfo> getAll(){
		List<SensorInfo> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			SensorInfoAccessor sensorInfoAccessor = mappingManager.createAccessor(SensorInfoAccessor.class);
			Result<SensorInfo> result = sensorInfoAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of SensorInfoDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [SensorInfoDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<SensorInfo>
	*/
	public List<SensorInfo> fetchSensorInfoImpl(String meid){
		List<SensorInfo> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			SensorInfoAccessor sensorInfoAccessor = mappingManager.createAccessor(SensorInfoAccessor.class);
			Result<SensorInfo> result = sensorInfoAccessor.fetchSensorInfo(meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchSensorInfo(meid) of SensorInfoDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [SensorInfoDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return List<SensorInfo>
	*/
	public List<SensorInfo> fetchSensorInfoImpl(String meid, String protocolType){
		List<SensorInfo> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			SensorInfoAccessor sensorInfoAccessor = mappingManager.createAccessor(SensorInfoAccessor.class);
			Result<SensorInfo> result = sensorInfoAccessor.fetchSensorInfo(meid, protocolType);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchSensorInfo(meid, protocolType) of SensorInfoDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [SensorInfoDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @exception Exception
	* @return List<SensorInfo>
	*/
	public List<SensorInfo> fetchSensorInfoImpl(String meid, String protocolType, int zoneId){
		List<SensorInfo> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			SensorInfoAccessor sensorInfoAccessor = mappingManager.createAccessor(SensorInfoAccessor.class);
			Result<SensorInfo> result = sensorInfoAccessor.fetchSensorInfo(meid, protocolType, zoneId);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchSensorInfo(meid, protocolType, zoneId) of SensorInfoDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [SensorInfoDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @param endpointId This parameter will act as a differentiating factor incase a single sensor is capable of exhibiting multiple functions. Currently Zwave supports being the examples from our inventory are SwitchStrip, SmartSocket etc
	* @exception Exception
	* @return SensorInfo
	*/
	public SensorInfo fetchSensorInfoImpl(String meid, String protocolType, int zoneId, int endpointId){
		SensorInfo list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			SensorInfoAccessor sensorInfoAccessor = mappingManager.createAccessor(SensorInfoAccessor.class);
			Result<SensorInfo> result = sensorInfoAccessor.fetchSensorInfo(meid, protocolType, zoneId, endpointId);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchSensorInfo(meid, protocolType, zoneId, endpointId) of SensorInfoDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean SensorInfo
	* @param sensorInfo variable referencing the object corresponds to SensorInfo
	* @exception Exception
	* @return boolean
	*/
	public boolean saveSensorInfo(SensorInfo sensorInfo){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<SensorInfo> mapper = mappingManager.mapper(SensorInfo.class);
			mapper.save(sensorInfo);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update SensorInfo Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table sensor_info
	* @param json json representation for record that refers table sensor_info
	* @exception Exception
	* @return boolean
	*/
	public boolean insertBySensorInfoJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into sensor_info json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String SensorInfo Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean SensorInfo
	* @param list List of SensorInfo objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkSensorInfoInsert(List<SensorInfo> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<SensorInfo> mapper = mappingManager.mapper(SensorInfo.class);
			BatchStatement batchStatement = new BatchStatement();
			for(SensorInfo bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution SensorInfo Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [sensor_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonSensorInfoImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from sensor_info where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on sensor_info table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [sensor_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonSensorInfoImpl(String meid, String protocolType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from sensor_info where meid='"+meid+"' and protocol_type='"+protocolType+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on sensor_info table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [sensor_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonSensorInfoImpl(String meid, String protocolType, int zoneId){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from sensor_info where meid='"+meid+"' and protocol_type='"+protocolType+"' and zone_id="+zoneId+"";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on sensor_info table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [sensor_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @param endpointId This parameter will act as a differentiating factor incase a single sensor is capable of exhibiting multiple functions. Currently Zwave supports being the examples from our inventory are SwitchStrip, SmartSocket etc
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonSensorInfoImpl(String meid, String protocolType, int zoneId, int endpointId){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from sensor_info where meid='"+meid+"' and protocol_type='"+protocolType+"' and zone_id="+zoneId+" and endpoint_id="+endpointId+"";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on sensor_info table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [sensor_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteSensorInfoImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from sensor_info where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on sensor_info table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [sensor_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteSensorInfoImpl(String meid, String protocolType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from sensor_info where meid='"+meid+"' and protocol_type='"+protocolType+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on sensor_info table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [sensor_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteSensorInfoImpl(String meid, String protocolType, int zoneId){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from sensor_info where meid='"+meid+"' and protocol_type='"+protocolType+"' and zone_id="+zoneId+"";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on sensor_info table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [sensor_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param protocolType Protocols supported by devices like Zwave, SRF etc
	* @param zoneId This is refered as zoneId or NodeId based on device category
	* @param endpointId This parameter will act as a differentiating factor incase a single sensor is capable of exhibiting multiple functions. Currently Zwave supports being the examples from our inventory are SwitchStrip, SmartSocket etc
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteSensorInfoImpl(String meid, String protocolType, int zoneId, int endpointId){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from sensor_info where meid='"+meid+"' and protocol_type='"+protocolType+"' and zone_id="+zoneId+" and endpoint_id="+endpointId+"";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on sensor_info table", e);
		}
		return status;
	}

}