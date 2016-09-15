package com.cloupia.feature.xio.reports.charts.pie;

import java.util.List;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.inventory.model.XIOData;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.ReportNameValuePair;
import com.cloupia.model.cIM.SnapshotReport;
import com.cloupia.model.cIM.SnapshotReportCategory;
import com.cloupia.service.cIM.inframgr.SnapshotReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;

public class XIOPieReportDiskUsageImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIOPieReportDiskUsageImpl.class );

	@Override
	public SnapshotReport getSnapshotReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIOPieReportDiskUsageImpl:getSnapshotReport ####----" );
		SnapshotReport report = new SnapshotReport();
        report.setContext( context );
        report.setReportName( reportEntry.getReportLabel());
        report.setNumericalData( true );
        report.setDisplayAsPie( true );
        report.setPrecision( 0 );   
        ReportNameValuePair[] rnv = new ReportNameValuePair[ 2 ];
        
		String accName = context.getId().split(";")[0];
		ObjStore<XIOData> dummyAssignStore = ObjStoreHelper.getStore( XIOData.class );
		List<XIOData> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );

		int used_count = 0;
		int total_count = 0;
		if( objs.size() == 1 ) {
			XIOData pojo = objs.get( 0 );
			used_count = Integer.parseInt( pojo.getTotalLunUsage());
			total_count = Integer.parseInt( pojo.getTotalCapacity());
		}
		
		logger.info( "----#### XIOPieReportDiskUsageImpl: USED ####----" + used_count );
		logger.info( "----#### XIOPieReportDiskUsageImpl: TOTAL ####----" + total_count );
		
        rnv[ 0 ] = new ReportNameValuePair( "Used Space in GB", used_count );
        rnv[ 1 ] = new ReportNameValuePair( "Unused Space in GB", total_count );

        SnapshotReportCategory cat = new SnapshotReportCategory();
        cat.setCategoryName( "Disk Space Usage" );
        cat.setNameValuePairs( rnv );
        report.setCategories( new SnapshotReportCategory[] { cat });
        
		return report;
	}
}
