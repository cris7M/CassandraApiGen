package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.PanelsByPartnerDealer;
import com.qolsys.cassandra.accessor.PanelsByPartnerDealerAccessor;
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
 * PanelsByPartnerDealerDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to panels_by_partner_dealer 
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
public class PanelsByPartnerDealerDao{

	private static Logger logger = LoggerFactory.getLogger(PanelsByPartnerDealerDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsByPartnerDealerDao]
	* @exception Exception
	* @return List<PanelsByPartnerDealer>
	*/
	public List<PanelsByPartnerDealer> getAll(){
		List<PanelsByPartnerDealer> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsByPartnerDealerAccessor panelsByPartnerDealerAccessor = mappingManager.createAccessor(PanelsByPartnerDealerAccessor.class);
			Result<PanelsByPartnerDealer> result = panelsByPartnerDealerAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of PanelsByPartnerDealerDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsByPartnerDealerDao]
	* @param partnerName Name of the partner where dealer is associated
	* @exception Exception
	* @return List<PanelsByPartnerDealer>
	*/
	public List<PanelsByPartnerDealer> fetchPanelsByPartnerDealerImpl(String partnerName){
		List<PanelsByPartnerDealer> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsByPartnerDealerAccessor panelsByPartnerDealerAccessor = mappingManager.createAccessor(PanelsByPartnerDealerAccessor.class);
			Result<PanelsByPartnerDealer> result = panelsByPartnerDealerAccessor.fetchPanelsByPartnerDealer(partnerName);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelsByPartnerDealer(partnerName) of PanelsByPartnerDealerDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsByPartnerDealerDao]
	* @param partnerName Name of the partner where dealer is associated
	* @param dealerName Name of the dealer customer associated with the panel
	* @exception Exception
	* @return List<PanelsByPartnerDealer>
	*/
	public List<PanelsByPartnerDealer> fetchPanelsByPartnerDealerImpl(String partnerName, String dealerName){
		List<PanelsByPartnerDealer> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsByPartnerDealerAccessor panelsByPartnerDealerAccessor = mappingManager.createAccessor(PanelsByPartnerDealerAccessor.class);
			Result<PanelsByPartnerDealer> result = panelsByPartnerDealerAccessor.fetchPanelsByPartnerDealer(partnerName, dealerName);
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelsByPartnerDealer(partnerName, dealerName) of PanelsByPartnerDealerDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelsByPartnerDealerDao]
	* @param partnerName Name of the partner where dealer is associated
	* @param dealerName Name of the dealer customer associated with the panel
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return PanelsByPartnerDealer
	*/
	public PanelsByPartnerDealer fetchPanelsByPartnerDealerImpl(String partnerName, String dealerName, String meid){
		PanelsByPartnerDealer list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelsByPartnerDealerAccessor panelsByPartnerDealerAccessor = mappingManager.createAccessor(PanelsByPartnerDealerAccessor.class);
			Result<PanelsByPartnerDealer> result = panelsByPartnerDealerAccessor.fetchPanelsByPartnerDealer(partnerName, dealerName, meid);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelsByPartnerDealer(partnerName, dealerName, meid) of PanelsByPartnerDealerDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean PanelsByPartnerDealer
	* @param panelsByPartnerDealer variable referencing the object corresponds to PanelsByPartnerDealer
	* @exception Exception
	* @return boolean
	*/
	public boolean savePanelsByPartnerDealer(PanelsByPartnerDealer panelsByPartnerDealer){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<PanelsByPartnerDealer> mapper = mappingManager.mapper(PanelsByPartnerDealer.class);
			mapper.save(panelsByPartnerDealer);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update PanelsByPartnerDealer Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table panels_by_partner_dealer
	* @param json json representation for record that refers table panels_by_partner_dealer
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByPanelsByPartnerDealerJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into panels_by_partner_dealer json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String PanelsByPartnerDealer Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean PanelsByPartnerDealer
	* @param list List of PanelsByPartnerDealer objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkPanelsByPartnerDealerInsert(List<PanelsByPartnerDealer> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<PanelsByPartnerDealer> mapper = mappingManager.mapper(PanelsByPartnerDealer.class);
			BatchStatement batchStatement = new BatchStatement();
			for(PanelsByPartnerDealer bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution PanelsByPartnerDealer Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panels_by_partner_dealer]
	* @param partnerName Name of the partner where dealer is associated
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelsByPartnerDealerImpl(String partnerName){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panels_by_partner_dealer where partner_name='"+partnerName+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_by_partner_dealer table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panels_by_partner_dealer]
	* @param partnerName Name of the partner where dealer is associated
	* @param dealerName Name of the dealer customer associated with the panel
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelsByPartnerDealerImpl(String partnerName, String dealerName){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panels_by_partner_dealer where partner_name='"+partnerName+"' and dealer_name='"+dealerName+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_by_partner_dealer table", e);
		}
		return str;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panels_by_partner_dealer]
	* @param partnerName Name of the partner where dealer is associated
	* @param dealerName Name of the dealer customer associated with the panel
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelsByPartnerDealerImpl(String partnerName, String dealerName, String meid){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panels_by_partner_dealer where partner_name='"+partnerName+"' and dealer_name='"+dealerName+"' and meid='"+meid+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_by_partner_dealer table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panels_by_partner_dealer]
	* @param partnerName Name of the partner where dealer is associated
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelsByPartnerDealerImpl(String partnerName){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panels_by_partner_dealer where partner_name='"+partnerName+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_by_partner_dealer table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panels_by_partner_dealer]
	* @param partnerName Name of the partner where dealer is associated
	* @param dealerName Name of the dealer customer associated with the panel
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelsByPartnerDealerImpl(String partnerName, String dealerName){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panels_by_partner_dealer where partner_name='"+partnerName+"' and dealer_name='"+dealerName+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_by_partner_dealer table", e);
		}
		return status;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panels_by_partner_dealer]
	* @param partnerName Name of the partner where dealer is associated
	* @param dealerName Name of the dealer customer associated with the panel
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelsByPartnerDealerImpl(String partnerName, String dealerName, String meid){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panels_by_partner_dealer where partner_name='"+partnerName+"' and dealer_name='"+dealerName+"' and meid='"+meid+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panels_by_partner_dealer table", e);
		}
		return status;
	}

}