package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.PanelAudit;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.core.utils.UUIDs;

/**
 * PanelAuditAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface PanelAuditAccessor {

	@Query("select * from panel_audit")
	public Result<PanelAudit> getAll();

	@Query("select * from panel_audit where  meid=:meid")
	public Result<PanelAudit> fetchPanelAudit(@Param("meid")String meid);

	@Query("select * from panel_audit where  meid=:meid and  session_id=:sessionId")
	public Result<PanelAudit> fetchPanelAudit(@Param("meid")String meid, @Param("sessionId")UUIDs sessionId);

}