package com.cloupia.feature.xio.reports.device.controller;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.model.XIOController;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaEasyReportWithActions;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;

public class XIODeviceControllerReport extends CloupiaEasyReportWithActions {
	private static Logger logger = Logger.getLogger( XIODeviceControllerReport.class );
	private static final String NAME = "xio.device.controller.report";
	private static final String LABEL = "Controllers";

	public XIODeviceControllerReport() {
		super( NAME, LABEL, XIOController.class );
		logger.info( "----#### XIODeviceControllerReport:XIODeviceControllerReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		//logger.info( "----#### XIODeviceControllerReport:getActions ####----" );
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIODeviceControllerReport:getImplementationClass ####----" );
		return XIODeviceControllerReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIODeviceControllerReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIODeviceControllerReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIODeviceControllerReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIODeviceControllerReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		//logger.info( "----#### XIODeviceControllerReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODeviceControllerReport:getMapRules ####----" );
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		logger.info("################ = " + context );
		logger.info("################ REPORT CONTEXT: CONTEXT ID = " + context.getId());
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( context.getId() );
		rule.setContextType( context.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;

		logger.info( "#########################XIODeviceSummaryReport:getMapRules###############################" );
		logger.info( "####---- ID = " + context.getId() + "----####");
		logger.info( "####---- TYPE = " + context.getType() + "----####");
		logger.info( "####---- NAME = " + context.getName() + "----####");
		logger.info( "####---- TOSTRING = " + context.toString() + "----####");
		logger.info( "#########################XIODeviceSummaryReport:getMapRules###############################" );
		
		return rules;
	}
}
