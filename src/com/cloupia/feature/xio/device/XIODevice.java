package com.cloupia.feature.xio.device;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.inventory.model.XIOController;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class XIODevice {
	private static Logger logger = Logger.getLogger( XIODevice.class );
	private static XIODevice instance = new XIODevice();
	
	private XIODevice() {}
	public static XIODevice getInstance() {
		logger.info( "----#### XIODevice:XIODevice ####----" );
		return instance;
	}
	public boolean connect( String ip, String login, String password ) {
		logger.info( "----#### XIODevice:connect ####----" );
		return true;
	}
	public String getData( String type ) {
		logger.info( "----#### XIODevice:getData ####----" );
		return "";
	}
	public static void main( String[] args ) {
		logger.info( "----#### XIODevice:main ####----" );
		String jsonData = XIODevice.getInstance().getData( XIOConstants.CONTROLLERS );
		JsonParser parser = new JsonParser();
		JsonObject obj = (JsonObject) parser.parse(jsonData);
		JsonArray array = obj.getAsJsonArray( "data" );
		List<InventoryDBItemIf> objs = new ArrayList<InventoryDBItemIf>();
		Gson gson = new Gson();
		for (int i=0; i<array.size(); i++) {
			JsonElement ele = array.get(i);
			InventoryDBItemIf invDBObj = gson.fromJson(ele, XIOController.class);
			objs.add(invDBObj);
		}
	}
}
