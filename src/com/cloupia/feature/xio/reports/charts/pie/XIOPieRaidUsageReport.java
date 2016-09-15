package com.cloupia.feature.xio.reports.charts.pie;

import org.apache.log4j.Logger;
import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

public class XIOPieRaidUsageReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( XIOPieReportDiskUsage.class );
	private static final String NAME = "xio.pie.report.raid.usage";
	private static final String LABEL = "Raid Usage Chart";

	public XIOPieRaidUsageReport() {
		super();
		this.setMgmtColumnIndex( 0 );
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIOPieReportDiskUsage:getImplementationClass ####----" );
		return XIOPieRaidUsageReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIOPieReportDiskUsage:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIOPieReportDiskUsage:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIOPieReportDiskUsage:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIOPieReportDiskUsage:isLeafReport ####----" );
		return true;
	}
	@Override
	public int getReportType() {
		//logger.info( "----#### XIOPieReportDiskUsage:getReportType ####----" );
		return ReportDefinition.REPORT_TYPE_SNAPSHOT;
	}
	@Override
	public int getReportHint() {
		//logger.info( "----#### XIOPieReportDiskUsage:getReportHint ####----" );
		return ReportDefinition.REPORT_HINT_PIECHART;
	}
	@Override
	public boolean showInSummary() {
		return true;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODeviceLunReport:getMsapRules ####----" );
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		logger.info("################ REPORT CONTEXT: " + context );
		logger.info("################ REPORT CONTEXT: " + context.getId());
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( context.getId() );
		rule.setContextType( context.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
	@Override
	public int getContextLevel() {
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		logger.info( "Context " + context.getId() + " " + context.getType());
		return context.getType();
	}
}