package com.cloupia.feature.xio.device.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.cloupia.feature.xio.accounts.XIOAccount;
import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.device.XIODeviceInfo;
import com.cloupia.feature.xio.inventory.model.XIOCache;
import com.cloupia.feature.xio.inventory.model.XIOCapability;
import com.cloupia.feature.xio.inventory.model.XIOCapacity;
import com.cloupia.feature.xio.inventory.model.XIOController;
import com.cloupia.feature.xio.inventory.model.XIOData;
import com.cloupia.feature.xio.inventory.model.XIODeviceChrono;
import com.cloupia.feature.xio.inventory.model.XIODrive;
import com.cloupia.feature.xio.inventory.model.XIOEventLog;
import com.cloupia.feature.xio.inventory.model.XIOFCNetwork;
import com.cloupia.feature.xio.inventory.model.XIOHost;
import com.cloupia.feature.xio.inventory.model.XIOLun;
import com.cloupia.feature.xio.inventory.model.XIOManagement;
import com.cloupia.feature.xio.inventory.model.XIOMapping;
import com.cloupia.feature.xio.inventory.model.XIOMedia;
import com.cloupia.feature.xio.inventory.model.XIONetwork;
import com.cloupia.feature.xio.inventory.model.XIOPool;
import com.cloupia.feature.xio.inventory.model.XIOPowerSupply;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.cIaaS.network.model.DeviceCredential;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.lib.util.Base64Coder;
import com.cloupia.model.cIM.ReportContext;

@SuppressWarnings("unused")
public class XIOFunctions {
	private static Logger logger = Logger.getLogger( XIOFunctions.class );
	private DeviceCredential credential;
	private String accountName;
	private XIOData newData = new XIOData();
	private int intLunSize = 0;
	private String lastStatusResponse = null;
	
	public XIOFunctions() {
		//logger.info( "----#### XIOFunctions:XIOFunctions ####----" );
	}
	public XIOFunctions( DeviceCredential credential ) {
		//logger.info( "----#### XIOFunctions:XIOFunctions ####----" );
		this.credential = credential;
	}
	public DeviceCredential getCredential(){
		//logger.info( "----#### XIOFunctions:getCredential ####----" );
		return this.credential;	
	}
	public void setCredential( DeviceCredential credential ){
		//logger.info( "----#### XIOFunctions:setCredential ####----" );
		this.credential = credential;
	}
	// Reports to do
	// http://10.52.208.227/storage/pools
	// http://10.52.208.227/storage/arrays/3FE1001M/batteries
	public void getSummaryInventory( String accountName ) throws Exception {
		//logger.info( "----#### XIOFunctions:getSummaryInventory ####----" );
		this.accountName = accountName;
		this.newData.setAccountName( this.accountName );
		String baseURL = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
		
		try {
			String globalid = getDeviceGlobalID( this.accountName );
			String response = "";
			
			this.getControllers();
			this.getVolumes();
			
			
			// GROUP 3 - DRIVES
			response = getResponse( baseURL, "storage/arrays/" + globalid + "/performance", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 3 RESPONSE = " + response );
			if( response != null ) {
				getDriveInventory( response );
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 3 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 3 - COLLECTION FAILED" );
			}

			// GROUP 4 - CHRONOMETER
			response = getResponse( baseURL, "storage/arrays/" + globalid + "/chronometer", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 4 RESPONSE = " + response );
			if( response != null ) {
				getChronoInventory( response );
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 4 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 4 - COLLECTION FAILED" );
			}
			
			// GROUP 5 - MANAGEMENT INTERFACES
			response = getResponse( baseURL, "storage/arrays/" + globalid + "/network", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 5 RESPONSE = " + response );
			if( response != null ) {
				getManagementInventory( response );
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 5 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 5 - COLLECTION FAILED" );
			}
			
			// GROUP 6 - NETWORK INTERFACES
			response = getResponse( baseURL, "storage/arrays/" + globalid + "/controllers/1", "GET", this.credential.getLogin(), this.credential.getPassword() );
			String response2 = getResponse( baseURL, "storage/arrays/" + globalid + "/controllers/2", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 6 RESPONSE = " + response );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 6 RESPONSE 2 = " + response );
			if( response != null ) {
				getNetworkInventory( response, "Contoller1" );
				getNetworkInventory( response2, "Controller2" );
				getFCNetworkInventory( response, "Controller1" );
				getFCNetworkInventory( response2, "Controller2" );
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 6 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 6 - COLLECTION FAILED" );
			}
			
			// GROUP 7 - MEDIA
			response = getResponse( baseURL, "storage/arrays/" + globalid + "/media", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 7 RESPONSE = " + response );
			if( response != null ) {
				getMediaInventory( response );
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 7 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 7 - COLLECTION FAILED" );
			}
			
			// GROUP 8 - POWER SUPPLIES
			response = getResponse( baseURL, "storage/arrays/" + globalid + "/powersupplies", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 8 RESPONSE = " + response );
			if( response != null ) {
				getPowerSupplyInventory( response );
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 8 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "---------#### XIOFunctions:getSummaryInventory ####---- GROUP 8 - COLLECTION FAILED" );
			}
			
			// GROUP 9 - IOPMAPS
			response = getResponse( baseURL, "storage/arrays/" + globalid + "/iomap", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 9 RESPONSE = " + response );
			if( response != null ) {
				getIOPMAPSInventory( response );
				getCacheInventory( response, "(?<=<array)[\\s\\S]*?.*?(?=</array>)", "Array" );
				getCacheInventory( response, "(?<=<cadpactivity>)[\\s\\S]*?.*?(?=</cadpactivity>)", "CADP" );
				getCacheInventory( response, "(?<=<cachemiss>)[\\s\\S]*?.*?(?=</cachemiss>)", "CACHEMISS" );
				getCacheInventory( response, "(?<=<cachehit>)[\\s\\S]*?.*?(?=</cachehit>)", "CACHEHIT" );
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 9 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 9 - COLLECTION FAILED" );
			}
			
			// GROUP 10 - HOSTS
			response = getResponse( baseURL, "storage/endpoints", "GET", this.credential.getLogin(), this.credential.getPassword() );
			response2 = getResponse( baseURL, "storage/hosts", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 10 RESPONSE = " + response );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 10 RESPONSE2 = " + response2 );
			if( response != null ) {
				getEndpointInventory( response, response2 );
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 10 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 10 - COLLECTION FAILED" );
			}
			
			// GROUP 11 - MAPPINGS
			response = getResponse( baseURL, "storage/allocations", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 11 RESPONSE = " + response );
			if( response != null ) {
				getMappingInventory( response );
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 11 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 11 - COLLECTION FAILED" );
			}
			
			// GROUP 12 - LOGS
			response = getResponse( baseURL, "storage/arrays/" + globalid + "/files/event", "GET", this.credential.getLogin(), this.credential.getPassword() );
			//logger.info( "----#### XIOFunctions:getSummaryInventory ####---- GROUP 12 RESPONSE = " + response );
			if( response != null ) {
				getEventLogInventory( response );
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 12 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- GROUP 12 - COLLECTION FAILED" );
			}
						
			// LAST GROUP
			StoreInterestingData();	
			logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- LAST GROUP - COLLECTION SUCCEEDED" );
			
			
			logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- #############################" );
			logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- INVENTORY COLLECTION COMPLETE" );
			logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- #############################" );
		} catch( Exception e ) {
			logger.info( "----------#### XIOFunctions:getSummaryInventory ####---- COLLECTION FAILED", e );
			throw( e );
		}
	}
	public void getControllers() throws Exception {
		try {
			String url = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			String response = getResponse( url, "query/arrays", "GET", null, null );
			if( response != null ){
				getSystemOverview( response );
				getControllerInventory( response );
				getCapabilitiesInventory( response );
				logger.info( "----------#### XIOFunctions:getControllers ####---- GROUP 1 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "----------#### XIOFunctions:getControllers ####---- GROUP 1 - COLLECTION FAILED" );
			}
		} catch( Exception e ) {
			logger.info( "----------#### XIOFunctions:getControllers ####---- ERROR - " + e );
		}
	}
	public void getVolumesExternal( String accName ) throws Exception {
		try {
			PhysicalInfraAccount phyAccount = AccountUtil.getAccountByName( accName );
			DeviceCredential cred = phyAccount.toDeviceCredential();
			this.credential = cred;
			this.accountName = accName;
			
			String url = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";

			String response = getResponse( url, "storage/volumes", "GET", this.credential.getLogin(), this.credential.getPassword() );
			
			if( response != null ) {
				getLunInventoryExternal( accName, response );
				logger.info( "----------#### XIOFunctions:getVolumes ####---- GROUP 2 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "----------#### XIOFunctions:getVolumes ####---- GROUP 2 - COLLECTION FAILED" );
			}
		} catch( Exception e ) {
			logger.info( "----------#### XIOFunctions:getVolumes ####---- ERROR - " + e );
		}
	}
	public String getAccountNameFromLunID( String id ) throws Exception {
		if( id.isEmpty() ) {
			logger.info( "-------------#### XIOFunctions:getAccountDetailsFromLunID ####---- LUN ID IS BLANK" );
			return null;
		} else {
			String ret = "";
			ObjStore<XIOLun> store = ObjStoreHelper.getStore( XIOLun.class );
			String query = "id == '" + id + "'";
			logger.info( "-------------#### XIOFunctions:getLunDetailsFromID ####---- ID = " + id );
			List<XIOLun> objs = store.query( query ); 
			String accountName = "";
			if( objs.size() == 1 ) {
				XIOLun pojo = objs.get( 0 );
				accountName = pojo.getAccountName();
			}
			return accountName;
		}
	}
	public String getLunDetailsFromID( String id ) throws Exception {
		if( id.isEmpty() ) {
			logger.info( "-------------#### XIOFunctions:getLunDetailsFromID ####---- LUN ID IS BLANK" );
			return null;
		} else {
			ObjStore<XIOLun> store = ObjStoreHelper.getStore( XIOLun.class );
			String query = "id == '" + id + "'";
			logger.info( "-------------#### XIOFunctions:getLunDetailsFromID ####---- ID = " + id );
			List<XIOLun> objs = store.query( query ); 
			String lsize = "";
			String lname = "";
			String lrend = "";
			String lcache = "";if( objs.size() == 1 ) {
				XIOLun pojo = objs.get( 0 );
				lname = pojo.getName().trim();
				lsize = pojo.getSize().trim();
				lrend = pojo.getRedundancy().trim();
				lcache = pojo.getAffinity().trim();
			}
			
			logger.info( "#### getLunDetails NAME  = " + lname );
			logger.info( "#### getLunDetails SIZE  = " + lsize );
			logger.info( "#### getLunDetails REDUNDANCY  = " + lrend );
			logger.info( "#### getLunDetails CACHE  = " + lcache );
			
			return lname + "|" + lsize + "|" + lrend + "|" + lcache;
		}
	}
	public String getLunDetailsFromName( String name ) throws Exception {
		if( name.isEmpty() ) {
			logger.info( "-------------#### XIOFunctions:getLunDetailsFromName ####---- LUN NAME IS BLANK" );
			return null;
		} else {
			ObjStore<XIOLun> store = ObjStoreHelper.getStore( XIOLun.class );
			String query = "name == '" + name + "'";
			logger.info( "-------------#### XIOFunctions:getLunDetails ####---- NAME = " + name);
			List<XIOLun> objs = store.query( query ); 
			String lsize = "";
			String lname = "";
			String lrend = "";
			String lcache = "";
			if( objs.size() == 1 ) {
				XIOLun pojo = objs.get( 0 );
				lname = pojo.getName().trim();
				lsize = pojo.getSize().trim();
				lrend = pojo.getRedundancy().trim();
				lcache = pojo.getAffinity().trim();
			}
			
			logger.info( "#### getLunDetails NAME  = " + lname );
			logger.info( "#### getLunDetails SIZE  = " + lsize );
			logger.info( "#### getLunDetails REDUNDANCY  = " + lrend );
			logger.info( "#### getLunDetails CACHE  = " + lcache );
			
			return lname + "|" + lsize + "|" + lrend + "|" + lcache;
		}
	}
	private void getLunInventoryExternal( String accName, String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getLunInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOLun> store = ObjStoreHelper.getStore( XIOLun.class );
			long count = store.deleteAll();
			logger.info( "-------------#### XIOFunctions:getLunInventoryExternal ####---- LUNS DELETED = " + count + " ENTRIES" );
			
			List<String> volumes = parseXMLResponse( xml, "(?<=<volume[^s])[\\s\\S]*?.*?(?=</volume>)" );
			
			int intLunSize = 0;
			int intLunRaid1 = 0;
			int intLunRaid5 = 0;
			
			for( String volume : volumes ) {
				List<String> names = parseXMLResponse( volume, "(?<=<name>).*?(?=</name>)" );
				List<String> sizes = parseXMLResponse( volume, "(?<=<size>).*?(?=</size>)" );
				List<String> ids = parseXMLResponse( volume, "(?<=<localid>).*?(?=</localid>)" );
				List<String> gids = parseXMLResponse( volume, "(?<=<globalid>).*?(?=</globalid>)" );
				List<String> dates = parseXMLResponse( volume, "(?<=<createdate>).*?(?=</createdate>)" );
				List<String> redundancys = parseXMLResponse( volume, "(?<=<redundancy).*?(?=/>)" );
				List<String> comments = parseXMLResponse( volume, "(?<=<comment>).*?(?=</comment>)" );
				List<String> affinitys = parseXMLResponse( volume, "(?<=<affinity).*?(?=\">)" );
				List<String> iopss = parseXMLResponse( volume, "(?<=<iops>).*?(?=</iops>)" );
				List<String> iopsmaxs = parseXMLResponse( volume, "(?<=<IOPSmax>).*?(?=</IOPSmax>)" );
				List<String> iopsmins = parseXMLResponse( volume, "(?<=<IOPSmin>).*?(?=</IOPSmin>)" );

				String rend[] = redundancys.get( 0 ).split( "=" );
				String rend2 = rend[ 2 ].replace( "\"", "" );
				String aff[] = affinitys.get( 0 ).split( "=" );
				String aff2[] = aff[ 1 ].split( " " );
				String aff3 = aff2[ 0 ].replace( "\"", "" );
				
				XIOLun newLun = new XIOLun(
					this.accountName,
					names.get( 0 ),
					sizes.get( 0 ),
					ids.get( 0 ),
					gids.get( 0 ),
					rend2,
					dates.get( 0 ),
					comments.get( 0 ),
					aff3,
					iopss.get( 0 ),
					iopsmins.get( 0 ),
					iopsmaxs.get( 0 )
				);
				
				intLunSize += Integer.parseInt( sizes.get( 0 ));
				if( rend2.equals( "RAID-1" )) {
					intLunRaid1 += 1;
				} else if( rend2.equals( "RAID-5" )) {
					intLunRaid5 += 1;
				}
				
				store.insert( newLun );
				inserted += 1;
			}

			ObjStore<XIOData> store2 = ObjStoreHelper.getStore( XIOData.class );
			String query2 = "accountName == '" + accName + "'";
			List<XIOData> objs = store2.query( query2 ); 
			if( objs.size() == 1 ) {
				XIOData pojo = objs.get( 0 );
				pojo.setLunRaid1Count( Integer.toString( intLunRaid1 ));
				pojo.setLunRaid5Count( Integer.toString( intLunRaid5 ));
				pojo.setTotalLunUsage( Integer.toString( intLunSize ));
				store2.modifySingleObject( query2, pojo);
				logger.info( "-------------#### XIOFunctions:getLunInventoryExternal ####---- DATA UPDATED SUCCESSFULLY" );
			}

			logger.info( "-------------#### XIOFunctions:getLunInventoryExternal ####---- LUNS INSERTED = " + inserted );
		} catch( Exception e ) {
			logger.info( "-------------#### XIOFunctions:getLunInventoryExternal ####---- ERROR ", e );
			throw( e );
		}
	}
	public void getVolumes() throws Exception {
		try {
			String url = this.credential.getProtocol() + "://" + this.credential.getDeviceIp() + ":" + this.credential.getPort() + "/";
			String response = getResponse( url, "storage/volumes", "GET", this.credential.getLogin(), this.credential.getPassword() );
			if( response != null ) {
				getLunInventory( response );
				logger.info( "----------#### XIOFunctions:getVolumes ####---- GROUP 2 - COLLECTION SUCCEEDED" );
			} else {
				logger.info( "----------#### XIOFunctions:getVolumes ####---- GROUP 2 - COLLECTION FAILED" );
			}
		} catch( Exception e ) {
			logger.info( "----------#### XIOFunctions:getVolumes ####---- ERROR - " + e );
		}
	}
	
	public String getGlobalLunIDFromLocalID( String localID ) throws Exception {
		ObjStore<XIOLun> store = ObjStoreHelper.getStore( XIOLun.class );
		String query = "id == '" + localID + "'";
		List<XIOLun> summaryList = store.query( query );
		if( summaryList.isEmpty() == false ) {
			XIOLun lun = summaryList.get( 0 );
			return lun.getGlobalID();
		} else {
			return null;
		}
	}
	public String getAccountName( ReportContext context ) {
		String[] arr =  null;
		if(context.getId() != null){
			arr = context.getId().split(";");
			return "accountName == '" + arr[0] + "'";
		}
		return null;
	}
	public String getDeviceGlobalID( String accountName ) throws Exception {
		//logger.info( "----#### XIOFunctions:getDeviceGlobalID ####----" );
		ObjStore<XIODeviceInfo> store = ObjStoreHelper.getStore( XIODeviceInfo.class );
		String query = "accountName == '" + accountName + "'";
		List<XIODeviceInfo> summaryList = store.query( query ); 
		if( summaryList.isEmpty() == false ) {
			XIODeviceInfo xioDeviceInfo = summaryList.get( 0 );
			return xioDeviceInfo.getGlobalID();
		} else {
			return null;
		}
	}
	public String getDeviceModel( String accountName ) throws Exception {
		//logger.info( "----#### XIOFunctions:getDeviceModel ####----" );
		ObjStore<XIODeviceInfo> store = ObjStoreHelper.getStore( XIODeviceInfo.class );
		String query = "accountName == '" + accountName + "'";
		List<XIODeviceInfo> summaryList = store.query( query ); 
		if( summaryList.isEmpty() == false ) {
			XIODeviceInfo xioDeviceInfo = summaryList.get( 0 );
			return xioDeviceInfo.getModel();
		} else {
			return "ISE3401";
		}
	}
	public String getDeviceVersion( String accountName ) throws Exception {
		//logger.info( "----#### XIOFunctions:getDeviceVersion ####----" );
		ObjStore<XIODeviceInfo> store = ObjStoreHelper.getStore( XIODeviceInfo.class );
		String query = "accountName == '" + accountName + "'";
		List<XIODeviceInfo> summaryList = store.query( query ); 
		if( summaryList.isEmpty() == false ) {
			XIODeviceInfo xioDeviceInfo = summaryList.get( 0 );
			return xioDeviceInfo.getFWVersion();
		} else {
			return "3.1.1";
		}
	}
 	private String getResponse( String baseURL, String customURL, String method, String username, String password ) throws Exception {
		//logger.info( "----#### XIOFunctions:getResponse ####----" );
		try {
			if( method.toUpperCase() == "GET" ) {
				String url = baseURL + customURL;
				String response = getData( url, username, password );
				if( response != null & response != "" ) {
					return response;
				}
				return null;
			} else if( method.toUpperCase() == "POST" ) {
				String url = baseURL;
				String response = setData( url, customURL, username, password );
				if( response != null & response != "" ) {
					return response;
				}
				return null;
			} else if( method.toUpperCase() == "DELETE" ) {
				String url = baseURL + customURL;
				String response = deleteData( url, username, password );
				if( response != null & response != "" ) {
					return response;
				}
			}
			return null;
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:getResponse ####---- ERROR - ", e );
			return null;
		}
	}
	@SuppressWarnings({ "unchecked" })
	private boolean isValid( Object a ) {
		//logger.info( "----#### XIOFunctions:isValid ####----" );
		if( a != null ) { 
			if( a instanceof List<?> ) {
				List<String> temp = (List<String>) a;
				if( temp.get( 0 ) != null && temp.get( 0 ) != "" ) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	private List<String> parseXMLResponse( String xml, String patt  ) throws Exception {
		//logger.info( "----#### XIOFunctions:parseXMLResponse ####----" );
        Pattern pattern = Pattern.compile( patt );
        Matcher matcher = pattern.matcher( xml );
        boolean found = false;
        List<String> results = new ArrayList<String>();

        while( matcher.find() ) {
        	results.add( matcher.group().trim() );
        	found = true;
        }
        if( !found ) {
        	logger.info( "----#### XIOFunctions:parseXMLResponse ####---- NO RESULTS FOUND FOR " + patt );
        }
    	return results;
	}
	private String deleteData( String url, String username, String password ) throws Exception {
		String resp = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpDelete httpdelete = new HttpDelete( url );
		
		if( username != null && password != null ) {
			InputStream instream = null;
			BufferedReader reader = null;
			String encoding = Base64Coder.encodeString( username + ":" + password );
			httpdelete.addHeader( "Authorization", "Basic " + encoding );

			HttpResponse response = httpclient.execute( httpdelete );
			HttpEntity entity = response.getEntity();
			if( entity != null ) {
				instream = entity.getContent();
			}
			
			try {
				if( instream != null ) {
					reader = new BufferedReader( new InputStreamReader( instream ));
				}
				String mess1 = Integer.toString( response.getStatusLine().getStatusCode());
	            this.lastStatusResponse = mess1;
	            
	            logger.info( "----#### XIOFunctions:deleteData ####---- RESPONSE = " + mess1 );
	            
				if( mess1.equals( XIOConstants.XIO_HTTP_STR_CODE_SUCCESS )) {
					String thisLine;
					if( reader != null ) {
						while(( thisLine = reader.readLine()) != null ) {
							resp += thisLine;
						}
					} else {
						resp = "OK";
					}
				} else if( mess1.equals( XIOConstants.XIO_HTTP_STR_CODE_NOTFOUND )) {
					resp = "NOTFOUND";
				} else {
	    			logger.info( "----#### XIOFunctions:deleteData ####---- A non HTTP " + XIOConstants.XIO_HTTP_STR_CODE_SUCCESS + " response was received." );
	    		}
			}  catch( IOException ex ) {
				logger.info( "----#### XIOFunctions:setData ####---- ERROR - IO Exception occurred - ", ex );
	            throw ex;
	        } catch( RuntimeException ex ) {
				logger.info( "----#### XIOFunctions:setData ####---- ERROR - Runtime Exception occurred - ", ex );
				httpdelete.abort();
	            throw ex;
	        } finally {
				if( instream != null ) {
					instream.close();
				}
	        }
	        httpclient.getConnectionManager().shutdown();
	        return resp;
		}
		return resp;
	}
	private String setData( String url, String body, String username, String password ) throws Exception {
		String resp = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost( url );
		
		if( username != null && password != null ) {
			String encoding = Base64Coder.encodeString( username + ":" + password );
			httppost.addHeader( "Authorization", "Basic " + encoding );
			httppost.setEntity( new StringEntity( body ));
			
			HttpResponse response = httpclient.execute( httppost );
			HttpEntity entity = response.getEntity();
			InputStream instream = entity.getContent();
			
			try {
				BufferedReader reader = new BufferedReader( new InputStreamReader( instream ));
				String mess1 = response.getStatusLine().toString().trim().toUpperCase();
	            this.lastStatusResponse = mess1;
	            String mess2 = "HTTP10201CREATED";
	            mess1 = mess1.replace( "/", "" );
	            mess1 = mess1.replace( " ", "" );
	            mess1 = mess1.replace( ".", "" );

				if( mess1.equals( mess2 )) {
					String thisLine;
					while(( thisLine = reader.readLine()) != null ) {
						resp += thisLine;
					}
				} else {
	    			logger.info( "----#### XIOFunctions:setData ####---- A non HTTP 200 response was received." );
	    		}
			}  catch( IOException ex ) {
				logger.info( "----#### XIOFunctions:setData ####---- ERROR - IO Exception occurred - ", ex );
	            throw ex;
	        } catch( RuntimeException ex ) {
				logger.info( "----#### XIOFunctions:setData ####---- ERROR - Runtime Exception occurred - ", ex );
	            httppost.abort();
	            throw ex;
	        } finally {
	            instream.close();
	        }
	        httpclient.getConnectionManager().shutdown();
	        return resp;
		}
		return resp;
	}
	private String getData( String url, String username, String password ) throws Exception {
		//logger.info( "----#### XIOFunctions:getData ####----" );
		String resp = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet( url );
		
		if( username != null && password != null ) {
			String encoding = Base64Coder.encodeString ( username + ":" + password );
			httpget.addHeader( "Authorization", "Basic " + encoding );
		}
		
		HttpResponse response = httpclient.execute( httpget );
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        try {
            BufferedReader reader = new BufferedReader( new InputStreamReader( instream ));
            String mess1 = response.getStatusLine().toString().trim();
            String mess2 = "HTTP10200OK";
            mess1 = mess1.replace( "/", "" );
            mess1 = mess1.replace( " ", "" );
            mess1 = mess1.replace( ".", "" );
            
    		if( mess1.equals( mess2 )) {
    			String thisLine;
    			while(( thisLine = reader.readLine()) != null ) {
    	            resp += thisLine;
    	         }
    		} else {
    			logger.info( "----#### XIOFunctions:getData ####---- A non HTTP 200 response was received." );
    		}
        }  catch( IOException ex ) {
			logger.info( "----#### XIOFunctions:getData ####---- ERROR - IO Exception occurred - ", ex );
            throw ex;
        } catch( RuntimeException ex ) {
			logger.info( "----#### XIOFunctions:getData ####---- ERROR - Runtime Exception occurred - ", ex );
            httpget.abort();
            throw ex;
        } finally {
            instream.close();
        }
        httpclient.getConnectionManager().shutdown();
        return resp;
	}
	private void getSystemOverview( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getSystemOverview ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIODeviceInfo> store = ObjStoreHelper.getStore( XIODeviceInfo.class );
			
			List<String> globalid = parseXMLResponse( xml, "(?<=<globalid>)[\\s\\S]*?.*?(?=</globalid>)" );
			List<String> id = parseXMLResponse( xml, "(?<=<id>)[\\s\\S]*?.*?(?=</id>)" );
			List<String> serialnumber = parseXMLResponse( xml, "(?<=<serialnumber>)[\\s\\S]*?.*?(?=</serialnumber>)" );
			List<String> model = parseXMLResponse( xml, "(?<=<model>)[\\s\\S]*?.*?(?=</model>)" );
			List<String> fwversion = parseXMLResponse( xml, "(?<=<fwversion>)[\\s\\S]*?.*?(?=</fwversion>)" );
			List<String> vendor = parseXMLResponse( xml, "(?<=<vendor>)[\\s\\S]*?.*?(?=</vendor>)" );
			List<String> status = parseXMLResponse( xml, "(?<=<status value=)[\\s\\S]*?.*?(?=/>)" );
			
			String status2[] = status.get( 0 ).split( "=" );
			String status3 = status2[ 1 ].replace( "\"", "" );

			XIODeviceInfo newDevice = new XIODeviceInfo(
					this.accountName,
					id.get( 0 ),
					this.credential.getDeviceIp(),
					globalid.get( 0 ),
					serialnumber.get( 0 ),
					model.get( 0 ),
					fwversion.get( 0 ),
					vendor.get( 0 ),
					status3
			);
			
			query = "accountName == '" + newDevice.getAccountName() +"'";
			if( store.queryCount( "accountName", query ) == 0 ) {
				store.insert( newDevice );
				inserted += 1;
			} else {
				store.modifySingleObject( query, newDevice );
				modified += 1;
			}
			if( inserted > 0 )
				logger.info( "-----------#### XIOFunctions:getSystemOverview ####---- SYSTEM OVERVIEW INFO INSERTED = " + inserted );
			if( modified > 0 )
				logger.info( "-----------#### XIOFunctions:getSystemOverview ####---- SYSTEM OVERVIEW INFO MODIFIED = " + modified );
		} catch( Exception e ) {
			logger.info( "-----------#### XIOFunctions:getSystemOverview::ERROR ####----", e );
		}
	}
	private void getControllerInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getControllerInventory ####----" );
		String query = "";
		String query2 = "";
		long count = 0;
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOController> store = ObjStoreHelper.getStore( XIOController.class );
			count = store.deleteAll();
			logger.info( "----#### XIOFunctions:getControllerInventory ####---- DELETED " + count + " ENTRIES" );
			
			List<String> ip = parseXMLResponse( xml, "(?<=<ipaddress>).*?(?=</ipaddress>)" );
			List<String> mac = parseXMLResponse( xml, "(?<=<macaddress>).*?(?=</macaddress>)" );
			List<String> fw = parseXMLResponse( xml, "(?<=<fwversion>).*?(?=</fwversion>)" );
			List<String> status = parseXMLResponse( xml, "(?<=<status).*?(?=/>)" );
			List<String> dns = parseXMLResponse( xml, "(?<=<dnsname>).*?(?=</dnsname>)" );
			List<String> master = parseXMLResponse( xml, "(?<=<rank).*?(?=/>)" );

			String parts[] = status.get( 1 ).split( "=" );
			String parts2[] = status.get( 2 ).split( "=" );
			String parts3[] = master.get( 0 ).split( "=" );
			String parts4[] = master.get( 1 ).split( "=" );			

			String status0 = parts[ 2 ].replace( "\"", "" );
			String status1 = parts2[ 2 ].replace( "\"", "" );
			String master0 = parts3[ 2 ].replace( "\"", "" );
			String master1 = parts4[ 2 ].replace( "\"", "" );

			XIOController newController = new XIOController( this.accountName, ip.get( 0 ), mac.get( 0 ), fw.get( 0 ), status0, dns.get( 0 ), master0 );
			XIOController newController2 = new XIOController( this.accountName, ip.get( 1 ), mac.get( 1 ), fw.get( 1 ), status1, dns.get( 1 ), master1 );

			query = "accountName == '" + newController.getAccountName() + "'";
			query2 = "accountName == '" + newController2.getAccountName() + "'";
			query = query + " && ip == '" + newController.getIP() + "'";
			query2 = query2 + " && ip == '" + newController2.getIP() + "'";			
			
			if( store.queryCount( "ip", query ) == 0 ) {
				store.insert( newController );
				inserted += 1;
			} else {
				store.modifySingleObject( query, newController );
				modified += 1;
			}
			if( store.queryCount( "ip", query2 ) == 0 ) {
				store.insert( newController2 );
				inserted += 1;
			} else {
				store.modifySingleObject( query2, newController2 );
				modified += 1;
			}

			if( inserted > 0 )
				logger.info( "------#### XIOFunctions:getControllerInventory ####---- CONTROLLERS INSERTED = " + inserted );
			if( modified > 0 )	
				logger.info( "------#### XIOFunctions:getControllerInventory ####---- CONTROLLERS MODIFIED = " + modified );
		} catch( Exception e ) {
			logger.info( "------#### XIOFunctions:getControllerInventory ####---- ERROR ", e );
			throw( e );
		}
	}
	private void getCapabilitiesInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getCapabilitiesInventory ####----" );
		String query = "";
		long count = 0;
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOCapability> store = ObjStoreHelper.getStore( XIOCapability.class );
			List<String> resp2 = parseXMLResponse( xml, "(?<=<capability)[\\s\\S]*?.*?(?=/>)" );
			count = store.deleteAll();
			logger.info( "----#### XIOFunctions:getCapabilitiesInventory ####---- DELETED " + count + " ENTRIES" );
			
			for( String string : resp2 ) {
				String[] parts = string.split( "=" );
				String part1 = parts[ 2 ];
				List<String> feature = parseXMLResponse( part1, "(?<=\")[\\s\\S]*?.*?(?=\")" );

				XIOCapability newCapability = new XIOCapability( this.accountName, feature.get( 0 ));
				
				store.insert( newCapability );
				inserted += 1;
			}
			logger.info( "----#### XIOFunctions:getCapabilitiesInventory ####---- Capabilities Inserted = " + inserted );
		} catch( Exception e ) {
			throw( e );
		}
	}
	private void getLunInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getLunInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOLun> store = ObjStoreHelper.getStore( XIOLun.class );
			long count = store.deleteAll();
			logger.info( "-------------#### XIOFunctions:getLunInventory ####---- LUNS DELETED = " + count + " ENTRIES" );
			
			List<String> volumes = parseXMLResponse( xml, "(?<=<volume[^s])[\\s\\S]*?.*?(?=</volume>)" );
			
			this.intLunSize = 0;
			int intLunRaid1 = 0;
			int intLunRaid5 = 0;
			
			for( String volume : volumes ) {
				List<String> names = parseXMLResponse( volume, "(?<=<name>).*?(?=</name>)" );
				List<String> sizes = parseXMLResponse( volume, "(?<=<size>).*?(?=</size>)" );
				List<String> ids = parseXMLResponse( volume, "(?<=<localid>).*?(?=</localid>)" );
				List<String> gids = parseXMLResponse( volume, "(?<=<globalid>).*?(?=</globalid>)" );
				List<String> dates = parseXMLResponse( volume, "(?<=<createdate>).*?(?=</createdate>)" );
				List<String> redundancys = parseXMLResponse( volume, "(?<=<redundancy).*?(?=/>)" );
				List<String> comments = parseXMLResponse( volume, "(?<=<comment>).*?(?=</comment>)" );
				List<String> affinitys = parseXMLResponse( volume, "(?<=<affinity).*?(?=\">)" );
				List<String> iopss = parseXMLResponse( volume, "(?<=<iops>).*?(?=</iops>)" );
				List<String> iopsmaxs = parseXMLResponse( volume, "(?<=<IOPSmax>).*?(?=</IOPSmax>)" );
				List<String> iopsmins = parseXMLResponse( volume, "(?<=<IOPSmin>).*?(?=</IOPSmin>)" );

				String rend[] = redundancys.get( 0 ).split( "=" );
				String rend2 = rend[ 2 ].replace( "\"", "" );
				String aff[] = affinitys.get( 0 ).split( "=" );
				String aff2[] = aff[ 1 ].split( " " );
				String aff3 = aff2[ 0 ].replace( "\"", "" );
				
				XIOLun newLun = new XIOLun(
					this.accountName,
					names.get( 0 ),
					sizes.get( 0 ),
					ids.get( 0 ),
					gids.get( 0 ),
					rend2,
					dates.get( 0 ),
					comments.get( 0 ),
					aff3,
					iopss.get( 0 ),
					iopsmins.get( 0 ),
					iopsmaxs.get( 0 )
				);
				
				this.intLunSize += Integer.parseInt( sizes.get( 0 ));
				if( rend2.equals( "RAID-1" )) {
					intLunRaid1 += 1;
				} else if( rend2.equals( "RAID-5" )) {
					intLunRaid5 += 1;
				}
				
				store.insert( newLun );
				inserted += 1;
			}
			
			this.newData.setLunRaid1Count( Integer.toString( intLunRaid1 ));
			this.newData.setLunRaid5Count( Integer.toString( intLunRaid5 ));
			this.newData.setTotalLunUsage( Integer.toString( intLunSize ));
			
			logger.info( "-------------#### XIOFunctions:getLunInventory ####---- LUNS INSERTED = " + inserted );
		} catch( Exception e ) {
			logger.info( "-------------#### XIOFunctions:getLunInventory ####---- ERROR ", e );
			throw( e );
		}
	}
	private void getDriveInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getDriveInventory ####----" );
		String query = "";
		int count = 0;
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIODrive> store = ObjStoreHelper.getStore( XIODrive.class );
			long delcount = store.deleteAll();
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- DRIVES DELETED = " + delcount + " ENTRIES" );
			
			List<String> drives = parseXMLResponse( xml, "(?<=<drive)[\\s\\S]*?.*?(?=</drive>)" );
			//logger.info( "----#### XIOFunctions:getDriveInventory ####---- FOUND - " + drives.size() + " DRIVES" );
			
			count = drives.size();
			int intPerDiskReadkbps = 0;
			int intPerDiskWritekbps = 0;
			int intTotalReadkbps = 0;
			int intPerDiskReadIOPS = 0;
			int intPerDiskWriteIOPS = 0;
			int intPerDiskReadLatency = 0;
			int intPerDiskWriteLatency = 0;
			
			for( String drive : drives ) {
				List<String> names = parseXMLResponse( drive, "(?<=<name>)[\\s\\S]*?.*?(?=</name>)" );
				List<String> iopsRead = parseXMLResponse( drive, "(?<=<readiops>)[\\s\\S]*?.*?(?=</readiops>)" );
				List<String> iopsWrite = parseXMLResponse( drive, "(?<=<writeiops>)[\\s\\S]*?.*?(?=</writeiops>)" );
				List<String> iopsTotal = parseXMLResponse( drive, "(?<=<totaliops>)[\\s\\S]*?.*?(?=</totaliops>)" );
				List<String> kbpsRead = parseXMLResponse( drive, "(?<=<readkbps>)[\\s\\S]*?.*?(?=</readkbps>)" );
				List<String> kbpsWrite = parseXMLResponse( drive, "(?<=<writekbps>)[\\s\\S]*?.*?(?=</writekbps>)" );
				List<String> kbpsTotal = parseXMLResponse( drive, "(?<=<totalkbps>)[\\s\\S]*?.*?(?=</totalkbps>)" );
				List<String> readLatency = parseXMLResponse( drive, "(?<=<readlatency>)[\\s\\S]*?.*?(?=</readlatency>)" );
				List<String> readLatencyMax = parseXMLResponse( drive, "(?<=<readlatencymax>)[\\s\\S]*?.*?(?=</readlatencymax>)" );
				List<String> writeLatency = parseXMLResponse( drive, "(?<=<writelatency>)[\\s\\S]*?.*?(?=</writelatency>)" );
				List<String> writeLatencyMax = parseXMLResponse( drive, "(?<=<writelatencymax>)[\\s\\S]*?.*?(?=</writelatencymax>)" );
				List<String> iopsTotalReboot = parseXMLResponse( drive, "(?<=<totaliosincelastboot>)[\\s\\S]*?.*?(?=</totaliosincelastboot>)" );
				List<String> kbpsTotalReboot = parseXMLResponse( drive, "(?<=<totalkbsincelastboot>)[\\s\\S]*?.*?(?=</totalkbsincelastboot>)" );
				
				XIODrive newDrive = new XIODrive(
					this.accountName,
					names.get( 0 ),
					iopsRead.get( 0 ),
					iopsWrite.get( 0 ),
					iopsTotal.get( 0 ),
					kbpsRead.get( 0 ),
					kbpsWrite.get( 0 ),
					kbpsTotal.get( 0 ),
					readLatency.get( 0 ),
					readLatencyMax.get( 0 ),
					writeLatency.get( 0 ),
					writeLatencyMax.get( 0 ),
					iopsTotalReboot.get( 0 ),
					kbpsTotalReboot.get( 0 )
				);

				intPerDiskReadkbps += Integer.parseInt( kbpsRead.get( 0 ));
				intPerDiskWritekbps += Integer.parseInt( kbpsWrite.get( 0 ));
				intPerDiskReadIOPS += Integer.parseInt( iopsRead.get( 0 ));
				intPerDiskWriteIOPS += Integer.parseInt( iopsWrite.get( 0 ));
				intPerDiskReadLatency += Integer.parseInt( readLatency.get( 0 ));
				intPerDiskWriteLatency += Integer.parseInt( writeLatency.get( 0 ));
				
				store.insert( newDrive );
				inserted += 1;
			}
			this.newData.setTotalReadIops( Integer.toString( intPerDiskReadkbps ));
			this.newData.setTotalReadKbps( Integer.toString( intTotalReadkbps ));
			this.newData.setTotalWriteIops( Integer.toString( intPerDiskWriteIOPS ));
			this.newData.setTotalWriteKbps( Integer.toString( intPerDiskWritekbps ));
			this.newData.setPerDiskReadKbps( Integer.toString( (int) Math.ceil((double)intPerDiskReadkbps / count )));
			this.newData.setPerDiskWriteKbps( Integer.toString( (int) Math.ceil((double)intPerDiskWritekbps / count )));
			this.newData.setPerDiskReadIops( Integer.toString( (int) Math.ceil((double)intPerDiskReadIOPS / count )));
			this.newData.setPerDiskWriteIops( Integer.toString( (int) Math.ceil((double)intPerDiskWriteIOPS / count )));
			this.newData.setPerDiskReadLatency( Integer.toString( (int) Math.ceil((double)intPerDiskReadLatency / count )));
			this.newData.setPerDiskWriteLatency( Integer.toString( (int) Math.ceil((double)intPerDiskWriteLatency / count  )));

			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Read IOPS = " + intPerDiskReadkbps );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Read KBPS = " + intTotalReadkbps );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Write IOPS = " + intPerDiskWriteIOPS );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Write KBPS = " + intPerDiskWritekbps );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Disk Read KBPS = " + intPerDiskReadkbps/count );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Disk Write KBPS = " + intPerDiskWritekbps/count );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Disk Read IOPS = " + intPerDiskReadIOPS/count );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Disk Write IOPS = " + intPerDiskWriteIOPS/count );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Disk Read Latency = " + intPerDiskReadLatency/count );
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- Total Disk Write Latency = " + intPerDiskWriteLatency/count );

			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- DRIVES INSERTED = " + inserted );
		} catch( Exception e ) {
			logger.info( "-----------#### XIOFunctions:getDriveInventory ####---- ERROR - ", e );
		}
	}
	private void getChronoInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getChronoInventory ####----" );
		int inserted = 0;
		int modified = 0;
		
		try {
			String query = "";
			ObjStore<XIODeviceChrono> store = ObjStoreHelper.getStore( XIODeviceChrono.class );
			
			List<String> scale = parseXMLResponse( xml, "(?<=<scale>)[\\s\\S]*?.*?(?=</scale>)" );
			List<String> date = parseXMLResponse( xml, "(?<=<date>)[\\s\\S]*?.*?(?=</date>)" );
			List<String> time = parseXMLResponse( xml, "(?<=<time>)[\\s\\S]*?.*?(?=</time>)" );
			List<String> timezone = parseXMLResponse( xml, "(?<=<timezone>)[\\s\\S]*?.*?(?=</timezone>)" );
			List<String> dst = parseXMLResponse( xml, "(?<=<dst>)[\\s\\S]*?.*?(?=</dst>)" );
			List<String> days = parseXMLResponse( xml, "(?<=<days>)[\\s\\S]*?.*?(?=</days>)" );
			List<String> hours = parseXMLResponse( xml, "(?<=<hours>)[\\s\\S]*?.*?(?=</hours>)" );
			List<String> mins = parseXMLResponse( xml, "(?<=<minutes>)[\\s\\S]*?.*?(?=</minutes>)" );
			List<String> secs = parseXMLResponse( xml, "(?<=<seconds>)[\\s\\S]*?.*?(?=</seconds>)" );
			List<String> ntp = parseXMLResponse( xml, "(?<=<ntpmode>)[\\s\\S]*?.*?(?=</ntpmode>)" );
			List<String> ntpServer = parseXMLResponse( xml, "(?<=<ntpserver>)[\\s\\S]*?.*?(?=</ntpserver>)" );
			
			XIODeviceChrono newChrono = new XIODeviceChrono(
					this.accountName,
					scale.get( 0 ),
					date.get( 0 ),
					time.get( 0 ),
					timezone.get( 0 ),
					dst.get( 0 ),
					days.get( 0 ),
					hours.get( 0 ),
					mins.get( 0 ),
					secs.get( 0 ),
					ntp.get( 0 ),
					ntpServer.get( 0 )
			);
			
			query = "accountName == '" + newChrono.getAccountName() + "'";
			if( store.queryCount( "accountName", query ) == 0 ) {
				store.insert( newChrono );
				inserted += 1;
			} else {
				store.modifySingleObject( query, newChrono );
				modified += 1;
			}
			if( inserted > 0 )
				logger.info( "----------#### XIOFunctions:getChronoInventory ####---- CHRONO INSERTED = " + inserted );
			if( modified > 0 )
				logger.info( "----------#### XIOFunctions:getChronoInventory ####---- CHRONO MODIFIED = " + modified );
		} catch( Exception e ) {
			logger.info( "----------#### XIOFunctions:getChronoInventory ####---- ERROR - ", e );
		}
	}
	private void getManagementInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getManagementInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOManagement> store = ObjStoreHelper.getStore( XIOManagement.class );
			
			List<String> dhcp = parseXMLResponse( xml, "(?<=<dhcp)[\\s\\S]*?.*?(?=/>)" );
			List<String> wakeonlan = parseXMLResponse( xml, "(?<=<wakeonlan)[\\s\\S]*?.*?(?=/>)" );
			List<String> ip = parseXMLResponse( xml, "(?<=<ipaddress>)[\\s\\S]*?.*?(?=</ipaddress>)" );
			List<String> mask = parseXMLResponse( xml, "(?<=<ipmask>)[\\s\\S]*?.*?(?=</ipmask>)" );
			List<String> gateway = parseXMLResponse( xml, "(?<=<gateway>)[\\s\\S]*?.*?(?=</gateway>)" );
			List<String> status = parseXMLResponse( xml, "(?<=<linkstatus>)[\\s\\S]*?.*?(?=</linkstatus>)" );
			List<String> domainServer = parseXMLResponse( xml, "(?<=<domainserver>)[\\s\\S]*?.*?(?=</domainserver>)" );
			List<String> nameServer = parseXMLResponse( xml, "(?<=<nameserver>)[\\s\\S]*?.*?(?=</nameserver>)" );
			
			String tmp[] = dhcp.get( 0 ).split( "=" );
			String dhc = tmp[ 2 ].replace( "\"", "" );
			String tmp2[] = wakeonlan.get( 0 ).split( "=" );
			String wake = tmp2[ 2 ].replace( "\"", "" );
			String ip2 = "";
			String mask2 = "";
			String gateway2 = "";
			String status2 = "";
			
			if( ip.size() == 2 ) {
				ip2 = ip.get( 1 );
				mask2 = mask.get( 1 );
				gateway2 = gateway.get( 1 );
				status2 = status.get( 1 );
			}
			
			XIOManagement newNetwork = new XIOManagement(
					this.accountName,
					dhc,
					wake,
					ip.get( 0 ),
					mask.get( 0 ),
					gateway.get( 0 ),
					status.get( 0 ),
					ip2,
					mask2,
					gateway2,
					status2,
					domainServer.get( 0 ),
					nameServer.get( 0 ),
					nameServer.get( 1 )
			);
			
			query = "accountName == '" + newNetwork.getAccountName() + "'";
			if( store.queryCount( "accountName", query ) == 0 ) {
				store.insert( newNetwork );
				inserted += 1;
			} else {
				store.modifySingleObject( query, newNetwork );
				modified += 1;
			}
			if( inserted > 0 )
				logger.info( "-------#### XIOFunctions:getManagementInventory ####---- MANAGEMENT INFO INSERTED = " + inserted );
			if( modified > 0 )	
				logger.info( "------#### XIOFunctions:getManagementInventory ####---- MANAGEMENT INFO MODIFIED = " + modified );
		} catch( Exception e ) {
			logger.info( "-------#### XIOFunctions:getManagementInventory ####---- ERROR - ", e );
		}
	}
	private void getNetworkInventory( String xml, String host ) throws Exception {
		//logger.info( "----#### XIOFunctions:getNetworkInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIONetwork> store = ObjStoreHelper.getStore( XIONetwork.class );
			
			List<String> ports = parseXMLResponse( xml, "(?<=<sfp[^s])[\\s\\S]*?.*?(?=</sfp>)" );

			for( String port : ports ) {
				List<String> id = parseXMLResponse( port, "(?<=<id>)[\\s\\S]*?.*?(?=</id>)" );
				List<String> status = parseXMLResponse( port, "(?<=<status)[\\s\\S]*?.*?(?=\">)" );
				List<String> detail = parseXMLResponse( port, "(?<=<detail>)[\\s\\S]*?.*?(?=</detail>)" );
				List<String> hw = parseXMLResponse( port, "(?<=<hwversion>)[\\s\\S]*?.*?(?=</hwversion>)" );
				List<String> serial = parseXMLResponse( port, "(?<=<serialnumber>)[\\s\\S]*?.*?(?=</serialnumber>)" );
				List<String> part = parseXMLResponse( port, "(?<=<partnumber>)[\\s\\S]*?.*?(?=</partnumber>)" );
				List<String> manuf = parseXMLResponse( port, "(?<=<manufacturingdate>)[\\s\\S]*?.*?(?=</manufacturingdate>)" );
				List<String> hec1 = parseXMLResponse( port, "(?<=<hecportsignalloss>)[\\s\\S]*?.*?(?=</hecportsignalloss>)" );
				List<String> hectx1 = parseXMLResponse( port, "(?<=<hectxfault>)[\\s\\S]*?.*?(?=</hectxfault>)" );
				List<String> los = parseXMLResponse( port, "(?<=<numlosevents>)[\\s\\S]*?.*?(?=</numlosevents>)" );
				List<String> txflt = parseXMLResponse( port, "(?<=<numtxfltevents>)[\\s\\S]*?.*?(?=</numtxfltevents>)" );
				
				String hec2[] = hec1.get( 0 ).split( "=" );
				String hec = hec2[ 2 ].replace( "\"", "" );
				String hectx2[] = hectx1.get( 0 ).split( "=" );
				String hectx = hectx2[ 2 ].replace( "\"", "" );
				
				XIONetwork newNetwork = new XIONetwork( 
					this.accountName,
					id.get( 0 ),
					status.get( 0 ),
					detail.get( 0 ),
					hw.get( 0 ),
					serial.get( 0 ),
					part.get( 0 ),
					manuf.get( 0 ),
					hec,
					hectx,
					los.get( 0 ),
					txflt.get( 0 )
				);
				
				query = "accountName == '" + newNetwork.getAccountName() + "'";
				query = query + " && id == '" + newNetwork.getID() + "'";
				
				if( store.queryCount( "accountName", query ) == 0 ) {
					store.insert( newNetwork );
					inserted += 1;
				} else {
					store.modifySingleObject( query, newNetwork );
					modified += 1;
				}
			}
			if( inserted > 0 )
				logger.info( "---------#### XIOFunctions:getNetworkInventory ####---- NETWORK INTERFACE ON " + host + " INSERTED = " + inserted );
			if( modified > 0 )
				logger.info( "---------#### XIOFunctions:getNetworkInventory ####---- NETWORK INTERFACE ON " + host + " MODIFIED = " + modified );
		} catch( Exception e ) {
			logger.info( "---------#### XIOFunctions:getNetworkInventory ####---- ERROR - ", e );
		}
	}
	private void getFCNetworkInventory( String xml, String host ) throws Exception {
		//logger.info( "----#### XIOFunctions:getFCNetworkInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOFCNetwork> store = ObjStoreHelper.getStore( XIOFCNetwork.class );
			
			List<String> ports = parseXMLResponse( xml, "(?<=<fcport[^s])[\\s\\S]*?.*?(?=</fcport>)" );

			for( String port : ports ) {
				List<String> id = parseXMLResponse( port, "(?<=<id>)[\\s\\S]*?.*?(?=</id>)" );
				List<String> status = parseXMLResponse( port, "(?<=<status)[\\s\\S]*?.*?(?=/>)" );
				List<String> type = parseXMLResponse( port, "(?<=<type)[\\s\\S]*?.*?(?=/>)" );
				List<String> wwpn = parseXMLResponse( port, "(?<=<wwn>)[\\s\\S]*?.*?(?=</wwn>)" );
				List<String> speed = parseXMLResponse( port, "(?<=<speed>)[\\s\\S]*?.*?(?=</speed>)" );
				List<String> bytesTrans = parseXMLResponse( port, "(?<=<bytestransmitted>)[\\s\\S]*?.*?(?=</bytestransmitted>)" );
				List<String> bytesRecv = parseXMLResponse( port, "(?<=<bytesreceived>)[\\s\\S]*?.*?(?=</bytesreceived>)" );
				List<String> loop = parseXMLResponse( port, "(?<=<lipcount>)[\\s\\S]*?.*?(?=</lipcount>)" );
				List<String> nos = parseXMLResponse( port, "(?<=<noscount>)[\\s\\S]*?.*?(?=</noscount>)" );
				
				String stat[] = status.get( 0 ).split( "=" );
				String sta = stat[ 2 ].replace( "\"", "" );
				String typ[] = type.get( 0 ).split( "=" );
				String ty = typ[ 2 ].replace( "\"", "" );
								
				XIOFCNetwork newNetwork = new XIOFCNetwork( 
					this.accountName,
					id.get( 0 ),
					sta,
					ty,
					wwpn.get( 0 ),
					speed.get( 0 ),
					bytesTrans.get( 0 ),
					bytesRecv.get( 0 ),
					loop.get( 0 ),
					nos.get( 0 )
				);
				
				query = "accountName == '" + newNetwork.getAccountName() + "'";
				query = query + " && id == '" + newNetwork.getID() + "'";

				if( store.queryCount( "accountName", query ) == 0 ) {
					store.insert( newNetwork );
					inserted += 1;
				} else {
					store.modifySingleObject( query, newNetwork );
					modified += 1;
				}
			}
			if( inserted > 0 )
				logger.info( "-------#### XIOFunctions:getFCNetworkInventory ####---- FC NETWORK INTERFACE ON " + host + " INSERTED = " + inserted );
			if( modified > 0 )
				logger.info( "-------#### XIOFunctions:getFCNetworkInventory ####---- FC NETWORK INTERFACE ON " + host + " MODIFIED = " + modified );
		} catch( Exception e ) {
			logger.info( "-------#### XIOFunctions:getFCNetworkInventory ####---- ERROR - ", e );
		}
	}
	private void getMediaInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getMediaInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOMedia> store = ObjStoreHelper.getStore( XIOMedia.class );
			
			List<String> medias = parseXMLResponse( xml, "(?<=<medium)[\\s\\S]*?.*?(?=</medium>)" );

			for( String media : medias ) {
				List<String> id = parseXMLResponse( media, "(?<=<id>)[\\s\\S]*?.*?(?=</id>)" );
				List<String> status = parseXMLResponse( media, "(?<=<status)[\\s\\S]*?.*?(?=>)" );
				List<String> serial = parseXMLResponse( media, "(?<=<serialnumber>)[\\s\\S]*?.*?(?=</serialnumber>)" );
				List<String> model = parseXMLResponse( media, "(?<=<model>)[\\s\\S]*?.*?(?=</model>)" );
				List<String> part = parseXMLResponse( media, "(?<=<partnumber>)[\\s\\S]*?.*?(?=</partnumber>)" );
				List<String> fw = parseXMLResponse( media, "(?<=<fwversion>)[\\s\\S]*?.*?(?=</fwversion>)" );
				List<String> tier = parseXMLResponse( media, "(?<=<tier)[\\s\\S]*?.*?(?=/>)" );
				List<String> hwhealth = parseXMLResponse( media, "(?<=<health value=)[\\s\\S]*?.*?(?=>)" );
				List<String> temperature = parseXMLResponse( media, "(?<=<temperature value=)[\\s\\S]*?.*?(?=>)" );
				List<String> scale = parseXMLResponse( media, "(?<=<scale>)[\\s\\S]*?.*?(?=</scale>)" );
				List<String> manufdate = parseXMLResponse( media, "(?<=<manufacturingdate>)[\\s\\S]*?.*?(?=</manufacturingdate>)" );
				List<String> led = parseXMLResponse( media, "(?<=<led value=)[\\s\\S]*?.*?(?=/>)" );
				List<String> hw = parseXMLResponse( media, "(?<=<hwversion>)[\\s\\S]*?.*?(?=</hwversion>)" );
				List<String> datahealth = parseXMLResponse( media, "(?<=<redundancyhealth value=)[\\s\\S]*?.*?(?=>)" );
				List<String> healing = parseXMLResponse( media, "(?<=<inselfhealing>)[\\s\\S]*?.*?(?=</inselfhealing>)" );
				List<String> healingstring = parseXMLResponse( media, "(?<=<healthstring>)[\\s\\S]*?.*?(?=\\)</healthstring>)" );
				
				String status2[] = status.get( 0 ).split( "=" );
				String status3 = status2[ 2 ].replace( "\"", "" );
				String tier2[] = tier.get( 0 ).split( "=" );
				String tier3 = tier2[ 2 ].replace( "\"", "" );
				String temp[] = temperature.get( 0 ).split( " " );
				String temp2 = temp[ 0 ].replace( "\"", "" );
				String led2[] = led.get( 0 ).split( "=" );
				String led3 = led2[ 1 ].replace( "\"", "" );
				
				XIOMedia newMedia = new XIOMedia(
					this.accountName,
					id.get( 0 ),
					status3,
					serial.get( 0 ),
					model.get( 0 ),
					part.get( 0 ),
					fw.get( 0 ),
					tier3,
					hwhealth.get( 0 ).replace( "\"", "" ),
					temp2,
					scale.get( 0 ),
					manufdate.get( 0 ),
					led3,
					hw.get( 0 ),
					datahealth.get( 0 ).replace( "\"", "" ),
					healing.get( 0 ),
					healingstring.get( 0 ).replace( "(", "" )
				);
				
				query = "accountName == '" + newMedia.getAccountName() + "'";
				query = query + " && id == '" + newMedia.getID() + "'";
				
				if( store.queryCount( "accountName", query ) == 0 ) {
					store.insert( newMedia );
					inserted += 1;
				} else {
					store.modifySingleObject( query, newMedia );
					modified += 1;
				}
			}
			if( inserted > 0 )
				logger.info( "----------#### XIOFunctions:XIOFunctions:getMediaInventory ####---- Media inserted = " + inserted );
			if( modified > 0 )
				logger.info( "----------#### XIOFunctions:XIOFunctions:getMediaInventory ####---- Media modified = " + modified );
		} catch( Exception e ) {
			logger.info( "----------#### XIOFunctions:XIOFunctions:getMediaInventory ####---- ERROR ", e );
			throw( e );
		}
	}
	private void getPowerSupplyInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getPowerSupplyInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOPowerSupply> store = ObjStoreHelper.getStore( XIOPowerSupply.class );
			
			List<String> psus = parseXMLResponse( xml, "(?<=<powersupply)[\\s\\S]*?.*?(?=</powersupply>)" );

			for( String psu : psus ) {
				List<String> id = parseXMLResponse( psu, "(?<=<id>)[\\s\\S]*?.*?(?=</id>)" );
				List<String> status = parseXMLResponse( psu, "(?<=<status value=)[\\s\\S]*?.*?(?=>)" );
				List<String> serialnumber = parseXMLResponse( psu, "(?<=<serialnumber>)[\\s\\S]*?.*?(?=</serialnumber>)" );
				List<String> model = parseXMLResponse( psu, "(?<=<model>)[\\s\\S]*?.*?(?=</model>)" );
				List<String> partnumber = parseXMLResponse( psu, "(?<=<partnumber>)[\\s\\S]*?.*?(?=</partnumber>)" );
				List<String> hwversion = parseXMLResponse( psu, "(?<=<hwversion>)[\\s\\S]*?.*?(?=</hwversion>)" );
				List<String> temperature = parseXMLResponse( psu, "(?<=<temperature value=)[\\s\\S]*?.*?(?=>)" );
				List<String> scale = parseXMLResponse( psu, "(?<=<scale>)[\\s\\S]*?.*?(?=</scale>)" );
				List<String> led = parseXMLResponse( psu, "(?<=<led value=)[\\s\\S]*?.*?(?=/>)" );
				List<String> manufdate = parseXMLResponse( psu, "(?<=<manufacturingdate>)[\\s\\S]*?.*?(?=</manufacturingdate>)" );
				List<String> fanrpm = parseXMLResponse( psu, "(?<=<rpm>)[\\s\\S]*?.*?(?=</rpm>)" );
				
				String status2[] = status.get( 0 ).split( "=" );
				String status3[] = status.get( 1 ).split( "=" );
				String status4[] = status.get( 2 ).split( "=" );
				String led2[] = led.get( 0 ).split( "=" );
				
				XIOPowerSupply newPSU = new XIOPowerSupply( 
						this.accountName,
						id.get( 0 ),
						status2[ 1 ].replace( "\"", "" ),
						serialnumber.get( 0 ),
						model.get( 0 ),
						partnumber.get( 0 ),
						hwversion.get( 0 ),
						temperature.get( 0 ).replace( "\"", "" ),
						scale.get( 0 ),
						led2[ 1 ].replace( "\"", "" ),
						manufdate.get( 0 ),
						status3[ 1 ].replace( "\"", "" ),
						fanrpm.get( 0 ),
						status4[ 1 ].replace( "\"", "" ),
						fanrpm.get( 1 )
				);
	
				query = "accountName == '" + newPSU.getAccountName() + "'";
				query = query + " && id == '" + newPSU.getID() + "'";
				
				if( store.queryCount( "accountName", query ) == 0 ) {
					store.insert( newPSU );
					inserted += 1;
				} else {
					store.modifySingleObject( query, newPSU );
					modified += 1;
				}
			}
			if( inserted > 0 )
				logger.info( "----#### XIOFunctions:getPowerSupplyInventory ####---- Power Supplies inserted = " + inserted );
			if( modified > 0 )
				logger.info( "----#### XIOFunctions:getPowerSupplyInventory ####---- Power Supplies modified = " + modified );
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:getPowerSupplyInventory ####---- ERROR ", e );
			throw( e );
		}
	}
	private void getIOPMAPSInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getIOPMAPSInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;

		try {
			ObjStore<XIOPool> store = ObjStoreHelper.getStore( XIOPool.class );
			
			List<String> pools = parseXMLResponse( xml, "(?<=<pool)[\\s\\S]*?.*?(?=</pool>)" );
			List<String> cachehit = parseXMLResponse( xml, "(?<=<cachehitpercent>)[\\s\\S]*?.*?(?=</cachehitpercent>)" );
			List<String> totaliops = parseXMLResponse( xml, "(?<=<isetotaliops>)[\\s\\S]*?.*?(?=</isetotaliops>)" );
			List<String> totalsize = parseXMLResponse( xml, "(?<=<isetotalsize>)[\\s\\S]*?.*?(?=</isetotalsize>)" );
			List<String> density = parseXMLResponse( xml, "(?<=<iodensity>)[\\s\\S]*?.*?(?=</iodensity>)" );

			this.newData.setTotalCapacity( totalsize.get( 0 ));
			this.newData.setTotalSystemIOPS( totaliops.get( 0 ));
			this.newData.setTotalIODensity( density.get( 0 ));
			this.newData.setTotalCacheHitPercent( cachehit.get( 0 ));
			
			for( String pool : pools ) {
				List<String> name = parseXMLResponse( xml, "(?<=<name>)[\\s\\S]*?.*?(?=</name>)" );
				List<String> hddios = parseXMLResponse( xml, "(?<=<hddios>)[\\s\\S]*?.*?(?=</hddios>)" );
				List<String> ssdios = parseXMLResponse( xml, "(?<=<ssdios>)[\\s\\S]*?.*?(?=</ssdios>)" );
				List<String> ssdpercentused = parseXMLResponse( xml, "(?<=<ssdpercentused>)[\\s\\S]*?.*?(?=</ssdpercentused>)" );
				List<String> usedsize = parseXMLResponse( xml, "(?<=<usedsize>)[\\s\\S]*?.*?(?=</usedsize>)" );
				List<String> freesize = parseXMLResponse( xml, "(?<=<freesize>)[\\s\\S]*?.*?(?=</freesize>)" );
				
				XIOPool newPool = new XIOPool( 
					this.accountName, 
					name.get( 0 ),
					hddios.get( 0 ),
					ssdios.get( 0 ),
					ssdpercentused.get( 0 ),
					usedsize.get( 0 ),
					freesize.get( 0 )
				);
				
				query = "accountName == '" + newPool.getAccountName() + "'";
				query = query + " && name == '" + newPool.getName() + "'";
				
				if( store.queryCount( "accountName", query ) == 0 ) {
					store.insert( newPool );
					inserted += 1;
				} else {
					store.modifySingleObject( query, newPool );
					modified += 1;
				}
			}
			if( inserted > 0 )
				logger.info( "----#### XIOFunctions:getIOPMAPSInventory ####---- IOP info inserted = " + inserted );
			if( modified > 0 )
				logger.info( "----#### XIOFunctions:getIOPMAPSInventory ####---- IOP info modified = " + modified );
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:getIOPMAPSInventory ####---- ERROR ", e );
		}
	}
	private void getCacheInventory( String xml, String captcha, String item ) throws Exception {
		//logger.info( "----#### XIOFunctions:getCacheInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;

		try {
			ObjStore<XIOCache> store = ObjStoreHelper.getStore( XIOCache.class );
			
			List<String> array = parseXMLResponse( xml, captcha );
			xml = array.get( 0 );

			List<String> name = parseXMLResponse( xml, "(?<=<name>)[\\s\\S]*?.*?(?=</name>)" );
			List<String> totaliops = parseXMLResponse( xml, "(?<=<totaliops>)[\\s\\S]*?.*?(?=</totaliops>)" );
			List<String> readiops = parseXMLResponse( xml, "(?<=<readiops>)[\\s\\S]*?.*?(?=</readiops>)" );
			List<String> writeiops = parseXMLResponse( xml, "(?<=<writeiops>)[\\s\\S]*?.*?(?=</writeiops>)" );
			List<String> totalkbps = parseXMLResponse( xml, "(?<=<totalkbps>)[\\s\\S]*?.*?(?=</totalkbps>)" );
			List<String> readkbps = parseXMLResponse( xml, "(?<=<readkbps>)[\\s\\S]*?.*?(?=</readkbps>)" );
			List<String> writekbps = parseXMLResponse( xml, "(?<=<writekbps>)[\\s\\S]*?.*?(?=</writekbps>)" );
			List<String> readpercent = parseXMLResponse( xml, "(?<=<readpercent>)[\\s\\S]*?.*?(?=</readpercent>)" );
			List<String> avgsize = parseXMLResponse( xml, "(?<=<avgxfrsize>)[\\s\\S]*?.*?(?=</avgxfrsize>)" );
			List<String> queuedepth = parseXMLResponse( xml, "(?<=<queuedepth>)[\\s\\S]*?.*?(?=</queuedepth>)" );
			List<String> readlatency = parseXMLResponse( xml, "(?<=<readlatency>)[\\s\\S]*?.*?(?=</readlatency>)" );
			List<String> writelatency = parseXMLResponse( xml, "(?<=<writelatency>)[\\s\\S]*?.*?(?=</writelatency>)" );
			
			XIOCache newCache = new XIOCache(
				this.accountName,
				name.get( 0 ),
				totaliops.get( 0 ),
				readiops.get( 0 ),
				writeiops.get( 0 ),
				totalkbps.get( 0 ),
				readkbps.get( 0 ),
				writekbps.get( 0 ),
				readpercent.get( 0 ),
				avgsize.get( 0 ),
				queuedepth.get( 0 ),
				readlatency.get( 0 ),
				writelatency.get( 0 )
			);
			
			query = "accountName == '" + newCache.getAccountName() + "'";
			query = query + " && name == '" + newCache.getName() + "'";
			
			if( store.queryCount( "accountName", query ) == 0 ) {
				store.insert( newCache );
				inserted += 1;
			} else {
				store.modifySingleObject( query, newCache );
				modified += 1;
			}
			if( inserted > 0 )
				logger.info( "----#### XIOFunctions:getCacheInventory ####---- " + item + " Cache info inserted = " + inserted );
			if( modified > 0 )
				logger.info( "----#### XIOFunctions:getCacheInventory ####---- " + item + " Cache info modified = " + modified );
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:getCacheInventory ####---- ERROR ", e );
		}
	}
	private void StoreInterestingData() throws Exception {
		//logger.info( "----#### XIOFunctions:StoreInterestingData ####----" );
		
		try {
			ObjStore<XIOData> store = ObjStoreHelper.getStore( XIOData.class );
			long count = store.deleteAll();
			logger.info( "----#### XIOFunctions:StoreInterestingData ####---- ACCOUNT NAME: " + this.newData.getAccountName() );
			logger.info( "----#### XIOFunctions:StoreInterestingData ####---- RAID 1 COUNT: " + this.newData.getLunRaid1Count() );
			logger.info( "----#### XIOFunctions:StoreInterestingData ####---- RAID 5 COUNT: " + this.newData.getLunRaid5Count() );
			logger.info( "----#### XIOFunctions:StoreInterestingData ####---- TOTAL CAPACITY: " + this.newData.getTotalCapacity() );
			logger.info( "----#### XIOFunctions:StoreInterestingData ####---- LUN USAGE: " + this.newData.getTotalLunUsage() );
			logger.info( "----#### XIOFunctions:StoreInterestingData ####---- TOTAL IO DENSITY: " + this.newData.getTotalIODensity() );
			store.insert( this.newData );
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:StoreInterestingData ####---- ERROR - ", e );
		}
	}
	private void getEndpointInventory( String endpointsXML, String hostsXML ) throws Exception {
		//logger.info( "----#### XIOFunctions:getEndpointInventory ####----" );
		String query = "";
		String host_name = "";
		String host_id = "";
		String host_type = "";
		String host_comment = "";
		int inserted = 0;
		int modified = 0;
		
		try {
			ObjStore<XIOHost> store = ObjStoreHelper.getStore( XIOHost.class );
			long count = store.deleteAll();
			logger.info( "----#### XIOFunctions:getEndpointInventory ####---- Deleted " + count + " entries." );
			
			List<String> endpoints = parseXMLResponse( endpointsXML, "(?<=<endpoint type=\"host\")[\\s\\S]*?.*?(?=</endpoint>)" );
			List<String> hosts = parseXMLResponse( hostsXML, "(?<=<host)[\\s\\S]*?.*?(?=</host>)" );

			logger.info( "----#### XIOFunctions:getEndpointInventory ####---- ENDPOINTS DISCOVERED = " + endpoints.size() + " | HOSTS DISCOVERED = " + hosts.size() );

			for( String endpoint : endpoints ) {
				List<String> globalid = parseXMLResponse( endpoint, "(?<=<globalid>)[\\s\\S]*?.*?(?=</globalid>)" );
				List<String> protocol = parseXMLResponse( endpoint, "(?<=<protocol>)[\\s\\S]*?.*?(?=</protocol>)" );
				
				for( String host : hosts ) {
					List<String> globalid2 = parseXMLResponse( host, "(?<=<globalid>)[\\s\\S]*?.*?(?=</globalid>)" );
					if( globalid.get( 0 ).toString().trim().equals( globalid2.get( 0 ).toString().trim())) {
						logger.info( "----#### XIOFunctions:getEndpointInventory ####---- INSIDE 2" );
						host_name = parseXMLResponse( host, "(?<=<name>)[\\s\\S]*?.*?(?=</name>)" ).get( 0 );
						host_id = parseXMLResponse( host, "(?<=<id>)[\\s\\S]*?.*?(?=</id>)" ).get( 0 );
						host_type = parseXMLResponse( host, "(?<=<type>)[\\s\\S]*?.*?(?=</type>)" ).get( 0 );
						host_comment = parseXMLResponse( host, "(?<=<comment>)[\\s\\S]*?.*?(?=</comment>)" ).get( 0 );
					}
				}
				
				XIOHost newHost = new XIOHost( 
					this.accountName,
					globalid.get( 0 ),
					protocol.get( 0 ),
					host_name,
					host_id,
					host_type,
					host_comment
				);
				
				store.insert( newHost );
				inserted += 1;
			}
			logger.info( "----#### XIOFunctions:getEndpointInventory ####---- Endpoint info inserted = " + inserted );
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:getEndpointInventory ####---- ERROR ", e );
			throw( e );
		}
	}
	private void getMappingInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getMappingInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;

		try {
			ObjStore<XIOMapping> store = ObjStoreHelper.getStore( XIOMapping.class );
			long count = store.deleteAll();
			logger.info( "----#### XIOFunctions:getMappingInventory ####---- Deleted " + count + " entries." );
			
			List<String> mappings = parseXMLResponse( xml, "(?<=<allocation[^s])[\\s\\S]*?.*?(?=</allocation>)" );
			
			for( String mapping : mappings ) {
				List<String> hostname = parseXMLResponse( mapping, "(?<=<hostname>)[\\s\\S]*?.*?(?=</hostname>)" );
				List<String> wwpn = parseXMLResponse( mapping, "(?<=<globalid>)[\\s\\S]*?.*?(?=</globalid>)" );
				List<String> volumename = parseXMLResponse( mapping, "(?<=<volumename>)[\\s\\S]*?.*?(?=</volumename>)" );
				List<String> volumeid = parseXMLResponse( mapping, "(?<=<lun>)[\\s\\S]*?.*?(?=</lun>)" );
				
				String wwpn2 = "";
				if( wwpn.size() > 3 )
					wwpn2 = wwpn.get( 3 );
				
				XIOMapping newMapping = new XIOMapping(
					this.accountName,
					hostname.get( 0 ),
					wwpn2,
					volumename.get( 0 ),
					volumeid.get( 0 )
				);
				
				store.insert( newMapping );
				inserted += 1;
			}
			
			logger.info( "----#### XIOFunctions:getMappingInventory ####---- Mapping info inserted = " + inserted );
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:getMappingInventory ####---- ERROR ", e );
			throw( e );
		}
	}
	private void getEventLogInventory( String xml ) throws Exception {
		//logger.info( "----#### XIOFunctions:getEventLogInventory ####----" );
		String query = "";
		int inserted = 0;
		int modified = 0;

		try {
			ObjStore<XIOEventLog> store = ObjStoreHelper.getStore( XIOEventLog.class );
			query = "accountName == '" + this.accountName + "'";
			long count = store.delete( query );
			logger.info( "----#### XIOFunctions:getEventLogInventory ####---- DELETED  " + count + " ENTRIES" );
			
			List<String> logs = parseXMLResponse( xml, "(?<=<CcsEvent>)[\\s\\S]*?.*?(?=</CcsEvent>)" );
			
			for( String log : logs ) {
				List<String> date = parseXMLResponse( log, "(?<=<date>)[\\s\\S]*?.*?(?=</date>)" );
				List<String> time = parseXMLResponse( log, "(?<=<time>)[\\s\\S]*?.*?(?=</time>)" );
				List<String> type = parseXMLResponse( log, "(?<=<Type>)[\\s\\S]*?.*?(?=</Type>)" );
				List<String> component = parseXMLResponse( log, "(?<=<Component>)[\\s\\S]*?.*?(?=</Component>)" );
				List<String> clas = parseXMLResponse( log, "(?<=<Class>)[\\s\\S]*?.*?(?=</Class>)" );
				List<String> description = parseXMLResponse( log, "(?<=<Description>)[\\s\\S]*?.*?(?=</Description>)" );
				
				String description2[] = description.get( 0 ).split( "-" );
				String description3 = description2[ 2 ].trim();
				if( description3.length() > 256 ) {
					description3 = description3.substring( 0, 256 );
				}
				
				XIOEventLog newLog = new XIOEventLog( 
						this.accountName, 
						date.get( 0 ),
						time.get( 0 ),
						type.get( 0 ),
						component.get( 0 ),
						clas.get( 0 ),
						description3
				);
					
				store.insert( newLog );
				inserted += 1;
			}
			logger.info( "----#### XIOFunctions:getEventLogInventory ####---- Endpoint info inserted = " + inserted );
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:getEventLogInventory ####---- ERROR ", e );
		}
	}
	public String createNewLun( String accName, String id, String name, int size, String redundancy, String cache, String comment ) throws Exception {
		PhysicalInfraAccount phyAccount = AccountUtil.getAccountByName( accName );
		DeviceCredential cred = phyAccount.toDeviceCredential();
		
		String url = cred.getProtocol() + "://" + cred.getDeviceIp() + ":" + cred.getPort();;
		url = url + "/storage/volumes";
		
		String body = "name=" + name + "&size=" + size + "&redundancy=" + redundancy + "&writecache=" + cache +"&comment=" + comment;
		String username = cred.getUsername();
		String password = cred.getPassword();

		String response = getResponse( url, body, "POST", username, password );
		String stat[] = this.lastStatusResponse.split( " " );
		String status = stat[ 1 ];
		
		if( response != null ) {
			List<String> results = parseXMLResponse( response, "(?<=<response value=\"(.?)\">)[\\s\\S]*?.*?(?=</response>)" );
			response = results.get( 0 );
		}

		return status + "--" + response;
	}
	public boolean editLun( String id, String name, int size, String redundancy, String cache ) {
		logger.info( "##################XIOFunctions:editLun##################" );
		logger.info( "#### CONTEXT ID = " + id );
		logger.info( "#### LUN NAME = " + name );
		logger.info( "#### LUN SIZE = " + size );
		logger.info( "#### LUN REDUNDANCY = " + redundancy );
		logger.info( "#### LUN CACHE = " + cache );
		logger.info( "##################XIOFunctions:editLun##################" );		
		return false;
	}
	public String deleteLun( String id, String accName ) throws Exception {
		String response = this.deleteLunFromXIO( id, accName );
		return response;
	}
	private String deleteLunFromXIO( String id, String accName ) throws Exception {
		PhysicalInfraAccount phyAccount = AccountUtil.getAccountByName( accName );
		DeviceCredential cred = phyAccount.toDeviceCredential();
		
		String url = cred.getProtocol() + "://" + cred.getDeviceIp() + ":" + cred.getPort();
		String username = cred.getUsername();
		String password = cred.getPassword();
		String globalID = getGlobalLunIDFromLocalID( id );
	
		String response = getResponse( url, "/storage/volumes/" + globalID, "DELETE", username, password );
		String status = this.lastStatusResponse;
		String response2 = response;
		
		//if( response != null ) {
		//	List<String> results = parseXMLResponse( response, "(?<=<response value=\"(.?)\">)[\\s\\S]*?.*?(?=</response>)" );
		//	response = results.get( 0 );
		//}

		logger.info( "##################XIOFunctions:deleteLunFromXIO##################" );	
		logger.info( "#### RAW RESPONSE = " + response );
		logger.info( "#### STATUS = " + status );
		logger.info( "#### RESPONSE = " + response2 );
		logger.info( "##################XIOFunctions:deleteLunFromXIO##################" );	
		
		if( status.equals( XIOConstants.XIO_HTTP_STR_CODE_SUCCESS )) {
			return status + "--" + response;
		} else {
			return null;
		}
	}
	private boolean deleteLunFromDB( String id ) {
		try {
			ObjStore<XIOLun> store = ObjStoreHelper.getStore( XIOLun.class );
			String query = "id == '" + id + "'";
			long count = store.delete( query );
			if( count == 1 ) {
				return true;
			} else {
				return false;
			}
		} catch( Exception e ) {
			logger.info( "----#### XIOFunctions:deleteLun ####---- ERROR ", e );
			return false;
		}
	}
	public boolean createNewHost( String id, String name, String os, String wwpn, String comment ) {
		logger.info( "##################XIOFunctions:createNewHost##################" );
		logger.info( "#### CONTEXT ID = " + id );
		logger.info( "#### HOST NAME = " + name );
		logger.info( "#### HOST OS = " + os );
		logger.info( "#### HOST WWPN = " + wwpn );
		logger.info( "#### HOST COMMENT = " + comment );
		logger.info( "##################XIOFunctions:createNewHost##################" );
		return false;
	}
	public boolean editHost( String id, String name, String operatingsystem, String wwpn, String comment ) {
		logger.info( "##################XIOFunctions:editHost##################" );
		logger.info( "#### CONTEXT ID = " + id );
		logger.info( "#### HOST NAME = " + name );
		logger.info( "#### HOST OS = " + operatingsystem );
		logger.info( "#### HOST WWPN = " + wwpn );
		logger.info( "#### HOST COMMENT  = " + comment );
		logger.info( "##################XIOFunctions:editHost##################" );
		return false;
	}
	public boolean deleteHost( String id ) {
		logger.info( "##################XIOFunctions:deleteHost##################" );
		logger.info( "#### CONTEXT ID = " + id );
		logger.info( "##################XIOFunctions:deleteHost##################" );
		return false;
	}
}
