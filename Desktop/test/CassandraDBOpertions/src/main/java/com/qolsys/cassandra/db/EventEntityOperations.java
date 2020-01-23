package com.qolsys.cassandra.db;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * @author suresh
 *
 */
public class EventEntityOperations {

	public static List<Row> row;
	private static Logger logger = LoggerFactory.getLogger(EventEntityOperations.class);

	public static boolean ingestJson(Session session, String tableName, String json) {
		logger.debug("Inside ingestJson() @EventEntityOperations ");
		boolean status = false;
		try{
			String query = "INSERT INTO "+tableName+" json '"+json+"'" ;
			ResultSet resultSet = session.execute(query);
			status = resultSet.wasApplied();   
		}catch(Exception e){
			logger.error("Json ingestion to Cassandra failed", e);
		}
		logger.debug("Exiting ingestJson() @EventEntityOperations ");
		return status;
	}

	public static boolean fetchAllEvents(Session session, String tableName){
		logger.debug("Inside fetchAllEvents() @EventEntityOperations ");
		boolean status2 = false;
		try{
			String fetchQuery = "select * from "+tableName+"";
			ResultSet resultSet = session.execute(fetchQuery);
			List<Row> row = resultSet.all();
			System.out.println(row);
			//status2 = resultSet.wasApplied();
			
		}catch(Exception e){
			logger.error("Error fetching events from Cassandra :: ", e);
		}
		logger.debug("Exiting fetchAllEvents() @EventEntityOperations ");
		return status2;

	}

}
