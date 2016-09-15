package com.cloupia.feature.xio.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOData implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOMedia.class );
	@Persistent
	private String accountName;
	@Persistent
	private String lunRaid1Count = "";
	@Persistent
	private String lunRaid5Count = "";
	@Persistent
	private String perDiskReadLatency = "";
	@Persistent
	private String perDiskWriteLatency = "";
	@Persistent
	private String perDiskReadKBPS = "";
	@Persistent
	private String perDiskWriteKBPS = "";
	@Persistent
	private String perDiskReadIOPS = "";
	@Persistent
	private String perDiskWriteIOPS = "";
	@Persistent
	private String totalIOPS = "";
	@Persistent
	private String totalKBPS = "";
	@Persistent
	private String totalReadKbps = "";
	@Persistent
	private String totalWriteKbps = "";
	@Persistent
	private String totalReadIops = "";
	@Persistent
	private String totalWriteIops = "";
	@Persistent
	private String totalCapacity = "";
	@Persistent
	private String totalLunUsage = "";
	private String totalSystemIOPS = "";
	private String totalIODensity = "";
	private String totalCacheHitPercent = "";
	
	public XIOData() {
		//logger.info( "----#### XIOData:XIOData ####----" );
	}
	public String getTotalSystemIOPS() {
		//logger.info( "----#### XIOData:getTotalSystemIOPS ####----" );
		return this.totalSystemIOPS;
	}
	public void setTotalSystemIOPS( String iops ) {
		//logger.info( "----#### XIOData:setTotalSystemIOPS ####----" );
		this.totalSystemIOPS = iops;
	}
	public String getTotalIODensity() {
		//logger.info( "----#### XIOData:getTotalIODensity ####----" );
		return this.totalIODensity;
	}
	public void setTotalIODensity( String density ) {
		//logger.info( "----#### XIOData:setTotalIODensity ####----" );
		this.totalIODensity = density;
	}
	public String getTotalCacheHitPercent() {
		return this.totalCacheHitPercent;
	}
	public void setTotalCacheHitPercent( String percent ) {
		this.totalCacheHitPercent = percent;
	}
	public String getTotalCapacity() {
		//logger.info( "----#### XIOData:getTotalCapacity ####----" );
		return this.totalCapacity;
	}
	public void setTotalCapacity( String capacity ) {
		//logger.info( "----#### XIOData:setTotalCapacity ####----" );
		this.totalCapacity = capacity;
	}
	public String getTotalLunUsage() {
		//logger.info( "----#### XIOData:getTotalLunUsage ####----" );
		return this.totalLunUsage;
	}
	public void setTotalLunUsage( String usage ) {
		//logger.info( "----#### XIOData:setTotalLunUsage ####----" );
		this.totalLunUsage = usage;
	}
	public String getLunRaid1Count() { 
		//logger.info( "----#### XIOData:getLunRaid1Count ####----" );
		return this.lunRaid1Count;
	}
	public void setLunRaid1Count( String count ) {
		//logger.info( "----#### XIOData:setLunRaid1Count ####----" );
		this.lunRaid1Count = count;
	}
	public String getLunRaid5Count() { 
		//logger.info( "----#### XIOData:getLunRaid5Count ####----" );
		return this.lunRaid5Count;
	}
	public void setLunRaid5Count( String count ) {
		//logger.info( "----#### XIOData:setLunRaid5Count ####----" );
		this.lunRaid5Count = count;
	}
	public String getPerDiskReadLatency() { 
		//logger.info( "----#### XIOData:getPerDiskReadLatency ####----" );
		return this.perDiskReadLatency;
	}
	public void setPerDiskReadLatency( String count ) {
		//logger.info( "----#### XIOData:setPerDiskReadLatency ####----" );
		this.perDiskReadLatency = count;
	}
	public String getPerDiskWriteLatency() { 
		//logger.info( "----#### XIOData:getPerDiskWriteLatency ####----" );
		return this.perDiskWriteLatency;
	}
	public void setPerDiskWriteLatency( String count ) {
		//logger.info( "----#### XIOData:setPerDiskWriteLatency ####----" );
		this.perDiskWriteLatency = count;
	}
	public String getPerDiskReadKbps() { 
		//logger.info( "----#### XIOData:getPerDiskReadKbps ####----" );
		return this.perDiskReadKBPS;
	}
	public void setPerDiskReadKbps( String count ) {
		//logger.info( "----#### XIOData:setPerDiskReadKbps ####----" );
		this.perDiskReadKBPS = count;
	}
	public String getPerDiskWriteKbps() { 
		//logger.info( "----#### XIOData:getPerDiskWriteKbps ####----" );
		return this.perDiskWriteKBPS;
	}
	public void setPerDiskWriteKbps( String count ) {
		//logger.info( "----#### XIOData:setPerDiskWriteKbps ####----" );
		this.perDiskWriteKBPS = count;
	}
	public String getPerDiskReadIops() { 
		//logger.info( "----#### XIOData:getPerDiskReadIops ####----" );
		return this.perDiskReadIOPS;
	}
	public void setPerDiskReadIops( String count ) {
		//logger.info( "----#### XIOData:setPerDiskReadIops ####----" );
		this.perDiskReadIOPS = count;
	}
	public String getPerDiskWriteIops() { 
		//logger.info( "----#### XIOData:getPerDiskWriteIops ####----" );
		return this.perDiskWriteIOPS;
	}
	public void setPerDiskWriteIops( String count ) {
		//logger.info( "----#### XIOData:setPerDiskWriteIops ####----" );
		this.perDiskWriteIOPS = count;
	}
	public String getPerTotalIops() { 
		//logger.info( "----#### XIOData:getPerTotalIops ####----" );
		return this.totalIOPS;
	}
	public void setPerTotalIops( String count ) {
		//logger.info( "----#### XIOData:setPerTotalIops ####----" );
		this.totalIOPS = count;
	}
	public String getPerTotalKbps() { 
		//logger.info( "----#### XIOData:getPerTotalKbps ####----" );
		return this.totalKBPS;
	}
	public void setPerTotalKbps( String count ) {
		//logger.info( "----#### XIOData:setPerTotalKbps ####----" );
		this.totalKBPS = count;
	}
	public String getTotalReadKbps() {
		//logger.info( "----#### XIOData:getTotalReadKbps ####----" );
		return this.totalReadKbps;
	}
	public void setTotalReadKbps( String count ) {
		//logger.info( "----#### XIOData:getTotalReadKbps ####----" );
		this.totalReadKbps = count;
	}
	public String getTotalWriteKbps() {
		//logger.info( "----#### XIOData:getTotalWriteKbps ####----" );
		return this.totalWriteKbps;
	}
	public void setTotalWriteKbps( String count ) {
		//logger.info( "----#### XIOData:setTotalWriteKbps ####----" );
		this.totalWriteKbps = count;
	}
	public String getTotalReadIops() {
		//logger.info( "----#### XIOData:getTotalReadIops ####----" );
		return this.totalReadIops;
	}
	public void setTotalReadIops( String count ) {
		//logger.info( "----#### XIOData:setTotalReadIops ####----" );
		this.totalReadIops = count;
	}public String getTotalWriteIops() {
		//logger.info( "----#### XIOData:getTotalWriteIops ####----" );
		return this.totalWriteIops;
	}
	public void setTotalWriteIops( String count ) {
		//logger.info( "----#### XIOData:setTotalWriteIops ####----" );
		this.totalWriteIops = count;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOData:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOData:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOData:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
