package com.cloupia.feature.xio.reports.device.psu;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.inventory.model.XIOPowerSupply;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;

@SuppressWarnings("unused")
public class XIODevicePowerSupplyReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODevicePowerSupplyReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODevicePowerSupplyReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<XIOPowerSupply> dummyAssignStore = ObjStoreHelper.getStore( XIOPowerSupply.class );
		List<XIOPowerSupply> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "PowerSupply", "PowerSupply" );
		model.addTextColumn( "Status", "Status" );
		model.addTextColumn( "Model", "Model" );
		model.addTextColumn( "Hardware Revision", "Hardware Revision" );
		model.addTextColumn( "Serial Number", "Serial Number" );
		model.addTextColumn( "Temperature", "Temperature" );
		model.addTextColumn( "Manufacture Date", "Manufacture Date" );
		model.addTextColumn( "Fan 1 Speed", "Fan 1 Speed" );
		model.addTextColumn( "Fan 2 Speed", "Fan 2 Speed" );

		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			XIOPowerSupply pojo = objs.get( i );
			model.addTextValue( pojo.getID());
			model.addTextValue( pojo.getStatus());
			model.addTextValue( pojo.getModel());
			model.addTextValue( pojo.getHWVersion());
			model.addTextValue( pojo.getSerialNumber());
			model.addTextValue( pojo.getTemperature() + " " + pojo.getScale());
			model.addTextValue( pojo.getManufactureDate());
			model.addTextValue( pojo.getFan1RPM());
			model.addTextValue( pojo.getFan2RPM());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
