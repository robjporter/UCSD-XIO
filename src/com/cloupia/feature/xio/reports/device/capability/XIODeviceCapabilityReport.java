package com.cloupia.feature.xio.reports.device.capability;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class XIODeviceCapabilityReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( XIODeviceCapabilityReport.class );
	private static final String NAME = "xio.device.capability.report";
	private static final String LABEL = "Capabilities";

	public XIODeviceCapabilityReport() {
		super();
		logger.info( "----#### XIODeviceCapabilityReport:XIODeviceCapabilityReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		//logger.info( "----#### XIODeviceCapabilityReport:getActions ####----" );
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIODeviceCapabilityReport:getImplementationClass ####----" );
		return XIODeviceCapabilityReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIODeviceCapabilityReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIODeviceCapabilityReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIODeviceCapabilityReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIODeviceCapabilityReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		//logger.info( "----#### XIODeviceCapabilityReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODeviceCapabilityReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
