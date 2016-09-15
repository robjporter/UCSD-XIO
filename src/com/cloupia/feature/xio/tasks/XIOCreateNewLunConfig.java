package com.cloupia.feature.xio.tasks;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.customactions.WorkflowInputFieldTypeDeclaration;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

@PersistenceCapable(detachable = "true", table = "xio_createnewluntask")
public class XIOCreateNewLunConfig implements TaskConfigIf {
	public static final String displayLabel = "CreateNewVolumeTask";
	
	@Persistent
	private long configEntryId;
	@Persistent
	private long actionId;
	
	@FormField(label = "Volume Name", help = "New Volume Name", mandatory = true)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.VOLUME)
	@Persistent
	private String volumeName  = "";

	@FormField(label = "Volume Size", help = "New Volume Size", mandatory = true)
	@UserInputField(type = WorkflowInputFieldTypeDeclaration.SIZE_UNIT)
	@Persistent
	private int volumeSize = 0;

	@FormField(label = "Volume Raid", help = "New Volume Raid Type", mandatory = true )
	@Persistent
	private String volumeRaid = "RAID-1";
	
	@FormField(label = "Volume Cache", help = "New Volume Cache Type", mandatory = true )
	@Persistent
	private String volumeCache = "Write-Back";
	
	@Override
	public long getActionId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getConfigEntryId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDisplayLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setActionId(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConfigEntryId(long arg0) {
		// TODO Auto-generated method stub
		
	}

}
