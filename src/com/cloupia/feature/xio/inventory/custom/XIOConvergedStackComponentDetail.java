package com.cloupia.feature.xio.inventory.custom;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

import com.cloupia.model.cIM.ConvergedStackComponentDetail;

public class XIOConvergedStackComponentDetail extends ConvergedStackComponentDetail {
	private static Logger logger = Logger.getLogger( XIOConvergedStackComponentDetail.class );
	private String serialNumber = "";
	private String buildVersion = "0.0.0";
	
	@Override
	public List<String> getComponentSummaryList() {
		logger.info( "----#### XIOConvergedStackComponentDetail:getComponentSummaryList ####----" );
		List<String> info = new ArrayList<String>();
		info.add( "Serial Number" + "," + this.getSerialNumber() );
		info.add( "Build" + "," + this.getBuildVersion() );
		return info;
	}
	public String getSerialNumber() {
		return this.serialNumber;
	}
	public void setSerialNumber( String serialNumber ) {
		this.serialNumber = serialNumber;
	}
	public void setBuildVersion( String build ) {
		this.buildVersion = build;
	}
	public String getBuildVersion() {
		return this.buildVersion;
	}
}
