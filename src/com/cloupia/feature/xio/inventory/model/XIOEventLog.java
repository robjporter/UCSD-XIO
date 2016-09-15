package com.cloupia.feature.xio.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOEventLog  implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOLun.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Date" )
	@Persistent
	private String date;
	@ReportField( label = "Time" )
	@Persistent
	private String time;
	@ReportField( label = "Type" )
	@Persistent
	private String type;
	@ReportField( label = "Component" )
	@Persistent
	private String component;
	@ReportField( label = "Class" )
	@Persistent
	private String clas;
	@ReportField( label = "Description" )
	@Persistent
	private String description;

	public XIOEventLog() {
		//logger.info( "----#### XIOEventLog:getAccountName ####----" );
	}
	public XIOEventLog( String accountname, String date, String time, String type, String component, String clas, String description ) {
		//logger.info( "----#### XIOEventLog:getAccountName ####----" );
		this.accountName = accountname;
		this.date = date;
		this.time = time;
		this.type = type;
		this.component = component;
		this.clas = clas;
		this.description = description;
	}
	public String getDate() {
		//logger.info( "----#### XIOEventLog:getDate ####----" );
		return this.date;
	}
	public void setDate( String date ) {
		//logger.info( "----#### XIOEventLog:setDate ####----" );
		this.date = date;
	}
	public String getTime() {
		//logger.info( "----#### XIOEventLog:getTime ####----" );
		return this.time;
	}
	public void setTime( String time ) {
		//logger.info( "----#### XIOEventLog:setTime ####----" );
		this.time = time;
	}
	public String getType() {
		//logger.info( "----#### XIOEventLog:getType ####----" );
		return this.type;
	}
	public void setType( String type ) {
		//logger.info( "----#### XIOEventLog:setType ####----" );
		this.type = type;
	}
	public String getClas() {
		//logger.info( "----#### XIOEventLog:getClas ####----" );
		return this.clas;
	}
	public void setClas( String clas ) {
		//logger.info( "----#### XIOEventLog:getClas ####----" );
		this.clas = clas;
	}
	public String getComponent() {
		//logger.info( "----#### XIOEventLog:getComponent ####----" );
		return this.component;
	}
	public void setComponent( String component ) {
		//logger.info( "----#### XIOEventLog:getComponent ####----" );
		this.component = component;
	}
	public String getDescription() {
		//logger.info( "----#### XIOEventLog:getDescription ####----" );
		return this.description;
	}
	public void setDescription( String description ) {
		//logger.info( "----#### XIOEventLog:setDescription ####----" );
		this.description = description;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOEventLog:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOEventLog:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOEventLog:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
