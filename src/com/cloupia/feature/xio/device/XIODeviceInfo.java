package com.cloupia.feature.xio.device;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

@PersistenceCapable( detachable = "true" )
public class XIODeviceInfo {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIODeviceInfo.class );
	@Persistent
	private String accountName;
	@Persistent
	private String id;
	@Persistent
	private String ip;
	@Persistent
	private String globalid;
	@Persistent
	private String serialnumber;
	@Persistent
	private String model;
	@Persistent
	private String fwversion;
	@Persistent
	private String vendor;
	@Persistent
	private String status;
	
	public XIODeviceInfo() {
		//logger.info( "----#### XIODeviceInfo:XIODeviceInfo ####----" );
		this.id = "Unknown";
		this.globalid = "Unknown";
		this.serialnumber = "Unknown";
		this.model = "Unknown";
		this.fwversion = "Unknown";
		this.vendor = "Unknown";
		this.status = "Unknown";
	}
	public XIODeviceInfo( String accountname, String id, String ip, String globalid, String serialnumber, String model, String fwversion, String vendor, String status ) {
		//logger.info( "----#### XIODeviceInfo:XIODeviceInfo ####----" );
		this.accountName = accountname;
		this.id = id;
		this.ip = ip;
		this.globalid = globalid;
		this.serialnumber = serialnumber;
		this.model = model;
		this.fwversion = fwversion;
		this.vendor = vendor;
		this.status = status;
	}
	public String getAccountName(){
		return this.accountName;		
	}
	public void setAccountName( String accountName ){
		this.accountName = accountName;		
	}
	public String getID() {
		//logger.info( "----#### XIODeviceInfo:getID ####----" );
		return this.id;
	}
	public void setID( String id ) {
		//logger.info( "----#### XIODeviceInfo:setID ####----" );
		this.id = id;
	}
	public String getIP() {
		//logger.info( "----#### XIODeviceInfo:getIP ####----" );
		return this.ip;
	}
	public void setIP( String ip ) {
		//logger.info( "----#### XIODeviceInfo:setIP ####----" );
		this.ip = ip;
	}
	public String getGlobalID() {
		//logger.info( "----#### XIODeviceInfo:getGlobalID ####----" );
		return this.globalid;
	}
	public void setglobalID( String globalid ) {
		//logger.info( "----#### XIODeviceInfo:setGlobalID ####----" );
		this.globalid = globalid;
	}
	public String getSerialNumber() {
		//logger.info( "----#### XIODeviceInfo:getSerialNumber ####----" );
		return this.serialnumber;
	}
	public void setSerialNumber( String serialnumber ) {
		//logger.info( "----#### XIODeviceInfo:setSerialNumber ####----" );
		this.serialnumber = serialnumber;
	}
	public String getModel() {
		//logger.info( "----#### XIODeviceInfo:getModel ####----" );
		return this.model;
	}
	public void setModel( String model ) {
		//logger.info( "----#### XIODeviceInfo:setModel ####----" );
		this.model = model;
	}
	public String getFWVersion() {
		//logger.info( "----#### XIODeviceInfo:getFWVersion ####----" );
		return this.fwversion;
	}
	public void setFWVersion( String fwversion ) {
		//logger.info( "----#### XIODeviceInfo:setFWVersion ####----" );
		this.fwversion = fwversion;
	}
	public String getVendor() {
		//logger.info( "----#### XIODeviceInfo:getVendor ####----" );
		return this.vendor;
	}
	public void setVendor( String vendor ) {
		//logger.info( "----#### XIODeviceInfo:setVendor ####----" );
		this.vendor = vendor;
	}
	public String getStatus() {
		//logger.info( "----#### XIODeviceInfo:getStatus ####----" );
		return this.status;
	}
	public void setStatus( String status ) {
		//logger.info( "----#### XIODeviceInfo:setStatus ####----" );
		this.status = status;
	}
}