package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOController implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOController.class );
	@Persistent
	private String accountName;
	@ReportField( label = "IP" )
	@Persistent
	private String ip;
	@ReportField( label = "MAC Address" )
	@Persistent
	private String mac;
	@ReportField( label = "Firmware" )
	@Persistent
	private String firmware;
	@ReportField( label = "Status" )
	@Persistent
	private String status;
	@ReportField( label = "DNS Name" )
	@Persistent
	private String dns;
	@ReportField( label = "XO Master" )
	@Persistent
	private String master;

	public XIOController() {
		//logger.info( "----#### XIOController:XIOController ####----" );
	}
	public XIOController( String accountName, String ip, String mac, String firmware, String status, String name, String master ) {
		//logger.info( "----#### XIOController:XIOController ####----" );
		this.accountName = accountName;
		this.ip = ip;
		this.mac = mac;
		this.firmware = firmware;
		this.status = status;
		this.dns = name;
		this.master = master;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOController:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOController:setAccountName ####----" );
		this.accountName = accountName;
	}
	public String getIP() {
		//logger.info( "----#### XIOController:getIP ####----" );
		return this.ip;
	}
	public void setIP( String ip ) {
		//logger.info( "----#### XIOController:setIP ####----" );
		this.ip = ip;
	}
	public String getMAC() {
		//logger.info( "----#### XIOController:getMAC ####----" );
		return this.mac;
	}
	public void setMAC( String mac ) {
		//logger.info( "----#### XIOController:setMAC ####----" );
		this.mac = mac;
	}
	public String getFirmware() {
		//logger.info( "----#### XIOController:getFirmware ####----" );
		return this.firmware;
	}
	public void setFirmware( String firmware ) {
		//logger.info( "----#### XIOController:setFirmware ####----" );
		this.firmware = firmware;
	}
	public String getStatus() {
		//logger.info( "----#### XIOController:getStatus ####----" );
		return this.status;
	}
	public void setStatus( String status ) {
		//logger.info( "----#### XIOController:setStatus ####----" );
		this.status = status;
	}
	public String getDNS() {
		//logger.info( "----#### XIOController:getDNS ####----" );
		return this.dns;
	}
	public void setDNS( String dns ) {
		//logger.info( "----#### XIOController:setDNS ####----" );
		this.dns = dns;
	}
	public String getMaster() {
		//logger.info( "----#### XIOController:getMaster ####----" );
		return this.master;
	}
	public void setMaster( String master ) {
		//logger.info( "----#### XIOController:setMaster ####----" );
		this.master = master;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOController:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
