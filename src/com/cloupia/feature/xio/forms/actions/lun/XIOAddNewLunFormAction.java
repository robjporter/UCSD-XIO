package com.cloupia.feature.xio.forms.actions.lun;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.device.functions.XIOFunctions;
import com.cloupia.feature.xio.forms.lun.XIOAddNewLunForm;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class XIOAddNewLunFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger( XIOAddNewLunFormAction.class );
	private static final String formId = "xio.add.lun.form";
	private static final String ACTION_ID = "xio.add.lun.form.action";
	private static final String LABEL = "Create Lun";
	
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
		page.bind( formId, XIOAddNewLunForm.class );
		
	}
	@Override
	public void loadDataToPage( Page page, ReportContext context, WizardSession session ) throws Exception {
		XIOAddNewLunForm form = new XIOAddNewLunForm();
		form.setName( "LUN Name" );
		form.setSize( 10 );
		form.setRedundancy( "RAID-1" );
		session.getSessionAttributes().put( formId, form );
		page.marshallFromSession( formId );
	}
	@Override
	public int validatePageData( Page page, ReportContext context, WizardSession session ) throws Exception {
		String accName = context.getId().split(";")[0];
		Object obj = page.unmarshallToSession( formId );
		XIOAddNewLunForm form = (XIOAddNewLunForm) obj;
		String id = context.getId();
		String name = form.getName();
		String redundancy = form.getRedundancy();
		int size = form.getSize();
		String cache = form.getWriteCache();
		String comment = form.getComment();

		XIOFunctions func = new XIOFunctions();
		
		String response = func.createNewLun( accName, id, name, size, redundancy, cache, comment );
		
		String results[] = response.split( "--" );
		String status = results[ 0 ].trim();
		String resp = results[ 1 ].trim();
		
		if( status.equals( XIOConstants.XIO_HTTP_STR_CODE_OK )) {
			page.setPageMessage( resp );
			func.getVolumesExternal( accName );
			page.setRefreshInSeconds( 10 );
			logger.info( "#### STATUS 2 = " + XIOConstants.XIO_HTTP_STR_CODE_OK );
			return PageIf.STATUS_OK;
		} else if( status.equals( XIOConstants.XIO_HTTP_STR_CODE_CONFLICT )){
			page.setPageMessage( "Lun name already in use!" );
			logger.info( "#### STATUS 4 = " + XIOConstants.XIO_HTTP_STR_CODE_CONFLICT );
			return PageIf.STATUS_ERROR;
		} else {
			page.setPageMessage( "Lun creation failed!" );
			logger.info( "#### STATUS = " + status );
			return PageIf.STATUS_ERROR;
		}
	}
	@Override
	public String getTitle() {
		return LABEL;
	}
}
