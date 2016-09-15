package com.cloupia.feature.xio.triggers;

import org.apache.log4j.Logger;
import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.thresholdmonitor.MonitoredParameterIf;

public class MonitorDummyDeviceStatusParam implements MonitoredParameterIf {
	private static Logger logger = Logger.getLogger( MonitorDummyDeviceStatusParam.class );
	private String deviceStatusLabel = "Dummy Device Status";
	private String deviceStatusName = "xio.dummy.device.status";
	
	@Override
	public String getParamLabel() {
		logger.info( "----#### MonitorDummyDeviceStatusParam:getParamLabel ####----" );
		return this.deviceStatusLabel;
	}
	@Override
	public String getParamName() {
		logger.info( "----#### MonitorDummyDeviceStatusParam:getParamName ####----" );
		return this.deviceStatusName;
	}
	@Override
	public FormLOVPair[] getSupportedOps() {
		logger.info( "----#### MonitorDummyDeviceStatusParam:getSupportedOps ####----" );
		FormLOVPair isOp = new FormLOVPair( "is", "is" );
		FormLOVPair[] ops = { isOp };
		return ops;
	}
	@Override
	public int getValueConstraintType() {
		logger.info( "----#### MonitorDummyDeviceStatusParam:getValueConstraintType ####----" );
		return 0;
	}
	@Override
	public FormLOVPair[] getValueLOVs() {
		logger.info( "----#### MonitorDummyDeviceStatusParam:getValueLOVs ####----" );
		FormLOVPair valueUP = new FormLOVPair( "Up", "up" );
		FormLOVPair valueDOWN = new FormLOVPair( "Down", "down" );
		FormLOVPair valueUNKNOWN = new FormLOVPair( "Unknown", "unknown" );
		FormLOVPair[] statuses = { valueDOWN, valueUNKNOWN, valueUP };
		return statuses;
	}
	@Override
	public int getApplicableContextType() {
		logger.info( "----#### MonitorDummyDeviceStatusParam:getApplicableContextType ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.XIO_CONTEXT_ONE_NAME );
		return dummyContextOneType.getType();
	}
	@Override
	public String getApplicableCloudType() {
		logger.info( "----#### MonitorDummyDeviceStatusParam:getApplicableCloudType ####----" );
		return null;
	}
	@Override
	public int checkTrigger( StringBuffer messageBuf, int contextType, String objects, String param, String op, String values ) {
		logger.info( "----#### MonitorDummyDeviceStatusParam:checkTrigger ####----" );
		if( objects.equals( "ddOne" )) {
			if( op.equals( "is" )) {
				if( values.equals( "up" )) {
					return RULE_CHECK_TRIGGER_ACTIVATED;
				} else {
					return RULE_CHECK_TRIGGER_NOT_ACTIVATED;
				}
			} else {
				return RULE_CHECK_ERROR;
			}
		} else {
			if( op.equals( "is" )) {
				if( values.equals( "up" )) {
					return RULE_CHECK_TRIGGER_NOT_ACTIVATED;
				} else {
					return RULE_CHECK_TRIGGER_ACTIVATED;
				}
			} else {
				return RULE_CHECK_ERROR;
			}
		}
	}
}
