package com.cloupia.feature.xio.accounts.api;

import org.apache.log4j.Logger;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.cloupia.feature.xio.accounts.XIOAccount;

public class XIOAccountAPI {
	private static Logger logger = Logger.getLogger( XIOAccountAPI.class );
	private static final HashMap<String, XIOAccountAPI> instances = new HashMap<String, XIOAccountAPI>();
	private String ipAddress;
	@SuppressWarnings("unused")
	private String username;
	@SuppressWarnings("unused")
	private String password;
	private String protocol;
	private int port;
	@SuppressWarnings("unused")
	private int apiVersion = 1;
	@SuppressWarnings("unused")
	private String token = null;

	private XIOAccountAPI() {
		logger.info( "----#### XIOAccountAPI:getDeviceId ####----" );
	}
	private XIOAccountAPI( String ipAddress, String username, String password, int port, String protocol ) {
		logger.info( "----#### XIOAccountAPI:getDeviceId ####----" );
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		this.port = port;
		this.protocol = protocol;
	}
	public static XIOAccountAPI getXIOAccountAPI( XIOAccount account ) throws Exception {
		logger.info( "----#### XIOAccountAPI:getDeviceId ####----" );
		return getInstanceFor( account.getServerAddress(), account.getLogin(), account.getPassword(), Integer.parseInt( account.getPort()), account.getProtocol());
	}
	public static XIOAccountAPI getInstanceFor( String ipAddress, String username, String password, int port, String protocol ) throws Exception {
		logger.info( "----#### XIOAccountAPI:getDeviceId ####----" );
		XIOAccountAPI api = instances.get( ipAddress + username + password + port );
		if( api == null ) {
			api = new XIOAccountAPI( ipAddress, username, password, port, protocol );
			instances.put(ipAddress + username + password + port, api);
		} else {
		}
		return api;
	}
	@SuppressWarnings("unused")
	private HttpClient trustEveryoneSSLHttpClient() {
		logger.info( "----#### XIOAccountAPI:trustEveryoneSSLHttpClient ####----" );
		try {
			SchemeRegistry registry = new SchemeRegistry();
			SSLSocketFactory socketFactory = new SSLSocketFactory( new TrustStrategy() {
						public boolean isTrusted( final X509Certificate[] chain, String authType ) throws CertificateException {
							return true;
						}
					},
					org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			registry.register( new Scheme( this.protocol, port, socketFactory ));
			ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager( registry );
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout( params, 30000 );
			HttpConnectionParams.setSoTimeout( params, 30000 );
			DefaultHttpClient client = new DefaultHttpClient( mgr );
			client.setParams(params);
			return client;
		} catch (GeneralSecurityException e) {
			logger.info( "----#### XIOAccountAPI:trustEveryoneSSLHttpClient ####----" );
			throw new RuntimeException(e);
		}
	}
	public String getInventoryData( String url ) throws Exception {
		logger.info( "----#### XIOAccountAPI:getInventoryData ####----" );
		return url;
	}
	@SuppressWarnings("unused")
	private String getCompleteUrl(String url) {
		logger.info( "----#### XIOAccountAPI:getCompleteUrl ####----" );
		return this.protocol + "://" + ipAddress + ":" + port + "/" + url;
	}
}
