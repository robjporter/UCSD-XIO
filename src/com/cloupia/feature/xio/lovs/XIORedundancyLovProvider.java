package com.cloupia.feature.xio.lovs;

import org.apache.log4j.Logger;

import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.forms.wizard.LOVProviderIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;

public class XIORedundancyLovProvider implements LOVProviderIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIORedundancyLovProvider.class );
	public static final String SIMPLE_LOV_PROVIDER = "xio_redundancy_lov_provider";

	@Override
	public FormLOVPair[] getLOVs( WizardSession session ) {
		FormLOVPair[] pairs = new FormLOVPair[2];
		FormLOVPair pair1 = new FormLOVPair( "RAID-1", "1" );
		FormLOVPair pair2 = new FormLOVPair( "RAID-5", "5" );
		pairs[0] = pair1;
		pairs[1] = pair2;
		return pairs;
	}

}
