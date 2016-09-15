package com.cloupia.feature.xio.triggers;

import org.apache.log4j.Logger;
import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.thresholdmonitor.MonitoredContextIf;

public class MonitorDummyDeviceType implements MonitoredContextIf {
	private static Logger logger = Logger.getLogger( MonitorDummyDeviceType.class );
	private String contextLabel = "Dummy Device";
	
	@Override
	public int getContextType() {
		logger.info( "----#### MonitorDummyDeviceType:getContextType ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.XIO_CONTEXT_ONE_NAME );
		return dummyContextOneType.getType();
	}
	@Override
	public String getContextLabel() {
		logger.info( "----#### MonitorDummyDeviceType:getContextLabel ####----" );
		return this.contextLabel;
	}
	@Override
	public FormLOVPair[] getPossibleLOVs( WizardSession session ) {
		logger.info( "----#### MonitorDummyDeviceType:getPossibleLOVs ####----" );
		FormLOVPair deviceOne = new FormLOVPair( "ddOne", "ddOne" );
		FormLOVPair deviceTwo = new FormLOVPair( "ddTwo", "ddTwo" );
		FormLOVPair[] dummyDevices = { deviceOne, deviceTwo };
		return dummyDevices;
	}
	@Override
	public String getContextValueDetail( String selectedContextValue ) {
		logger.info( "----#### MonitorDummyDeviceType:getContextValueDetail ####----" );
		return "you picked " + selectedContextValue;
	}
	@Override
	public String getCloudType( String selectedContextValue ) {
		logger.info( "----#### MonitorDummyDeviceType:getCloudType ####----" );
		return null;
	}
}
