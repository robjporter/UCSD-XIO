package com.cloupia.feature.xio.reports.device;

import org.apache.log4j.Logger;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaEasyReportQueryBuilder;

public class XIOQueryBuilder extends CloupiaEasyReportQueryBuilder{
	private static Logger logger = Logger.getLogger( XIOQueryBuilder.class );
	
	@Override
	public String buildQuery( ReportContext context ) {

		logger.info( "################XIOQueryBuilder:buildQuery################" );
		logger.info( "# Context = " + context.toString() );
		logger.info( "################XIOQueryBuilder:buildQuery################" );
		
		String[] arr =  null;
		if(context.getId() != null){
			arr = context.getId().split(";");
			return "accountName == '" + arr[ 0 ] + "'";
		}
		return null;
	}
}