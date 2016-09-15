package com.cloupia.feature.xio.accounts.api;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemDataObjectBinderIf;

public abstract class XIOJSONBinder implements ItemDataObjectBinderIf {
	private static Logger logger = Logger.getLogger(XIOJSONBinder.class);
	@SuppressWarnings("rawtypes")
	public abstract List<Class> getPersistantClassList();

	protected void bindContext( Object obj, Map<String, Object> context ) {
		logger.info( "----#### XIOJSONBinder:bindContext ####----" );
		for( Map.Entry<String, Object> entry : context.entrySet()) {
			String varName = entry.getKey();
			Object value = entry.getValue();
			try {
				Field field = obj.getClass().getDeclaredField( varName );
				field.setAccessible( true );
				if( value != null ) {
					field.set( obj, value );
				}
			} catch( SecurityException e ) {
				logger.info( "----#### XIOJSONBinder:bindContext::ERROR::: SECURITY EXCEPTION ####----" );
			} catch( NoSuchFieldException e ) {
				logger.info( "----#### XIOJSONBinder:bindContext::ERROR::: MISSING FIELD EXCEPTION ####----" );
				continue;
			} catch( IllegalArgumentException e ) {
				logger.info( "----#### XIOJSONBinder:bindContext::ERROR::: ILLEGAL ARGUMENT EXCEPTION ####----" );
			} catch (IllegalAccessException e) {
				logger.info( "----#### XIOJSONBinder:bindContext::ERROR::: ILLEGAL ACCESS EXCEPTION ####----" );
			}
		}
	}
}
