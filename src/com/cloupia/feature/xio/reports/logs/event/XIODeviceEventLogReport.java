package com.cloupia.feature.xio.reports.logs.event;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class XIODeviceEventLogReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( XIODeviceEventLogReport.class );
	private static final String NAME = "xio.device.log.event.report";
	private static final String LABEL = "Event Log";

	public XIODeviceEventLogReport() {
		super();
		logger.info( "----#### XIODeviceEventLogReport:XIODeviceHostReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		//logger.info( "----#### XIODeviceEventLogReport:getActions ####----" );
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIODeviceEventLogReport:getImplementationClass ####----" );
		return XIODeviceEventLogReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIODeviceEventLogReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIODeviceEventLogReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIODeviceEventLogReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIODeviceEventLogReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		//logger.info( "----#### XIODeviceEventLogReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODeviceEventLogReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}