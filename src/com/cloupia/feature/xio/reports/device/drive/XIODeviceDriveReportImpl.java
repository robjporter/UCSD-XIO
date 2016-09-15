package com.cloupia.feature.xio.reports.device.drive;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.model.XIODrive;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;

@SuppressWarnings("unused")
public class XIODeviceDriveReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceDriveReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceDriveReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<XIODrive> dummyAssignStore = ObjStoreHelper.getStore( XIODrive.class );
		List<XIODrive> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Name", "Name" );
		model.addTextColumn( "Read IOPS", "Read IOPS" );
		model.addTextColumn( "Write IOPS", "Write IOPS" );
		model.addTextColumn( "Total IOPS", "Total IOPS" );
		model.addTextColumn( "Read Kbps", "Read Kbps" );
		model.addTextColumn( "Write Kbps", "Write Kbps" );
		model.addTextColumn( "Total Kbps", "Total Kbps" );
		model.addTextColumn( "Read Latency", "Read Latency" );
		model.addTextColumn( "Read Latency Max", "Read Latency Max" );
		model.addTextColumn( "Write Latency", "Write Latency" );
		model.addTextColumn( "Write Latency Max", "Write Latency Max" );
		model.addTextColumn( "Total IOPS", "Total IOPS since Reboot" );
		model.addTextColumn( "Total Kbps", "Total Kbps since Reboot" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIODrive pojo = objs.get( i );
			model.addTextValue( pojo.getName() );
			model.addTextValue( pojo.getIopsRead() );
			model.addTextValue( pojo.getIopsWrite() );
			model.addTextValue( pojo.getIopsTotal() );
			model.addTextValue( pojo.getKbpsRead() );
			model.addTextValue( pojo.getKbpsWrite() );
			model.addTextValue( pojo.getKbpsTotal() );
			model.addTextValue( pojo.getReadLatency() );
			model.addTextValue( pojo.getReadLatencyMax() );
			model.addTextValue( pojo.getWriteLatency() );
			model.addTextValue( pojo.getWriteLatencyMax() );
			model.addTextValue( pojo.getIopsTotalReboot() );
			model.addTextValue( pojo.getKbpsTotalReboot() );
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
