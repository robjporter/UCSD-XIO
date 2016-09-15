package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIODrive implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIODrive.class );
	@Persistent
	private String accountName;
	@ReportField( label = "Name" )
	@Persistent
	private String name;
	@ReportField( label = "Read IOPS" )
	@Persistent
	private String iopsRead;
	@ReportField( label = "Write IOPS" )
	@Persistent
	private String iopsWrite;
	@ReportField( label = "Total IOPS" )
	@Persistent
	private String iopsTotal;
	@ReportField( label = "Read Kbps" )
	@Persistent
	private String kbpsRead;
	@ReportField( label = "Write Kbps" )
	@Persistent
	private String kbpsWrite;
	@ReportField( label = "Total Kbps" )
	@Persistent
	private String kbpsTotal;
	@ReportField( label = "Read Latency" )
	@Persistent
	private String readLatency;
	@ReportField( label = "Read Latency Max" )
	@Persistent
	private String readLatencyMax;
	@ReportField( label = "Write Latency" )
	@Persistent
	private String writeLatency;
	@ReportField( label = "Write Latency Max" )
	@Persistent
	private String writeLatencyMax;
	@ReportField( label = "Total IOPS Since Reboot" )
	@Persistent
	private String iopsTotalReboot;
	@ReportField( label = "Total Kbps Since Reboot" )
	@Persistent
	private String kbpsTotalReboot;

	public XIODrive() {
		//logger.info( "----#### XIODrive:XIODrive ####----" );
	}
	public XIODrive( String accountname, String name, String iopsRead, String iopsWrite, String iopsTotal, String kbpsRead, String kbpsWrite, String kbpsTotal, String readLatency, String readLatencyMax, String writeLatency, String writeLatencyMax, String iopsTotalReboot, String kbpsTotalReboot ) {
		//logger.info( "----#### XIODrive:XIODrive ####----" );
		this.accountName = accountname;
		this.name = name;
		this.iopsRead = iopsRead;
		this.iopsWrite = iopsWrite;
		this.iopsTotal = iopsTotal;
		this.kbpsRead = kbpsRead;
		this.kbpsWrite = kbpsWrite;
		this.kbpsTotal = kbpsTotal;
		this.readLatency = readLatency;
		this.readLatencyMax = readLatencyMax;
		this.writeLatency = writeLatency;
		this.writeLatencyMax = writeLatencyMax;
		this.iopsTotalReboot = iopsTotalReboot;
		this.kbpsTotalReboot = kbpsTotalReboot;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIODrive:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIODrive:setAccountName ####----" );
		this.accountName = accountName;
	}
	public String getName() {
		//logger.info( "----#### XIODrive:getName ####----" );
		return this.name;
	}
	public void setName( String name ) {
		//logger.info( "----#### XIODrive:setName ####----" );
		this.name = name;
	}
	public String getIopsRead() {
		//logger.info( "----#### XIODrive:getIopsRead ####----" );
		return this.iopsRead;
	}
	public void setIopsRead( String iopsRead ) {
		//logger.info( "----#### XIODrive:setIopsRead ####----" );
		this.iopsRead = iopsRead;
	}
	public String getIopsWrite() {
		//logger.info( "----#### XIODrive:getIopsWrite ####----" );
		return this.iopsWrite;
	}
	public void setIopsWrite( String iopsWrite ) {
		//logger.info( "----#### XIODrive:setIopsWrite ####----" );
		this.iopsWrite = iopsWrite;
	}
	public String getIopsTotal() {
		//logger.info( "----#### XIODrive:getIopsTotal ####----" );
		return this.iopsTotal;
	}
	public void setIopsTotal( String iopsTotal ) {
		//logger.info( "----#### XIODrive:setIopsTotal ####----" );
		this.iopsTotal = iopsTotal;
	}
	public String getKbpsRead() {
		//logger.info( "----#### XIODrive:getKbpsRead ####----" );
		return this.kbpsRead;
	}
	public void setKbpsRead( String kbpsRead ) {
		//logger.info( "----#### XIODrive:setKbpsRead ####----" );
		this.kbpsRead = kbpsRead;
	}
	public String getKbpsWrite() {
		//logger.info( "----#### XIODrive:getKbpsWrite ####----" );
		return this.kbpsWrite;
	}
	public void setKbpsWrite( String kbpsWrite ) {
		//logger.info( "----#### XIODrive:setKbpsWrite ####----" );
		this.kbpsWrite = kbpsWrite;
	}
	public String getKbpsTotal() {
		//logger.info( "----#### XIODrive:getKbpsTotal ####----" );
		return this.kbpsTotal;
	}
	public void setKbpsTotal( String kbpsTotal ) {
		//logger.info( "----#### XIODrive:setKbpsTotal ####----" );
		this.kbpsTotal = kbpsTotal;
	}
	public String getReadLatency() {
		//logger.info( "----#### XIODrive:getReadLatency ####----" );
		return this.readLatency;
	}
	public void setReadLatency( String readLatency ) {
		//logger.info( "----#### XIODrive:setReadLatency ####----" );
		this.readLatency = readLatency;
	}
	public String getReadLatencyMax() {
		//logger.info( "----#### XIODrive:getReadLatencyMax ####----" );
		return this.readLatencyMax;
	}
	public void setReadLatencyMax( String readLatencyMax ) {
		//logger.info( "----#### XIODrive:setReadLatencyMax ####----" );
		this.readLatencyMax = readLatencyMax;
	}
	public String getWriteLatency() {
		//logger.info( "----#### XIODrive:getWriteLatency ####----" );
		return this.writeLatency;
	}
	public void setWriteLatency( String writeLatency ) {
		//logger.info( "----#### XIODrive:setWriteLatency ####----" );
		this.writeLatency = writeLatency;
	}
	public String getWriteLatencyMax() {
		//logger.info( "----#### XIODrive:getWriteLatencyMax ####----" );
		return this.writeLatencyMax;
	}
	public void setWriteLatencyMax( String writeLatencyMax ) {
		//logger.info( "----#### XIODrive:setWriteLatencyMax ####----" );
		this.writeLatencyMax = writeLatencyMax;
	}
	public String getIopsTotalReboot() {
		//logger.info( "----#### XIODrive:getIopsTotalReboot ####----" );
		return this.iopsTotalReboot;
	}
	public void setIopsTotalReboot( String iopsTotalReboot ) {
		//logger.info( "----#### XIODrive:setIopsTotalReboot ####----" );
		this.iopsTotalReboot = iopsTotalReboot;
	}
	public String getKbpsTotalReboot() {
		//logger.info( "----#### XIODrive:getKbpsTotalReboot ####----" );
		return this.kbpsTotalReboot;
	}
	public void setKbpsTotalReboot( String kbpsTotalReboot ) {
		//logger.info( "----#### XIODrive:setKbpsTotalReboot ####----" );
		this.kbpsTotalReboot = kbpsTotalReboot;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIODrive:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
