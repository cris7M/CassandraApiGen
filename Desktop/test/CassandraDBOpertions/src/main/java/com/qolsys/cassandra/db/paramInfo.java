package com.qolsys.cassandra.db;
import java.util.HashMap;
import java.util.Map;


/**
 * @author suresh
 *
 */
public class paramInfo {

	public static Map<String, String> map = new HashMap<String, String>();
	
	static{
	     // 1. Dealer Customer Details Table
		 map.put("dealerName", "Name of the dealer customer associated with the panel");
		 map.put("homeContact", "Primary Mobile number (Notification purpose via mobile like SMS alerts)");
		 map.put("workContact", "Secondary Mobile number(Alternative number or office number)");
		 map.put("email", "Email address");
		 map.put("address1", "Address description stating building number and name");
		 map.put("address2", "Address description that has street and locality information");
		 map.put("city", "City name");
		 map.put("state", "State name");
		 map.put("country", "Country [Can follow ISO-3166]");
		 map.put("zipcode", "Postal or Zip codes");
		 
		 // 2. Panel Settings table
		 map.put("settingsType", "Panel settings corresponding to particular module like arming, Zwave settings etc");
		 map.put("settings", "Setting type with DataType and Setting value");
		 
		 //Carrier info is an UDT
		 map.put("basebandVersion", "Carrier baseband version defines range of frequency");
		 map.put("iccid", "It is 22 digit Integrated Circuit Card Identifier in general");
		 map.put("imsi", "An International Mobile Subscriber Identity is up to 15 digits long. The first three digits[ISO-3166 n-3] represent the country code, followed by the the network code. The remaining digits, up to fifteen represents the unique subscriber number from within the network's customer base");
		 map.put("imei", "Every mobile phone, GSM modem or device with a built-in phone / modem has a unique 15 digit IMEI number. Based on this number, you can check some information about the device, eg brand or model.");
		 
		 //Daughter card info is an UDT
		 map.put("name", "Daughter card name it may be complete model info");
		 map.put("protocolType", "Protocols supported by devices like Zwave, SRF etc");
		 map.put("version", "Revision of the release");
		 map.put("firmwareVersion", "Firmware version details");
		 map.put("additionalProperties", "Any additional properties related to daughter card will be accommodated in the form of Key/Value pairs");
		 
		 // 3. Panel Details table
		 map.put("meid", "Panel unique number(meid -- mobile equipment identifier) ... This is also decimal number version of the IMEI number which is in HexFormat");
		 map.put("macaddress", "Panel macaddress[The universal format of Mac address in general are a six pair of hexadecimal number for example A0:23:DE:67:33:46]");
		 map.put("partition", "Panel partition[This is intended to support future versions as of now all panels fall into partition-0]");
		 map.put("softwareVersion", "Panel software version ");
		 map.put("buildNumber", "Panel build number");
		 map.put("linuxVersion", "Panel linux version ");
		 map.put("androidOsVersion", "Panel android os version");
		 map.put("hardwareVersion", "Panel hardware revision");
		 map.put("partNumber", "Panel part number [aka. P/N number]");
		 map.put("pcaSerialNumber", "Panel pca serial number");
		 map.put("armingMode", "As of now Qolsys panel supports several modes of operation. They are 1.armaway 2.armstay & 3.disarm");
		 map.put("manufacture", "Panel manufacturer name");
		 map.put("panelProperties", "Any additional properties of the panel will be accommodate here in form of Name/Value pairs");
		 map.put("areaCode", "Area code");
		 map.put("zipCode", "Postal code");
		 map.put("panelStatus", "Active/Inactive/any other states like alarm");
		 map.put("carrierStatus", "");
		 map.put("celluarConnection", "Available/Not available");
		 map.put("cellularSignalStrength", "Number of signals shown");
		 map.put("carrierDetails", "Refer to corresponding UDT");
		 map.put("daughterCards", "Refer to corresponding UDT");
		 map.put("installationDate", "Device installation date and time");
		 map.put("updatedTime", "Last device information updated date and time");
		 
		 // 4. Panel inventory table
		 map.put("panelDeviceType", "Type of device(DoorWindow, Motion, Doorlock etc)");
		 map.put("count", "Count of the particular device type");
		 
		 // 5. panels_count_by_parameter
		 map.put("id", "Random unique number that signifies unique device type");
		 map.put("paramType", "Type of device");
		 map.put("paramName", "Device name");
		 
		 // 6. Sensor table
		 map.put("endpointId", "This parameter will act as a differentiating factor incase a single sensor is capable of exhibiting multiple functions. Currently Zwave supports being the examples from our inventory are SwitchStrip, SmartSocket etc");
		 map.put("zoneId", "This is refered as zoneId or NodeId based on device category");
		 map.put("vendorName", "Sensor manufaturer/seller name");
		 map.put("deviceProperties", "Device specific and Futuristic additional sensor properties are all accommodated here");
		 map.put("sequenceNumber", "Unique identifier of the sensor which manufacturer assign per batch. Some manufacturers even follow specification which may signifies some info in number itself");
		 map.put("sensorType", "Categorization based on the uscase/purpose of the sensor like MainDoor/Thermostat etc");
		 map.put("batteryStatus", "Battery status that indicates either level(in %) or high/low.");
		 map.put("chimeType", "Sensor notification sounds.");
		 map.put("sensorName", "Custom sensor name chosen by customer");
		 map.put("sensorState", "Status of sensor tripped/Open/Close/Tamper/Failure etc");
		 map.put("sensorStatus", "Active/Inactive");
		 map.put("sensorTts", "TTS are the different speech messages based on event for particular sensor");
		 
		 // 7. Panel Audit table 
		 map.put("sessionId", "Session id along with unique time which is an UUID[Universal Unique id] based out of time");
		 map.put("upTime", "total uptime of the panel");
		 map.put("panelStartupProperties", "Description of all high priority or important properties at the panel startup");
		 map.put("sessionStatus", "whether it is active or expired session");
		 map.put("sessionTermissionReason", "detailed reason for the session termination like crash/tombstones etc");
		 map.put("startTime", "Start time of the panel");
		 map.put("endTime", "Endtime will be updated only on graceful shutdown else will be updated on next successful startup of the panel.");
		 map.put("panelSessionStopProperties", "lists out properties on session terminate, this will be recorded in case of only gracefulshutdown");
		 map.put("sessionType", "Type of session and it can be also source of session initiation");
		 
		 // 8. panel_daily_analytic
		 map.put("panelDate", "Specific date of the panel to get summary of activity on critical events base");
		 map.put("alarmCounter", "Alarm type and corresponding count per day");
		 map.put("deviceWakeupTime", "Device wake up time (update it every 24 hours)");
		 map.put("exceptionCounter", "Exceptions occured on daily basis with count");
		 map.put("eventsCounter", "Events reported on daily basis with count");
		 
		 // 9. event_history_all table
		 map.put("zoneNodeId", "Zone id");
		 map.put("event", "Additional event related properties as key/value pairs");
		 map.put("eventTime", "Event unique time");
		 map.put("deviceType", "device type indicates panels, sensor types etc");
		 map.put("eventName", "Name of the event");
		 
		 // 10. Event history by device 
		 // 11. Event history by event name
		 // 12. Event history by device type table
		 
		 // 13. Dealer details table
		 map.put("dealerId", "Dealer unique id");
		 map.put("parentDealerId", "This field is intended to support sub-dealer concept");
		 map.put("partnerName", "Name of the partner where dealer is associated");
		 map.put("partnerDetails", "If dealer is attached to any partner as of now its qolsys however in future it is ADT for ADT Dealers");
		 map.put("primaryContact", "Primary mobile number");
		 map.put("secondaryContact", "Alternative contact");
		 map.put("primaryEmail", "Primary email address");
		 
		 // 14. Dealer devices table
		 map.put("batchNumber", "Batch number is specific to manufacturer, incase it is not available Anand suggested to use combination of month & Year or year alone based on the frequency of batches released.");
		 map.put("deviceId", "Device id");
		 map.put("inductionDate", "When the device is delivered to dealer");
		 map.put("deviceState", "To track whether successfully installed in field or subjected to RMI");
		 
		 // 15. notifications table
		 map.put("notificationType", "Type of notification");
		 map.put("description", "Description of notification");
		 map.put("notifiedStatus", "Nofied/Not notified");
		 
		 // 16. panels_by_partner_dealer table
		 map.put("registeredDate", "Panel registrated date");
		 
		 // 17. request_response_tracking_info table
		 map.put("tokenId", "Request unique Id");
		 map.put("reqTime", "UUID based on timestamp used for range computations");
		 map.put("requestBody", "Request info like payload");
		 map.put("requestType", "Request type like arming request/sensor event etc");
		 map.put("respTime", "UUID based on timestamp used for range computations");
		 map.put("responseBody", "Response detailed payload");
		 map.put("status", "Success/failure ");
		 
		 // 18. dealers_by_area table 
	}
}
