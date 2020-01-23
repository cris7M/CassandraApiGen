package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.DealersByArea;
import com.qolsys.cassandra.accessor.DealersByAreaAccessor;
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
 * DealersByAreaDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to dealers_by_area 
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
public class DealersByAreaDao{

	private static Logger logger = LoggerFactory.getLogger(DealersByAreaDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealersByAreaDao]
	* @exception Exception
	* @return List<DealersByArea>
	*/
	public List<DealersByArea> getAll(){
		List<DealersByArea> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealersByAreaAccessor dealersByAreaAccessor = mappingManager.createAccessor(DealersByAreaAccessor.class);
			Result<DealersByArea> result = dealersByAreaAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of DealersByAreaDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealersByAreaDao]
	* @param areaCode Area code
	* @exception Exception
	* @return List<DealersByArea>
	*/
	public List<DealersByArea> fetchDealersByAreaImpl(String areaCode){
		List<DealersByArea> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealersByAreaAccessor dealersByAreaAccessor = mappingManager.createAccessor(DealersByAreaAccessor.class);
			Result<DealersByArea> result = dealersByAreaAccessor.fetchDealersByArea(areaCode);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchDealersByArea(areaCode) of DealersByAreaDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [DealersByAreaDao]
	* @param areaCode Area code
	* @param dealerId Dealer unique id
	* @exception Exception
	* @return DealersByArea
	*/
	public DealersByArea fetchDealersByAreaImpl(String areaCode, UUIDs dealerId){
		DealersByArea list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			DealersByAreaAccessor dealersByAreaAccessor = mappingManager.createAccessor(DealersByAreaAccessor.class);
			Result<DealersByArea> result = dealersByAreaAccessor.fetchDealersByArea(areaCode, dealerId);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchDealersByArea(areaCode, dealerId) of DealersByAreaDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean DealersByArea
	* @param dealersByArea variable referencing the object corresponds to DealersByArea
	* @exception Exception
	* @return boolean
	*/
	public boolean saveDealersByArea(DealersByArea dealersByArea){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<DealersByArea> mapper = mappingManager.mapper(DealersByArea.class);
			mapper.save(dealersByArea);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update DealersByArea Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table dealers_by_area
	* @param json json representation for record that refers table dealers_by_area
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByDealersByAreaJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into dealers_by_area json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String DealersByArea Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean DealersByArea
	* @param list List of DealersByArea objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkDealersByAreaInsert(List<DealersByArea> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<DealersByArea> mapper = mappingManager.mapper(DealersByArea.class);
			BatchStatement batchStatement = new BatchStatement();
			for(DealersByArea bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution DealersByArea Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [dealers_by_area]
	* @param areaCode Area code
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonDealersByAreaImpl(String areaCode){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from dealers_by_area where area_code='"+areaCode+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealers_by_area table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [dealers_by_area]
	* @param areaCode Area code
	* @param dealerId Dealer unique id
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonDealersByAreaImpl(String areaCode, UUIDs dealerId){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from dealers_by_area where area_code='"+areaCode+"' and dealer_id='"+dealerId+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealers_by_area table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [dealers_by_area]
	* @param areaCode Area code
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteDealersByAreaImpl(String areaCode){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from dealers_by_area where area_code='"+areaCode+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealers_by_area table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [dealers_by_area]
	* @param areaCode Area code
	* @param dealerId Dealer unique id
	* @exception Exception
	* @return boolean
	*/
	public boolean deleteDealersByAreaImpl(String areaCode, UUIDs dealerId){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from dealers_by_area where area_code='"+areaCode+"' and dealer_id='"+dealerId+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on dealers_by_area table", e);
		}
		return status;
	}

}