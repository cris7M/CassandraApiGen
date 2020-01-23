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

public class CreateTable {

	private static final String CASSANDRA_DEALER_DETAILS = null;
	private static Logger logger = LoggerFactory.getLogger(CreateTable.class);
	Session session = CassandraCluster.getKeyspaceSession(Constants.CASSANDRA_KEYSPACE, Constants.CASSANDRA_PORT, Constants.CASSANDRA_HOSTS);
	
	public void Customer_details() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a Customer_details Table in cassandra cluster, please wait...");
		String createTable1 = null;
	
		createTable1 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_CUSTOMER_DETAILS_TABLE+"(customer_id uuid primary key, "
				+ "dealer_name text, meid text, password text, primary_contact text, secondary_contact text, primary_email text, secondary_email text);"; 
		try{
			ResultSet resultSet1 = session.execute(createTable1);
			
		}catch(Exception e){
			logger.info("Customer_details table created::: ", e);
			e.printStackTrace();
		}finally{
			////session.close();
		}
		System.out.println("Customer_details table created");
	}

	public void Panel_settings() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a Panel_settings Table in cassandra cluster, please wait...");
		String createTable2 = null;
	
		createTable2 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANEL_SETTINGS_TABLE+"(meid text, "
				+ "value_type text, settings_type text, settings map<text, text>, primary key(meid, settings_type));"; 
		try{
			ResultSet resultSet2 = session.execute(createTable2);
		}catch(Exception e){
			logger.info("Panel_settings table created::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("Panel_settings table created");
	}

	public void panel_details() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a panel_details Table in cassandra cluster, please wait...");
		String createTable3 = null;
		String queryType = null;
		String queryType1 = null;
		queryType = "CREATE TYPE IF NOT EXISTS "+Constants.CASSANDRA_CARRIER_INFO+"(baseband_version text, iccid text, imsi text, imei text);";
		
		queryType1 = "CREATE TYPE IF NOT EXISTS "+Constants.CASSANDRA_DAUGHTER_CARD_INFO+"(name text, protocol_type text, version text, "
				+ "firmware_version text, additional_properties map<text,text>);";
		
		createTable3 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANEL_DETAILS_TABLE+"(meid text, "
				+ "macaddress text, partition text, software_version text, build_number text, linux_version text, android_os_version text, "
				+ "hardware_version text, part_number text, pca_serial_number text, system_serial_number text, arming_mode text, manufacturer text, "
				+ "panel_properties map<text, text>, city text, State text, area_code text, zip_code text, country text, panel_status text, carrier_name text, "
				+ "cellular_connection text, cellular_signal_strength text, carrier_details frozen<carrier_info>, daughter_cards list<frozen<daughter_card_info>>, "
				+ "installation_date timestamp, updated_time timestamp, primary key(meid, partition));"; 
		try{
			ResultSet Type = session.execute(queryType);
			ResultSet Type1 = session.execute(queryType1);
			ResultSet resultSet3 = session.execute(createTable3);
			System.out.println("::" +createTable3);
		}catch(Exception e){
			logger.info("panel_details table created::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("panel_details table created");
	}

	public void panel_inventory() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a panel_inventory Table in cassandra cluster, please wait...");
		String createTable4 = null;
		createTable4 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANEL_INVENTORY+"(meid text, "
				+ "panel_device_type text, panel_devices map<text, int>, primary key(meid, panel_device_type));"; 
		try{
			ResultSet resultSet4 = session.execute(createTable4);
		}catch(Exception e){
			logger.info("panel_inventory table created::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("panel_inventory table created");
	}

	public void panels_by_version() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a panels_by_version Table in cassandra cluster, please wait...");
		String createTable5 = null;
		createTable5 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANELS_BY_VERSION+"(version text, "
				+ "version_type text, panel text, primary key ((version, version_type), panel));"; 
		try{
			ResultSet resultSet5 = session.execute(createTable5);
		}catch(Exception e){
			logger.info("panels_by_version table created::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("panels_by_version table created");
	}

	public void panels_by_area() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a panels_by_area Table in cassandra cluster, please wait...");
		String createTable6 = null;
		createTable6 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANELS_BY_AREA+"(area_code text, panel text, primary key (area_code, panel));"; 
		try{
			ResultSet resultSet6 = session.execute(createTable6);
		}catch(Exception e){
			logger.info("panels_by_area table created::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("panels_by_area table created");
	}

	public void panels_by_manufacturer() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a panels_by_manufacturer Table in cassandra cluster, please wait...");
		String createTable7 = null;
		createTable7 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANELS_BY_MANUFACTURER+"(manufacturer text, "
				+ "panel text, primary key (manufacturer, panel));"; 
		try{
			ResultSet resultSet7 = session.execute(createTable7);
		}catch(Exception e){
			logger.info("panels_by_manufacturer table created::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("panels_by_manufacturer table created");
	}

	public void sensor_table() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a sensor_table Table in cassandra cluster, please wait...");
		String createTable8 = null;
		
		createTable8 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_SENSOR_TABLE+"(meid text, end_point_id int, "
				+ "protocol_type text, zone_id int, last_event map<text, text>, vendor_name text, device_properties map<text, text>, "
				+ "sequence_number text, sensor_type text, battery_status text, chime_type text, sensor_name text, "
				+ "sensor_state text, sensor_status text, sensor_tts text, updated_time timestamp, "
				+ "installation_date timestamp, primary key (meid, protocol_type, zone_id, end_point_id));"; 
		try{
			ResultSet resultSet8 = session.execute(createTable8);
		}catch(Exception e){
			logger.info("sensor_table table created::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("sensor_table table created");
	}

	public void panel_session() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a panel_session Table in cassandra cluster, please wait...");
		String createTable9 = null;
		createTable9 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANEL_SESSION+"(meid text, "
				+ "session_id TimeUUID, up_time text, panel_startup_properties map<text, text>, session_status text, session_termination_reason text, "
				+ "start_time timestamp, end_time timestamp, panel_session_stop_properties map<text, text>, PRIMARY KEY (meid, session_id))"; 
		try{
			ResultSet resultSet9 = session.execute(createTable9);
		}catch(Exception e){
			logger.info("panel_session table created::: ", e);
			e.printStackTrace();
		}finally{
			////session.close();
		}
		System.out.println("panel_session table created");
	}

	public void panel_daily_analytics() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a panel_daily_analytics Table in cassandra cluster, please wait...");
		String createTable10 = null;
		
		createTable10 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANEL_DAILY_ANALYTICS+"(meid text, "
				+ "panel_date date, alarm_counter map<text, int>, device_wakeup_time text, exception_counter map<text, int>, "
				+ "events_counter map<text, int>, PRIMARY KEY(meid, panel_date))"; 
		try{
			ResultSet resultSet10 = session.execute(createTable10);
		}catch(Exception e){
			logger.info("panel_daily_analytics table created::: ", e);
			e.printStackTrace();
		}finally{
			////session.close();
		}
		System.out.println("panel_daily_analytics table created");
	}

	public void event_history_all() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a event_history_all Table in cassandra cluster, please wait...");
		String createTable11 = null;
		createTable11 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_EVENT_HISTORY_ALL+"(meid text, "
				+ "zone_node_id int, event map<text,text>, event_time TimeUUID, protocol_type int, device_type text, "
				+ "event_name text, primary key(meid, event_time))"; 
		try{
			ResultSet resultSet11 = session.execute(createTable11);
		}catch(Exception e){
			logger.info("event_history_all table created ::: ", e);
			e.printStackTrace();
		}finally{
			////session.close();
		}
		System.out.println("event_history_all table created");
	}

	public void event_history_by_device() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a event_history_by_device Table in cassandra cluster, please wait...");
		String createTable12 = null;
		createTable12 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_EVENT_HISTORY_BY_DEVICE+"(meid text, "
				+ "zone_id int, protocol_type text, device_type text, event_name text, event map<text, text>, "
				+ "event_time TimeUUID, primary key (meid, protocol_type, device_type, zone_id, event_time));"; 
		try{
			ResultSet resultSet12 = session.execute(createTable12);
		}catch(Exception e){
			logger.info("event_history_by_device table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("event_history_by_device table created");
	}

	public void event_history_by_event_name() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a event_history_by_event_name Table in cassandra cluster, please wait...");
		String createTable13 = null;

		createTable13 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_EVENT_HISTORY_BY_EVENT_NAME+"(event_name text, "
				+ "meid text, event_time TimeUUID, event map<text, text>, primary key(event_name, event_time));"; 
		try{
			ResultSet resultSet13 = session.execute(createTable13);
		}catch(Exception e){
			logger.info("event_history_by_event_name table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("event_history_by_event_name table created");
	}

	public void event_history_by_device_type() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a event_history_by_device_type Table in cassandra cluster, please wait...");
		String createTable14 = null;

		createTable14 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_EVENT_HISTORY_BY_DEVICE_TYPE+"(device_type text, "
				+ "meid text, event_time TimeUUID, event map<text,text>, primary key(device_type, meid, event_time));"; 
		try{
			ResultSet resultSet14 = session.execute(createTable14);
		}catch(Exception e){
			logger.info("event_history_by_device_type table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("event_history_by_device_type table created");
	}
	
	public void dealer_details() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a dealer_details Table in cassandra cluster, please wait...");
		String createTable15 = null;

		createTable15 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_DEALER_DETAILS+"(dealer_id uuid, "
				+ "partner_name text, partner_details map<text,text>, primary_contact text, secondary_contact text, primary_email text, secondary_email text,"
				+ "city text, area_code text, state text, country text, zipcode text, PRIMARY KEY (dealer_id));"; 
		try{
			ResultSet resultSet15 = session.execute(createTable15);
		}catch(Exception e){
			logger.info("dealer_details table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("dealer_details table created");
	}
	
	public void dealer_devices() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a dealer_devices Table in cassandra cluster, please wait...");
		String createTable16 = null;

		createTable16 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_DEALER_DEVICES+"(dealer_id uuid, "
				+ "device_type text, batch_number text, dealer_devices map<text, int>, PRIMARY KEY (dealer_id, device_type, batch_number));"; 
		try{
			ResultSet resultSet16 = session.execute(createTable16);
		}catch(Exception e){
			logger.info("dealer_devices table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("dealer_devices table created");
	}
	
	public void notifications() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a notifications Table in cassandra cluster, please wait...");
		String createTable17 = null;

		createTable17 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_NOTIFICATIONS+"(meid text,"
				+ "notification_type int, description text, notified_status boolean, PRIMARY KEY (meid, notification_type));"; 
		try{
			ResultSet resultSet17 = session.execute(createTable17);
		}catch(Exception e){
			logger.info("notifications table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("notifications table created");
	}

	public void panels_by_partner_dealer() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a panels_by_partner_dealer Table in cassandra cluster, please wait...");
		String createTable18 = null;

		createTable18 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_PANELS_BY_PARTNER_DEALER+"(partner_name text,"
				+ "dealer_name text, meid text, registered_date timestamp, PRIMARY KEY (partner_name, dealer_name, meid));"; 
		try{
			ResultSet resultSet18 = session.execute(createTable18);
		}catch(Exception e){
			logger.info("panels_by_partner_dealer table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("panels_by_partner_dealer table created");
	}
	
	public void request_response_tracking_info() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a request_response_tracking_info Table in cassandra cluster, please wait...");
		String createTable19 = null;

		createTable19 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_REQUEST_RESPONSE_TRACKING_INFO+"(meid text, "
				+ "token_id uuid, req_time TimeUUID, request_body text, request_type int, resp_time TimeUUID, response_body text, status boolean, PRIMARY KEY (meid, req_time));"; 
		try{
			ResultSet resultSet19 = session.execute(createTable19);
		}catch(Exception e){
			logger.info("request_response_tracking_info table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("request_response_tracking_info table created");
	}

	public void dealers_by_area() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		logger.info("Creating a dealers_by_area Table in cassandra cluster, please wait...");
		String createTable20 = null;

		createTable20 = "CREATE TABLE IF NOT EXISTS "+Constants.CASSANDRA_KEYSPACE+"."+Constants.CASSANDRA_DEALERS_BY_AREA+"(area_code text, "
				+ "dealer_id uuid, dealer_name text, PRIMARY KEY (area_code, dealer_id));"; 
		try{
			ResultSet resultSet20 = session.execute(createTable20);
		}catch(Exception e){
			logger.info("dealers_by_area table created ::: ", e);
			e.printStackTrace();
		}finally{
			//session.close();
		}
		System.out.println("dealers_by_area table created");
	}

	
}
