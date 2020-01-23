package com.qolsys.cassandra.Test;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.constants.Constants;

public class NewThread {

	private static Logger logger = LoggerFactory.getLogger(NewThread.class);
	public void Keyspace(){
		logger.info("Creating a new keyspace in cassandra, please wait...");
		Session session = CassandraCluster.getSession(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
		String createKeySpace = null;
		String createTable = null;

		createKeySpace = "CREATE KEYSPACE IF NOT EXISTS test WITH "
				+ "replication = {'class':'SimpleStrategy', 'replication_factor':3};";

		createTable = "CREATE TABLE IF NOT EXISTS test.testing(s_no int primary key, "
				+ "name text, age int, designation text, city text, phone_no text);";

		try{
			ResultSet resultSet2 = session.execute(createKeySpace);
			//System.out.println("keyspace created successfully:: "+ resultSet2);
			ResultSet resultSet = session.execute(createTable);
			//System.out.println("Table created successfully:: "+ resultSet);

		}catch(Exception e){
			logger.info("KeySpace Created ::: ", e);
			logger.info("Table Created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
	}

	static class MyRunnable implements Runnable {
		int i;
		public MyRunnable(int i) {
			this.i = i; 
		}
		@Override
		public void run() {
			Session session = CassandraCluster.getSession(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
			/*long l = new Date().getTime();
			Statement query = new SimpleStatement("select count(*) from test.testing;");
			//String fetchQuery = "select count(*) from test.testing;";
			query.setReadTimeoutMillis(600*1000);
			//query.setConsistencyLevel(ConsistencyLevel.ALL);
			ResultSet resultSet = session.execute(query);
			System.out.println("Count :: "+ resultSet.one());
			long l2 = new Date().getTime();
			System.out.println("Time taken :: "+(l2-l));*/
			String insertdata = null;
			for(int s_no=0; s_no<10; s_no++){
				insertdata = "insert into test.testing(s_no, name, age, designation, city, phone_no) "
						+ "values("+(s_no+i)+", 'suresh', 24, 'Devloper', 'Hyd', '9494920987');";

				try{
					if(session == null)
						session = CassandraCluster.getSession(Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
					ResultSet resultSet3 = session.execute(insertdata);
					/*for(int j=0; j<1; j++){
						//System.out.println("Thread"+i+":: "+ j);
						//Thread.sleep(50);
					}*/
					//System.out.println("Thread are Running");
				}catch (Exception e) {
					logger.info("inserted data successfully::: ", e);
					e.printStackTrace();
					System.exit(0);
				}
			}
			
		}
	}

	public static void main(String[] args)throws Exception {
		//NewThread key = new NewThread();
		//key.Keyspace();
		//Thread.sleep(120000);
		ExecutorService executor = Executors.newFixedThreadPool(100000);
		for(int i=0;i<100000;i++)
			executor.execute(new MyRunnable(i*10));
		executor.shutdown();
		System.out.println("testing apppp");

	}
}
