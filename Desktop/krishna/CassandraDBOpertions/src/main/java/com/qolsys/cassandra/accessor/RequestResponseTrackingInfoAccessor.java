package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.RequestResponseTrackingInfo;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * RequestResponseTrackingInfoAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface RequestResponseTrackingInfoAccessor {

	@Query("select * from request_response_tracking_info")
	public Result<RequestResponseTrackingInfo> getAll();

	@Query("select * from request_response_tracking_info where  meid=:meid and  req_time=:reqTime")
	public Result<RequestResponseTrackingInfo> fetchRequestResponseTrackingInfo(@Param("meid")String meid, @Param("reqTime")UUIDs reqTime);

}