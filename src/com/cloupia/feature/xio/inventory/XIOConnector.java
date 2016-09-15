package com.cloupia.feature.xio.inventory;

import org.apache.log4j.Logger;
import com.cloupia.feature.xio.device.XIODevice;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.service.cIM.inframgr.collector.controller.NodeConnectorIf;
import com.cloupia.service.cIM.inframgr.collector.model.ConfigItemIf;
import com.cloupia.service.cIM.inframgr.collector.model.ConfigResponse;
import com.cloupia.service.cIM.inframgr.collector.model.ConnectionProperties;
import com.cloupia.service.cIM.inframgr.collector.model.ConnectionStatus;
import com.cloupia.service.cIM.inframgr.collector.model.ItemIf;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;
import com.cloupia.service.cIM.inframgr.collector.model.NodeID;
import com.cloupia.service.cIM.inframgr.collector.view2.ConnectorCredential;

public class XIOConnector implements NodeConnectorIf {
	private static Logger logger = Logger.getLogger( XIOConnector.class );
	private XIODevice device = XIODevice.getInstance();
	
	@Override
	public ConnectionStatus connect( ConnectorCredential credential, ConnectionProperties props ) throws Exception {
		logger.info( "----#### XIOConnector:connect ####----" );
		InfraAccount account = credential.toInfraAccount();
		String serverIP = account.getServer(); // deviceCreds.getDeviceIp();
		String login = account.getUserID(); // deviceCreds.getLogin();
		String password = account.getPassword();
		boolean result = device.connect( serverIP, login, password );
		ConnectionStatus status = new ConnectionStatus();
		status.setConnected( result );
		return status;
	}
	@Override
	public void disconnect() throws Exception {
		logger.info( "----#### XIOConnector:disconnect ####----" );
	}
	@Override
	public ConfigResponse executeItems( NodeID arg0, ConfigItemIf arg1 ) throws Exception {
		logger.info( "----#### XIOConnector:executeItems ####----" );
		return null;
	}
	@Override
	public ItemResponse getItem( NodeID nodeId, ItemIf item ) throws Exception {
		logger.info( "----#### XIOConnector:getItem ####----" );
		XIOInventoryItem invItem = (XIOInventoryItem) item;
		String dataToCollect = invItem.getName();
		String data = device.getData( dataToCollect );
		ItemResponse itemResponse = new ItemResponse();
		itemResponse.setItem( item );
		itemResponse.setCollectedData( data );
		itemResponse.setNodeId( nodeId );
		return itemResponse;
	}
}
