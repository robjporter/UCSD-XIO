package com.cloupia.feature.xio.inventory;

import org.apache.log4j.Logger;
import com.cloupia.lib.cIaaS.network.model.DeviceIdentity;
import com.cloupia.service.cIM.inframgr.stackView.AbstractOANetworkStackViewProvider;

public class XIOStackViewProvider  extends AbstractOANetworkStackViewProvider {
	private static Logger logger = Logger.getLogger( XIOStackViewProvider.class );
	private String bottomLabel = "some other info";
	private String topLabel = "dummydevice";
	
	@Override
	public String getBottomLabel( DeviceIdentity identity ) {
		logger.info( "----#### XIOStackViewProvider:getBottomLabel ####----" );
		return this.bottomLabel;
	}
	@Override
	public String getTopLabel( DeviceIdentity identity ) {
		logger.info( "----#### XIOStackViewProvider:getTopLabel ####----" );
		return this.topLabel;
	}
	@Override
	public boolean isApplicable( DeviceIdentity identity ) {
		logger.info( "----#### XIOStackViewProvider:isApplicable ####----" );
		if (identity != null) {
			//if (identity.getDeviceIp().equals("172.25.168.60")) {
			return true;
			//}
		}
		return false;
	}

}
