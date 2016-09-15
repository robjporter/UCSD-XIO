package com.cloupia.feature.xio.forms.actions.mapping;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.forms.mapping.XIOEditMappingForm;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class XIOEditMappingFormAction extends CloupiaPageAction {

	private static Logger logger = Logger.getLogger( XIOEditMappingFormAction.class );

	//need to provide a unique string to identify this form and action (note: prefix is module id, good practice)
	private static final String formId = "xio.edit.mapping.form";
	private static final String ACTION_ID = "xio.edit.mapping.form.action";
	//this is the label show in UI for this action
	private static final String label = "Edit Mapping";

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
		return label;
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
		page.bind( formId, XIOEditMappingForm.class );
	}

	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		//the report context id is the value of the selected row's management column index.  If you'll notice,
		//in my DummyVLANsReport I explicitly set the mgmtColumnIndex to 1, this means I want the 2nd column of the
		//table (index starts at 0) to be used as the context ids
		String query = context.getId();
		logger.info( "##################XIOAddNewLunFormAction:loadDataToPage##################" );
		logger.info( "#### CONTEXT ID = " + query );
		logger.info( "##################XIOAddNewLunFormAction:loadDataToPage##################" );
		//just setting some dummy data for demo purposes
		XIOEditMappingForm form = new XIOEditMappingForm();
		form.setName("dummy name - hardcoded for demo purposes");
		//once my form object is ready, i just need to place it into the session attributes and use the 
		//marshall from session function as shown below
		session.getSessionAttributes().put(formId, form);
		page.marshallFromSession(formId);
	}

	//this is called when the user hits the submit button in the UI
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		//use the unmarshall to session method to retrieve all the data the user entered in the UI.
		//you use this method to get the form object binded during definePage, anything changes the user made
		//in the UI will be accessible now in the returned object once you call unmarshall to session!
		Object obj = page.unmarshallToSession(formId);
		XIOEditMappingForm form = (XIOEditMappingForm) obj;
		int number = form.getNumber();
		boolean boolValue = form.getBoolType();

		logger.info( "##################XIOEditLunFormAction:validatePageData##################" );
		logger.info( "#### CONTEXT ID = " + context.getId() );
		logger.info( "#### NAME = " + form.getName() );
		logger.info( "#### Number Value = " + number );
		logger.info( "#### Bool Value = " + boolValue );
		logger.info( "##################XIOEditLunFormAction:validatePageData##################" );
		
		String name = form.getName();
		if (name.equals("fail")) {
			//to signal an error you can throw an exception OR return error status
			page.setPageMessage("simple dummy action failed!");
			return PageIf.STATUS_ERROR;
		} else {
			//if you want to display a message to the user, use page.SetPageMessage(...)
			page.setPageMessage("simple dummy action was completed!");
			//return this constant so the UI will display to the user, the action has succeeded.
			return PageIf.STATUS_OK;
		}
	}

	@Override
	public String getTitle() {
		return label;
	}


}