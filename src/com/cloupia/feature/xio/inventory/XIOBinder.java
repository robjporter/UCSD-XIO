package com.cloupia.feature.xio.inventory;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemDataObjectBinderIf;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class XIOBinder implements ItemDataObjectBinderIf {
	private static Logger logger = Logger.getLogger( XIOBinder.class );

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ItemResponse bind(ItemResponse response) {
		logger.info( "----#### XIOBinder:bind ####----" );
		String jsonData = response.getCollectedData();		
		JsonParser parser = new JsonParser();
		JsonObject obj = (JsonObject) parser.parse( jsonData );
		JsonArray array = obj.getAsJsonArray( "data" );
		Class clazz = response.getItem().getBoundToClass();
		List<InventoryDBItemIf> objs = new ArrayList<InventoryDBItemIf>();
		Gson gson = new Gson();
		for (int i = 0; i < array.size(); i++) {
			JsonElement ele = array.get( i );
			InventoryDBItemIf invDBObj = gson.fromJson( ele, clazz );
			String accountName = response.getNodeId().getConnectorId();
			invDBObj.setAccountName( accountName );
			objs.add(invDBObj);
		}
		response.setBoundObjects(objs);
		return response;
	}
}
