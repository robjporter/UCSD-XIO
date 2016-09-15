package com.cloupia.feature.xio.accounts.inventory;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.xio.accounts.XIOAccount;
import com.cloupia.feature.xio.accounts.util.XIOAccountPersistenceUtil;
import com.cloupia.lib.connector.InventoryContext;
import com.cloupia.lib.connector.InventoryEventListener;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountTypeEntry;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalAccountManager;
import com.cloupia.lib.connector.account.PhysicalConnectivityStatus;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;

public class XIOInventoryListener implements InventoryEventListener {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOInventoryListener.class );

	@Override
	public void beforeInventoryStart( String accountName, InventoryContext arg1 ) throws Exception {
		//logger.info( "----#### XIOInventoryListener:beforeInventoryStart ####----" );
	}
	@Override
	public void afterInventoryDone( String accountName, InventoryContext context ) throws Exception {
		//logger.info( "----#### XIOInventoryListener:afterInventoryDone ####----" );
		XIOAccountPersistenceUtil.persistCollectedInventory( accountName );
		AccountTypeEntry entry = PhysicalAccountManager.getInstance().getAccountTypeEntryByName( accountName );
		PhysicalConnectivityStatus connectivityStatus = null;
		if( entry != null ) {
			connectivityStatus = entry.getTestConnectionHandler().testConnection( accountName );
		} else {}
		XIOAccount acc = getXIOCredential( accountName );
		if (acc != null && connectivityStatus != null) {
			//logger.info( "++8+XIOInventoryListener:afterInventoryDone:: Inventory collected successfully" );
		} else {
			//logger.info( "++9+XIOInventoryListener:afterInventoryDone::ERROR::: Inventory failed collected" );
		}
	}
	private static XIOAccount getXIOCredential( String accountName ) throws Exception {
		//logger.info( "----#### XIOInventoryListener:getXIOCredential ####----" );
		PhysicalInfraAccount acc = AccountUtil.getAccountByName(accountName);
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount) JSON.jsonToJavaObject(json, XIOAccount.class);
		specificAcc.setAccount(acc);
		return (XIOAccount) specificAcc;
	}
}
