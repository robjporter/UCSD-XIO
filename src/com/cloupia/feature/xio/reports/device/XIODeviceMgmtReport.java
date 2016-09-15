package com.cloupia.feature.xio.reports.device;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.reports.charts.pie.XIOPieRaidUsageReport;
import com.cloupia.feature.xio.reports.charts.pie.XIOPieReadWriteLatencyReport;
import com.cloupia.feature.xio.reports.charts.pie.XIOPieReportDiskUsage;
import com.cloupia.feature.xio.reports.device.about.XIODeviceAboutReport;
import com.cloupia.feature.xio.reports.device.capability.XIODeviceCapabilityReport;
import com.cloupia.feature.xio.reports.device.controller.XIODeviceControllerReport;
import com.cloupia.feature.xio.reports.device.datapac.XIODeviceDataPacReport;
import com.cloupia.feature.xio.reports.device.drive.XIODeviceDriveReport;
import com.cloupia.feature.xio.reports.device.fcnetwork.XIODeviceFCInterfaceReport;
import com.cloupia.feature.xio.reports.device.hosts.XIODeviceHostReport;
import com.cloupia.feature.xio.reports.device.lun.XIODeviceLunReport;
import com.cloupia.feature.xio.reports.device.network.XIODeviceEInterfaceReport;
import com.cloupia.feature.xio.reports.device.psu.XIODevicePowerSupplyReport;
import com.cloupia.feature.xio.reports.device.summary.XIODeviceSummaryReport;
import com.cloupia.feature.xio.reports.logs.event.XIODeviceEventLogReport;
import com.cloupia.feature.xio.reports.mappings.XIODeviceMappingReport;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.service.cIM.inframgr.collector.impl.GenericInfraAccountReport;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReport;

public class XIODeviceMgmtReport extends GenericInfraAccountReport {
	private static Logger logger = Logger.getLogger( XIODeviceMgmtReport.class );
	private static String NAME = "XIOAccount";
	private CloupiaReport[] ddReports = new CloupiaReport[] {
			new XIODeviceSummaryReport(),
			new XIOPieReportDiskUsage(),
			new XIOPieRaidUsageReport(),
			new XIOPieReadWriteLatencyReport(),
			new XIODeviceControllerReport(),
			new XIODeviceDataPacReport(),
			new XIODevicePowerSupplyReport(),
			new XIODeviceFCInterfaceReport(),
			new XIODeviceEInterfaceReport(),
			new XIODeviceLunReport(),
			new XIODeviceHostReport(),
			new XIODeviceMappingReport(),
			new XIODeviceDriveReport(),
			new XIODeviceCapabilityReport(),
			new XIODeviceEventLogReport(),
			new XIODeviceAboutReport()
	};
	
	public XIODeviceMgmtReport() {
		super( NAME, XIOConstants.XIO_ACCOUNT_TYPE, InfraAccountTypes.CAT_STORAGE );
		logger.info( "----#### XIODeviceMgmtReport:XIODeviceMgmtReport ####----" );
	}
	@Override
	public CloupiaReport[] getDrilldownReports() {
		logger.info( "----#### XIODeviceMgmtReport:getDrilldownReports ####----" );
		return this.ddReports;
	}
}
