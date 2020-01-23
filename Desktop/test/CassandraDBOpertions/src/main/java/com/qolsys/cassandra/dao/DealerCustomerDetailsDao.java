package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.DealerCustomerDetails;
import com.qolsys.cassandra.accessor.DealerCustomerDetailsAccessor;
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
 * DealerCustomerDetailsDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to dealer_customer_details 
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
public class DealerCustomerDetailsDao{

	private static Logger logger = LoggerFactory.getLogger(DealerCustomerDetailsDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealerCustomerDetailsDao]
	* @exception Exception
	* @return List<DealerCustomerDetails>
	*/
	public List<DealerCustomerDetails> getAll(){
		List<DealerCustomerDetails> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealerCustomerDetailsAccessor dealerCustomerDetailsAccessor = mappingManager.createAccessor(DealerCustomerDetailsAccessor.class);
			Result<DealerCustomerDetails> result = dealerCustomerDetailsAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of DealerCustomerDetailsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealerCustomerDetailsDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return DealerCustomerDetails
	*/
	public DealerCustomerDetails fetchDealerCustomerDetailsImpl(String meid){
		DealerCustomerDetails list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealerCustomerDetailsAccessor dealerCustomerDetailsAccessor = mappingManager.createAccessor(DealerCustomerDetailsAccessor.class);
			Result<DealerCustomerDetails> result = dealerCustomerDetailsAccessor.fetchDealerCustomerDetails(meid);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchDealerCustomerDetails(meid) of DealerCustomerDetailsDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean DealerCustomerDetails
	* @param dealerCustomerDetails variable referencing the object corresponds to DealerCustomerDetails
	* @exception Exception
	* @return boolean
	*/
	public boolean saveDealerCustomerDetails(DealerCustomerDetails dealerCustomerDetails){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<DealerCustomerDetails> mapper = mappingManager.mapper(DealerCustomerDetails.class);
			mapper.save(dealerCustomerDetails);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update DealerCustomerDetails Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table dealer_customer_details
	* @param json json representation for record that refers table dealer_customer_details
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByDealerCustomerDetailsJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into dealer_customer_details json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String DealerCustomerDetails Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean DealerCustomerDetails
	* @param list List of DealerCustomerDetails objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkDealerCustomerDetailsInsert(List<DealerCustomerDetails> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<DealerCustomerDetails> mapper = mappingManager.mapper(DealerCustomerDetails.class);
			BatchStatement batchStatement = new BatchStatement();
			for(DealerCustomerDetails bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution DealerCustomerDetails Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [dealer_customer_details]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonDealerCustomerDetailsImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from dealer_customer_details where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_customer_details table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [dealer_customer_details]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteDealerCustomerDetailsImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from dealer_customer_details where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealer_customer_details table", e);
		}
		return status;
	}

}