import java.io.IOException;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class ConnectionPool {

	public static void main(String[] args) throws IOException {

		String query = "CREATE KEYSPACE IF NOT EXISTS Conn_Pool WITH "
				+ "replication = {'class':'SimpleStrategy', 'replication_factor':3};";

		String query1 = "CREATE TABLE IF NOT EXISTS Conn_Pool.test(emp_id int PRIMARY KEY, "
				+ "emp_name text, emp_city text, emp_sal varint, emp_phone varint) ;"; 

		String query2 = "INSERT INTO Conn_Pool.test(emp_id, emp_name, emp_city, emp_sal, emp_phone)"
				+ " VALUES(1,'ram', 'Hyderabad', 50000, 9494920987);" ;


		PoolingOptions poolingOptions = new PoolingOptions();
		Cluster cluster = Cluster.builder().addContactPoint("master1").withPoolingOptions(poolingOptions).build();
		Session session = cluster.connect();
	    poolingOptions.setConnectionsPerHost(HostDistance.LOCAL, 2, 200);
	    poolingOptions.setConnectionsPerHost(HostDistance.REMOTE, 2, 200);
	    poolingOptions.getMaxConnectionsPerHost(HostDistance.LOCAL);
	    poolingOptions.getMaxConnectionsPerHost(HostDistance.REMOTE);
	    
	    Set<Host> set  = cluster.getMetadata().getAllHosts();
	    for(Host host :set){
	    	System.out.println("local Host Address ::" + host.getAddress().getHostAddress());
	    	System.out.println("Host connection Address :: " + session.getState().getSession());
	    }
	    System.out.println("Hosts size:: " + set.size());
	    System.out.println("local connections :: " + poolingOptions.getMaxConnectionsPerHost(HostDistance.LOCAL));
	    System.out.println("Remote connections :: " + poolingOptions.getMaxConnectionsPerHost(HostDistance.REMOTE));

		session.execute(query);
		session.execute(query1);
		session.execute(query2);

		String fetchQuery = "select * from Conn_Pool.test;";
		ResultSet resultSet = session.execute(fetchQuery);
		Row row = resultSet.one();
		System.out.println(row);
		session.close();

	}
}


