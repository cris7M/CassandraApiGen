package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.PanelDetails;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * PanelDetailsAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface PanelDetailsAccessor {

	@Query("select * from panel_details")
	public Result<PanelDetails> getAll();

	@Query("select * from panel_details where  meid=:meid")
	public Result<PanelDetails> fetchPanelDetails(@Param("meid")String meid);

	@Query("select * from panel_details where  meid=:meid and  partition=:partition")
	public Result<PanelDetails> fetchPanelDetails(@Param("meid")String meid, @Param("partition")String partition);

}