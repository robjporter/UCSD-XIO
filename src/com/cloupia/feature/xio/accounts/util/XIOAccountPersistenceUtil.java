package com.cloupia.feature.xio.accounts.util;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.xio.accounts.XIOAccount;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;

public class XIOAccountPersistenceUtil {
	private static Logger logger = Logger.getLogger( XIOAccountPersistenceUtil.class );

	public static void persistCollectedInventory( String accountName ) throws Exception {
		logger.info( "----#### XIOAccountPersistenceUtil:persistCollectedInventory ####----" );
		XIOAccount acc = getXIOCredential( accountName );
		if (acc != null) {
			//logger.info( "++3+XIOAccountPersistenceUtil:persistCollectedInventory:: Account gained successfully - IP = " + acc.getServerAddress() );
		} else {
			//logger.info( "++4+XIOAccountPersistenceUtil:persistCollectedInventory::ERROR::: Account lookup failed" );
		}
	}
	public static XIOAccount getXIOCredential( String accountName ) throws Exception {
		logger.info( "----#### XIOAccountPersistenceUtil:getXIOCredential ####----" );
		PhysicalInfraAccount acc = AccountUtil.getAccountByName(accountName);
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount) JSON.jsonToJavaObject(json, XIOAccount.class);
		specificAcc.setAccount(acc);
		return (XIOAccount) specificAcc;
	}
}
