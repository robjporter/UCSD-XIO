package com.cloupia.feature.xio.accounts.connector;

import org.apache.log4j.Logger;

import com.cisco.cuic.api.client.JSON;
import com.cloupia.feature.xio.accounts.XIOAccount;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.lib.util.ssh.SSHClient;
import com.cloupia.lib.util.ssh.SSHCommandOutput;
import com.cloupia.service.cIM.inframgr.collector.model.ConfigItemIf;

public class XIOConnector {
	static Logger logger = Logger.getLogger(XIOConnector.class);

	public String collectData( String accountName, String command ) throws Exception {
		logger.info( "----#### XIOConnector:collectData ####----" );
		XIOAccount acc = getXIOCredential( accountName );
		SSHClient client = connection( acc.getServerAddress(), Integer.parseInt( acc.getPort() ), acc.getLogin(), acc.getPassword());
		client.connect();
		String data = null;
		if( client.isConnected()) {
			try {
				SSHCommandOutput output = client.executeCommand( command );
				if( output.getExitCode() > 0 && output.getStdErr() != null && !output.getStdErr().trim().isEmpty()) {
					throw new Exception("----#### XIOConnector:collectData::ERROR::: ####---- while executing command: " + command + " on account: " + accountName + " ERROR: " + output.getStdErr());
				}
				data = output.getStdOut();
			} finally {
				if (client != null)
					client.disconnect();
			}
		} else {}
		return data;
	}
	public SSHClient connection( String host, int port, String userName, String passWord ) {
		logger.info( "----#### XIOConnector:connection ####----" );
		SSHClient client = new SSHClient( host, port, userName, passWord );
		return client;
	}
	public boolean execute( String accountName, ConfigItemIf item ) throws Exception {
		logger.info( "----#### XIOConnector:execute ####----" );
		String data = collectData( accountName, item.getCommand());
		String[] errormessages = item.getErrorMessages();
		for( String d : errormessages ) {
			if( data.toLowerCase().contains( d.toLowerCase())) {
				throw new Exception( "Error occurred:" + d );
			}
		}
		return true;
	}
	private static XIOAccount getXIOCredential( String accountName ) throws Exception {
		logger.info( "----#### XIOConnector:getXIOCredential ####----" );
		PhysicalInfraAccount acc = AccountUtil.getAccountByName( accountName );
		String json = acc.getCredential();
		AbstractInfraAccount specificAcc = (AbstractInfraAccount)JSON.jsonToJavaObject( json, XIOAccount.class );
		specificAcc.setAccount( acc );
		return (XIOAccount) specificAcc;
	}
}
