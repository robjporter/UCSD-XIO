package com.cloupia.feature.xio.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true" )
public class XIOMedia implements InventoryDBItemIf, ReportableIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOMedia.class );
	@Persistent
	private String accountName;
	@ReportField( label = "DataPac ID" )
	@Persistent
	private String id;
	@ReportField( label = "Status" )
	@Persistent
	private String status;
	@ReportField( label = "Tier" )
	@Persistent
	private String tier;
	@ReportField( label = "Serial Number")
	@Persistent
	private String serial;
	@ReportField( label = "Model" )
	@Persistent
	private String model;
	@Persistent
	private String part;
	@ReportField( label = "Firmware Version" )
	@Persistent
	private String fw;
	@ReportField( label = "Hardware Health" )
	@Persistent
	private String hwhealth;
	@ReportField( label = "Temperature" )
	@Persistent
	private String temperature;
	@Persistent
	private String scale;
	@ReportField( label = "Manufacture Date" )
	@Persistent
	private String manufdate;
	@Persistent
	private String led;
	@ReportField( label = "Hardware Version" )
	@Persistent
	private String hw;
	@ReportField( label = "Data Health" )
	@Persistent
	private String datahealth;
	@ReportField( label = "Data Healing" )
	@Persistent
	private String healing;
	@ReportField( label = "Data Description" )
	@Persistent
	private String healingstring;

	public XIOMedia() {
		//logger.info( "----#### XIOMedia:XIOMedia ####----" );
	}
	public XIOMedia( String accountName, String id, String status, String serial, String model, String part, String fw, String tier, String hwhealth, String temperature, String scale, String manufdate, String led, String hw, String datahealth, String healing, String healingstring ) {
		//logger.info( "----#### XIOMedia:XIOMedia ####----" );
		this.accountName = accountName;
		this.id = id;
		this.status = status;
		this.serial = serial;
		this.model = model;
		this.part = part;
		this.fw = fw;
		this.tier = tier;
		this.hwhealth = hwhealth;
		this.temperature = temperature;
		this.scale = scale;
		this.manufdate = manufdate;
		this.led = led;
		this.hw = hw;
		this.datahealth = datahealth;
		this.healing = healing;
		this.healingstring = healingstring;
	}
	public String getID() {
		//logger.info( "----#### XIOMedia:getID ####----" );
		return this.id;
	}
	public void setID( String id ) {
		//logger.info( "----#### XIOMedia:setID ####----" );
		this.id = id;
	}
	public String getStatus() {
		//logger.info( "----#### XIOMedia:getStatus ####----" );
		return this.status;
	}
	public void setStatus( String status ) {
		//logger.info( "----#### XIOMedia:setStatus ####----" );
		this.status = status;
	}
	public String getSerial() {
		//logger.info( "----#### XIOMedia:getSerial ####----" );
		return this.serial;
	}
	public void setSerial( String serial ) {
		//logger.info( "----#### XIOMedia:setSerial ####----" );
		this.serial = serial;
	}
	public String getModel() {
		//logger.info( "----#### XIOMedia:getModel ####----" );
		return this.model;
	}
	public void setModel( String model ) {
		//logger.info( "----#### XIOMedia:setModel ####----" );
		this.model = model;
	}
	public String getPart() {
		//logger.info( "----#### XIOMedia:getPart ####----" );
		return this.part;
	}
	public void setPart( String part ) {
		//logger.info( "----#### XIOMedia:setPart ####----" );
		this.part = part;
	}
	public String getFirmware() {
		//logger.info( "----#### XIOMedia:getFirmware ####----" );
		return this.fw;
	}
	public void setFirmware( String fw ) {
		//logger.info( "----#### XIOMedia:setFirmware ####----" );
		this.fw = fw;
	}
	public String getTier() {
		//logger.info( "----#### XIOMedia:getTier ####----" );
		return this.tier;
	}
	public void setTier( String tier ) {
		//logger.info( "----#### XIOMedia:setTier ####----" );
		this.tier = tier;
	}
	public String getHWHealth() {
		//logger.info( "----#### XIOMedia:getHWHealth ####----" );
		return this.hwhealth;
	}
	public void setHWHealth( String hwhealth ) {
		//logger.info( "----#### XIOMedia:setHWHealth ####----" );
		this.hwhealth = hwhealth;
	}
	public String getTemperature() {
		//logger.info( "----#### XIOMedia:getTemperature ####----" );
		return this.temperature;
	}
	public void setTemperature( String temperature ) {
		//logger.info( "----#### XIOMedia:setTemperature ####----" );
		this.temperature = temperature;
	}
	public String getScale() {
		//logger.info( "----#### XIOMedia:getScale ####----" );
		return this.scale;
	}
	public void setScale( String scale ) {
		//logger.info( "----#### XIOMedia:setScale ####----" );
		this.scale = scale;
	}
	public String getManufactureDate() {
		//logger.info( "----#### XIOMedia:getManufactureDate ####----" );
		return this.manufdate;
	}
	public void setManufactureDate( String manufdate ) {
		//logger.info( "----#### XIOMedia:setManufactureDate ####----" );
		this.manufdate = manufdate;
	}
	public String getLED() {
		//logger.info( "----#### XIOMedia:getLED ####----" );
		return this.led;
	}
	public void setLED( String led ) {
		//logger.info( "----#### XIOMedia:setLED ####----" );
		this.led = led;
	}
	public String getHW() {
		//logger.info( "----#### XIOMedia:getHW ####----" );
		return this.hw;
	}
	public void setHW( String hw ) {
		//logger.info( "----#### XIOMedia:setHW ####----" );
		this.hw = hw;
	}
	public String getDataHealth() {
		//logger.info( "----#### XIOMedia:getDataHealth ####----" );
		return this.datahealth;
	}
	public void setDataHealth( String datahealth ) {
		//logger.info( "----#### XIOMedia:setDataHealth ####----" );
		this.datahealth = datahealth;
	}
	public String getHealing() {
		//logger.info( "----#### XIOMedia:getHealing ####----" );
		return this.healing;
	}
	public void setHealing( String healing ) {
		//logger.info( "----#### XIOMedia:setHealing ####----" );
		this.healing = healing;
	}
	public String getHealingString() {
		//logger.info( "----#### XIOMedia:getHealing ####----" );
		return this.healingstring;
	}
	public void setHealingString( String healingstring ) {
		//logger.info( "----#### XIOMedia:setHealingString ####----" );
		this.healingstring = healingstring;
	}
	@Override
	public String getAccountName() {
		//logger.info( "----#### XIOMedia:getAccountName ####----" );
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		//logger.info( "----#### XIOMedia:setAccountName ####----" );
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		//logger.info( "----#### XIOMedia:getInstanceQuery ####----" );
		return "accountName == '" + this.accountName + "'";
	}
}
