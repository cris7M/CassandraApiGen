package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.DealersByArea;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * DealersByAreaAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface DealersByAreaAccessor {

	@Query("select * from dealers_by_area")
	public Result<DealersByArea> getAll();

	@Query("select * from dealers_by_area where  area_code=:areaCode")
	public Result<DealersByArea> fetchDealersByArea(@Param("areaCode")String areaCode);

	@Query("select * from dealers_by_area where  area_code=:areaCode and  dealer_id=:dealerId")
	public Result<DealersByArea> fetchDealersByArea(@Param("areaCode")String areaCode, @Param("dealerId")UUIDs dealerId);

}