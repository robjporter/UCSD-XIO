package com.cloupia.feature.xio.reports.device.network;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.inventory.model.XIONetwork;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;

@SuppressWarnings("unused")
public class XIODeviceEInterfaceReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceEInterfaceReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceEInterfaceReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<XIONetwork> dummyAssignStore = ObjStoreHelper.getStore( XIONetwork.class );
		List<XIONetwork> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Port No.", "Port No." );
		model.addTextColumn( "Status", "Status" );
		model.addTextColumn( "Detail", "Detail" );
		model.addTextColumn( "Hardware Revision", "Hardware Revision" );
		model.addTextColumn( "Serial No.", "Serial No." );
		model.addTextColumn( "Part No.", "Part No." );
		model.addTextColumn( "Manufacture Date", "Manufacture Date" );
		//model.addTextColumn( "HEC" , "HEC" );
		//model.addTextColumn( "HEC TX" , "HEC TX" );
		//model.addTextColumn( "LOS" , "LOS" );
		//model.addTextColumn( "FLT TX" , "FLT TX" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIONetwork pojo = objs.get( i );
			model.addTextValue( pojo.getID());
			model.addTextValue( pojo.getStatus());
			model.addTextValue( pojo.getDetail());
			model.addTextValue( pojo.getHW());
			model.addTextValue( pojo.getSerial());
			model.addTextValue( pojo.getPart());
			model.addTextValue( pojo.getManuf());
			//model.addTextValue( pojo.getHec());
			//model.addTextValue( pojo.getHectx());
			//model.addTextValue( pojo.getLos());
			//model.addTextValue( pojo.getTxflt());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
