package com.cloupia.feature.xio.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOCapacity implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOLun.class );
	@Persistent
	private String accountName;
	@Persistent
	private String capacity;
	@Persistent
	private String used;

	public XIOCapacity() {
		//logger.info( "----#### XIOCapacity:XIOCapacity ####----" );
	}
	public XIOCapacity( String accountName, String capacity, String used ) {
		//logger.info( "----#### XIOCapacity:XIOCapacity ####----" );
		this.accountName = accountName;
		this.capacity = capacity;
		this.used = used;
	}
	public String getCapacity() {
		//logger.info( "----#### XIOCapacity:getCapacity ####----" );
		return this.capacity;
	}
	public void setCapacity( String capacity ) {
		//logger.info( "----#### XIOCapacity:setCapacity ####----" );
		this.capacity = capacity;
	}
	public String getUsed() {
		//logger.info( "----#### XIOCapacity:getUsed ####----" );
		return this.used;
	}
	public void setUsed( String used ) {
		//logger.info( "----#### XIOCapacity:setUsed ####----" );
		this.used = used;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOCapacity:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOCapacity:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOCapacity:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
