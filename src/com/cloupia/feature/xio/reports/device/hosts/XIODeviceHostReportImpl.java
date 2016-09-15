package com.cloupia.feature.xio.reports.device.hosts;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.model.XIOHost;
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
public class XIODeviceHostReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceHostReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceHostReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<XIOHost> dummyAssignStore = ObjStoreHelper.getStore( XIOHost.class );
		List<XIOHost> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Global ID", "Global ID" );
		model.addTextColumn( "Host Protocol", "Host Protocol" );
		model.addTextColumn( "Host Name", "Host Name" );
		model.addTextColumn( "Host ID", "Host ID" );
		model.addTextColumn( "Host Type", "Host Type" );
		model.addTextColumn( "Host Comment", "Host Comment" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIOHost pojo = objs.get( i );
			model.addTextValue( pojo.getID());
			model.addTextValue( pojo.getProtocol());
			model.addTextValue( pojo.getHostName());
			model.addTextValue( pojo.getHostID());
			model.addTextValue( pojo.getHostType());
			model.addTextValue( pojo.getHostComment());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
