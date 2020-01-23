package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.SensorInfo;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * SensorInfoAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface SensorInfoAccessor {

	@Query("select * from sensor_info")
	public Result<SensorInfo> getAll();

	@Query("select * from sensor_info where  meid=:meid")
	public Result<SensorInfo> fetchSensorInfo(@Param("meid")String meid);

	@Query("select * from sensor_info where  meid=:meid and  protocol_type=:protocolType")
	public Result<SensorInfo> fetchSensorInfo(@Param("meid")String meid, @Param("protocolType")String protocolType);

	@Query("select * from sensor_info where  meid=:meid and  protocol_type=:protocolType and  zone_id=:zoneId")
	public Result<SensorInfo> fetchSensorInfo(@Param("meid")String meid, @Param("protocolType")String protocolType, @Param("zoneId")int zoneId);

	@Query("select * from sensor_info where  meid=:meid and  protocol_type=:protocolType and  zone_id=:zoneId and  endpoint_id=:endpointId")
	public Result<SensorInfo> fetchSensorInfo(@Param("meid")String meid, @Param("protocolType")String protocolType, @Param("zoneId")int zoneId, @Param("endpointId")int endpointId);

}