package com.cloupia.feature.xio.accounts.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;

public class XIOAccountJSONBinder extends XIOJSONBinder {
	private static Logger logger = Logger.getLogger(XIOAccountJSONBinder.class);

	@Override
	public ItemResponse bind(ItemResponse bindable) {
		logger.info( "----#### XIOAccountJSONBinder:bind ####----" );
		String jsonData = bindable.getCollectedData();
		if( jsonData != null && !jsonData.isEmpty()) {}
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getPersistantClassList() {
		logger.info( "----#### XIOAccountJSONBinder:getPersistantClassList ####----" );
		List<Class> cList = new ArrayList<Class>();
		return cList;
	}
}
