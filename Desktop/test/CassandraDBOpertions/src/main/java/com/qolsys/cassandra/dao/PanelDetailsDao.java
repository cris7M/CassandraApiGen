package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.PanelDetails;
import com.qolsys.cassandra.accessor.PanelDetailsAccessor;
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
 * PanelDetailsDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to panel_details 
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
public class PanelDetailsDao{

	private static Logger logger = LoggerFactory.getLogger(PanelDetailsDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelDetailsDao]
	* @exception Exception
	* @return List<PanelDetails>
	*/
	public List<PanelDetails> getAll(){
		List<PanelDetails> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelDetailsAccessor panelDetailsAccessor = mappingManager.createAccessor(PanelDetailsAccessor.class);
			Result<PanelDetails> result = panelDetailsAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of PanelDetailsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelDetailsDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<PanelDetails>
	*/
	public List<PanelDetails> fetchPanelDetailsImpl(String meid){
		List<PanelDetails> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelDetailsAccessor panelDetailsAccessor = mappingManager.createAccessor(PanelDetailsAccessor.class);
			Result<PanelDetails> result = panelDetailsAccessor.fetchPanelDetails(meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelDetails(meid) of PanelDetailsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelDetailsDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param partition Panel partition[This is intended to support future versions as of now all panels fall into partition-0]
	* @exception Exception
	* @return PanelDetails
	*/
	public PanelDetails fetchPanelDetailsImpl(String meid, String partition){
		PanelDetails list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelDetailsAccessor panelDetailsAccessor = mappingManager.createAccessor(PanelDetailsAccessor.class);
			Result<PanelDetails> result = panelDetailsAccessor.fetchPanelDetails(meid, partition);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelDetails(meid, partition) of PanelDetailsDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean PanelDetails
	* @param panelDetails variable referencing the object corresponds to PanelDetails
	* @exception Exception
	* @return boolean
	*/
	public boolean savePanelDetails(PanelDetails panelDetails){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<PanelDetails> mapper = mappingManager.mapper(PanelDetails.class);
			mapper.save(panelDetails);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update PanelDetails Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table panel_details
	* @param json json representation for record that refers table panel_details
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByPanelDetailsJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into panel_details json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String PanelDetails Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean PanelDetails
	* @param list List of PanelDetails objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkPanelDetailsInsert(List<PanelDetails> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<PanelDetails> mapper = mappingManager.mapper(PanelDetails.class);
			BatchStatement batchStatement = new BatchStatement();
			for(PanelDetails bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution PanelDetails Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_details]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelDetailsImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_details where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_details table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_details]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param partition Panel partition[This is intended to support future versions as of now all panels fall into partition-0]
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelDetailsImpl(String meid, String partition){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_details where meid='"+meid+"' and partition='"+partition+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_details table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_details]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelDetailsImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_details where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_details table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_details]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param partition Panel partition[This is intended to support future versions as of now all panels fall into partition-0]
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelDetailsImpl(String meid, String partition){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_details where meid='"+meid+"' and partition='"+partition+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_details table", e);
		}
		return status;
	}

}