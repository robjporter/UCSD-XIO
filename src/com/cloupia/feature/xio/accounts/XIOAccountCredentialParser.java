package com.cloupia.feature.xio.accounts;

import org.apache.log4j.Logger;
import com.cloupia.lib.connector.account.credential.CredentialParserIf;

public class XIOAccountCredentialParser implements CredentialParserIf {
	private static Logger logger = Logger.getLogger( XIOAccountCredentialParser.class );
	@Override
	public Object getCredentialsFromPolicy( String arg0, Object arg1 ) throws Exception {
		logger.info( "----#### XIOAccountCredentialParser:getCredentialsFromPolicy ####----" );
		return null;
	}

}
