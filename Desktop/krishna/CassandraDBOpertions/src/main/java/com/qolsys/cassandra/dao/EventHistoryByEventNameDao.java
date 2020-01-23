package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.EventHistoryByEventName;
import com.qolsys.cassandra.accessor.EventHistoryByEventNameAccessor;
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
 * EventHistoryByEventNameDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to event_history_by_event_name 
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
public class EventHistoryByEventNameDao{

	private static Logger logger = LoggerFactory.getLogger(EventHistoryByEventNameDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByEventNameDao]
	* @exception Exception
	* @return List<EventHistoryByEventName>
	*/
	public List<EventHistoryByEventName> getAll(){
		List<EventHistoryByEventName> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByEventNameAccessor eventHistoryByEventNameAccessor = mappingManager.createAccessor(EventHistoryByEventNameAccessor.class);
			Result<EventHistoryByEventName> result = eventHistoryByEventNameAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of EventHistoryByEventNameDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [EventHistoryByEventNameDao]
	* @param eventName Name of the event
	* @param eventTime Event unique time
	* @exception Exception
	* @return EventHistoryByEventName
	*/
	public EventHistoryByEventName fetchEventHistoryByEventNameImpl(String eventName, UUIDs eventTime){
		EventHistoryByEventName list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			EventHistoryByEventNameAccessor eventHistoryByEventNameAccessor = mappingManager.createAccessor(EventHistoryByEventNameAccessor.class);
			Result<EventHistoryByEventName> result = eventHistoryByEventNameAccessor.fetchEventHistoryByEventName(eventName, eventTime);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchEventHistoryByEventName(eventName, eventTime) of EventHistoryByEventNameDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean EventHistoryByEventName
	* @param eventHistoryByEventName variable referencing the object corresponds to EventHistoryByEventName
	* @exception Exception
	* @return boolean
	*/
	public boolean saveEventHistoryByEventName(EventHistoryByEventName eventHistoryByEventName){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<EventHistoryByEventName> mapper = mappingManager.mapper(EventHistoryByEventName.class);
			mapper.save(eventHistoryByEventName);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update EventHistoryByEventName Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table event_history_by_event_name
	* @param json json representation for record that refers table event_history_by_event_name
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByEventHistoryByEventNameJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into event_history_by_event_name json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String EventHistoryByEventName Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean EventHistoryByEventName
	* @param list List of EventHistoryByEventName objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkEventHistoryByEventNameInsert(List<EventHistoryByEventName> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<EventHistoryByEventName> mapper = mappingManager.mapper(EventHistoryByEventName.class);
			BatchStatement batchStatement = new BatchStatement();
			for(EventHistoryByEventName bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution EventHistoryByEventName Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [event_history_by_event_name]
	* @param eventName Name of the event
	* @param eventTime Event unique time
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonEventHistoryByEventNameImpl(String eventName, UUIDs eventTime){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from event_history_by_event_name where event_name='"+eventName+"' and event_time='"+eventTime+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_event_name table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [event_history_by_event_name]
	* @param eventName Name of the event
	* @param eventTime Event unique time
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteEventHistoryByEventNameImpl(String eventName, UUIDs eventTime){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from event_history_by_event_name where event_name='"+eventName+"' and event_time='"+eventTime+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on event_history_by_event_name table", e);
		}
		return status;
	}

}