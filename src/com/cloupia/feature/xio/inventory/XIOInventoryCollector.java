package com.cloupia.feature.xio.inventory;

import org.apache.log4j.Logger;
import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.collector.controller.InventoryCollector;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemDataObjectBinderIf;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemParserIf;
import com.cloupia.service.cIM.inframgr.collector.controller.JDOPersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.controller.NodeConnectorIf;
import com.cloupia.service.cIM.inframgr.collector.controller.PersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.model.NodeID;

public class XIOInventoryCollector extends InventoryCollector {
	private static Logger logger = Logger.getLogger( XIOInventoryCollector.class );
	private NodeID node;
	private XIOConnector connector;
	private XIOBinder binder;
	private JDOPersistenceListener listener;
	
	public XIOInventoryCollector( NodeID nodeId ) {
		super( nodeId );
		logger.info( "----#### XIOInventoryCollector:DummyInventoryCollector ####----" );
		this.node = nodeId;
		this.connector = new XIOConnector();
		this.binder = new XIOBinder();
		this.listener = new JDOPersistenceListener();	
	}
	@Override
	public NodeConnectorIf getConnector() {
		logger.info( "----#### XIOInventoryCollector:getConnector ####----" );
		return this.connector;
	}
	@Override
	public PersistenceListener getItemListener() {
		logger.info( "----#### XIOInventoryCollector:getItemListener ####----" );
		return this.listener;
	}
	@Override
	public ItemParserIf getItemParser() {
		logger.info( "----#### XIOInventoryCollector:getItemParser ####----" );
		return null;
	}
	@Override
	public ItemDataObjectBinderIf getObjectBinder() {
		logger.info( "----#### XIOInventoryCollector:getObjectBinder ####----" );
		return this.binder;
	}
	@Override
	public FormLOVPair[] getFrequencyHoursLov() {
		logger.info( "----#### XIOInventoryCollector:getFrequencyHoursLov ####----" );
		return null;
	}
	@Override
	public FormLOVPair[] getFrequencyMinsLov() {
		logger.info( "----#### XIOInventoryCollector:getFrequencyMinsLov ####----" );
		return null;
	}
	@Override
	public String getTaskName() {
		logger.info( "----#### XIOInventoryCollector:getTaskName ####----" );
		return XIOConstants.XIO_INVENTORY_COLLECTOR_NAME + "_" + node.getConnectorId();
	}
	@Override
	public long getFrequenceInMinutes() {
		logger.info( "----#### XIOInventoryCollector:getFrequenceInMinutes ####----" );
		return 15;
	}
}
