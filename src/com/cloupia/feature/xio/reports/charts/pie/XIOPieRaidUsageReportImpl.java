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

public class XIOPieRaidUsageReportImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIOPieReportDiskUsageImpl.class );

	@Override
	public SnapshotReport getSnapshotReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIOPieRaidUsageReportImpl:getSnapshotReport ####----" );
		SnapshotReport report = new SnapshotReport();
        report.setContext( context );
        report.setReportName( reportEntry.getReportLabel());
        report.setNumericalData( true );
        report.setDisplayAsPie( true );
        report.setPrecision( 0 );   

		String accName = context.getId().split(";")[0];
		ObjStore<XIOData> dummyAssignStore = ObjStoreHelper.getStore( XIOData.class );
		List<XIOData> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );

		int raid1_count = 0;
		int raid5_count = 0;
		if( objs.size() == 1 ) {
			XIOData pojo = objs.get( 0 );
			raid1_count = Integer.parseInt( pojo.getLunRaid1Count());
			raid5_count = Integer.parseInt( pojo.getLunRaid5Count());
		}

		logger.info( "----#### XIOPieRaidUsageReportImpl: RAID 1 ####----" + raid1_count );
		logger.info( "----#### XIOPieRaidUsageReportImpl: RAID 5 ####----" + raid5_count );

        ReportNameValuePair[] rnv = new ReportNameValuePair[ 2 ];
        
        rnv[ 0 ] = new ReportNameValuePair( "RAID-1", raid1_count );
        rnv[ 1 ] = new ReportNameValuePair( "RAID-5", raid5_count );

        SnapshotReportCategory cat = new SnapshotReportCategory();
        cat.setCategoryName( "Raid Usage" );
        cat.setNameValuePairs( rnv );
        report.setCategories( new SnapshotReportCategory[] { cat });
        
		return report;
	}
}
