package com.cloupia.feature.xio.forms.host;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.lovs.XIOOperatingSystemLovProvider;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class XIOEditHostForm {
	@FormField(label = "Host Name", help = "Enter the name for this host", mandatory = true )
 	private String name;
    @FormField(label = "Operating System", help = "Please select the operating system on this host", type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = XIOOperatingSystemLovProvider.SIMPLE_LOV_PROVIDER)
    private String operatingsystem;
    @FormField(label = "WWPN", help = "Please enter the WWPN for this host", mandatory = true )
    private String wwpn;
   
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public String getOperatingSystem() {
		return operatingsystem;
	}
	public void setOperatingSystem( String value ) {
		this.operatingsystem = value;
	}
	public String getWWPN() {
		return this.wwpn;
	}
	public void setWWPN( String wwpn ) {
		this.wwpn = wwpn;
	}
	public String getComment() {
		return XIOConstants.XIO_NEW_HOST_COMMENT;
	}
}