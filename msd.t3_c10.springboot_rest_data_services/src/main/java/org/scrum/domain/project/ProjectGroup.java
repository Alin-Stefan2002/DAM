package org.scrum.domain.project;

import lombok.Value;

// ValueObject Abstract Data Type
@Value
public class ProjectGroup {
	private String groupName;
	private String groupLabel;
}
