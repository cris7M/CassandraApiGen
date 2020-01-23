package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.RequestResponseTrackingInfo;
import com.qolsys.cassandra.accessor.RequestResponseTrackingInfoAccessor;
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
 * RequestResponseTrackingInfoDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to request_response_tracking_info 
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
public class RequestResponseTrackingInfoDao{

	private static Logger logger = LoggerFactory.getLogger(RequestResponseTrackingInfoDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [RequestResponseTrackingInfoDao]
	* @exception Exception
	* @return List<RequestResponseTrackingInfo>
	*/
	public List<RequestResponseTrackingInfo> getAll(){
		List<RequestResponseTrackingInfo> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			RequestResponseTrackingInfoAccessor requestResponseTrackingInfoAccessor = mappingManager.createAccessor(RequestResponseTrackingInfoAccessor.class);
			Result<RequestResponseTrackingInfo> result = requestResponseTrackingInfoAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of RequestResponseTrackingInfoDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [RequestResponseTrackingInfoDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param reqTime UUID based on timestamp used for range computations
	* @exception Exception
	* @return RequestResponseTrackingInfo
	*/
	public RequestResponseTrackingInfo fetchRequestResponseTrackingInfoImpl(String meid, UUIDs reqTime){
		RequestResponseTrackingInfo list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			RequestResponseTrackingInfoAccessor requestResponseTrackingInfoAccessor = mappingManager.createAccessor(RequestResponseTrackingInfoAccessor.class);
			Result<RequestResponseTrackingInfo> result = requestResponseTrackingInfoAccessor.fetchRequestResponseTrackingInfo(meid, reqTime);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchRequestResponseTrackingInfo(meid, reqTime) of RequestResponseTrackingInfoDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean RequestResponseTrackingInfo
	* @param requestResponseTrackingInfo variable referencing the object corresponds to RequestResponseTrackingInfo
	* @exception Exception
	* @return boolean
	*/
	public boolean saveRequestResponseTrackingInfo(RequestResponseTrackingInfo requestResponseTrackingInfo){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<RequestResponseTrackingInfo> mapper = mappingManager.mapper(RequestResponseTrackingInfo.class);
			mapper.save(requestResponseTrackingInfo);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update RequestResponseTrackingInfo Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table request_response_tracking_info
	* @param json json representation for record that refers table request_response_tracking_info
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByRequestResponseTrackingInfoJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into request_response_tracking_info json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String RequestResponseTrackingInfo Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean RequestResponseTrackingInfo
	* @param list List of RequestResponseTrackingInfo objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkRequestResponseTrackingInfoInsert(List<RequestResponseTrackingInfo> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<RequestResponseTrackingInfo> mapper = mappingManager.mapper(RequestResponseTrackingInfo.class);
			BatchStatement batchStatement = new BatchStatement();
			for(RequestResponseTrackingInfo bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution RequestResponseTrackingInfo Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [request_response_tracking_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param reqTime UUID based on timestamp used for range computations
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonRequestResponseTrackingInfoImpl(String meid, UUIDs reqTime){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from request_response_tracking_info where meid='"+meid+"' and req_time='"+reqTime+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on request_response_tracking_info table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [request_response_tracking_info]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param reqTime UUID based on timestamp used for range computations
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteRequestResponseTrackingInfoImpl(String meid, UUIDs reqTime){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from request_response_tracking_info where meid='"+meid+"' and req_time='"+reqTime+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on request_response_tracking_info table", e);
		}
		return status;
	}

}