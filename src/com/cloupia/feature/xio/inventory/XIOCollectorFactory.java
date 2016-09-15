package com.cloupia.feature.xio.inventory;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.model.XIOCapability;
import com.cloupia.feature.xio.inventory.model.XIOController;
import com.cloupia.feature.xio.inventory.model.XIOLun;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.model.cIM.stackView.StackViewItemProviderIf;
import com.cloupia.service.cIM.inframgr.GenericInfraAccountConnectionTestHandler;
import com.cloupia.service.cIM.inframgr.InfraAccountConnectionTestHandlerIf;
import com.cloupia.service.cIM.inframgr.collector.controller.CollectorFactory;
import com.cloupia.service.cIM.inframgr.collector.controller.InventoryCollector;
import com.cloupia.service.cIM.inframgr.collector.impl.GenericNodeID;
import com.cloupia.service.cIM.inframgr.reports.contextresolve.ConvergedStackComponentBuilderIf;

public class XIOCollectorFactory extends CollectorFactory {
	private static Logger logger = Logger.getLogger( XIOCollectorFactory.class );

	public XIOCollectorFactory( int accountType ) {
		super(accountType);		
		logger.info( "----#### XIOCollectorFactory:XIOCollectorFactory ####----" );
	}
	@Override
	public InventoryCollector createCollector( String accountName ) throws Exception {
		logger.info( "----#### XIOCollectorFactory:createCollector ####----" );
		GenericNodeID nodeID = new GenericNodeID( accountName, InfraAccountTypes.CAT_STORAGE );
		XIOInventoryCollector collector = new XIOInventoryCollector( nodeID );
		XIOInventoryItem capabilities = new XIOInventoryItem( XIOConstants.CAPABILITIES, XIOCapability.class );
		XIOInventoryItem controllers = new XIOInventoryItem( XIOConstants.CONTROLLERS, XIOController.class );
		XIOInventoryItem luns = new XIOInventoryItem( XIOConstants.LUNS, XIOLun.class );
		collector.addItem( capabilities );
		collector.addItem( controllers );
		collector.addItem( luns );
		return collector;
	}
	@Override
	public int getAccountCategory() {
		logger.info( "----#### XIOCollectorFactory:getAccountCategory ####----" );
		return CollectorFactory.STORAGE_CATEGORY;
	}
	@Override
	public ConvergedStackComponentBuilderIf getStackComponentBuilder() {
		logger.info( "----#### XIOCollectorFactory:getStackComponentBuilder ####----" );
		return new XIOConvergedStackBuilder();
	}
	@Override
	public StackViewItemProviderIf getStackViewProvider() {
		logger.info( "----#### XIOCollectorFactory:getStackViewProvider ####----" );
		return new XIOStackViewProvider();
	}
	@Override
	public InfraAccountConnectionTestHandlerIf getTestConnectionHandler() {
		logger.info( "----#### XIOCollectorFactory:getTestConnectionHandler ####----" );
		return new GenericInfraAccountConnectionTestHandler() {
			@Override
			public boolean testConnectionTo( InfraAccount arg0, StringBuffer arg1 ) {
				logger.info( "----#### XIOCollectorFactory:testConnectionTo ####----" );
				return true;
			}
		};
	}
}
