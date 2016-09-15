package com.cloupia.feature.xio.reports.device.about;

import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;
import com.cloupia.service.cIM.inframgr.forms.wizard.HtmlPopupStyles;

public class XIODeviceAboutReportObject {
	@FormField(type = FormFieldDefinition.FIELD_TYPE_LABEL, label = "dummy link", 
			htmlPopupTag = "http://www.cisco.com",
			htmlPopupLabel = "http://www.cisco.com", htmlPopupStyle = HtmlPopupStyles.CUSTOM_URL )
	private String dummyLink;
	@FormField( type = FormFieldDefinition.FIELD_TYPE_HTML_LABEL, label = "Sample Link:" , htmlPopupLabel = "<a href='http://www.cisco.com'>Cisco</a>" )
	private String dummyLink2;
	@FormField( label = "Name", help = "Name" )
 	private String name;
	
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public String getDummyLink() {
		return dummyLink;
	}
	public void setDummyLink( String dummyLink ) {
		this.dummyLink = dummyLink;
	}
	public String getDummyLink2() {
		return dummyLink2;
	}
	public void setDummyLink2( String dummyLink2 ) {
		this.dummyLink2 = dummyLink2;
	}
}
