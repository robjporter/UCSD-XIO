package com.cloupia.feature.xio.reports.mappings;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.inventory.model.XIOMapping;
import com.cloupia.feature.xio.reports.device.hosts.XIODeviceHostReportImpl;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;

@SuppressWarnings("unused")
public class XIODeviceMappingReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceMappingReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceMappingReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<XIOMapping> dummyAssignStore = ObjStoreHelper.getStore( XIOMapping.class );
		List<XIOMapping> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Allocated Host Name", "Allocated Host Name" );
		model.addTextColumn( "Host WWPN", "Host WWPN" );
		model.addTextColumn( "Mapped Volume Name", "Mapped Volume Name" );
		model.addTextColumn( "Volume LUN ID", "Volume LUN ID" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIOMapping pojo = objs.get( i );
			model.addTextValue( pojo.getHostName());
			model.addTextValue( pojo.getHostWWPN());
			model.addTextValue( pojo.getVolumeName());
			model.addTextValue( pojo.getVolumeLunID());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
