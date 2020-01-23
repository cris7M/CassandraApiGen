
package com.qolsys.cassandra.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.constants.Constants;

/**
 * @author suresh
 *
 */
public class KeySpace {

	private static Logger logger = LoggerFactory.getLogger(KeySpace.class);

	public void Keyspace(){
		logger.info("Creating a new keyspace in cassandra, please wait...");

		Session session = CassandraCluster.getSession(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
		String createKeySpace = null;
		
			createKeySpace = "CREATE KEYSPACE IF NOT EXISTS Binary WITH "
					+ "replication = {'class':'SimpleStrategy', 'replication_factor':3};";
		//ResultSet resultSet = null;
		try{
			ResultSet resultSet = session.execute(createKeySpace);
		}catch(Exception e){
			logger.info("KeySpace Created ::: ", e);
		}finally{
			session.close();
		}
	}
}

