package org.scrum.domain.project;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 * Domain Entity View
 */
@Data @RequiredArgsConstructor @NoArgsConstructor
public class ProjectCurrentReleaseView {
	// Encapsulated tuple-field structure
	@NonNull private Integer projectNo;
	@NonNull private String name;
	//
	@NonNull private Integer currentReleaseId;
	@NonNull private String releaseCodeName;
	//
	@NonNull private Integer releaseFeatureCount;
	// to Print
	@Override
	public String toString() {
		return "ProjectCurrentReleaseView [projectNo=" + projectNo + ", name=" + name + ", currentReleaseId="
				+ currentReleaseId + ", releaseCodeName=" + releaseCodeName + ", releaseFeatureCount="
				+ releaseFeatureCount + "]";
	}
}
