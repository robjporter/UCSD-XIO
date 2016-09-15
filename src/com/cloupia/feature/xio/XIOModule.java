package com.cloupia.feature.xio;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.accounts.XIOAccount;
import com.cloupia.feature.xio.accounts.handler.XIOTestConnectionHandler;
import com.cloupia.feature.xio.accounts.inventory.XIOConvergedStackBuilder;
import com.cloupia.feature.xio.accounts.inventory.XIOInventoryItemHandler;
import com.cloupia.feature.xio.accounts.inventory.XIOInventoryListener;
import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.XIOCollectorFactory;
import com.cloupia.feature.xio.lovs.SimpleTabularProvider;
import com.cloupia.feature.xio.lovs.XIOOperatingSystemLovProvider;
import com.cloupia.feature.xio.lovs.XIORedundancyLovProvider;
import com.cloupia.feature.xio.lovs.XIOWriteCacheLovProvider;
import com.cloupia.feature.xio.reports.device.XIODeviceMgmtReport;
import com.cloupia.feature.xio.tasks.HelloWorldTask;
import com.cloupia.feature.xio.triggers.MonitorDummyDeviceStatusParam;
import com.cloupia.feature.xio.triggers.MonitorDummyDeviceType;
import com.cloupia.lib.connector.ConfigItemDef;
import com.cloupia.lib.connector.account.AccountTypeEntry;
import com.cloupia.lib.connector.account.PhysicalAccountTypeManager;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.AbstractCloupiaModule;
import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.CustomFeatureRegistry;
import com.cloupia.service.cIM.inframgr.collector.controller.CollectorFactory;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReport;
import com.cloupia.service.cIM.inframgr.thresholdmonitor.MonitoringTrigger;
import com.cloupia.service.cIM.inframgr.thresholdmonitor.MonitoringTriggerUtil;

public class XIOModule extends AbstractCloupiaModule {
	private static Logger logger = Logger.getLogger( XIOModule.class );

	@Override
	public AbstractTask[] getTasks() {
		//logger.info( "----#### XIOModule:getTasks ####----" );
		AbstractTask task1 = new HelloWorldTask();
		AbstractTask[] tasks = new AbstractTask[1];
		tasks[0] = task1;
		return tasks;
	}
	@Override
	public CollectorFactory[] getCollectors() {
		//logger.info( "----#### XIOModule:getCollectors ####----" );
		XIOCollectorFactory collector = new XIOCollectorFactory( XIOConstants.XIO_ACCOUNT_TYPE );
		CollectorFactory[] collectors = new CollectorFactory[ 1 ];
		collectors[ 0 ] = collector;
		return collectors;
	}
	@Override
	public CloupiaReport[] getReports() {
		//logger.info( "----#### XIOModule:getReports ####----" );
		XIODeviceMgmtReport XIODeviceReport = new XIODeviceMgmtReport();
		CloupiaReport[] reports = new CloupiaReport[ 1 ];
		reports[ 0 ] = XIODeviceReport;
		//reports[ 1 ] = new XIODeviceSummaryReport();
		return reports;
	}
	@Override
	public void onStart( CustomFeatureRegistry cfr ) {
		logger.info( "----#### XIOModule:onStart ####----" );
		try {
			cfr.registerLovProviders( XIORedundancyLovProvider.SIMPLE_LOV_PROVIDER, new XIORedundancyLovProvider());
			cfr.registerLovProviders( XIOWriteCacheLovProvider.SIMPLE_LOV_PROVIDER, new XIOWriteCacheLovProvider());
			cfr.registerLovProviders( XIOOperatingSystemLovProvider.SIMPLE_LOV_PROVIDER, new XIOOperatingSystemLovProvider());
			cfr.registerTabularField( SimpleTabularProvider.SIMPLE_TABULAR_PROVIDER, SimpleTabularProvider.class, "0", "0" );
			ReportContextRegistry.getInstance().register( XIOConstants.INFRA_ACCOUNT_TYPE, XIOConstants.INFRA_ACCOUNT_LABEL );
			MonitoringTrigger monTrigger = new MonitoringTrigger( new MonitorDummyDeviceType(), new MonitorDummyDeviceStatusParam());
			MonitoringTriggerUtil.register( monTrigger );
			createAccountType();
		} catch (Exception e) {
			logger.error( "----#### XIOModule:onStart::ERROR ####----", e );
		}
	}
	private void createAccountType() {
		//logger.info( "----#### XIOModule:createAccountType ####----" );
		AccountTypeEntry entry = new AccountTypeEntry();
		entry.setCredentialClass( XIOAccount.class );
		entry.setAccountType( XIOConstants.INFRA_ACCOUNT_TYPE );
		entry.setAccountLabel( XIOConstants.INFRA_ACCOUNT_LABEL );
		entry.setCategory( InfraAccountTypes.CAT_STORAGE );
		entry.setContextType( ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE ).getType());
		entry.setAccountClass( AccountTypeEntry.PHYSICAL_ACCOUNT );
		entry.setInventoryTaskPrefix( "XIO Module Inventory Task" );
		entry.setWorkflowTaskCategory( "XIO Tasks" );
		entry.setInventoryFrequencyInMins( 15 );
		entry.setPodTypes( new String[] { "X-POD" });
		entry.setTestConnectionHandler( new XIOTestConnectionHandler() );
		entry.setInventoryListener( new XIOInventoryListener() );
		entry.setConvergedStackComponentBuilder( new XIOConvergedStackBuilder() );
		try {
			registerInventoryObjects( entry );
			PhysicalAccountTypeManager.getInstance().addNewAccountType(entry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unused")
	private void registerInventoryObjects( AccountTypeEntry xioRecoverPointAccountEntry ) {
		//logger.info( "----#### XIOModule:registerInventoryObjects ####----" );
		ConfigItemDef xioRecoverPointStateInfo = xioRecoverPointAccountEntry.createInventoryRoot( "xio.inventory.root", XIOInventoryItemHandler.class );
	}
}
