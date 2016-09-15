package com.cloupia.feature.xio.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOMapping implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOMapping.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Allocated Host Name" )
	@Persistent
	private String hostName;
	@ReportField( label = "Host WWPN" )
	@Persistent
	private String hostWWPN;
	@ReportField( label = "Mapped Volume Name" )
	@Persistent
	private String volumeName;
	@ReportField( label = "Volume LUN ID" )
	@Persistent
	private String volumeLunID;
	
	
	public XIOMapping() {
		//logger.info( "----#### XIOMapping:XIOMapping ####----" );
	}
	public XIOMapping( String accountname, String hostname, String hostwwpn, String volumename, String volumeid ) {
		//logger.info( "----#### XIOMapping:XIOMapping ####----" );
		this.accountName = accountname;
		this.hostName = hostname;
		this.hostWWPN = hostwwpn;
		this.volumeName = volumename;
		this.volumeLunID = volumeid;
	}
	public String getHostName() {
		return this.hostName;
	}
	public void setHostName( String hostname ) {
		this.hostName = hostname;
	}
	public String getHostWWPN() {
		return this.hostWWPN;
	}
	public void setHostWWPN( String wwpn ) {
		this.hostWWPN = wwpn;
	}
	public String getVolumeName() {
		return this.volumeName;
	}
	public void setVolumeName( String name ) {
		this.volumeName = name;
	}
	public String getVolumeLunID() {
		return this.volumeLunID;
	}
	public void setVolumeLunID( String id ) {
		this.volumeLunID = id;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOMapping:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOMapping:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOMapping:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
