package com.cloupia.feature.xio.accounts.inventory;

import org.apache.log4j.Logger;

import com.cloupia.service.cIM.inframgr.collector.controller.PersistenceListener;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;

public class XIOCollectorInventroyPersistenceListener extends PersistenceListener {
	private static Logger logger = Logger.getLogger( XIOCollectorInventroyPersistenceListener.class );

	@Override
	public void persistItem( ItemResponse arg0 ) throws Exception {
		logger.info( "----#### XIOCollectorInventroyPersistenceListener:persistItem ####----" );
	}
}
