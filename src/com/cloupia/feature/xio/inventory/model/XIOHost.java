package com.cloupia.feature.xio.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOHost  implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOLun.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Global ID" )
	@Persistent
	private String id;
	@ReportField( label = "Host Protocol" )
	@Persistent
	private String protocol;
	@ReportField( label = "Host name" )
	@Persistent
	private String name;
	@ReportField( label = "Host ID" )
	@Persistent
	private String hid;
	@ReportField( label = "Host Type" )
	@Persistent
	private String type;
	@ReportField( label = "Host Comment" )
	@Persistent
	private String comment;
	
	public XIOHost() {
		//logger.info( "----#### XIOHost:XIOHost ####----" );
	}
	public XIOHost( String accountname, String id, String protocol, String name, String hid, String type, String comment ) {
		//logger.info( "----#### XIOHost:XIOHost ####----" );
		this.accountName = accountname;
		this.id = id;
		this.protocol = protocol;
		this.name = name;
		this.hid = hid;
		this.type = type;
		this.comment = comment;
	}
	public String getID() {
		return this.id;
	}
	public void setID( String id ) {
		this.id = id;
	}
	public String getProtocol() {
		return this.protocol;
	}
	public void setProtocol( String protocol ) {
		this.protocol = protocol;
	}
	public String getHostName() {
		return this.name;
	}
	public void setHostName( String name ) {
		this.name = name;
	}
	public String getHostID() {
		return this.hid;
	}
	public void setHostID( String hid ) {
		this.hid = hid;
	}
	public String getHostType() {
		return this.type;
	}
	public void setHostType( String type ) {
		this.type = type;
	}
	public String getHostComment() {
		return this.comment;
	}
	public void setHostComment( String comment ) {
		this.comment = comment;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOHost:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOHost:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOHost:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}