package com.cloupia.feature.xio.reports.device.psu;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class XIODevicePowerSupplyReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( XIODevicePowerSupplyReport.class );
	private static final String NAME = "xio.device.power.supply.report";
	private static final String LABEL = "Power Supply";

	public XIODevicePowerSupplyReport() {
		super();
		logger.info( "----#### XIODevicePowerSupplyReport:XIODeviceLunReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		//logger.info( "----#### XIODevicePowerSupplyReport:getActions ####----" );
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIODevicePowerSupplyReport:getImplementationClass ####----" );
		return XIODevicePowerSupplyReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIODevicePowerSupplyReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIODevicePowerSupplyReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIODevicePowerSupplyReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIODevicePowerSupplyReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		//logger.info( "----#### XIODevicePowerSupplyReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODevicePowerSupplyReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
