package com.cloupia.feature.xio.forms.mapping;

import com.cloupia.feature.xio.lovs.SimpleTabularProvider;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

public class XIOEditMappingForm {
	@FormField(label = "Name", help = "Name")
 	private String name;
 
    @FormField(label = "Number", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
    private int number;
    
    @FormField(label = "Bool", type = FormFieldDefinition.FIELD_TYPE_BOOLEAN)
    private boolean boolType;
  
    @FormField(label = "Tabular Popup", type = FormFieldDefinition.FIELD_TYPE_TABULAR_POPUP, table = SimpleTabularProvider.SIMPLE_TABULAR_PROVIDER)
    private String tabularPopup;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean getBoolType() {
		return boolType;
	}

	public void setBoolType(boolean boolType) {
		this.boolType = boolType;
	}

	public String getTabularPopup() {
		return tabularPopup;
	}

	public void setTabularPopup(String tabularPopup) {
		this.tabularPopup = tabularPopup;
	}

}