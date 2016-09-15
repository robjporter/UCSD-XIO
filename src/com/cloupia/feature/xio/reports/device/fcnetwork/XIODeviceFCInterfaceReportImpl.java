package com.cloupia.feature.xio.reports.device.fcnetwork;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.model.XIOFCNetwork;
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
public class XIODeviceFCInterfaceReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceFCInterfaceReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceFCInterfaceReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<XIOFCNetwork> dummyAssignStore = ObjStoreHelper.getStore( XIOFCNetwork.class );
		List<XIOFCNetwork> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Port No.", "Port No." );
		model.addTextColumn( "Status", "Status" );
		model.addTextColumn( "Type", "Type" );
		model.addTextColumn( "WWPN", "WWPN" );
		model.addTextColumn( "Speed", "Speed" );
		model.addTextColumn( "Bytes Sent", "Bytes Sent" );
		model.addTextColumn( "Bytes Received", "Bytes Received" );
		model.addTextColumn( "Loop Init Count", "Loop Init Count" );
		model.addTextColumn( "Not Operation Count" , "Not Operational Count" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIOFCNetwork pojo = objs.get( i );
			model.addTextValue( pojo.getID());
			model.addTextValue( pojo.getStatus());
			model.addTextValue( pojo.getType());
			model.addTextValue( pojo.getWWPN());
			model.addTextValue( pojo.getSpeed());
			model.addTextValue( pojo.getBytesSent());
			model.addTextValue( pojo.getBytesRecv());
			model.addTextValue( pojo.getLoop());
			model.addTextValue( pojo.getNos());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
