package com.cloupia.feature.xio.reports.device.datapac;

import java.util.List;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.model.XIOMedia;
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
public class XIODeviceDataPacReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceDataPacReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceDataPacReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<XIOMedia> dummyAssignStore = ObjStoreHelper.getStore( XIOMedia.class );
		List<XIOMedia> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "DataPac ID", "DataPac ID" );
		model.addTextColumn( "Tier", "Tier" );
		model.addTextColumn( "Status", "Status" );
		model.addTextColumn( "Model", "Model" );
		model.addTextColumn( "Serial Number", "Serial Number" );
		model.addTextColumn( "Firmware Version", "Firmware Version" );
		model.addTextColumn( "Hardware Health", "Hardware Health" );
		model.addTextColumn( "Temperature", "Temperature" );
		model.addTextColumn( "Manufacture Date", "Manufacture Date" );
		model.addTextColumn( "Hardware Version", "Hardware Version" );
		model.addTextColumn( "Data Health", "Data Health" );
		model.addTextColumn( "Data Healing", "Data Healing" );
		model.addTextColumn( "Data Description", "Data Description" );

		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIOMedia pojo = objs.get( i );
			model.addTextValue( pojo.getID());
			model.addTextValue( pojo.getTier());
			model.addTextValue( pojo.getStatus());
			model.addTextValue( pojo.getModel());
			model.addTextValue( pojo.getSerial());
			model.addTextValue( pojo.getFirmware());
			model.addTextValue( pojo.getHWHealth() + "%" );
			model.addTextValue( pojo.getTemperature() + " " + pojo.getScale());
			model.addTextValue( pojo.getManufactureDate());
			model.addTextValue( pojo.getHW());
			model.addTextValue( pojo.getDataHealth() + "%" );
			model.addTextValue( pojo.getHealing());
			model.addTextValue( pojo.getHealingString());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}