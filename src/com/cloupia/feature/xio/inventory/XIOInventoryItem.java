package com.cloupia.feature.xio.inventory;

import org.apache.log4j.Logger;

import com.cloupia.service.cIM.inframgr.collector.controller.ItemDataObjectBinderIf;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemParserIf;
import com.cloupia.service.cIM.inframgr.collector.controller.MappedItemListener;
import com.cloupia.service.cIM.inframgr.collector.model.ItemDataFormat;
import com.cloupia.service.cIM.inframgr.collector.model.ItemIf;

public class XIOInventoryItem implements ItemIf {
	private static Logger logger = Logger.getLogger( XIOInventoryItem.class );
	private String type;
	@SuppressWarnings("rawtypes")
	private Class model;

	@SuppressWarnings("rawtypes")
	public XIOInventoryItem( String type, Class model ) {
		logger.info( "----#### XIOInventoryItem:DummyInventoryItem ####----" );
		this.type = type;
		this.model = model;
	}
	@Override
	public ItemDataObjectBinderIf getBinder() {
		logger.info( "----#### XIOInventoryItem:getBinder ####----" );
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getBoundToClass() {
		logger.info( "----#### XIOInventoryItem:getBoundToClass ####----" );
		return model;
	}
	@Override
	public ItemDataFormat getCollectedDataFormat() {
		logger.info( "----#### XIOInventoryItem:getCollectedDataFormat ####----" );
		return null;
	}
	@Override
	public MappedItemListener getItemListener() {
		logger.info( "----#### XIOInventoryItem:getItemListener ####----" );
		return null;
	}
	@Override
	public String getLabel() {
		logger.info( "----#### XIOInventoryItem:getLabel ####----" );
		return this.type;
	}
	@Override
	public String getName() {
		logger.info( "----#### XIOInventoryItem:getName ####----" );
		return this.type;
	}
	@Override
	public ItemDataFormat getParsedDataFormat() {
		logger.info( "----#### XIOInventoryItem:getParsedDataFormat ####----" );
		return null;
	}
	@Override
	public ItemParserIf getParser() {
		logger.info( "----#### XIOInventoryItem:getParser ####----" );
		return null;
	}
}
