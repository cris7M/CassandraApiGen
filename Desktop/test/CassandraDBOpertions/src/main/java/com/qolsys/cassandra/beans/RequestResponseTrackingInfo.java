package com.qolsys.cassandra.beans;

import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.core.utils.UUIDs;

/**
 * RequestResponseTrackingInfo class corresponds to java bean class to table(request_response_tracking_info) in database(iqcloud)
 *
 * @author cassandraIDC
 * 
 */
@Table(name="request_response_tracking_info", keyspace="iqcloud")
public class RequestResponseTrackingInfo {

	@PartitionKey(0)
	@Column(name="meid")
	private String meid;

	@PartitionKey(1)
	@Column(name="req_time")
	private UUIDs reqTime;

	@Column(name="request_body")
	private String requestBody;

	@Column(name="request_type")
	private int requestType;

	@Column(name="resp_time")
	private UUIDs respTime;

	@Column(name="response_body")
	private String responseBody;

	@Column(name="status")
	private boolean status;

	@Column(name="token_id")
	private UUIDs tokenId;


	/**
	 * @return meid 
	 */
	public String getMeid(){
		return meid;
	}

	/**
	 * @param meid Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat
	 */
	public void setMeid(String meid){
		this.meid = meid;
	}

	/**
	 * @return reqTime 
	 */
	public UUIDs getReqTime(){
		return reqTime;
	}

	/**
	 * @param reqTime UUID based on timestamp used for range computations
	 */
	public void setReqTime(UUIDs reqTime){
		this.reqTime = reqTime;
	}

	/**
	 * @return requestBody 
	 */
	public String getRequestBody(){
		return requestBody;
	}

	/**
	 * @param requestBody Request info like payload
	 */
	public void setRequestBody(String requestBody){
		this.requestBody = requestBody;
	}

	/**
	 * @return requestType 
	 */
	public int getRequestType(){
		return requestType;
	}

	/**
	 * @param requestType Request type like arming request/sensor event etc
	 */
	public void setRequestType(int requestType){
		this.requestType = requestType;
	}

	/**
	 * @return respTime 
	 */
	public UUIDs getRespTime(){
		return respTime;
	}

	/**
	 * @param respTime UUID based on timestamp used for range computations
	 */
	public void setRespTime(UUIDs respTime){
		this.respTime = respTime;
	}

	/**
	 * @return responseBody 
	 */
	public String getResponseBody(){
		return responseBody;
	}

	/**
	 * @param responseBody Response detailed payload
	 */
	public void setResponseBody(String responseBody){
		this.responseBody = responseBody;
	}

	/**
	 * @return status 
	 */
	public boolean getStatus(){
		return status;
	}

	/**
	 * @param status Success/failure 
	 */
	public void setStatus(boolean status){
		this.status = status;
	}

	/**
	 * @return tokenId 
	 */
	public UUIDs getTokenId(){
		return tokenId;
	}

	/**
	 * @param tokenId Request unique Id
	 */
	public void setTokenId(UUIDs tokenId){
		this.tokenId = tokenId;
	}

	/**
	 * String representation of Table(Entity) Object:RequestResponseTrackingInfo 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[meid::"+meid+
		", reqTime::"+reqTime+", requestBody::"+requestBody+", requestType::"+requestType+
		", respTime::"+respTime+", responseBody::"+responseBody+", status::"+status+
		", tokenId::"+tokenId+"]";
	}

}