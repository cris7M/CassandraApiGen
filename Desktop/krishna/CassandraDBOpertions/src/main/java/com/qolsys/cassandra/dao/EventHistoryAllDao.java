package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.EventHistoryAll;
import com.qolsys.cassandra.accessor.EventHistoryAllAccessor;
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
 * EventHistoryAllDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to event_history_all 
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
public class EventHistoryAllDao{

	private static Logger logger = LoggerFactory.getLogger(EventHistoryAllDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryAllDao]
	* @exception Exception
	* @return List<EventHistoryAll>
	*/
	public List<EventHistoryAll> getAll(){
		List<EventHistoryAll> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryAllAccessor eventHistoryAllAccessor = mappingManager.createAccessor(EventHistoryAllAccessor.class);
			Result<EventHistoryAll> result = eventHistoryAllAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of EventHistoryAllDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryAllDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<EventHistoryAll>
	*/
	public List<EventHistoryAll> fetchEventHistoryAllImpl(String meid){
		List<EventHistoryAll> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryAllAccessor eventHistoryAllAccessor = mappingManager.createAccessor(EventHistoryAllAccessor.class);
			Result<EventHistoryAll> result = eventHistoryAllAccessor.fetchEventHistoryAll(meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryAll(meid) of EventHistoryAllDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryAllDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param eventTime Event unique time
	* @exception Exception
	* @return EventHistoryAll
	*/
	public EventHistoryAll fetchEventHistoryAllImpl(String meid, UUIDs eventTime){
		EventHistoryAll list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryAllAccessor eventHistoryAllAccessor = mappingManager.createAccessor(EventHistoryAllAccessor.class);
			Result<EventHistoryAll> result = eventHistoryAllAccessor.fetchEventHistoryAll(meid, eventTime);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryAll(meid, eventTime) of EventHistoryAllDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean EventHistoryAll
	* @param eventHistoryAll variable referencing the object corresponds to EventHistoryAll
	* @exception Exception
	* @return boolean
	*/
	public boolean saveEventHistoryAll(EventHistoryAll eventHistoryAll){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<EventHistoryAll> mapper = mappingManager.mapper(EventHistoryAll.class);
			mapper.save(eventHistoryAll);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update EventHistoryAll Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table event_history_all
	* @param json json representation for record that refers table event_history_all
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByEventHistoryAllJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into event_history_all json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String EventHistoryAll Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean EventHistoryAll
	* @param list List of EventHistoryAll objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkEventHistoryAllInsert(List<EventHistoryAll> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<EventHistoryAll> mapper = mappingManager.mapper(EventHistoryAll.class);
			BatchStatement batchStatement = new BatchStatement();
			for(EventHistoryAll bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution EventHistoryAll Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_all]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryAllImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_all where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_all table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_all]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param eventTime Event unique time
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryAllImpl(String meid, UUIDs eventTime){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_all where meid='"+meid+"' and event_time='"+eventTime+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_all table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_all]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryAllImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_all where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_all table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_all]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param eventTime Event unique time
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryAllImpl(String meid, UUIDs eventTime){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_all where meid='"+meid+"' and event_time='"+eventTime+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_all table", e);
		}
		return status;
	}

}