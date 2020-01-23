/**
 * 
 */
package com.qolsys.cassandra.constants;

/**
 * @author suresh
 *
 */
public class Constants {

	//Start of Kafka Consumer Properties with zookeeper with events topic

	public static String ZOOKEEPER_HOST = "192.168.15.138:2181"; 
	public static String BOOTSTRAP_SERVER_HOST = "192.168.15.138:9092";
	public static String EVENTS_TOPIC = "panelEvents";
	public static String EVENTS_GROUP_ID = "events-group";
	public static String ZOOKEEPER_SESSION_TIMEOUT = "400";
	public static String ZOOKEEPER_SYNC_TIME = "300";
	public static String AUTO_COMMIT_INTERVAL = "1000";
	public static String KEY_STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
	public static String VALUE_STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
	public static String KEY_STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
	public static String VALUE_STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";;

	//End of Kafka consumer properties
	
	
	//Start of DB Connection properties
	public static String[] CASSANDRA_HOSTS = {"master1", "bvs-desktop"};
	public static int CASSANDRA_PORT = 9042;
	public static String CASSANDRA_KEYSPACE = "iqcloud";
	//public static String CASSANDRA_KEYSPACE1 = "test";
	//public static String CASSANDRA_EVENTS_TABLE = "sample";
	public static String CASSANDRA_CUSTOMER_DETAILS_TABLE = "customer_details";
	public static String CASSANDRA_PANEL_SETTINGS_TABLE = "panel_settings";
	public static String CASSANDRA_PANEL_DETAILS_TABLE = "panel_details";
	public static String CASSANDRA_PANEL_INVENTORY = "panel_inventory";
	public static String CASSANDRA_PANELS_BY_VERSION = "panels_by_version";
	public static String CASSANDRA_PANELS_BY_AREA = "panels_by_area";
	public static String CASSANDRA_PANELS_BY_MANUFACTURER = "panels_by_manufacturer";
	public static String CASSANDRA_SENSOR_TABLE = "sensor_table";
	public static String CASSANDRA_PANEL_SESSION = "panel_session ";
	public static String CASSANDRA_PANEL_DAILY_ANALYTICS = "panel_daily_analytics";
	public static String CASSANDRA_EVENT_HISTORY_ALL = "event_history_all";
	public static String CASSANDRA_EVENT_HISTORY_BY_DEVICE = "event_history_by_device";
	public static String CASSANDRA_EVENT_HISTORY_BY_EVENT_NAME = "event_history_by_event_name";
	public static String CASSANDRA_EVENT_HISTORY_BY_DEVICE_TYPE = "event_history_by_device_type";
	public static String CASSANDRA_CARRIER_INFO = "carrier_info";
	public static String CASSANDRA_DAUGHTER_CARD_INFO = "daughter_card_info";
	public static String CASSANDRA_DEALER_DETAILS = "dealer_details";
	public static String CASSANDRA_DEALER_DEVICES = "dealer_devices";
	public static String CASSANDRA_NOTIFICATIONS = "notifications";
	public static String CASSANDRA_PANELS_BY_PARTNER_DEALER = "panels_by_partner_dealer";
	public static String CASSANDRA_REQUEST_RESPONSE_TRACKING_INFO = "request_response_tracking_info";
	public static String CASSANDRA_DEALERS_BY_AREA = "dealers_by_area";
	
	//End of DB Connection Properties
	
	//start java beans
	
	public static String CASSANDRA_PACKAGE = "com.qolsys.cassandra.beans";
	public static String CASSANDRA_BEAN_DIR = "src/main/java/com/qolsys/cassandra/beans";
	public static String CASSANDRA_ACCESSOR_PACKAGE = "com.qolsys.cassandra.accessor";
	public static String CASSANDRA_ACCESSOR_DIR = "src/main/java/com/qolsys/cassandra/accessor";
	public static String CASSANDRA_DAO_PACKAGE = "com.qolsys.cassandra.dao";
	public static String CASSANDRA_DAO_PACKAGE_DIR = "src/main/java/com/qolsys/cassandra/dao";
	public static String CASSANDRA_CRUD_PACKAGE = "com.qolsys.cassandra.curd";
	public static String CASSANDRA_CRUD_PACKAGE_DIR = "src/main/java/com/qolsys/cassandra/curd";
	
	
	//end of java beans

}
