package com.qolsys.cassandra.Test;

import java.io.IOException;
import java.util.Date;

import com.qolsys.cassandra.connection.CassandraCluster;
import com.qolsys.cassandra.db.CreateDaoClasses;
import com.qolsys.cassandra.db.CreateAccessors;
import com.qolsys.cassandra.db.CreateJavaBeans;
import com.qolsys.cassandra.db.CrudOperations;;

/**
 * @author suresh
 *
 */
public class MainClass {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		//LeshanEventsConsumer kafkaConsumer = new LeshanEventsConsumer();
		//CreateTable CTable = new CreateTable();
		//KeySpace key = new KeySpace();
		
		CreateJavaBeans bean = new CreateJavaBeans();
		CreateAccessors accessor = new CreateAccessors();
		CreateDaoClasses dao = new CreateDaoClasses();
		//CrudOperations crud = new CrudOperations();
		
		/*key.Keyspace();
		//CTable.Table();	
		CTable.Customer_details();
		CTable.Panel_settings();
		CTable.panel_details();
		CTable.panel_inventory();
		CTable.panels_by_version();
		CTable.panels_by_area();
		CTable.panels_by_manufacturer();
		CTable.sensor_table();
		CTable.panel_session();
		CTable.panel_daily_analytics();
		CTable.event_history_all();
		CTable.event_history_by_device();
		CTable.event_history_by_event_name();
		CTable.event_history_by_device_type();
		CTable.dealer_details();
		CTable.dealer_devices();
		CTable.notifications();
		CTable.panels_by_partner_dealer();
		CTable.request_response_tracking_info();
		CTable.dealers_by_area();*/
		long l = new Date().getTime();
		bean.create();
		accessor.create();
		dao.create(true);
		//crud.create(true);
		long l2 = new Date().getTime();
		System.out.println("Time taken :: "+(l2-l));
		
		//kafkaConsumer.initialize();
		//kafkaConsumer.consume();
		CassandraCluster.closeCluster();
	}

}
