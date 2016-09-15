package com.cloupia.feature.xio.reports.device.summary;

import java.util.List;

import org.apache.log4j.Logger;

import com.cloupia.feature.xio.inventory.model.XIODeviceChrono;
import com.cloupia.feature.xio.inventory.model.XIOManagement;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.SummaryReportInternalModel;

public class XIODeviceSummaryReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( XIODeviceSummaryReportImpl.class );
	private static final String XIO_CHRONO_TABLE = "Chronometer";
	private static final String XIO_NETWORK_TABLE = "Network Info";
	private static final String[] GROUP_ORDER = { XIO_CHRONO_TABLE, XIO_NETWORK_TABLE  };

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### XIODeviceSummaryReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
        report.setContext( context );
        report.setGeneratedTime( System.currentTimeMillis());
        report.setReportName( reportEntry.getReportLabel());
        SummaryReportInternalModel model = new SummaryReportInternalModel();        

        XIODeviceChrono xioChrono = null;
		String accName = context.getId().split(";")[0];
        ObjStore<XIODeviceChrono> dummyAssignStore = ObjStoreHelper.getStore( XIODeviceChrono.class );
		List<XIODeviceChrono> summaryList = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		if( summaryList.isEmpty() == false ) {
			xioChrono = summaryList.get( 0 );

	        model.addText( "Scale", xioChrono.getScale(), XIO_CHRONO_TABLE );
	        model.addText( "System Date", xioChrono.getDate(), XIO_CHRONO_TABLE );
	        model.addText( "System Time", xioChrono.getTime(), XIO_CHRONO_TABLE );
	        model.addText( "Timezone", xioChrono.getTimezone(), XIO_CHRONO_TABLE );
	        model.addText( "DST Active", xioChrono.getDST(), XIO_CHRONO_TABLE );
	        model.addText( "Uptime Days", xioChrono.getDays(), XIO_CHRONO_TABLE );
	        model.addText( "Uptime Hours", xioChrono.getHours(), XIO_CHRONO_TABLE );
	        model.addText( "Uptime Mins", xioChrono.getMins(), XIO_CHRONO_TABLE );
	        model.addText( "Uptime Secs", xioChrono.getSecs(), XIO_CHRONO_TABLE );
	        model.addText( "NTP Mode", xioChrono.getNTP(), XIO_CHRONO_TABLE );
	        model.addText( "NTP Server", xioChrono.getNTPServer(), XIO_CHRONO_TABLE );
		}
        
		XIOManagement xioNetwork = null;
		ObjStore<XIOManagement> store2 = ObjStoreHelper.getStore( XIOManagement.class );
		List<XIOManagement> summaryList2 = store2.queryAll();
		
		if( summaryList2.isEmpty() == false ) {
			xioNetwork = summaryList2.get( 0 );
	        model.addText( "DHCP Status", xioNetwork.getDHCP(), XIO_NETWORK_TABLE );
	        model.addText( "Wake On LAN", xioNetwork.getWakeOnLan(), XIO_NETWORK_TABLE );
	        model.addText( "Controller 1 IP Address", xioNetwork.getIPAddress1(), XIO_NETWORK_TABLE );
	        model.addText( "Controller 1 Netmask", xioNetwork.getNetMask1(), XIO_NETWORK_TABLE );
	        model.addText( "Controller 1 Gateway", xioNetwork.getGateway1(), XIO_NETWORK_TABLE );
	        model.addText( "Controller 1 Status", xioNetwork.getStatus1(), XIO_NETWORK_TABLE );
	        model.addText( "Controller 2 IP Address", xioNetwork.getIPAddress2(), XIO_NETWORK_TABLE );
	        model.addText( "Controller 2 Netmask", xioNetwork.getNetMask2(), XIO_NETWORK_TABLE );
	        model.addText( "Controller 2 Gateway", xioNetwork.getGateway2(), XIO_NETWORK_TABLE );
	        model.addText( "Controller 2 Status", xioNetwork.getStatus2(), XIO_NETWORK_TABLE );
	        model.addText( "Domain Server", xioNetwork.getDomainServer(), XIO_NETWORK_TABLE );
	        model.addText( "Name Server 1", xioNetwork.getNameServer1(), XIO_NETWORK_TABLE );
	        model.addText( "Name Server 2", xioNetwork.getNameServer2(), XIO_NETWORK_TABLE );
		}
		
        model.setGroupOrder( GROUP_ORDER );
        model.updateReport( report );
        
        return report;
	}
}
