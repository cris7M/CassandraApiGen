package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.PanelSettings;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * PanelSettingsAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface PanelSettingsAccessor {

	@Query("select * from panel_settings")
	public Result<PanelSettings> getAll();

	@Query("select * from panel_settings where  meid=:meid")
	public Result<PanelSettings> fetchPanelSettings(@Param("meid")String meid);

	@Query("select * from panel_settings where  meid=:meid and  settings_type=:settingsType")
	public Result<PanelSettings> fetchPanelSettings(@Param("meid")String meid, @Param("settingsType")String settingsType);

}