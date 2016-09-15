package com.cloupia.feature.xio.reports.device.hosts;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.forms.actions.host.XIOAddNewHostFormAction;
import com.cloupia.feature.xio.forms.actions.host.XIODeleteHostFormAction;
import com.cloupia.feature.xio.forms.actions.host.XIOEditHostFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class XIODeviceHostReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( XIODeviceHostReport.class );
	private static final String NAME = "xio.device.host.report";
	private static final String LABEL = "Hosts";

	public XIODeviceHostReport() {
		super();
		logger.info( "----#### XIODeviceHostReport:XIODeviceHostReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		//logger.info( "----#### XIODeviceHostReport:getActions ####----" );
		XIOAddNewHostFormAction newHostAction = new XIOAddNewHostFormAction();
		XIOEditHostFormAction editHostAction = new XIOEditHostFormAction();
		XIODeleteHostFormAction deleteHostAction = new XIODeleteHostFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 3 ];
		actions[ 0 ] = newHostAction;
		actions[ 1 ] = editHostAction;
		actions[ 2 ] = deleteHostAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIODeviceHostReport:getImplementationClass ####----" );
		return XIODeviceHostReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIODeviceHostReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIODeviceHostReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIODeviceHostReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIODeviceHostReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		//logger.info( "----#### XIODeviceHostReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODeviceHostReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}