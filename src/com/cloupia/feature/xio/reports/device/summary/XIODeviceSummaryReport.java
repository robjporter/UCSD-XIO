package com.cloupia.feature.xio.reports.device.summary;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

public class XIODeviceSummaryReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( XIODeviceSummaryReport.class );
	private static final String NAME = "xio.device.summary.report";
	private static final String LABEL = "Device Summary";

	public XIODeviceSummaryReport() {
		super();
		logger.info( "----#### XIODeviceSummayReport:XIODeviceSummaryReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIODeviceSummayReport:getImplementationClass ####----" );
		return XIODeviceSummaryReportImpl.class;
	}
	@Override
	public int getMenuID() {
		return 51;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIODeviceSummayReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIODeviceSummayReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIODeviceSummayReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIODeviceSummayReport:isLeafReport ####----" );
		return true;
	}
	@Override
	public int getReportType() {
		//logger.info( "----#### XIODeviceSummayReport:getReportType ####----" );
		return ReportDefinition.REPORT_TYPE_SUMMARY;
	}
	@Override
	public int getReportHint() {
		//logger.info( "----#### XIODeviceSummayReport:getReportHint ####----" );
		return ReportDefinition.REPORT_HINT_VERTICAL_TABLE_WITH_GRAPHS;
	}
	@Override
	public boolean isManagementReport() {
		//logger.info( "----#### XIODeviceSummayReport:isManagementReport ####----" );
		return true;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODeviceSummaryReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;

		logger.info( "#########################XIODeviceSummaryReport:getMapRules###############################" );
		logger.info( "####---- ID = " + dummyContextOneType.getId() + "----####");
		logger.info( "####---- TYPE = " + dummyContextOneType.getType() + "----####");
		logger.info( "####---- NAME = " + dummyContextOneType.getName() + "----####");
		logger.info( "####---- TOSTRING = " + dummyContextOneType.toString() + "----####");
		logger.info( "#########################XIODeviceSummaryReport:getMapRules###############################" );
		
		return rules;
	}
}
