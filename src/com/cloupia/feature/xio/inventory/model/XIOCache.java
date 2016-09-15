package com.cloupia.feature.xio.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOCache  implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOFCNetwork.class );
	@Persistent
	private String accountName;
	@Persistent
	private String name;
	@Persistent
	private String totaliops;
	@Persistent
	private String readiops;
	@Persistent
	private String writeiops;
	@Persistent
	private String totalkbps;
	@Persistent
	private String readkbps;
	@Persistent
	private String writekbps;
	@Persistent
	private String readpercent;
	@Persistent
	private String averageframesize;
	@Persistent
	private String queuedepth;
	@Persistent
	private String readlatency;
	@Persistent
	private String writelatency;

	public XIOCache() {
		//logger.info( "----#### XIOCache:XIOCache ####----" );
	}
	public XIOCache( String accountName, String name, String totaliops, String readiops, String writeiops, String totalkbps, String readkbps, String writekbps, String readpercent, String averageframesize, String queuedepth, String readlatency, String writelatency ) {
		//logger.info( "----#### XIOCache:XIOCache ####----" );
		this.accountName = accountName;
		this.name = name;
		this.totaliops = totaliops;
		this.readiops = readiops;
		this.writeiops = writeiops;
		this.totalkbps = totalkbps;
		this.readkbps = readkbps;
		this.writekbps = writekbps;
		this.readpercent = readpercent;
		this.averageframesize = averageframesize;
		this.queuedepth = queuedepth;
		this.readlatency = readlatency;
		this.writelatency = writelatency;
	}
	public String getName() {
		//logger.info( "----#### XIOCache:getName ####----" );
		return this.name;
	}
	public void setName( String name ) {
		//logger.info( "----#### XIOCache:setName ####----" );
		this.name = name;
	}
	public String getTotalIops() {
		//logger.info( "----#### XIOCache:getTotalIops ####----" );
		return this.totaliops;
	}
	public void setTotalIops( String iops ) {
		//logger.info( "----#### XIOCache:setTotalIops ####----" );
		this.totaliops = iops;
	}
	public String getReadIops() {
		//logger.info( "----#### XIOCache:getReadIops ####----" );
		return this.readiops;
	}
	public void setReadIops( String iops ) {
		//logger.info( "----#### XIOCache:setReadIops ####----" );
		this.readiops = iops;
	}
	public String getWriteIops() {
		//logger.info( "----#### XIOCache:getWriteIops ####----" );
		return this.writeiops;
	}
	public void setWriteIops( String iops ) {
		//logger.info( "----#### XIOCache:setWriteIops ####----" );
		this.writeiops = iops;
	}
	public String getTotalKbps() {
		//logger.info( "----#### XIOCache:getTotalKbps ####----" );
		return this.totalkbps;
	}
	public void setTotalKbps( String kbps ) {
		//logger.info( "----#### XIOCache:setTotalKbps ####----" );
		this.totalkbps = kbps;
	}
	public String getReadKbps() {
		//logger.info( "----#### XIOCache:getReadKbps ####----" );
		return this.readkbps;
	}
	public void setReadKbps( String kbps ) {
		//logger.info( "----#### XIOCache:setReadKbps ####----" );
		this.readkbps = kbps;
	}
	public String getWriteKbps() {
		//logger.info( "----#### XIOCache:getWriteKbps ####----" );
		return this.writekbps;
	}
	public void setWriteKbps( String kbps ) {
		//logger.info( "----#### XIOCache:setWriteKbps ####----" );
		this.writekbps = kbps;
	}
	public String getReadPercent() {
		//logger.info( "----#### XIOCache:getReadPercent ####----" );
		return this.readpercent;
	}
	public void setReadPercent( String read ) {
		//logger.info( "----#### XIOCache:setReadPercent ####----" );
		this.readpercent = read;
	}
	public String getAverageFrameSize() {
		//logger.info( "----#### XIOCache:getAverageFrameSize ####----" );
		return this.averageframesize;
	}
	public void setAverageFrameSize( String size ) {
		//logger.info( "----#### XIOCache:setAverageFrameSize ####----" );
		this.averageframesize = size;
	}
	public String getQueueDepth() {
		//logger.info( "----#### XIOCache:getQueueDepth ####----" );
		return this.queuedepth;
	}
	public void setQueueDepth( String queue ) {
		//logger.info( "----#### XIOCache:setQueueDepth ####----" );
		this.queuedepth = queue;
	}
	public String getReadLatency() {
		//logger.info( "----#### XIOCache:getReadLatency ####----" );
		return this.readlatency;
	}
	public void setReadLatency( String latency ) {
		//logger.info( "----#### XIOCache:setReadLatency ####----" );
		this.readlatency = latency;
	}
	public String getWriteLatency() {
		//logger.info( "----#### XIOCache:getWriteLatency ####----" );
		return this.writelatency;
	}
	public void setWriteLatency( String latency ) {
		//logger.info( "----#### XIOCache:setWriteLatency ####----" );
		this.writelatency = latency;
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
