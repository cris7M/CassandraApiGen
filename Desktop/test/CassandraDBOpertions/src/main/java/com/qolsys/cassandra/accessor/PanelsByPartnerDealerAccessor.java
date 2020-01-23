package com.qolsys.cassandra.accessor;

import com.qolsys.cassandra.beans.PanelsByPartnerDealer;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * PanelsByPartnerDealerAccessor class corresponds to java bean class to support all fetch queries that are 
 * not supported by Object mapper framework and also eases the transformation to list of java beans 
 * from database records
 *
 * @author cassandraIDC
 * 
 */

@Accessor
public interface PanelsByPartnerDealerAccessor {

	@Query("select * from panels_by_partner_dealer")
	public Result<PanelsByPartnerDealer> getAll();

	@Query("select * from panels_by_partner_dealer where  partner_name=:partnerName")
	public Result<PanelsByPartnerDealer> fetchPanelsByPartnerDealer(@Param("partnerName")String partnerName);

	@Query("select * from panels_by_partner_dealer where  partner_name=:partnerName and  dealer_name=:dealerName")
	public Result<PanelsByPartnerDealer> fetchPanelsByPartnerDealer(@Param("partnerName")String partnerName, @Param("dealerName")String dealerName);

	@Query("select * from panels_by_partner_dealer where  partner_name=:partnerName and  dealer_name=:dealerName and  meid=:meid")
	public Result<PanelsByPartnerDealer> fetchPanelsByPartnerDealer(@Param("partnerName")String partnerName, @Param("dealerName")String dealerName, @Param("meid")String meid);

}