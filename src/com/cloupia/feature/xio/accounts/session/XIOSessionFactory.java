package com.cloupia.feature.xio.accounts.session;

import org.apache.log4j.Logger;
import com.cloupia.lib.connector.pooling.ConnectorSession;
import com.cloupia.lib.connector.pooling.ConnectorSessionFactory;

public class XIOSessionFactory implements ConnectorSessionFactory {
	private static Logger logger = Logger.getLogger( XIOSessionFactory.class );
	
	@Override
	public ConnectorSession createSession( String arg0 ) {
		logger.info( "----#### XIOSessionFactory:createSession ####----" );
		return null;
	}

}
