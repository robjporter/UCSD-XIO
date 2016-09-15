package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOPool  implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOFCNetwork.class );
	@Persistent
	private String accountName;
	@Persistent
	private String index;
	@Persistent
	private String name;
	@Persistent
	private String hddios;
	@Persistent
	private String sddios;
	@Persistent
	private String ssdpercentused;
	@Persistent
	private String usedsize;
	@Persistent
	private String freesize;
	
	public XIOPool() {
		//logger.info( "----#### XIOPool:XIOPool ####----" );
	}
	public XIOPool( String accountName, String name, String hddios, String sddios, String ssdpercentused, String usedsize, String freesize ) {
		//logger.info( "----#### XIOPool:XIOPool ####----" );
		this.accountName = accountName;
		this.name = name;
		this.hddios = hddios;
		this.sddios = sddios;
		this.ssdpercentused = ssdpercentused;
		this.usedsize = usedsize;
		this.freesize = freesize;
	}
	public String getIndex() {
		//logger.info( "----#### XIOPool:getIndex ####----" );
		return this.index;
	}
	public void setIndex( String index ) {
		//logger.info( "----#### XIOPool:setIndex ####----" );
		this.index = index;
	}
	public String getName() {
		//logger.info( "----#### XIOPool:getName ####----" );
		return this.name;
	}
	public void setName( String name ) {
		//logger.info( "----#### XIOPool:setName ####----" );
		this.name = name;
	}
	public String getHddIos() {
		//logger.info( "----#### XIOPool:getHddIos ####----" );
		return this.hddios;
	}
	public void setHddIos( String hddios ) {
		//logger.info( "----#### XIOPool:setHddIos ####----" );
		this.hddios = hddios;
	}
	public String getSddIos() {
		//logger.info( "----#### XIOPool:getSddIos ####----" );
		return this.sddios;
	}
	public void setSddIos( String sddios ) {
		//logger.info( "----#### XIOPool:setSddIos ####----" );
		this.sddios = sddios;
	}
	public String getSddPercentUsed() {
		//logger.info( "----#### XIOPool:getSddPercentUsed ####----" );
		return this.ssdpercentused;
	}
	public void setSddPercentUsed( String ssdpercentused ) {
		//logger.info( "----#### XIOPool:setSddPercentUsed ####----" );
		this.ssdpercentused = ssdpercentused;
	}
	public String getUsedSize() {
		//logger.info( "----#### XIOPool:getUsedSize ####----" );
		return this.usedsize;
	}
	public void setUsedSize( String usedsize ) {
		//logger.info( "----#### XIOPool:setUsedSize ####----" );
		this.usedsize = usedsize;
	}
	public String getFreeSize() {
		//logger.info( "----#### XIOPool:getFreeSize ####----" );
		return this.freesize;
	}
	public void setFreeSize( String freesize ) {
		//logger.info( "----#### XIOPool:setFreeSize ####----" );
		this.freesize = freesize;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOPool:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOPool:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOPool:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
