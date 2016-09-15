package com.cloupia.feature.xio.forms.lun;

import com.cloupia.feature.xio.constants.XIOConstants;
import com.cloupia.feature.xio.lovs.XIORedundancyLovProvider;
import com.cloupia.feature.xio.lovs.XIOWriteCacheLovProvider;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class XIOAddNewLunForm {
	@FormField(label = "Name", help = "Enter the name for the LUN", mandatory = true )
 	private String name;
    @FormField(label = "Redundancy", help = "Select the Raid level for the LUN", type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = XIORedundancyLovProvider.SIMPLE_LOV_PROVIDER)
    private String redundancy;
    @FormField( label = "Size (GB)", help = "Enter the size of the LUN in GB", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = XIOConstants.XIO_MAX_LUN_SIZE, mandatory = true )
    private int size;
    @FormField(label = "Write Cache", help = "Select the Write Cache required", type = FormFieldDefinition.FIELD_TYPE_EMBEDDED_LOV, lovProvider = XIOWriteCacheLovProvider.SIMPLE_LOV_PROVIDER)
    private String cache;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRedundancy() {
		return redundancy;
	}
	public void setRedundancy( String value ) {
		this.redundancy = value;
	}
	public int getSize() {
		return size;
	}
	public void setSize( int number ) {
		this.size = number;
	}
	public String getWriteCache() {
		return cache;
	}
	public void setWriteCache( String value ) {
		this.cache = value;
	}
	public String getComment() {
		return XIOConstants.XIO_NEW_LUN_COMMENT;
	}
}
