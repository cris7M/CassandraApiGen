package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.EventHistoryByEventName;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * EventHistoryByEventNameAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface EventHistoryByEventNameAccessor {

	@Query("select * from event_history_by_event_name")
	public Result<EventHistoryByEventName> getAll();

	@Query("select * from event_history_by_event_name where  event_name=:eventName and  event_time=:eventTime")
	public Result<EventHistoryByEventName> fetchEventHistoryByEventName(@Param("eventName")String eventName, @Param("eventTime")UUIDs eventTime);

}