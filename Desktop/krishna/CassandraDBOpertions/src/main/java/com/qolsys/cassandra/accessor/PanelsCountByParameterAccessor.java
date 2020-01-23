package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.PanelsCountByParameter;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * PanelsCountByParameterAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface PanelsCountByParameterAccessor {

	@Query("select * from panels_count_by_parameter")
	public Result<PanelsCountByParameter> getAll();

	@Query("select * from panels_count_by_parameter where  dealer_name=:dealerName")
	public Result<PanelsCountByParameter> fetchPanelsCountByParameter(@Param("dealerName")String dealerName);

	@Query("select * from panels_count_by_parameter where  dealer_name=:dealerName and  id=:id")
	public Result<PanelsCountByParameter> fetchPanelsCountByParameter(@Param("dealerName")String dealerName, @Param("id")UUIDs id);

	@Query("select * from panels_count_by_parameter where  dealer_name=:dealerName and  id=:id and  param_type=:paramType")
	public Result<PanelsCountByParameter> fetchPanelsCountByParameter(@Param("dealerName")String dealerName, @Param("id")UUIDs id, @Param("paramType")String paramType);

	@Query("select * from panels_count_by_parameter where  dealer_name=:dealerName and  id=:id and  param_type=:paramType and  param_name=:paramName")
	public Result<PanelsCountByParameter> fetchPanelsCountByParameter(@Param("dealerName")String dealerName, @Param("id")UUIDs id, @Param("paramType")String paramType, @Param("paramName")String paramName);

}