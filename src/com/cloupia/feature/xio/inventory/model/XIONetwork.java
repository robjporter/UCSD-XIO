package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIONetwork  implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIONetwork.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Port No." )
	@Persistent
	private String id;
	@ReportField( label = "Status" )
	@Persistent
	private String status;
	@ReportField( label = "Detail" )
	@Persistent
	private String detail;
	@ReportField( label = "Hardware Revision" )
	@Persistent
	private String hw;
	@ReportField( label = "Serial" )
	@Persistent
	private String serial;
	@ReportField( label = "Part No." )
	@Persistent
	private String part;
	@ReportField( label = "Manufacture Date" )
	@Persistent
	private String manuf;
	@ReportField( label = "HEC" )
	@Persistent
	private String hec;
	@ReportField( label = "HEC TX" )
	@Persistent
	private String hectx;
	@ReportField( label = "LOS" )
	@Persistent
	private String los;
	@ReportField( label = "FLT TX" )
	@Persistent
	private String txflt;
	
	public XIONetwork() {
		//logger.info( "----#### XIONetwork:XIONetwork ####----" );
	}
	public XIONetwork( String accountName, String id, String status, String detail, String hw, String serial, String part, String manuf, String hec, String hectx, String los, String txflt ) {
		//logger.info( "----#### XIONetwork:XIONetwork ####----" );
		this.accountName = accountName;
		this.id = id;
		this.detail = detail;
		this.hw = hw;
		this.serial = serial;
		this.part = part;
		this.manuf = manuf;
		this.hec = hec;
		this.hectx = hectx;
		this.los = los;
		this.txflt = txflt;
	}
	public String getID() {
		//logger.info( "----#### XIONetwork:getID ####----" );
		return this.id;
	}
	public void setID( String id ) {
		//logger.info( "----#### XIONetwork:setID ####----" );
		this.id = id;
	}
	public String getStatus() {
		//logger.info( "----#### XIONetwork:getStatus ####----" );
		return this.status;
	}
	public void setStatus( String status ) {
		//logger.info( "----#### XIONetwork:setStatus ####----" );
		this.status = status;
	}
	public String getDetail() {
		//logger.info( "----#### XIONetwork:getDetail ####----" );
		return this.detail;
	}
	public void setDetail( String detail ) {
		//logger.info( "----#### XIONetwork:setDetail ####----" );
		this.detail = detail;
	}
	public String getHW() {
		//logger.info( "----#### XIONetwork:getHW ####----" );
		return this.hw;
	}
	public void setHW( String hw ) {
		//logger.info( "----#### XIONetwork:setHW ####----" );
		this.hw = hw;
	}
	public String getSerial() {
		//logger.info( "----#### XIONetwork:getSerial ####----" );
		return this.serial;
	}
	public void setSerial( String serial ) {
		//logger.info( "----#### XIONetwork:setSerial ####----" );
		this.serial = serial;
	}
	public String getPart() {
		//logger.info( "----#### XIONetwork:getPart ####----" );
		return this.part;
	}
	public void setPart( String part ) {
		//logger.info( "----#### XIONetwork:setPart ####----" );
		this.part = part;
	}
	public String getManuf() {
		//logger.info( "----#### XIONetwork:getManuf ####----" );
		return this.manuf;
	}
	public void setManuf( String manuf ) {
		//logger.info( "----#### XIONetwork:setManuf ####----" );
		this.manuf = manuf;
	}
	public String getHec() {
		//logger.info( "----#### XIONetwork:getHec ####----" );
		return this.hec;
	}
	public void setHec( String hec ) {
		//logger.info( "----#### XIONetwork:setHec ####----" );
		this.hec = hec;
	}
	public String getHectx() {
		//logger.info( "----#### XIONetwork:getHectx ####----" );
		return this.hectx;
	}
	public void setHectx( String hectx ) {
		//logger.info( "----#### XIONetwork:setHectx ####----" );
		this.hectx = hectx;
	}
	public String getLos() {
		//logger.info( "----#### XIONetwork:getLos ####----" );
		return this.los;
	}
	public void setLos( String los ) {
		//logger.info( "----#### XIONetwork:setLos ####----" );
		this.los = los;
	}
	public String getTxflt() {
		//logger.info( "----#### XIONetwork:getTxflt ####----" );
		return this.txflt;
	}
	public void setTxflt( String txflt ) {
		//logger.info( "----#### XIONetwork:setTxflt ####----" );
		this.txflt = txflt;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIONetwork:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIONetwork:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIONetwork:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
