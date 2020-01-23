package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.DealerDetails;
import com.qolsys.cassandra.accessor.DealerDetailsAccessor;
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
 * DealerDetailsDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to dealer_details 
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
public class DealerDetailsDao{

	private static Logger logger = LoggerFactory.getLogger(DealerDetailsDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealerDetailsDao]
	* @exception Exception
	* @return List<DealerDetails>
	*/
	public List<DealerDetails> getAll(){
		List<DealerDetails> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealerDetailsAccessor dealerDetailsAccessor = mappingManager.createAccessor(DealerDetailsAccessor.class);
			Result<DealerDetails> result = dealerDetailsAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of DealerDetailsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealerDetailsDao]
	* @param dealerId Dealer unique id
	* @exception Exception
	* @return List<DealerDetails>
	*/
	public List<DealerDetails> fetchDealerDetailsImpl(UUIDs dealerId){
		List<DealerDetails> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealerDetailsAccessor dealerDetailsAccessor = mappingManager.createAccessor(DealerDetailsAccessor.class);
			Result<DealerDetails> result = dealerDetailsAccessor.fetchDealerDetails(dealerId);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchDealerDetails(dealerId) of DealerDetailsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealerDetailsDao]
	* @param dealerId Dealer unique id
	* @param partnerName Name of the partner where dealer is associated
	* @exception Exception
	* @return DealerDetails
	*/
	public DealerDetails fetchDealerDetailsImpl(UUIDs dealerId, String partnerName){
		DealerDetails list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealerDetailsAccessor dealerDetailsAccessor = mappingManager.createAccessor(DealerDetailsAccessor.class);
			Result<DealerDetails> result = dealerDetailsAccessor.fetchDealerDetails(dealerId, partnerName);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchDealerDetails(dealerId, partnerName) of DealerDetailsDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean DealerDetails
	* @param dealerDetails variable referencing the object corresponds to DealerDetails
	* @exception Exception
	* @return boolean
	*/
	public boolean saveDealerDetails(DealerDetails dealerDetails){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<DealerDetails> mapper = mappingManager.mapper(DealerDetails.class);
			mapper.save(dealerDetails);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update DealerDetails Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table dealer_details
	* @param json json representation for record that refers table dealer_details
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByDealerDetailsJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into dealer_details json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String DealerDetails Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean DealerDetails
	* @param list List of DealerDetails objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkDealerDetailsInsert(List<DealerDetails> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<DealerDetails> mapper = mappingManager.mapper(DealerDetails.class);
			BatchStatement batchStatement = new BatchStatement();
			for(DealerDetails bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution DealerDetails Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [dealer_details]
	* @param dealerId Dealer unique id
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonDealerDetailsImpl(UUIDs dealerId){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from dealer_details where dealer_id='"+dealerId+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_details table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [dealer_details]
	* @param dealerId Dealer unique id
	* @param partnerName Name of the partner where dealer is associated
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonDealerDetailsImpl(UUIDs dealerId, String partnerName){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from dealer_details where dealer_id='"+dealerId+"' and partner_name='"+partnerName+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_details table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [dealer_details]
	* @param dealerId Dealer unique id
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteDealerDetailsImpl(UUIDs dealerId){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from dealer_details where dealer_id='"+dealerId+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_details table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [dealer_details]
	* @param dealerId Dealer unique id
	* @param partnerName Name of the partner where dealer is associated
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteDealerDetailsImpl(UUIDs dealerId, String partnerName){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from dealer_details where dealer_id='"+dealerId+"' and partner_name='"+partnerName+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_details table", e);
		}
		return status;
	}

}