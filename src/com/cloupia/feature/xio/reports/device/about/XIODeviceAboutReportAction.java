package com.cloupia.feature.xio.reports.device.about;

import org.apache.log4j.Logger;

import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class XIODeviceAboutReportAction extends CloupiaPageAction {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIODeviceAboutReportAction.class );
	private static final String formId = "xio.device.about.form";
	private static final String ACTION_ID = "xio.device.about.action";
	private static final String LABEL = "label unused, report label overrides this when using a config form!";

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
	public void definePage(Page page, ReportContext context) {
		page.bind( formId, XIODeviceAboutReportObject.class );
		page.setSubmitButton( "" );
	}
	@Override
	public void loadDataToPage( Page page, ReportContext context, WizardSession session ) throws Exception {
		page.marshallFromSession( formId );
	}
	@Override
	public int validatePageData( Page page, ReportContext context, WizardSession session ) throws Exception {
		return PageIf.STATUS_OK;
	}
	@Override
	public String getTitle() {
		return LABEL;
	}
}