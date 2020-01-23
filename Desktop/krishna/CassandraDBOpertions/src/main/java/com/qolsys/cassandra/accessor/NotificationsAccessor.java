package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.Notifications;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * NotificationsAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface NotificationsAccessor {

	@Query("select * from notifications")
	public Result<Notifications> getAll();

	@Query("select * from notifications where  meid=:meid")
	public Result<Notifications> fetchNotifications(@Param("meid")String meid);

	@Query("select * from notifications where  meid=:meid and  notification_type=:notificationType")
	public Result<Notifications> fetchNotifications(@Param("meid")String meid, @Param("notificationType")int notificationType);

}