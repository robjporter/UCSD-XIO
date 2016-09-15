package com.cloupia.feature.xio.reports.charts.pie;

import java.util.List;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.inventory.model.XIODrive;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.ReportNameValuePair;
import com.cloupia.model.cIM.SnapshotReport;
import com.cloupia.model.cIM.SnapshotReportCategory;
import com.cloupia.service.cIM.inframgr.SnapshotReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;

public class XIOPieReadWriteLatencyReportImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIOPieReportDiskUsageImpl.class );

	@Override
	public SnapshotReport getSnapshotReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIOPieReadWriteLatencyReportImpl:getSnapshotReport ####----" );
		SnapshotReport report = new SnapshotReport();
        report.setContext( context );
        report.setReportName( reportEntry.getReportLabel());
        report.setNumericalData( true );
        report.setDisplayAsPie( true );
        report.setPrecision( 0 );   

		String accName = context.getId().split(";")[0];

		ObjStore<XIODrive> dummyAssignStore = ObjStoreHelper.getStore( XIODrive.class );
		List<XIODrive> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		int readlatency_count = 0;
		int writelatency_count =0;
		for (int i = 0; i < objs.size(); i++) {
			XIODrive pojo = objs.get( i );
			readlatency_count += Integer.parseInt( pojo.getReadLatencyMax());
			writelatency_count += Integer.parseInt( pojo.getWriteLatencyMax());
		}
		readlatency_count = (int) Math.ceil((double)readlatency_count / objs.size());
		writelatency_count = (int) Math.ceil((double)writelatency_count / objs.size());
		logger.info( "----#### XIOPieReadWriteLatencyReportImpl: READ TOTAL = " + readlatency_count );
		logger.info( "----#### XIOPieReadWriteLatencyReportImpl: WRITE TOTAL = " + writelatency_count );
		
        ReportNameValuePair[] rnv = new ReportNameValuePair[ 2 ];
        
        rnv[ 0 ] = new ReportNameValuePair( "Read Latency", readlatency_count );
        rnv[ 1 ] = new ReportNameValuePair( "Write Latency", writelatency_count );

        SnapshotReportCategory cat = new SnapshotReportCategory();
        cat.setCategoryName( "Latency ms" );
        cat.setNameValuePairs( rnv );
        report.setCategories( new SnapshotReportCategory[] { cat });
        
		return report;
	}
}
