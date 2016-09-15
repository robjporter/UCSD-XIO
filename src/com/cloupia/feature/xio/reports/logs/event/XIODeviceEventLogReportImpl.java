package com.cloupia.feature.xio.reports.logs.event;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.inventory.model.XIOEventLog;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;

@SuppressWarnings("unused")
public class XIODeviceEventLogReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceEventLogReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceEventLogReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<XIOEventLog> dummyAssignStore = ObjStoreHelper.getStore( XIOEventLog.class );
		List<XIOEventLog> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Date", "Date" );
		model.addTextColumn( "Time", "Time" );
		model.addTextColumn( "Type", "Type" );
		model.addTextColumn( "Class", "Class" );
		model.addTextColumn( "Component", "Component" );
		model.addTextColumn( "Description", "Description" );
		
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIOEventLog pojo = objs.get( i );
			model.addTextValue( pojo.getDate());
			model.addTextValue( pojo.getTime());
			model.addTextValue( pojo.getType());
			model.addTextValue( pojo.getClas());
			model.addTextValue( pojo.getComponent());
			model.addTextValue( pojo.getDescription());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
