package com.cloupia.feature.xio.reports.device.lun;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.forms.actions.lun.XIOAddNewLunFormAction;
import com.cloupia.feature.xio.forms.actions.lun.XIODeleteLunFormAction;
import com.cloupia.feature.xio.forms.actions.lun.XIOEditLunFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class XIODeviceLunReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( XIODeviceLunReport.class );
	private static final String NAME = "xio.device.lun.report";
	private static final String LABEL = "LUN's";

	public XIODeviceLunReport() {
		super();
		logger.info( "----#### XIODeviceLunReport:XIODeviceLunReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		//logger.info( "----#### XIODeviceLunReport:getActions ####----" );
		XIOAddNewLunFormAction newLunAction = new XIOAddNewLunFormAction();
		XIOEditLunFormAction editLunAction = new XIOEditLunFormAction();
		XIODeleteLunFormAction deleteLunAction = new XIODeleteLunFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 3 ];
		actions[ 0 ] = newLunAction;
		actions[ 1 ] = editLunAction;
		actions[ 2 ] = deleteLunAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIODeviceLunReport:getImplementationClass ####----" );
		return XIODeviceLunReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIODeviceLunReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIODeviceLunReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIODeviceLunReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIODeviceLunReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		//logger.info( "----#### XIODeviceLunReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODeviceLunReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
