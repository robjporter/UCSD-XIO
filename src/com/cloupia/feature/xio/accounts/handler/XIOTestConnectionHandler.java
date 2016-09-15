package com.cloupia.feature.xio.accounts.handler;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.xio.accounts.XIOAccount;
import com.cloupia.feature.xio.accounts.api.XIOAccountAPI;
import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalConnectivityStatus;
import com.cloupia.lib.connector.account.PhysicalConnectivityTestHandler;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.model.cIM.InfraAccountTypes;

@SuppressWarnings("unused")
public class XIOTestConnectionHandler extends PhysicalConnectivityTestHandler {
	static Logger logger = Logger.getLogger(XIOTestConnectionHandler.class);

	@Override
	public PhysicalConnectivityStatus testConnection( String accountName ) throws Exception {
		logger.info( "----#### XIOTestConnectionHandler:PhysicalConnectivityStatus ####----" );
		XIOAccount acc = getXIOCredential( accountName );
		PhysicalInfraAccount infraAccount = AccountUtil.getAccountByName( accountName );
		PhysicalConnectivityStatus status = new PhysicalConnectivityStatus( infraAccount );
		status.setConnectionOK( false );
		if( infraAccount != null ) {
			if( infraAccount.getAccountType() != null ) {
				if( infraAccount.getAccountType().equals( XIOConstants.INFRA_ACCOUNT_LABEL )) {
					/**
					 * The below snippet will be used for the real device
					 * connection.
					 * 
					 * try { FooAccountAPI.getFooAccountAPI(acc);
					 * logger.info("*******Setting test Connection as true*****"
					 * ); } catch(Exception e) { status.setConnectionOK(false);
					 * status.setErrorMsg(
					 * "Failed to establish connection with the Device.");
					 * logger.debug("Test Connection is failed"); return status;
					 * }
					 */
					status.setConnectionOK( true );
				} else {
					logger.info( "----#### XIOTestConnectionHandler:PhysicalConnectivityStatus ####---- ERROR: Incorrect Account Type" );
				}
			} else {
				logger.info( "----#### XIOTestConnectionHandler:PhysicalConnectivityStatus ####---- ERROR: Account Type = null" );			}
		} else {
			logger.info( "----#### XIOTestConnectionHandler:PhysicalConnectivityStatus ####---- ERROR: InfraAccount = null" );
		}
		return status;
	}
	private static XIOAccount getXIOCredential( String accountName ) throws Exception {
		logger.info( "----#### XIOTestConnectionHandler:getXIOCredential ####----" );
		PhysicalInfraAccount acc = AccountUtil.getAccountByName(accountName);
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount) JSON.jsonToJavaObject(json, XIOAccount.class);
		specificAcc.setAccount(acc);
		return (XIOAccount) specificAcc;
	}
}
