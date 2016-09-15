package com.cloupia.feature.xio.accounts;

import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.apache.log4j.Logger;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.cIaaS.network.model.DeviceCredential;
import com.cloupia.lib.connector.account.AbstractInfraAccount;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.service.cIM.inframgr.collector.view2.ConnectorCredential;
import com.cloupia.service.cIM.inframgr.forms.wizard.FieldValidation;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;
import com.cloupia.service.cIM.inframgr.forms.wizard.HideFieldOnCondition;

@PersistenceCapable(detachable = "true", table = "xio_account")
public class XIOAccount extends AbstractInfraAccount implements ConnectorCredential {
	static Logger logger = Logger.getLogger( DeviceCredential.class );

	@Persistent( sequence = "deviceidseq", valueStrategy = IdGeneratorStrategy.INCREMENT )
	private long deviceId;
	@Persistent
	@FormField( label = "Device IP", help = "Device IP", mandatory = true )
	private String deviceIp;
	@Persistent
	@FormField( label = "Use Credential Policy", validate = true, help = "Select if you want to use policy to give the credentials.", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN )
	private boolean isCredentialPolicy = false;
	@Persistent
	@FormField( label = "Protocol", help = "Protocol", type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, validate = true, lov = { "http", "https" })
	@HideFieldOnCondition(field = "isCredentialPolicy", op = FieldValidation.OP_EQUALS, value = "true" )
	private String protocol = "http";
	@FormField( label = "Port", help = "Port Number", type = FormFieldDefinition.FIELD_TYPE_TEXT )
	@Persistent
	@HideFieldOnCondition( field = "isCredentialPolicy", op = FieldValidation.OP_EQUALS, value = "true" )
	private String port = "80";
	@Persistent
	@FormField( label = "Login", help = "Login" )
	@HideFieldOnCondition( field = "isCredentialPolicy", op = FieldValidation.OP_EQUALS, value = "true" )
	private String login;
	@Persistent
	@FormField( label = "Password", help = "Password", type = FormFieldDefinition.FIELD_TYPE_PASSWORD )
	@HideFieldOnCondition( field = "isCredentialPolicy", op = FieldValidation.OP_EQUALS, value = "true" )
	private String password;

	public long getDeviceId() {
		logger.info( "----#### XIOAccount:getDeviceId ####----" );
		return this.deviceId;
	}
	public void setDeviceId( long deviceId ) {
		logger.info( "----#### XIOAccount:setDeviceId ####----" );
		this.deviceId = deviceId;
	}
	public String getDeviceIp() {
		logger.info( "----#### XIOAccount:getDeviceIp ####----" );
		return deviceIp;
	}
	public void setDeviceIp( String deviceIp ) {
		logger.info( "----#### XIOAccount:setDeviceIp ####----" );
		this.deviceIp = deviceIp;
	}
	public boolean isCredentialPolicy() {
		logger.info( "----#### XIOAccount:isCredentialPolicy ####----" );
		return this.isCredentialPolicy;
	}
	public void setCredentialPolicy( boolean isCredentialPolicy ) {
		logger.info( "----#### XIOAccount:setCredentialPolicy ####----" );
		this.isCredentialPolicy = isCredentialPolicy;
	}
	public String getProtocol() {
		logger.info( "----#### XIOAccount:getProtocol ####----" );
		return this.protocol;
	}
	public void setProtocol( String protocol ) {
		logger.info( "----#### XIOAccount:setProtocol ####----" );
		this.protocol = protocol;
	}
	public String getPort() {
		logger.info( "----#### XIOAccount:getPort ####----" );
		return this.port;
	}
	public void setPort( String port ) {
		logger.info( "----#### XIOAccount:setPort ####----" );
		this.port = port;
	}
	public String getLogin() {
		logger.info( "----#### XIOAccount:getLogin ####----" );
		return this.login;
	}
	public void setLogin(String login) {
		logger.info( "----#### XIOAccount:setLogin ####----" );
		this.login = login;
	}
	public String getPassword() {
		logger.info( "----#### XIOAccount:getPassword ####----" );
		return this.password;
	}
	public void setPassword(String password) {
		logger.info( "----#### XIOAccount:setPassword ####----" );
		this.password = password;
	}
	@Override
	public InfraAccount toInfraAccount() {
		logger.info( "----#### XIOAccount:toInfraAccount ####----" );
		try {
			ObjStore<InfraAccount> store = ObjStoreHelper.getStore(InfraAccount.class);
			String cquery = "server == '" + deviceIp + "' && userID == '" + login + "' && transport == '" + protocol + "' && port == " + Integer.parseInt(port);
			List<InfraAccount> accList = store.query(cquery);
			if( accList != null && accList.size() > 0 ) {
				return accList.get(0);
			} else {
				logger.info( "----#### XIOAccount:toInfraAccount ####----" );
				return null;
			}
		} catch (Exception e) {
			logger.info( "----#### XIOAccount:toInfraAccount::ERROR:::: ####----", e );
		}
		return null;
	}
	@Override
	public String getServerAddress() {
		logger.info( "----#### XIOAccount:getServerAddress ####----" );
		return this.getDeviceIp();
	}
	@Override
	public String getPolicy() {
		logger.info( "----#### XIOAccount:getPolicy ####----" );
		return null;
	}
	@Override
	public void setPolicy( String policy ) {
		logger.info( "----#### XIOAccount:setPolicy ####----" );
	}
	@Override
	public void setPort( int port ) {
		logger.info( "----#### XIOAccount:setPort ####----" );
	}
	@Override
	public void setUsername( String username ) {
		logger.info( "----#### XIOAccount:setUsername ####----" );
	}
}
