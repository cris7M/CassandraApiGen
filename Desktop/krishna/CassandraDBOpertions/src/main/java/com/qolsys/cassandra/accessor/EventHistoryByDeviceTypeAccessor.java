package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.EventHistoryByDeviceType;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * EventHistoryByDeviceTypeAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface EventHistoryByDeviceTypeAccessor {

	@Query("select * from event_history_by_device_type")
	public Result<EventHistoryByDeviceType> getAll();

	@Query("select * from event_history_by_device_type where  device_type=:deviceType and  meid=:meid")
	public Result<EventHistoryByDeviceType> fetchEventHistoryByDeviceType(@Param("deviceType")String deviceType, @Param("meid")String meid);

	@Query("select * from event_history_by_device_type where  device_type=:deviceType and  meid=:meid and  event_time=:eventTime")
	public Result<EventHistoryByDeviceType> fetchEventHistoryByDeviceType(@Param("deviceType")String deviceType, @Param("meid")String meid, @Param("eventTime")UUIDs eventTime);

}