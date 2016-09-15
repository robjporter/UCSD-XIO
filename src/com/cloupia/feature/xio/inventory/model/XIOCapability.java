package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOCapability implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOCapability.class );
	@Persistent
	private String accountName;
	@ReportField( label = "XIO Features and Capabilities" )
	@Persistent
	private String feature;
	
	public XIOCapability() {
		//logger.info( "----#### XIOCapability:XIOCapability ####----" );
	}
	public XIOCapability( String accountName, String feature ) {
		//logger.info( "----#### XIOCapability:XIOCapability ####----" );
		this.accountName = accountName;
		this.feature = feature;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOCapability:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOCapability:setAccountName ####----" );
		this.accountName = accountName;
	}
	public String getFeature() {
		//logger.info( "----#### XIOCapability:getFeature ####----" );
		return this.feature;
	}
	public void setFeature( String feature ) {
		//logger.info( "----#### XIOCapability:setFeature ####----" );
		this.feature = feature;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOCapability:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
