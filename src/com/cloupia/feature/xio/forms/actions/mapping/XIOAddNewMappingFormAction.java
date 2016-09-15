package com.cloupia.feature.xio.forms.actions.mapping;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.forms.mapping.XIOAddNewMappingForm;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class XIOAddNewMappingFormAction  extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger( XIOAddNewMappingFormAction.class );
	private static final String formId = "xio.add.mapping.form";
	private static final String ACTION_ID = "xio.add.mapping.form.action";
	private static final String LABEL = "Create Mapping";
	
	@Override
	public String getActionId() {
		return ACTION_ID;
	}
	public String getFormId() {
		return formId;
	}
	@Override
	public String getLabel() {
		return LABEL;
	}
	@Override
	public int getActionType() {
		return ConfigTableAction.ACTION_TYPE_POPUP_FORM;
	}
	@Override
	public boolean isSelectionRequired() {
		return false;
	}
	@Override
	public boolean isDoubleClickAction() {
		return false;
	}
	@Override
	public boolean isDrilldownAction() {
		return false;
	}
	@Override
	public void definePage( Page page, ReportContext context ) {
		page.bind( formId, XIOAddNewMappingForm.class );
	}
	@Override
	public void loadDataToPage( Page page, ReportContext context, WizardSession session ) throws Exception {
		String query = context.getId();
		logger.info( "##################XIOAddNewLunFormAction:loadDataToPage##################" );
		logger.info( "#### CONTEXT ID = " + query );
		logger.info( "##################XIOAddNewLunFormAction:loadDataToPage##################" );
		XIOAddNewMappingForm form = new XIOAddNewMappingForm();
		form.setName( "Host Name" );
		session.getSessionAttributes().put( formId, form );
		page.marshallFromSession( formId );
	}
	@Override
	public int validatePageData( Page page, ReportContext context, WizardSession session ) throws Exception {
		Object obj = page.unmarshallToSession( formId );
		XIOAddNewMappingForm form = (XIOAddNewMappingForm) obj;
		int number = form.getNumber();
		boolean boolValue = form.getBoolType();

		logger.info( "##################XIOAddNewLunFormAction:validatePageData##################" );
		logger.info( "#### CONTEXT ID = " + context.getId() );
		logger.info( "#### NAME = " + form.getName() );
		logger.info( "#### Number Value = " + number );
		logger.info( "#### Bool Value = " + boolValue );
		logger.info( "##################XIOAddNewLunFormAction:validatePageData##################" );

		String name = form.getName();
		if( name.equals( "fail" )) {
			page.setPageMessage( "simple dummy action failed!" );
			return PageIf.STATUS_ERROR;
		} else {
			page.setPageMessage( "simple dummy action was completed!" );
			return PageIf.STATUS_OK;
		}
	}
	@Override
	public String getTitle() {
		return LABEL;
	}
}
