package org.scrum.domain.project;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
public class ProjectAuditView {
	@NonNull private Integer projectNo;
	@NonNull private String featureName;
	@NonNull private EFeatureOperation operation;
	
	@Override
	public String toString() {
		return "ProjectAuditView [projectNo=" + projectNo + ", featureName=" + featureName + ", operation=" + operation
				+ "]";
	}

	public enum EFeatureOperation{
		UPDATED, REMOVED, ADDED;
	}	
}
