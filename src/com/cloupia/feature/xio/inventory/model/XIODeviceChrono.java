package com.cloupia.feature.xio.inventory.model;

import org.apache.log4j.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIODeviceChrono implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIODrive.class );
	@Persistent
	private String accountName;
	@Persistent
	private String scale;
	@Persistent
	private String date;
	@Persistent
	private String time;
	@Persistent
	private String timezone;
	@Persistent
	private String dst;
	@Persistent
	private String days;
	@Persistent
	private String hours;
	@Persistent
	private String mins;
	@Persistent
	private String secs;
	@Persistent
	private String ntp;
	@Persistent
	private String ntpServer;
	
	public XIODeviceChrono() {
		//logger.info( "----#### XIODeviceChrono:XIODeviceChrono ####----" );
	}
	public XIODeviceChrono( String accountName, String scale, String date, String time, String timezone, String dst, String days, String hours, String mins, String secs, String ntp, String ntpServer ) {
		//logger.info( "----#### XIODeviceChrono:XIODeviceChrono ####----" );
		this.accountName = accountName;
		this.scale = scale;
		this.date = date;
		this.time = time;
		this.timezone = timezone;
		this.dst = dst;
		this.days = days;
		this.hours = hours;
		this.mins = mins;
		this.secs = secs;
		this.ntp = ntp;
		this.ntpServer = ntpServer;
	}
	public String getScale() {
		//logger.info( "----#### XIODeviceChrono:getScale ####----" );
		return this.scale;
	}
	public void setScale( String scale ) {
		//logger.info( "----#### XIODeviceChrono:setScale ####----" );
		this.scale = scale;
	}
	public String getDate() {
		//logger.info( "----#### XIODeviceChrono:getDate ####----" );
		return this.date;
	}
	public void setDate( String date ) {
		//logger.info( "----#### XIODeviceChrono:setDate ####----" );
		this.date = date;
	}
	public String getTime() {
		//logger.info( "----#### XIODeviceChrono:getTime ####----" );
		return this.time;
	}
	public void setTime( String time ) {
		//logger.info( "----#### XIODeviceChrono:setTime ####----" );
		this.time = time;
	}
	public String getTimezone() {
		//logger.info( "----#### XIODeviceChrono:getTimezone ####----" );
		return this.timezone;
	}
	public void setTimezone( String timezone ) {
		//logger.info( "----#### XIODeviceChrono:setTimezone ####----" );
		this.timezone = timezone;
	}
	public String getDST() {
		//logger.info( "----#### XIODeviceChrono:getDST ####----" );
		return this.dst;
	}
	public void setDST( String dst ) {
		//logger.info( "----#### XIODeviceChrono:setDST ####----" );
		this.dst = dst;
	}
	public String getDays() {
		//logger.info( "----#### XIODeviceChrono:getDays ####----" );
		return this.days;
	}
	public void setDays( String days ) {
		//logger.info( "----#### XIODeviceChrono:setDays ####----" );
		this.days = days;
	}
	public String getHours() {
		//logger.info( "----#### XIODeviceChrono:getHours ####----" );
		return this.hours;
	}
	public void setHours( String hours ) {
		//logger.info( "----#### XIODeviceChrono:setHours ####----" );
		this.hours = hours;
	}
	public String getMins() {
		//logger.info( "----#### XIODeviceChrono:getMins ####----" );
		return this.mins;
	}
	public void setMins( String mins ) {
		//logger.info( "----#### XIODeviceChrono:setMins ####----" );
		this.mins = mins;
	}
	public String getSecs() {
		//logger.info( "----#### XIODeviceChrono:getSecs ####----" );
		return this.secs;
	}
	public void setSecs( String secs ) {
		//logger.info( "----#### XIODeviceChrono:setSecs ####----" );
		this.secs = secs;
	}
	public String getNTP() {
		//logger.info( "----#### XIODeviceChrono:getNTP ####----" );
		return this.ntp;
	}
	public void setNTP( String ntp ) {
		//logger.info( "----#### XIODeviceChrono:setNTP ####----" );
		this.ntp = ntp;
	}
	public String getNTPServer() {
		//logger.info( "----#### XIODeviceChrono:getNTPServer ####----" );
		return this.ntpServer;
	}
	public void setNTPServer( String ntpServer ) {
		//logger.info( "----#### XIODeviceChrono:setNTPServer ####----" );
		this.ntpServer = ntpServer;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIODeviceChrono:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIODeviceChrono:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIODeviceChrono:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}