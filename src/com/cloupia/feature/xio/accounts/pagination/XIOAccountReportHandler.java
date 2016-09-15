package com.cloupia.feature.xio.accounts.pagination;

import org.apache.log4j.Logger;

import com.cloupia.model.cIM.ColumnDefinition;
import com.cloupia.model.cIM.Query;
import com.cloupia.model.cIM.QueryBuilder;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.pagination.PaginatedReportHandler;
import com.cloupia.model.cIM.pagination.TabularReportMetadata;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;

public class XIOAccountReportHandler extends PaginatedReportHandler {
	private static Logger logger = Logger.getLogger( XIOAccountReportHandler.class );

	@Override
	public Query appendContextSubQuery( ReportRegistryEntry entry, TabularReportMetadata md, ReportContext rc, Query query ) {
		logger.info( "----#### XIOAccountReportHandler:appendContextSubQuery ####----" );
		String contextID = rc.getId();

		//logger.info( "################XIOAccountReportHandler:appendContextSubQuery################1" );
		//logger.info( "# Account Name = " + contextID );
		//logger.info( "# Context = " + rc.toString());
		//logger.info( "# Query = " + query );
		//logger.info( "################XIOAccountReportHandler:appendContextSubQuery################1" );
		
		//contextID = "XIO;testing";
		
		if( contextID != null && !contextID.isEmpty()) {
			String str[] = contextID.split( ";" );
			String accountName = str[ 0 ];
			
			logger.info( "################XIOAccountReportHandler:appendContextSubQuery################2" );
			logger.info( "# Account Name = " + accountName );
			logger.info( "################XIOAccountReportHandler:appendContextSubQuery################2" );
			
			int mgmtColIndex = entry.getManagementColumnIndex();
			ColumnDefinition[] colDefs = md.getColumns();
			ColumnDefinition mgmtCol = colDefs[mgmtColIndex];
			String colId = mgmtCol.getColumnId();
			QueryBuilder sqb = new QueryBuilder();
			sqb.putParam( colId ).eq( accountName );

			if( query == null ) {
				Query q = sqb.get();
				return q;
			} else {
				QueryBuilder qb = new QueryBuilder();
				qb.and(query, sqb.get());
				return qb.get();
			}
		} else {
			/*
			 * UserSession session = UserSessionUtil.getCurrentUserSession(); if
			 * (session == null) { return query; } try {
			 * session.retrieveProfileAndAccess(); if
			 * (session.getAccess().isMSPUser()) { logger.info("MSP User");
			 * Group[] grpList =
			 * GroupManagerImpl.getCustomerGroupsUnderMSP(Integer.parseInt(
			 * session.getLoginProfile().getCustomerId())); QueryBuilder sqb =
			 * new QueryBuilder(); Query [] queries = new Query[grpList.length];
			 * int index = 0; for(Group g : grpList) { queries[index++] = new
			 * QueryBuilder().putParam("groupId").eq( g.getGroupId()).get(); }
			 * query = sqb.or(queries).get(); } } catch(Exception ex) {
			 * logger.error("Error while retrieving group info"); }
			 */

			//logger.info( "################XIOAccountReportHandler:appendContextSubQuery################5" );
			//if( query != null ) { logger.info( "# QUERY = " + query.toString() ); } else { logger.info( "# QUERY = NULL" ); }
			//logger.info( "################XIOAccountReportHandler:appendContextSubQuery################5" );
			
			return query;
		}
	}
}
