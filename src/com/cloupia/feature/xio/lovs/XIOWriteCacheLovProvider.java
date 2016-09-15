package com.cloupia.feature.xio.lovs;

import org.apache.log4j.Logger;

import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.forms.wizard.LOVProviderIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;

public class XIOWriteCacheLovProvider implements LOVProviderIf {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger( XIOWriteCacheLovProvider.class );
	public static final String SIMPLE_LOV_PROVIDER = "xio_writecache_lov_provider";

	@Override
	public FormLOVPair[] getLOVs( WizardSession session ) {
		FormLOVPair[] pairs = new FormLOVPair[2];
		FormLOVPair pair1 = new FormLOVPair( "Write-Back", "write-back" );
		FormLOVPair pair2 = new FormLOVPair( "Write-Through", "write-through" );
		pairs[0] = pair1;
		pairs[1] = pair2;
		return pairs;
	}

}
