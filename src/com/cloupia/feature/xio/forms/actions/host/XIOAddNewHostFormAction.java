package com.cloupia.feature.xio.forms.actions.host;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.device.functions.XIOFunctions;
import com.cloupia.feature.xio.forms.host.XIOAddNewHostForm;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class XIOAddNewHostFormAction  extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger( XIOAddNewHostFormAction.class );
	private static final String formId = "xio.add.host.form";
	private static final String ACTION_ID = "xio.add.host.form.action";
	private static final String LABEL = "Create Host";
	
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
		page.bind( formId, XIOAddNewHostForm.class );
	}
	@Override
	public void loadDataToPage( Page page, ReportContext context, WizardSession session ) throws Exception {
		String query = context.getId();
		logger.info( "##################XIOAddNewHostFormAction:loadDataToPage##################" );
		logger.info( "#### CONTEXT ID = " + query );
		logger.info( "##################XIOAddNewHostFormAction:loadDataToPage##################" );
		XIOAddNewHostForm form = new XIOAddNewHostForm();
		form.setName( "Host Name" );
		form.setOperatingSystem( "Windows" );
		form.setWWPN( "00:00:00:00:00:00:00:00" );
		session.getSessionAttributes().put( formId, form );
		page.marshallFromSession( formId );
	}
	@Override
	public int validatePageData( Page page, ReportContext context, WizardSession session ) throws Exception {
		Object obj = page.unmarshallToSession( formId );
		XIOAddNewHostForm form = (XIOAddNewHostForm) obj;
		String id = context.getId();
		String name = form.getName();
		String operatingsystem = form.getOperatingSystem();
		String wwpn = form.getWWPN();
		String comment = form.getComment();

		XIOFunctions func = new XIOFunctions();
		if( func.createNewHost( id, name, operatingsystem, wwpn, comment )) {
			page.setPageMessage( "New Host " + name + " was created successfully!" );
			return PageIf.STATUS_OK;
		} else {
			page.setPageMessage( "Host creation failed!" );
			return PageIf.STATUS_ERROR;
		}
	}
	@Override
	public String getTitle() {
		return LABEL;
	}
}
