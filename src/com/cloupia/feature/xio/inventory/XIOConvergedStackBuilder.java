package com.cloupia.feature.xio.inventory;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.device.functions.XIOFunctions;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.service.cIM.inframgr.reports.contextresolve.AbstractConvergedStackComponentBuilder;

public class XIOConvergedStackBuilder extends AbstractConvergedStackComponentBuilder {
	private static Logger logger = Logger.getLogger( XIOConvergedStackBuilder.class );

	public XIOConvergedStackBuilder() {
		super( XIOConstants.XIO_ACCOUNT_TYPE, InfraAccountTypes.CAT_STORAGE );
		logger.info( "----#### XIOConvergedStackBuilder:XIOConvergedStackBuilder ####----" );
	}
	public String getSerialNumber() {
		logger.info( "----#### XIOConvergedStackBuilder:getSerialNumber ####----" );
		return "SERIAL NUMBER";
	}
	@Override
	public String getModel() {
		logger.info( "----#### XIOConvergedStackBuilder:getModel ####----" );
		String ret = XIOConstants.XIO_DEFAULT_MODEL;
		String accountName = this.getIdentifier();
		try {
			if (accountName != null) {
				XIOFunctions funcs = new XIOFunctions();
				ret = funcs.getDeviceModel( accountName );
			} 
		} catch( Exception e ) {
			logger.info( "----#### XIOConvergedStackBuilder:getModel ####---- ERROR - ", e );
		}
		return ret;
	}
	@Override
	public String getOSVersion() {
		logger.info( "----#### XIOConvergedStackBuilder:getOSVersion ####----" );
		String ret = XIOConstants.XIO_DEFAULT_VERSION;
		String accountName = this.getIdentifier();
		try {
			if (accountName != null) {
				XIOFunctions funcs = new XIOFunctions();
				ret = funcs.getDeviceVersion( accountName );
			} 
		} catch( Exception e ) {
			logger.info( "----#### XIOConvergedStackBuilder:getOSVersion ####---- ERROR - ", e );
		}
		return ret;
	}
	@Override
	public String getVendor() {
		logger.info( "----#### XIOConvergedStackBuilder:getVendor ####----" );
		return XIOConstants.XIO_VENDOR_NAME;
	}
	@Override
	public String getVendorImageName() {
		logger.info( "----#### XIOConvergedStackBuilder:getVendorImageName ####----" );
		return XIOConstants.XIO_VENDOR_IMAGE;
	}
}
