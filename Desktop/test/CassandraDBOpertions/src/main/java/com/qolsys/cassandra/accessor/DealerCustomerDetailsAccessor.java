package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.DealerCustomerDetails;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * DealerCustomerDetailsAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface DealerCustomerDetailsAccessor {

	@Query("select * from dealer_customer_details")
	public Result<DealerCustomerDetails> getAll();

	@Query("select * from dealer_customer_details where  meid=:meid")
	public Result<DealerCustomerDetails> fetchDealerCustomerDetails(@Param("meid")String meid);

}