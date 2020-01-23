package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.PanelInventory;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * PanelInventoryAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface PanelInventoryAccessor {

	@Query("select * from panel_inventory")
	public Result<PanelInventory> getAll();

	@Query("select * from panel_inventory where  meid=:meid")
	public Result<PanelInventory> fetchPanelInventory(@Param("meid")String meid);

	@Query("select * from panel_inventory where  meid=:meid and  protocol_type=:protocolType")
	public Result<PanelInventory> fetchPanelInventory(@Param("meid")String meid, @Param("protocolType")String protocolType);

	@Query("select * from panel_inventory where  meid=:meid and  protocol_type=:protocolType and  device_type=:deviceType")
	public Result<PanelInventory> fetchPanelInventory(@Param("meid")String meid, @Param("protocolType")String protocolType, @Param("deviceType")String deviceType);

}