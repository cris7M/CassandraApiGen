package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.EventHistoryByDevice;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * EventHistoryByDeviceAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface EventHistoryByDeviceAccessor {

	@Query("select * from event_history_by_device")
	public Result<EventHistoryByDevice> getAll();

	@Query("select * from event_history_by_device where  meid=:meid")
	public Result<EventHistoryByDevice> fetchEventHistoryByDevice(@Param("meid")String meid);

	@Query("select * from event_history_by_device where  meid=:meid and  protocol_type=:protocolType")
	public Result<EventHistoryByDevice> fetchEventHistoryByDevice(@Param("meid")String meid, @Param("protocolType")String protocolType);

	@Query("select * from event_history_by_device where  meid=:meid and  protocol_type=:protocolType and  device_type=:deviceType")
	public Result<EventHistoryByDevice> fetchEventHistoryByDevice(@Param("meid")String meid, @Param("protocolType")String protocolType, @Param("deviceType")String deviceType);

	@Query("select * from event_history_by_device where  meid=:meid and  protocol_type=:protocolType and  device_type=:deviceType and  zone_id=:zoneId")
	public Result<EventHistoryByDevice> fetchEventHistoryByDevice(@Param("meid")String meid, @Param("protocolType")String protocolType, @Param("deviceType")String deviceType, @Param("zoneId")int zoneId);

	@Query("select * from event_history_by_device where  meid=:meid and  protocol_type=:protocolType and  device_type=:deviceType and  zone_id=:zoneId and  event_time=:eventTime")
	public Result<EventHistoryByDevice> fetchEventHistoryByDevice(@Param("meid")String meid, @Param("protocolType")String protocolType, @Param("deviceType")String deviceType, @Param("zoneId")int zoneId, @Param("eventTime")UUIDs eventTime);

}