package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.PanelDailyAnalytics;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.LocalDate;

/**
 * PanelDailyAnalyticsAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface PanelDailyAnalyticsAccessor {

	@Query("select * from panel_daily_analytics")
	public Result<PanelDailyAnalytics> getAll();

	@Query("select * from panel_daily_analytics where  meid=:meid and  panel_date=:panelDate")
	public Result<PanelDailyAnalytics> fetchPanelDailyAnalytics(@Param("meid")String meid, @Param("panelDate")LocalDate panelDate);

}