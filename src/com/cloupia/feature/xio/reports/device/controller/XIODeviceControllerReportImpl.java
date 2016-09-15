package com.cloupia.feature.xio.reports.device.controller;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.model.XIOController;
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
public class XIODeviceControllerReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceControllerReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceControllerReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<XIOController> dummyAssignStore = ObjStoreHelper.getStore( XIOController.class );
		List<XIOController> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Account Name", "Account Name" );
		model.addTextColumn( "IP Address", "IP Address" );
		model.addTextColumn( "DNS Name", "DNS Name" );
		model.addTextColumn( "MAC Address", "MAC Address" );
		model.addTextColumn( "Firmware", "Firmware" );
		model.addTextColumn( "XO Master", "XO Master" );
		model.addTextColumn( "Status", "Status" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIOController pojo = objs.get( i );
			model.addTextValue( pojo.getAccountName());
			model.addTextValue( pojo.getIP());
			model.addTextValue( pojo.getDNS());
			model.addTextValue( pojo.getMAC());
			model.addTextValue( pojo.getFirmware());
			model.addTextValue( pojo.getMaster());
			model.addTextValue( pojo.getStatus());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
