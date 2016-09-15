package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOManagement implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOLun.class );
	@Persistent
	private String accountName;
	@Persistent
	private String dhcp;
	@Persistent
	private String wakeOnLan;
	@Persistent
	private String ipAddress1;
	@Persistent
	private String netMask1;
	@Persistent
	private String gateway1;
	@Persistent
	private String status1;
	@Persistent
	private String ipAddress2;
	@Persistent
	private String netMask2;
	@Persistent
	private String gateway2;
	@Persistent
	private String status2;
	@Persistent
	private String domainServer;
	@Persistent
	private String nameServer1;
	@Persistent
	private String nameServer2;
	
	public XIOManagement() {
		//logger.info( "----#### XIOManagement:XIOManagement ####----" );
	}
	public XIOManagement( String accountName, String dhcp, String wakeOnLan, String ipAddress1, String netMask1, String gateway1, String status1, String ipAddress2, String netMask2, String gateway2, String status2, String domainServer, String nameServer1, String nameServer2 ) {
		//logger.info( "----#### XIOManagement:XIOManagement ####----" );
		this.accountName = accountName;
		this.dhcp = dhcp;
		this.wakeOnLan = wakeOnLan;
		this.ipAddress1 = ipAddress1;
		this.netMask1 = netMask1;
		this.gateway1 = gateway1;
		this.status1 = status1;
		this.ipAddress2 = ipAddress2;
		this.netMask2 = netMask2;
		this.gateway2 = gateway2;
		this.status2 = status2;
		this.domainServer = domainServer;
		this.nameServer1 = nameServer1;
		this.nameServer2 = nameServer2;
	}
	public String getDHCP() {
		//logger.info( "----#### XIOManagement:getDHCP ####----" );
		return this.dhcp;
	}
	public void setDHCP( String dhcp ) {
		//logger.info( "----#### XIOManagement:setDHCP ####----" );
		this.dhcp = dhcp;
	}
	public String getWakeOnLan() {
		//logger.info( "----#### XIOManagement:getWakeOnLan ####----" );
		return this.wakeOnLan;
	}
	public void setWakeOnLan( String wakeOnLan ) {
		//logger.info( "----#### XIOManagement:setWakeOnLan ####----" );
		this.wakeOnLan = wakeOnLan;
	}
	public String getIPAddress1() {
		//logger.info( "----#### XIOManagement:getIPAddress1 ####----" );
		return this.ipAddress1;
	}
	public void setIPAddress1( String ipAddress1 ) {
		//logger.info( "----#### XIOManagement:setIPAddress1 ####----" );
		this.ipAddress1 = ipAddress1;
	}
	public String getNetMask1() {
		//logger.info( "----#### XIOManagement:getNetMask1 ####----" );
		return this.netMask1;
	}
	public void setNetMask1( String netMask1 ) {
		//logger.info( "----#### XIOManagement:setNetMask1 ####----" );
		this.netMask1 = netMask1;
	}
	public String getGateway1() {
		//logger.info( "----#### XIOManagement:getGateway1 ####----" );
		return this.gateway1;
	}
	public void setGateway1( String gateway1 ) {
		//logger.info( "----#### XIOManagement:setGateway1 ####----" );
		this.gateway1 = gateway1;
	}
	public String getStatus1() {
		//logger.info( "----#### XIOManagement:getStatus1 ####----" );
		return this.status1;
	}
	public void setStatus1( String status1 ) {
		//logger.info( "----#### XIOManagement:setStatus1 ####----" );
		this.status1 = status1;
	}
	public String getIPAddress2() {
		//logger.info( "----#### XIOManagement:getIPAddress2 ####----" );
		return this.ipAddress2;
	}
	public void setIPAddress2( String ipAddress2 ) {
		//logger.info( "----#### XIOManagement:setIPAddress2 ####----" );
		this.ipAddress2 = ipAddress2;
	}
	public String getNetMask2() {
		//logger.info( "----#### XIOManagement:getNetMask2 ####----" );
		return this.netMask2;
	}
	public void setNetMask2( String netMask2 ) {
		//logger.info( "----#### XIOManagement:setNetMask2 ####----" );
		this.netMask2 = netMask2;
	}
	public String getGateway2() {
		//logger.info( "----#### XIOManagement:getGateway2 ####----" );
		return this.gateway2;
	}
	public void setGateway2( String gateway2 ) {
		//logger.info( "----#### XIOManagement:setGateway2 ####----" );
		this.gateway2 = gateway2;
	}
	public String getStatus2() {
		//logger.info( "----#### XIOManagement:getStatus2 ####----" );
		return this.status2;
	}
	public void setStatus2( String status2 ) {
		//logger.info( "----#### XIOManagement:setStatus2 ####----" );
		this.status2 = status2;
	}
	public String getDomainServer() {
		//logger.info( "----#### XIOManagement:getDomainServer ####----" );
		return this.domainServer;
	}
	public void setDomainServer( String domainServer ) {
		//logger.info( "----#### XIOManagement:setDomainServer ####----" );
		this.domainServer = domainServer;
	}
	public String getNameServer1() {
		//logger.info( "----#### XIOManagement:getNameServer1 ####----" );
		return this.nameServer1;
	}
	public void setNameServer1( String nameServer1 ) {
		//logger.info( "----#### XIOManagement:setNameServer1 ####----" );
		this.nameServer1 = nameServer1;
	}
	public String getNameServer2() {
		//logger.info( "----#### XIOManagement:getNameServer2 ####----" );
		return this.nameServer2;
	}
	public void setNameServer2( String nameServer2 ) {
		//logger.info( "----#### XIOManagement:setNameServer2 ####----" );
		this.nameServer2 = nameServer2;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOManagement:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOManagement:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOManagement:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
