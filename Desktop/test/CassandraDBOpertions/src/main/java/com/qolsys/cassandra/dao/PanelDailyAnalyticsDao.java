package com.qolsys.cassandra.dao;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.beans.PanelDailyAnalytics;
import com.qolsys.cassandra.accessor.PanelDailyAnalyticsAccessor;
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
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.BatchStatement;

/**
 * PanelDailyAnalyticsDao performs all CRUD(Create, Retrieve, Update, Delete) operations that corresponds to panel_daily_analytics 
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
public class PanelDailyAnalyticsDao{

	private static Logger logger = LoggerFactory.getLogger(PanelDailyAnalyticsDao.class);

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelDailyAnalyticsDao]
	* @exception Exception
	* @return List<PanelDailyAnalytics>
	*/
	public List<PanelDailyAnalytics> getAll(){
		List<PanelDailyAnalytics> list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelDailyAnalyticsAccessor panelDailyAnalyticsAccessor = mappingManager.createAccessor(PanelDailyAnalyticsAccessor.class);
			Result<PanelDailyAnalytics> result = panelDailyAnalyticsAccessor.getAll();
			if(result != null)
				list = result.all();
		}catch(Exception e){
			logger.error("Exception Occured in getAll() of PanelDailyAnalyticsDao Class", e);
		}
		return list;
	}

	/**
	* Fetch Method for all the records based on search criteria for the Class [PanelDailyAnalyticsDao]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param panelDate Specific date of the panel to get summary of activity on critical events base
	* @exception Exception
	* @return PanelDailyAnalytics
	*/
	public PanelDailyAnalytics fetchPanelDailyAnalyticsImpl(String meid, LocalDate panelDate){
		PanelDailyAnalytics list = null;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			PanelDailyAnalyticsAccessor panelDailyAnalyticsAccessor = mappingManager.createAccessor(PanelDailyAnalyticsAccessor.class);
			Result<PanelDailyAnalytics> result = panelDailyAnalyticsAccessor.fetchPanelDailyAnalytics(meid, panelDate);
			if(result != null)
				list = result.one();
		}catch(Exception e){
			logger.error("Exception Occured in fetchPanelDailyAnalytics(meid, panelDate) of PanelDailyAnalyticsDao Class", e);
		}
		return list;
	}

	/**
	* Save or Update Method for one record via object mapper for bean PanelDailyAnalytics
	* @param panelDailyAnalytics variable referencing the object corresponds to PanelDailyAnalytics
	* @exception Exception
	* @return boolean
	*/
	public boolean savePanelDailyAnalytics(PanelDailyAnalytics panelDailyAnalytics){
		boolean status = false;
		try{
			MappingManager mappingManager= new MappingManager(CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS));
			Mapper<PanelDailyAnalytics> mapper = mappingManager.mapper(PanelDailyAnalytics.class);
			mapper.save(panelDailyAnalytics);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert or update PanelDailyAnalytics Object", e);
		}
		return status;
	}

	/**
	* Insert record into database which takes Json as input param for table panel_daily_analytics
	* @param json json representation for record that refers table panel_daily_analytics
	* @exception Exception
	* @return boolean
	*/
	public boolean insertByPanelDailyAnalyticsJson(String json){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String insertJsonQuery = "insert into panel_daily_analytics json '"+json+"'";
			session.execute(insertJsonQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing insert by json String PanelDailyAnalytics Object", e);
		}
		return status;
	}

	/**
	* Bulk inserts or updates for the bean PanelDailyAnalytics
	* @param list List of PanelDailyAnalytics objects to be inserted or updated
	* @exception Exception
	* @return boolean
	*/
	public boolean bulkPanelDailyAnalyticsInsert(List<PanelDailyAnalytics> list){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			MappingManager mappingManager = new MappingManager(session);
			Mapper<PanelDailyAnalytics> mapper = mappingManager.mapper(PanelDailyAnalytics.class);
			BatchStatement batchStatement = new BatchStatement();
			for(PanelDailyAnalytics bean:list){
				Statement statement = mapper.saveQuery(bean);
				batchStatement.add(statement);
			}
			session.execute(batchStatement);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing Batch-Statement Execution PanelDailyAnalytics Object", e);
		}
		return status;
	}

	/**
	* Fetch Method for all the Json records in string array based on search criteria for the Class [panel_daily_analytics]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param panelDate Specific date of the panel to get summary of activity on critical events base
	* @exception Exception
	* @return String[]
	*/
	public String[] fetchJsonPanelDailyAnalyticsImpl(String meid, LocalDate panelDate){
		String str[] = null;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String fetchJsonQuery = "select json * from panel_daily_analytics where meid='"+meid+"' and panel_date='"+panelDate+"'";
			ResultSet resultSet = session.execute(fetchJsonQuery);
			List<Row> list =resultSet.all();
			int len = list.size();
			str = new String[len];
			for(int i=0; i<len; i++){
				str[i] = list.get(i).getString(0);
			}
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_daily_analytics table", e);
		}
		return str;
	}

	/**
	* Delete method for all the records based on search criteria for the Class [panel_daily_analytics]
	* @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	* @param panelDate Specific date of the panel to get summary of activity on critical events base
	* @exception Exception
	* @return boolean
	*/
	public boolean deletePanelDailyAnalyticsImpl(String meid, LocalDate panelDate){
		boolean status = false;
		try{
			Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			String deleteQuery = "delete from panel_daily_analytics where meid='"+meid+"' and panel_date='"+panelDate+"'";
			session.execute(deleteQuery);
			status = true;
		}catch(Exception e){
			logger.error("Exception Occured while performing delete() on panel_daily_analytics table", e);
		}
		return status;
	}

}