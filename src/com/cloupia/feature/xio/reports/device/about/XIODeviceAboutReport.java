package com.cloupia.feature.xio.reports.device.about;

import org.apache.log4j.Logger;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

public class XIODeviceAboutReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( XIODeviceAboutReport.class );
	private static final String NAME = "xio.device.about.report";
	private static final String LABEL = "About";

	public XIODeviceAboutReport() {
		super();
		logger.info( "----#### XIODeviceAboutReport:XIODeviceAboutReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		return XIODeviceAboutReportAction.class;
	}
	@Override
	public String getReportLabel() {
		return LABEL;
	}
	@Override
	public String getReportName() {
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		return true;
	}
	@Override
	public boolean isLeafReport() {
		return true;
	}
	@Override
	public int getReportType() {
		return ReportDefinition.REPORT_TYPE_CONFIG_FORM;
	}
	@Override
	public boolean isManagementReport() {
		return true;
	}
}