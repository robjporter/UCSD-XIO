package com.cloupia.feature.xio.accounts.inventory;

import java.util.Map;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.xio.accounts.XIOAccount;
import com.cloupia.feature.xio.accounts.api.XIOAccountAPI;
import com.cloupia.feature.xio.accounts.api.XIOAccountJSONBinder;
import com.cloupia.feature.xio.accounts.api.XIOJSONBinder;
import com.cloupia.feature.xio.device.functions.XIOFunctions;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.cIaaS.network.model.DeviceCredential;
import com.cloupia.lib.connector.AbstractInventoryItemHandler;
import com.cloupia.lib.connector.InventoryContext;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.service.cIM.inframgr.collector.controller.PersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;

@SuppressWarnings("unused")
public class XIOInventoryItemHandler extends AbstractInventoryItemHandler {
	private static Logger logger = Logger.getLogger( XIOInventoryItemHandler.class );

	@Override
	public void cleanup( String accountName ) throws Exception {
		//logger.info( "----#### XIOInventoryItemHandler:cleanup ####----" );
	}
	@Override
	public void doInventory( String accountName, InventoryContext inventoryCtxt ) throws Exception {
		//logger.info( "----#### XIOInventoryItemHandler:doInventory ####----" );
		doInventory( accountName );
	}
	@Override
	public void doInventory( String accountName, Object obj ) throws Exception {
		//logger.info( "----#### XIOInventoryItemHandler:doInventory ####----" );
	}
	private void doInventory( String accountName ) throws Exception {
		//logger.info( "----#### XIOInventoryItemHandler:doInventory ####----" );
		PhysicalInfraAccount infraAccount = AccountUtil.getAccountByName( accountName );
		DeviceCredential cred = infraAccount.toDeviceCredential();
		XIOFunctions xioFunctions = new XIOFunctions( cred );
		try {
			xioFunctions.getSummaryInventory( infraAccount.getAccountName());
			infraAccount.setReachable( true );
		} catch( Exception e ) {
			infraAccount.setReachable( false );
			throw( e );	
		}
		String jsonData = null; //api.getInventoryData( "http://10.52.208.227/query/arrays" );
		ItemResponse bindableResponse = new ItemResponse();
		bindableResponse.setContext( getContext( accountName ));
		bindableResponse.setCollectedData( jsonData );
		ItemResponse bindedResponse = null;
		XIOJSONBinder binder = getBinder();
		
		if (binder != null) {
			bindedResponse = binder.bind( bindableResponse );
		} else {}
		
		// TODO: NOT PERFECT
		PersistenceListener listener = getListener();
		
		if (listener != null) {
			listener.persistItem( bindedResponse );
			// NEW
			//XIOCollectorInventroyPersistenceListener.persistItem2( cred );
		} else {}
	}
	private void doInventoryOld( String accountName ) throws Exception {
		//logger.info( "----#### XIOInventoryItemHandler:doInventoryOld ####----" );
		XIOAccount acc = getXIOCredential( accountName );
		String jsonData = null;
		ItemResponse bindableResponse = new ItemResponse();
		bindableResponse.setContext( getContext( accountName ));
		bindableResponse.setCollectedData( jsonData );
		ItemResponse bindedResponse = null;
		
		XIOJSONBinder binder = getBinder();
		
		if (binder != null) {
			bindedResponse = binder.bind( bindableResponse );
		} else {}
		
		PersistenceListener listener = getListener();
		
		if( listener != null ) {
			listener.persistItem( bindedResponse );
		} else {}
	}
	public String getUrl() {
		//logger.info( "----#### XIOInventoryItemHandler:getUrl ####----" );
		return "platform/1/protocols/smb/shares";
	}
	public XIOAccountJSONBinder getBinder() {
		//logger.info( "----#### XIOInventoryItemHandler:getBinder ####----" );
		return new XIOAccountJSONBinder();
	}
	private Map<String, Object> getContext( String accountName ) {
		//logger.info( "----#### XIOInventoryItemHandler:getContext ####----" );
		return null;
	}
	private PersistenceListener getListener() {
		//logger.info( "----#### XIOInventoryItemHandler:getListener ####----" );
		return new XIOCollectorInventroyPersistenceListener();
	}
	private static XIOAccount getXIOCredential( String accountName ) throws Exception {
		//logger.info( "----#### XIOInventoryItemHandler:getXIOCredential ####----" );
		PhysicalInfraAccount acc = AccountUtil.getAccountByName( accountName );
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount) JSON.jsonToJavaObject( json, XIOAccount.class );
		specificAcc.setAccount( acc );
		return (XIOAccount) specificAcc;
	}
}
