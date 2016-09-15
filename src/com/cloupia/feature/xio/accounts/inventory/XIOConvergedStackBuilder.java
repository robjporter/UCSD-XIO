package com.cloupia.feature.xio.accounts.inventory;

import java.util.List;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.device.XIODeviceInfo;
import com.cloupia.feature.xio.inventory.custom.XIOConvergedStackComponentDetail;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.model.cIM.ConvergedStackComponentDetail;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reports.contextresolve.ConvergedStackComponentBuilderIf;

public class XIOConvergedStackBuilder implements ConvergedStackComponentBuilderIf {
	private static Logger logger = Logger.getLogger( XIOConvergedStackBuilder.class );
	
	@Override
	public ConvergedStackComponentDetail buildConvergedStackComponent( String accountIdentity ) throws Exception {
		XIOConvergedStackComponentDetail detail = new XIOConvergedStackComponentDetail();
		ObjStore<XIODeviceInfo> store = ObjStoreHelper.getStore( XIODeviceInfo.class );
		String accountName = accountIdentity.split( ";" )[0];
		String query = "accountName == '" + accountName + "'";
		
		logger.info( "@@@@@@@@@@@@@@@@@@@ Looking for Inventory summary for account: " + accountName );
		PhysicalInfraAccount account = AccountUtil.getAccountByName(accountName);
		logger.info("AccountName:" + account.getAccountName());
		
		
		List<XIODeviceInfo> summaryList = store.query( query );
		if( summaryList.isEmpty() == false ) {
			XIODeviceInfo xioDeviceInfo = summaryList.get( 0 );
			detail.setModel( xioDeviceInfo.getModel() );
			detail.setOsVersion( xioDeviceInfo.getFWVersion() );
			detail.setVendorLogoUrl( "/app/uploads/openauto/xio_logo.png" );
			detail.setMgmtIPAddr( xioDeviceInfo.getIP() );
			detail.setStatus( xioDeviceInfo.getStatus() );
			detail.setSerialNumber( xioDeviceInfo.getSerialNumber());
			detail.setVendorName( xioDeviceInfo.getVendor() );
			detail.setBuildVersion( XIOConstants.BUILD );
			detail.setContextType( ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE ).getType());
			detail.setLayerType( com.cloupia.model.cIM.ConvergedStackComponentDetail.LAYER_TYPE_PHY_STORAGE );
			detail.setContextValue( accountIdentity );
		} else {
			logger.info( "@@@@@@@@@@@@@@@@@@@ No inventory summary found for account: " + accountName );
			detail.setStatus( "OK" );
		}
		return detail;
	}
}
