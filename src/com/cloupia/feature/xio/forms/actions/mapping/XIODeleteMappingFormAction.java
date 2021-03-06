package com.cloupia.feature.xio.forms.actions.mapping;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.forms.mapping.XIODeleteMappingForm;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class XIODeleteMappingFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger( XIODeleteMappingFormAction.class );
	private static final String formId = "xio.delete.mapping.form";
	private static final String ACTION_ID = "xio.delete.mapping.form.action";
	private static final String LABEL = "Delete Mapping";
	
	@Override
	public String getActionId() {
		return ACTION_ID;
	}
	public String getFormId()
	{
		return formId;
	}
	@Override
	public String getLabel() {
		return LABEL;
	}
	@Override
	public String getTitle() {
		return LABEL;
	}
	@Override
	public int getActionType() {
		//just assume you need to pass this constant for this method for any UI actions
		return ConfigTableAction.ACTION_TYPE_POPUP_FORM;
	}
	@Override
	public boolean isSelectionRequired() {
		//return true when a row needs to be selected for this action to proceed
		//return false if no row selection is required
		return true;
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
		page.bind(formId, XIODeleteMappingForm.class);
		logger.info( "##################XIODeleteLunFormAction:definePage##################" );
		logger.info( "#### CONTEXT ID = " + context.getId() );
		logger.info( "##################XIODeleteLunFormAction:definePage##################" );
	}
	@Override
	public void loadDataToPage( Page page, ReportContext context, WizardSession session) throws Exception {
		String query = context.getId();
		XIODeleteMappingForm form = new XIODeleteMappingForm();
		String accountName = context.getId().split(";")[0];
		logger.info( "##################XIOAddNewLunFormAction:loadDataToPage##################" );
		logger.info( "#### CONTEXT ID = " + query );
		logger.info( "#### AccountName  = " + accountName );
		logger.info( "##################XIOAddNewLunFormAction:loadDataToPage##################" );
		
		form.setMessage("Delete the selected Host?");
		session.getSessionAttributes().put(formId, form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		Object obj = page.unmarshallToSession(formId);
		XIODeleteMappingForm form = (XIODeleteMappingForm) obj;
		String accountName = context.getId().split(";")[0];

		logger.info( "##################XIOEditLunFormAction:validatePageData##################" );
		logger.info( "#### CONTEXT ID = " + context.getId() );
		logger.info( "#### Number Value = " + form.getMessage() );
		logger.info( "#### AccountName = " + accountName );
		logger.info( "##################XIOEditLunFormAction:validatePageData##################" );
		page.setPageMessage( "Lun Deleted successfully." );
		return PageIf.STATUS_OK;
	}
}
