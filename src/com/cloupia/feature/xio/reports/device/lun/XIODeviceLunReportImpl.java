package com.cloupia.feature.xio.reports.device.lun;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.accounts.XIOAccount;
import com.cloupia.feature.xio.inventory.model.XIOLun;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;

@SuppressWarnings("unused")
public class XIODeviceLunReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceLunReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceLunReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<XIOLun> dummyAssignStore = ObjStoreHelper.getStore( XIOLun.class );
		List<XIOLun> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "ID", "ID" );
		model.addTextColumn( "LUN Name", "LUN Name" );
		model.addTextColumn( "Size", "Size" );
		model.addTextColumn( "Creation Date", "Creation Date" );
		model.addTextColumn( "Redundancy", "Redundancy" );
		model.addTextColumn( "Affinity", "Affinity" );
		model.addTextColumn( "Comment", "Comment" );
		model.addTextColumn( "IOPS" , "IOPS" );
		model.addTextColumn( "IOPS Min" , "IOPS Min" );
		model.addTextColumn( "IOPS Max" , "IOPS Max" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIOLun pojo = objs.get( i );
			model.addTextValue( pojo.getID());
			model.addTextValue( pojo.getName());
			model.addTextValue( pojo.getSize());
			model.addTextValue( pojo.getCreationDate());
			model.addTextValue( pojo.getRedundancy());
			model.addTextValue( pojo.getAffinity());
			model.addTextValue( pojo.getComment());
			model.addTextValue( pojo.getIOPS());
			model.addTextValue( pojo.getIOPSMin());
			model.addTextValue( pojo.getIOPSMax());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
