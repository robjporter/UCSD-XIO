package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOFCNetwork implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOFCNetwork.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Port No." )
	@Persistent
	private String id;
	@ReportField( label = "Status" )
	@Persistent
	private String status;
	@ReportField( label = "Type" )
	@Persistent
	private String type;
	@ReportField( label = "WWPN" )
	@Persistent
	private String wwpn;
	@ReportField( label = "Speed" )
	@Persistent
	private String speed;
	@ReportField( label = "Bytes Transmitted" )
	@Persistent
	private String bytesTrans;
	@ReportField( label = "Bytes Received" )
	@Persistent
	private String bytesRecv;
	@ReportField( label = "Loop Init Count" )
	@Persistent
	private String loop;
	@ReportField( label = "Not Operational Count" )
	@Persistent
	private String nos;
	
	public XIOFCNetwork() {
		//logger.info( "----#### XIOFCNetwork:XIOFCNetwork ####----" );
	}
	public XIOFCNetwork( String accountName, String id, String status, String type, String wwpn, String speed, String bytesTrans, String bytesRecv, String loop, String nos ) {
		//logger.info( "----#### XIOFCNetwork:XIOFCNetwork ####----" );
		this.accountName = accountName;
		this.id = id;
		this.status = status;
		this.type = type;
		this.wwpn = wwpn;
		this.speed = speed;
		this.bytesTrans = bytesTrans;
		this.bytesRecv = bytesRecv;
		this.loop = loop;
		this.nos = nos;
	}
	public String getID() {
		//logger.info( "----#### XIOFCNetwork:getID ####----" );
		return this.id;
	}
	public void setID( String id ) {
		//logger.info( "----#### XIOFCNetwork:setID ####----" );
		this.id = id;
	}
	public String getStatus() {
		//logger.info( "----#### XIOFCNetwork:getStatus ####----" );
		return this.status;
	}
	public void setStatus( String status ) {
		//logger.info( "----#### XIOFCNetwork:setStatus ####----" );
		this.status = status;
	}
	public String getType() {
		//logger.info( "----#### XIOFCNetwork:getType ####----" );
		return this.type;
	}
	public void setType( String type ) {
		//logger.info( "----#### XIOFCNetwork:setType ####----" );
		this.type = type;
	}
	public String getWWPN() {
		//logger.info( "----#### XIOFCNetwork:getWWPN ####----" );
		return this.wwpn;
	}
	public void setWWPN( String wwpn ) {
		//logger.info( "----#### XIOFCNetwork:setWWPN ####----" );
		this.wwpn = wwpn;
	}
	public String getSpeed() {
		//logger.info( "----#### XIOFCNetwork:getSpeed ####----" );
		return this.speed;
	}
	public void setSpeed( String speed ) {
		//logger.info( "----#### XIOFCNetwork:setSpeed ####----" );
		this.speed = speed;
	}
	public String getBytesSent() {
		//logger.info( "----#### XIOFCNetwork:getBytesSent ####----" );
		return this.bytesTrans;
	}
	public void setBytesSent( String bytesTrans ) {
		//logger.info( "----#### XIOFCNetwork:setBytesSent ####----" );
		this.bytesTrans = bytesTrans;
	}
	public String getBytesRecv() {
		//logger.info( "----#### XIOFCNetwork:getBytesRecv ####----" );
		return this.bytesRecv;
	}
	public void setBytesRecv( String bytesRecv ) {
		//logger.info( "----#### XIOFCNetwork:setBytesRecv ####----" );
		this.bytesRecv = bytesRecv;
	}
	public String getLoop() {
		//logger.info( "----#### XIOFCNetwork:getLoop ####----" );
		return this.loop;
	}
	public void setLoop( String loop ) {
		//logger.info( "----#### XIOFCNetwork:setLoop ####----" );
		this.loop = loop;
	}
	public String getNos() {
		//logger.info( "----#### XIOFCNetwork:getNos ####----" );
		return this.nos;
	}
	public void setNos( String nos ) {
		//logger.info( "----#### XIOFCNetwork:setNos ####----" );
		this.nos = nos;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOFCNetwork:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOFCNetwork:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOFCNetwork:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
