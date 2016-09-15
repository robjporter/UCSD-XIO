package com.cloupia.feature.xio.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOPowerSupply implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOPowerSupply.class );
	@Persistent
	private String accountName;
	@ReportField( label = "PowerSupply" )
	@Persistent
	private String id;
	@ReportField( label = "Status" )
	@Persistent
	private String status;
	@ReportField( label = "Serial Number" )
	@Persistent
	private String serialnumber;
	@ReportField( label = "Model" )
	@Persistent
	private String model;
	@Persistent
	private String partnumber;
	@ReportField( label = "Hardware Revision" )
	@Persistent
	private String hwversion;
	@ReportField( label = "Temperature" )
	@Persistent
	private String temperature;
	@Persistent
	private String scale;
	@Persistent
	private String led;
	@ReportField( label = "Manufacture Date" )
	@Persistent
	private String manufdate;
	@Persistent
	private String fan1status;
	@ReportField( label = "Fan 1 Speed" )
	@Persistent
	private String fan1rpm;
	@Persistent
	private String fan2status;
	@ReportField( label = "Fan 2 Speed" )
	@Persistent
	private String fan2rpm;
	
	public XIOPowerSupply() {
		//logger.info( "----#### XIOPowerSupply:XIOPowerSupply ####----" );
	}
	public XIOPowerSupply( String accountName, String id, String status, String serialnumber, String model, String partnumber, String hwversion, String temperature, String scale, String led, String manufdate, String fan1status, String fan1rpm, String fan2status, String fan2rpm ) {
		//logger.info( "----#### XIOPowerSupply:XIOPowerSupply ####----" );
		this.accountName = accountName;
		this.id = id;
		this.status = status;
		this.serialnumber = serialnumber;
		this.model = model;
		this.partnumber = partnumber;
		this.hwversion = hwversion;
		this.temperature = temperature;
		this.scale = scale;
		this.led = led;
		this.manufdate = manufdate;
		this.fan1status = fan1status;
		this.fan1rpm = fan1rpm;
		this.fan2status = fan2status;
		this.fan2rpm = fan2rpm;
	}
	public String getID() {
		//logger.info( "----#### XIOPowerSupply:getID ####----" );
		return this.id;
	}
	public void setID( String id ) {
		//logger.info( "----#### XIOPowerSupply:getID ####----" );
		this.id = id;
	}
	public String getStatus() {
		//logger.info( "----#### XIOPowerSupply:getStatus ####----" );
		return this.status;
	}
	public void setStatus( String status ) {
		//logger.info( "----#### XIOPowerSupply:getStatus ####----" );
		this.status = status;
	}
	public String getSerialNumber() {
		//logger.info( "----#### XIOPowerSupply:getSerialNumber ####----" );
		return this.serialnumber;
	}
	public void setSerialNumber( String serialnumber ) {
		//logger.info( "----#### XIOPowerSupply:getSerialNumber ####----" );
		this.serialnumber = serialnumber;
	}
	public String getModel() {
		//logger.info( "----#### XIOPowerSupply:getModel ####----" );
		return this.model;
	}
	public void setModel( String model ) {
		//logger.info( "----#### XIOPowerSupply:getModel ####----" );
		this.model = model;
	}
	public String getPartNumber() {
		//logger.info( "----#### XIOPowerSupply:getPartNumber ####----" );
		return this.partnumber;
	}
	public void setPartNumber( String partnumber ) {
		//logger.info( "----#### XIOPowerSupply:getPartNumber ####----" );
		this.partnumber = partnumber;
	}
	public String getHWVersion() {
		//logger.info( "----#### XIOPowerSupply:getHWVersion ####----" );
		return this.hwversion;
	}
	public void setHWVersion( String hwversion ) {
		//logger.info( "----#### XIOPowerSupply:getHWVersion ####----" );
		this.hwversion = hwversion;
	}
	public String getTemperature() {
		//logger.info( "----#### XIOPowerSupply:getTemperature ####----" );
		return this.temperature;
	}
	public void setTemperature( String temperature ) {
		//logger.info( "----#### XIOPowerSupply:getTemperature ####----" );
		this.temperature = temperature;
	}
	public String getScale() {
		//logger.info( "----#### XIOPowerSupply:getScale ####----" );
		return this.scale;
	}
	public void setScale( String scale ) {
		//logger.info( "----#### XIOPowerSupply:getScale ####----" );
		this.scale = scale;
	}
	public String getLED() {
		//logger.info( "----#### XIOPowerSupply:getLED ####----" );
		return this.led;
	}
	public void setLED( String led ) {
		//logger.info( "----#### XIOPowerSupply:getLED ####----" );
		this.led = led;
	}
	public String getManufactureDate() {
		//logger.info( "----#### XIOPowerSupply:getManufactureDate ####----" );
		return this.manufdate;
	}
	public void setManufactureDate( String manufdate ) {
		//logger.info( "----#### XIOPowerSupply:getManufactureDate ####----" );
		this.manufdate = manufdate;
	}
	public String getFan1Status() {
		//logger.info( "----#### XIOPowerSupply:getFan1Status ####----" );
		return this.fan1status;
	}
	public void setFan1Status( String fan1status ) {
		//logger.info( "----#### XIOPowerSupply:getFan1Status ####----" );
		this.fan1status = fan1status;
	}
	public String getFan1RPM() {
		//logger.info( "----#### XIOPowerSupply:getFan1RPM ####----" );
		return this.fan1rpm;
	}
	public void setFan1RPM( String fan1rpm ) {
		//logger.info( "----#### XIOPowerSupply:getFan1RPM ####----" );
		this.fan1rpm = fan1rpm;
	}
	public String getFan2Status() {
		//logger.info( "----#### XIOPowerSupply:getFan2Status ####----" );
		return this.fan2status;
	}
	public void setFan2Status( String fan2status ) {
		//logger.info( "----#### XIOPowerSupply:getFan2Status ####----" );
		this.fan2status = fan2status;
	}
	public String getFan2RPM() {
		//logger.info( "----#### XIOPowerSupply:getFan2RPM ####----" );
		return this.fan2rpm;
	}
	public void setFan2RPM( String fan2rpm ) {
		//logger.info( "----#### XIOPowerSupply:getFan2RPM ####----" );
		this.fan2rpm = fan2rpm;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOPowerSupply:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOPowerSupply:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOPowerSupply:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
