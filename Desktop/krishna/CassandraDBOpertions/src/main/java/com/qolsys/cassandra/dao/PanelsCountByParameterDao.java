package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.PanelsCountByParameter;
import com.qolsys.cassandra.accessor.PanelsCountByParameterAccessor;
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
 * PanelsCountByParameterDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to panels_count_by_parameter 
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
public class PanelsCountByParameterDao{

	private static Logger logger = LoggerFactory.getLogger(PanelsCountByParameterDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsCountByParameterDao]
	* @exception Exception
	* @return List<PanelsCountByParameter>
	*/
	public List<PanelsCountByParameter> getAll(){
		List<PanelsCountByParameter> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsCountByParameterAccessor panelsCountByParameterAccessor = mappingManager.createAccessor(PanelsCountByParameterAccessor.class);
			Result<PanelsCountByParameter> result = panelsCountByParameterAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of PanelsCountByParameterDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsCountByParameterDao]
	* @param dealerName Name of the dealer customer associated with the panel
	* @exception Exception
	* @return List<PanelsCountByParameter>
	*/
	public List<PanelsCountByParameter> fetchPanelsCountByParameterImpl(String dealerName){
		List<PanelsCountByParameter> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsCountByParameterAccessor panelsCountByParameterAccessor = mappingManager.createAccessor(PanelsCountByParameterAccessor.class);
			Result<PanelsCountByParameter> result = panelsCountByParameterAccessor.fetchPanelsCountByParameter(dealerName);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelsCountByParameter(dealerName) of PanelsCountByParameterDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsCountByParameterDao]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @exception Exception
	* @return List<PanelsCountByParameter>
	*/
	public List<PanelsCountByParameter> fetchPanelsCountByParameterImpl(String dealerName, UUIDs id){
		List<PanelsCountByParameter> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsCountByParameterAccessor panelsCountByParameterAccessor = mappingManager.createAccessor(PanelsCountByParameterAccessor.class);
			Result<PanelsCountByParameter> result = panelsCountByParameterAccessor.fetchPanelsCountByParameter(dealerName, id);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelsCountByParameter(dealerName, id) of PanelsCountByParameterDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsCountByParameterDao]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @param paramType Type of device
	* @exception Exception
	* @return List<PanelsCountByParameter>
	*/
	public List<PanelsCountByParameter> fetchPanelsCountByParameterImpl(String dealerName, UUIDs id, String paramType){
		List<PanelsCountByParameter> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsCountByParameterAccessor panelsCountByParameterAccessor = mappingManager.createAccessor(PanelsCountByParameterAccessor.class);
			Result<PanelsCountByParameter> result = panelsCountByParameterAccessor.fetchPanelsCountByParameter(dealerName, id, paramType);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelsCountByParameter(dealerName, id, paramType) of PanelsCountByParameterDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsCountByParameterDao]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @param paramType Type of device
	* @param paramName Device name
	* @exception Exception
	* @return PanelsCountByParameter
	*/
	public PanelsCountByParameter fetchPanelsCountByParameterImpl(String dealerName, UUIDs id, String paramType, String paramName){
		PanelsCountByParameter list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsCountByParameterAccessor panelsCountByParameterAccessor = mappingManager.createAccessor(PanelsCountByParameterAccessor.class);
			Result<PanelsCountByParameter> result = panelsCountByParameterAccessor.fetchPanelsCountByParameter(dealerName, id, paramType, paramName);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelsCountByParameter(dealerName, id, paramType, paramName) of PanelsCountByParameterDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean PanelsCountByParameter
	* @param panelsCountByParameter variable referencing the object corresponds to PanelsCountByParameter
	* @exception Exception
	* @return boolean
	*/
	public boolean savePanelsCountByParameter(PanelsCountByParameter panelsCountByParameter){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<PanelsCountByParameter> mapper = mappingManager.mapper(PanelsCountByParameter.class);
			mapper.save(panelsCountByParameter);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update PanelsCountByParameter Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table panels_count_by_parameter
	* @param json json representation for record that refers table panels_count_by_parameter
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByPanelsCountByParameterJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into panels_count_by_parameter json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String PanelsCountByParameter Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean PanelsCountByParameter
	* @param list List of PanelsCountByParameter objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkPanelsCountByParameterInsert(List<PanelsCountByParameter> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<PanelsCountByParameter> mapper = mappingManager.mapper(PanelsCountByParameter.class);
			BatchStatement batchStatement = new BatchStatement();
			for(PanelsCountByParameter bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution PanelsCountByParameter Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panels_count_by_parameter]
	* @param dealerName Name of the dealer customer associated with the panel
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelsCountByParameterImpl(String dealerName){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panels_count_by_parameter where dealer_name='"+dealerName+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_count_by_parameter table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panels_count_by_parameter]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelsCountByParameterImpl(String dealerName, UUIDs id){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panels_count_by_parameter where dealer_name='"+dealerName+"' and id='"+id+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_count_by_parameter table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panels_count_by_parameter]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @param paramType Type of device
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelsCountByParameterImpl(String dealerName, UUIDs id, String paramType){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panels_count_by_parameter where dealer_name='"+dealerName+"' and id='"+id+"' and param_type='"+paramType+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_count_by_parameter table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panels_count_by_parameter]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @param paramType Type of device
	* @param paramName Device name
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelsCountByParameterImpl(String dealerName, UUIDs id, String paramType, String paramName){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panels_count_by_parameter where dealer_name='"+dealerName+"' and id='"+id+"' and param_type='"+paramType+"' and param_name='"+paramName+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_count_by_parameter table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panels_count_by_parameter]
	* @param dealerName Name of the dealer customer associated with the panel
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelsCountByParameterImpl(String dealerName){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panels_count_by_parameter where dealer_name='"+dealerName+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_count_by_parameter table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panels_count_by_parameter]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelsCountByParameterImpl(String dealerName, UUIDs id){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panels_count_by_parameter where dealer_name='"+dealerName+"' and id='"+id+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_count_by_parameter table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panels_count_by_parameter]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @param paramType Type of device
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelsCountByParameterImpl(String dealerName, UUIDs id, String paramType){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panels_count_by_parameter where dealer_name='"+dealerName+"' and id='"+id+"' and param_type='"+paramType+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_count_by_parameter table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panels_count_by_parameter]
	* @param dealerName Name of the dealer customer associated with the panel
	* @param id Random unique number that signifies unique device type
	* @param paramType Type of device
	* @param paramName Device name
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelsCountByParameterImpl(String dealerName, UUIDs id, String paramType, String paramName){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panels_count_by_parameter where dealer_name='"+dealerName+"' and id='"+id+"' and param_type='"+paramType+"' and param_name='"+paramName+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_count_by_parameter table", e);
		}
		return status;
	}

}