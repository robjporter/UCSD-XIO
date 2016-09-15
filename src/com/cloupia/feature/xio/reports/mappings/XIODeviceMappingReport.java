package com.cloupia.feature.xio.reports.mappings;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.forms.actions.mapping.XIOAddNewMappingFormAction;
import com.cloupia.feature.xio.forms.actions.mapping.XIODeleteMappingFormAction;
import com.cloupia.feature.xio.forms.actions.mapping.XIOEditMappingFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class XIODeviceMappingReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( XIODeviceMappingReport.class );
	private static final String NAME = "xio.device.mapping.report";
	private static final String LABEL = "Mappings";

	public XIODeviceMappingReport() {
		super();
		logger.info( "----#### XIODeviceMappingReport:XIODeviceMappingReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		//logger.info( "----#### XIODeviceMappingReport:getActions ####----" );
		XIOAddNewMappingFormAction newLunAction = new XIOAddNewMappingFormAction();
		XIOEditMappingFormAction editLunAction = new XIOEditMappingFormAction();
		XIODeleteMappingFormAction deleteLunAction = new XIODeleteMappingFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 3 ];
		actions[ 0 ] = newLunAction;
		actions[ 1 ] = editLunAction;
		actions[ 2 ] = deleteLunAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		//logger.info( "----#### XIODeviceMappingReport:getImplementationClass ####----" );
		return XIODeviceMappingReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		//logger.info( "----#### XIODeviceMappingReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		//logger.info( "----#### XIODeviceMappingReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		//logger.info( "----#### XIODeviceMappingReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		//logger.info( "----#### XIODeviceMappingReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		//logger.info( "----#### XIODeviceMappingReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		//logger.info( "----#### XIODeviceMappingReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( XIOConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}