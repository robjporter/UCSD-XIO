package com.cloupia.feature.xio.forms.actions.host;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.device.functions.XIOFunctions;
import com.cloupia.feature.xio.forms.host.XIOEditHostForm;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class XIOEditHostFormAction extends CloupiaPageAction {

	private static Logger logger = Logger.getLogger( XIOEditHostFormAction.class );

	//need to provide a unique string to identify this form and action (note: prefix is module id, good practice)
	private static final String formId = "xio.edit.host.form";
	private static final String ACTION_ID = "xio.edit.host.form.action";
	//this is the label show in UI for this action
	private static final String label = "Edit Host";

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
		page.bind( formId, XIOEditHostForm.class );
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
		XIOEditHostForm form = new XIOEditHostForm();
		
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
		XIOEditHostForm form = (XIOEditHostForm) obj;
		String id = context.getId();
		String name = form.getName();
		String operatingsystem = form.getOperatingSystem();
		String wwpn = form.getWWPN();
		String comment = form.getComment();

		XIOFunctions func = new XIOFunctions();
		if( func.editHost( id, name, operatingsystem, wwpn, comment )) {
			page.setPageMessage( "Edited Host " + name + " successfully!" );
			return PageIf.STATUS_OK;
		} else {
			page.setPageMessage( "Updating Host failed!" );
			return PageIf.STATUS_ERROR;
		}
	}

	@Override
	public String getTitle() {
		return label;
	}


}