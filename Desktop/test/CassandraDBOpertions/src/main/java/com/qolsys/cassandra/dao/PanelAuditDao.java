package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.PanelAudit;
import com.qolsys.cassandra.accessor.PanelAuditAccessor;
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
 * PanelAuditDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to panel_audit 
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
public class PanelAuditDao{

	private static Logger logger = LoggerFactory.getLogger(PanelAuditDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelAuditDao]
	* @exception Exception
	* @return List<PanelAudit>
	*/
	public List<PanelAudit> getAll(){
		List<PanelAudit> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelAuditAccessor panelAuditAccessor = mappingManager.createAccessor(PanelAuditAccessor.class);
			Result<PanelAudit> result = panelAuditAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of PanelAuditDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelAuditDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return List<PanelAudit>
	*/
	public List<PanelAudit> fetchPanelAuditImpl(String meid){
		List<PanelAudit> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelAuditAccessor panelAuditAccessor = mappingManager.createAccessor(PanelAuditAccessor.class);
			Result<PanelAudit> result = panelAuditAccessor.fetchPanelAudit(meid);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelAudit(meid) of PanelAuditDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelAuditDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param sessionId Session id along with unique time which is an UUID[Universal Unique id] based out of time
	* @exception Exception
	* @return PanelAudit
	*/
	public PanelAudit fetchPanelAuditImpl(String meid, UUIDs sessionId){
		PanelAudit list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelAuditAccessor panelAuditAccessor = mappingManager.createAccessor(PanelAuditAccessor.class);
			Result<PanelAudit> result = panelAuditAccessor.fetchPanelAudit(meid, sessionId);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelAudit(meid, sessionId) of PanelAuditDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean PanelAudit
	* @param panelAudit variable referencing the object corresponds to PanelAudit
	* @exception Exception
	* @return boolean
	*/
	public boolean savePanelAudit(PanelAudit panelAudit){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<PanelAudit> mapper = mappingManager.mapper(PanelAudit.class);
			mapper.save(panelAudit);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update PanelAudit Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table panel_audit
	* @param json json representation for record that refers table panel_audit
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByPanelAuditJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into panel_audit json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String PanelAudit Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean PanelAudit
	* @param list List of PanelAudit objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkPanelAuditInsert(List<PanelAudit> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<PanelAudit> mapper = mappingManager.mapper(PanelAudit.class);
			BatchStatement batchStatement = new BatchStatement();
			for(PanelAudit bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution PanelAudit Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_audit]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelAuditImpl(String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_audit where meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_audit table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_audit]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param sessionId Session id along with unique time which is an UUID[Universal Unique id] based out of time
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelAuditImpl(String meid, UUIDs sessionId){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_audit where meid='"+meid+"' and session_id='"+sessionId+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_audit table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_audit]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelAuditImpl(String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_audit where meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_audit table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_audit]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param sessionId Session id along with unique time which is an UUID[Universal Unique id] based out of time
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelAuditImpl(String meid, UUIDs sessionId){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_audit where meid='"+meid+"' and session_id='"+sessionId+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_audit table", e);
		}
		return status;
	}

}