package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.Notifications;
import com.qolsys.cassandra.accessor.NotificationsAccessor;
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
 * NotificationsDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to notifications 
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
public class NotificationsDao{

	private static Logger logger = LoggerFactory.getLogger(NotificationsDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [NotificationsDao]
	* @exception Exception
	* @return List<Notifications>
	*/
	public List<Notifications> getAll(){
		List<Notifications> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			NotificationsAccessor notificationsAccessor = mappingManager.createAccessor(NotificationsAccessor.class);
			Result<Notifications> result = notificationsAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of NotificationsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [NotificationsDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<Notifications>
	*/
	public List<Notifications> fetchNotificationsImpl(String meid){
		List<Notifications> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			NotificationsAccessor notificationsAccessor = mappingManager.createAccessor(NotificationsAccessor.class);
			Result<Notifications> result = notificationsAccessor.fetchNotifications(meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchNotifications(meid) of NotificationsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [NotificationsDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param notificationType Type of notification
	* @exception Exception
	* @return Notifications
	*/
	public Notifications fetchNotificationsImpl(String meid, int notificationType){
		Notifications list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			NotificationsAccessor notificationsAccessor = mappingManager.createAccessor(NotificationsAccessor.class);
			Result<Notifications> result = notificationsAccessor.fetchNotifications(meid, notificationType);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchNotifications(meid, notificationType) of NotificationsDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean Notifications
	* @param notifications variable referencing the object corresponds to Notifications
	* @exception Exception
	* @return boolean
	*/
	public boolean saveNotifications(Notifications notifications){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<Notifications> mapper = mappingManager.mapper(Notifications.class);
			mapper.save(notifications);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update Notifications Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table notifications
	* @param json json representation for record that refers table notifications
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByNotificationsJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into notifications json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String Notifications Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean Notifications
	* @param list List of Notifications objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkNotificationsInsert(List<Notifications> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<Notifications> mapper = mappingManager.mapper(Notifications.class);
			BatchStatement batchStatement = new BatchStatement();
			for(Notifications bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution Notifications Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [notifications]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonNotificationsImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from notifications where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on notifications table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [notifications]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param notificationType Type of notification
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonNotificationsImpl(String meid, int notificationType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from notifications where meid='"+meid+"' and notification_type="+notificationType+"";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on notifications table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [notifications]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteNotificationsImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from notifications where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on notifications table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [notifications]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param notificationType Type of notification
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteNotificationsImpl(String meid, int notificationType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from notifications where meid='"+meid+"' and notification_type="+notificationType+"";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on notifications table", e);
		}
		return status;
	}

}