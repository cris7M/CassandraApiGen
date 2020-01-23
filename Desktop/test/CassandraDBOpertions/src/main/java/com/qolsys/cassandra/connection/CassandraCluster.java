
/**
 * This Package will address all connection and session objects.
 */
package com.qolsys.cassandra.connection;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;

/**
 * This class provides a singleton pattern based object for Cassandra's Cluster and Session.
 * Datastax community strongly recommends to use strictly one and only one Cluster and session per keyspace.<br>
 * Note that one can view Cassandra connection is exactly synonymous to TCP connection.
 * @see <a href="http://www.datastax.com/dev/blog/4-simple-rules-when-using-the-datastax-drivers-for-cassandra">CassandraSessionRecommendations</a>
 * 
 * @version 1.0
 * @author Qolsys
 * @since 21-Mar-2017
 *
 */
public class CassandraCluster {

	private static Cluster clusterInstance;
	private static Session sessionInstance;
	private static Session genericSession;

	private CassandraCluster(){}

	private static Cluster getCluster(int port, String... hosts){
		PoolingOptions poolingOptions = new PoolingOptions();
		poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL, 25000);
		poolingOptions.setMaxConnectionsPerHost(HostDistance.REMOTE, 25000);
		
		if(clusterInstance == null){
			clusterInstance = Cluster.builder().withPoolingOptions(poolingOptions).addContactPoints(hosts).withPort(port).build();
		}
		return clusterInstance;
	}
	
	public static synchronized Cluster getClusterInstance(int port, String... hosts){
		PoolingOptions poolingOptions = new PoolingOptions();
		poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL, 25000);
		poolingOptions.setMaxConnectionsPerHost(HostDistance.REMOTE, 25000);
		if(clusterInstance == null){
			clusterInstance = Cluster.builder().withPoolingOptions(poolingOptions).addContactPoints(hosts).withPort(port).build();
		}
		return clusterInstance;
	}

	public static synchronized Session getKeyspaceSession(String keyspace, int port, String... hosts){
		if(sessionInstance == null){
			sessionInstance = getCluster(port, hosts).connect(keyspace);
		}
		return sessionInstance;
	}

	public static synchronized Session getSession(int port, String... hosts){
		if(genericSession == null){
			genericSession = getCluster(port, hosts).connect();
		}
		return genericSession;
	}

	public static synchronized void closeCluster(){
		if(clusterInstance != null)
			clusterInstance.close();
	}

	public static synchronized void closeSession(){
		if(sessionInstance != null)
			sessionInstance.close();
	}
}
