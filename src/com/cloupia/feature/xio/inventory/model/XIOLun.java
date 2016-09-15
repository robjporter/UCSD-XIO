package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOLun implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOLun.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Name" )
	@Persistent
	private String name;
	@ReportField( label = "Size GB" )
	@Persistent
	private String size;
	@ReportField( label = "ID" )
	@Persistent
	private String id;
	@Persistent
	private String gid;
	@ReportField( label = "Creation Date" )
	@Persistent
	private String creationDate;
	@ReportField( label = "Redundancy" )
	@Persistent
	private String redundancy;
	@ReportField( label = "Comment" )
	@Persistent
	private String comment;
	@ReportField( label = "Affinity" )
	@Persistent
	private String affinity;
	@ReportField( label = "IOPS" )
	@Persistent
	private String iops;
	@ReportField( label = "IOPS Min" )
	@Persistent
	private String iopsMin;
	@ReportField( label = "IOPS Max" )
	@Persistent
	private String iopsMax;

	public XIOLun() {
		//logger.info( "----#### XIOLun:XIOLun ####----" );
	}
	public XIOLun( String accountName, String name, String size, String id, String gid, String redundancy, String creationDate, String comment, String affinity, String iops, String iopsMin, String iopsMax ) {
		//logger.info( "----#### XIOLun:XIOLun ####----" );
		this.accountName = accountName;
		this.name = name;
		this.size = size;
		this.id = id;
		this.gid = gid;
		this.redundancy = redundancy;
		this.creationDate = creationDate;
		this.comment = comment;
		this.affinity = affinity;
		this.iops = iops;
		this.iopsMin = iopsMin;
		this.iopsMax = iopsMax;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOLun:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOLun:setAccountName ####----" );
		this.accountName = accountName;
	}
	public String getName() {
		//logger.info( "----#### XIOLun:getName ####----" );
		return this.name;
	}
	public void setName( String name ) {
		//logger.info( "----#### XIOLun:setName ####----" );
		this.name = name;
	}
	public String getSize() {
		//logger.info( "----#### XIOLun:getSize ####----" );
		return this.size;
	}
	public void setSize( String size ) {
		//logger.info( "----#### XIOLun:setSize ####----" );
		this.size = size;
	}
	public String getID() {
		//logger.info( "----#### XIOLun:getID ####----" );
		return this.id;
	}
	public void setID( String id ) {
		//logger.info( "----#### XIOLun:setID ####----" );
		this.id = id;
	}
	public String getGlobalID() {
		return this.gid;
	}
	public void setGlobalID( String id ) {
		this.gid = id;
	}
	public String getCreationDate() {
		//logger.info( "----#### XIOLun:getCreationDate ####----" );
		return this.creationDate;
	}
	public void setCreationDate( String creationDate ) {
		//logger.info( "----#### XIOLun:setCreationDate ####----" );
		this.creationDate = creationDate;
	}
	public String getRedundancy() {
		//logger.info( "----#### XIOLun:getRedundancy ####----" );
		return this.redundancy;
	}
	public void setRedundancy( String redundancy ) {
		//logger.info( "----#### XIOLun:setRedundancy ####----" );
		this.redundancy = redundancy;
	}
	public String getComment() {
		//logger.info( "----#### XIOLun:getComment ####----" );
		return this.comment;
	}
	public void setComment( String comment ) {
		//logger.info( "----#### XIOLun:setComment ####----" );
		this.comment = comment;
	}
	public String getAffinity() {
		//logger.info( "----#### XIOLun:getAffinity ####----" );
		return this.affinity;
	}
	public void setAffinity( String affinity ) {
		//logger.info( "----#### XIOLun:setAffinity ####----" );
		this.affinity = affinity;
	}
	public String getIOPS() {
		//logger.info( "----#### XIOLun:getIOPS ####----" );
		return this.iops;
	}
	public void setIOPS( String iops ) {
		//logger.info( "----#### XIOLun:setIOPS ####----" );
		this.iops = iops;
	}
	public String getIOPSMin() {
		//logger.info( "----#### XIOLun:getIOPSMin ####----" );
		return this.iopsMin;
	}
	public void setIOPSMin( String iopsmin ) {
		//logger.info( "----#### XIOLun:setIOPSMin ####----" );
		this.iopsMin = iopsmin;
	}
	public String getIOPSMax() {
		//logger.info( "----#### XIOLun:getIOPSMax ####----" );
		return this.iopsMax;
	}
	public void setIOPSMax( String iopsmax ) {
		//logger.info( "----#### XIOLun:setIOPSMax ####----" );
		this.iopsMax = iopsmax;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOLun:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
