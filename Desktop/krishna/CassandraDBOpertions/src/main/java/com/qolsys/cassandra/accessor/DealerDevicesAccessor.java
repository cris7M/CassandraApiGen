package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.DealerDevices;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * DealerDevicesAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface DealerDevicesAccessor {

	@Query("select * from dealer_devices")
	public Result<DealerDevices> getAll();

	@Query("select * from dealer_devices where  dealer_id=:dealerId and  device_type=:deviceType")
	public Result<DealerDevices> fetchDealerDevices(@Param("dealerId")UUIDs dealerId, @Param("deviceType")String deviceType);

	@Query("select * from dealer_devices where  dealer_id=:dealerId and  device_type=:deviceType and  batch_number=:batchNumber")
	public Result<DealerDevices> fetchDealerDevices(@Param("dealerId")UUIDs dealerId, @Param("deviceType")String deviceType, @Param("batchNumber")String batchNumber);

}