package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.DealerDetails;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * DealerDetailsAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface DealerDetailsAccessor {

	@Query("select * from dealer_details")
	public Result<DealerDetails> getAll();

	@Query("select * from dealer_details where  dealer_id=:dealerId")
	public Result<DealerDetails> fetchDealerDetails(@Param("dealerId")UUIDs dealerId);

	@Query("select * from dealer_details where  dealer_id=:dealerId and  partner_name=:partnerName")
	public Result<DealerDetails> fetchDealerDetails(@Param("dealerId")UUIDs dealerId, @Param("partnerName")String partnerName);

}