package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.EventHistoryAll;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * EventHistoryAllAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface EventHistoryAllAccessor {

	@Query("select * from event_history_all")
	public Result<EventHistoryAll> getAll();

	@Query("select * from event_history_all where  meid=:meid")
	public Result<EventHistoryAll> fetchEventHistoryAll(@Param("meid")String meid);

	@Query("select * from event_history_all where  meid=:meid and  event_time=:eventTime")
	public Result<EventHistoryAll> fetchEventHistoryAll(@Param("meid")String meid, @Param("eventTime")UUIDs eventTime);

}