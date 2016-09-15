package com.cloupia.feature.xio.lovs;

import org.apache.log4j.Logger;

import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.forms.wizard.LOVProviderIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;

public class XIOOperatingSystemLovProvider implements LOVProviderIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOOperatingSystemLovProvider.class );
	public static final String SIMPLE_LOV_PROVIDER = "xio_operating_system_lov_provider";

	@Override
	public FormLOVPair[] getLOVs( WizardSession session ) {
		FormLOVPair[] pairs = new FormLOVPair[2];
		FormLOVPair pair1 = new FormLOVPair( "Windows", "windows" );
		FormLOVPair pair2 = new FormLOVPair( "VMware", "vmware" );
		pairs[0] = pair1;
		pairs[1] = pair2;
		return pairs;
	}

}
