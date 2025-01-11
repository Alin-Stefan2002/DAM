package org.scrum.domain.project;

import jakarta.persistence.AttributeConverter;

public class ProjectGroupConverter implements 
	AttributeConverter<ProjectGroup, String>{

	@Override
	public String convertToDatabaseColumn(ProjectGroup attribute) {
		if (attribute != null) {
			System.out.println(">>> convertToDatabaseColumn: " + attribute.getGroupName() + ";" + attribute.getGroupLabel());
			return attribute.getGroupName() + ";" 
					+ attribute.getGroupLabel();
		}
		return null;
	}

	@Override
	public ProjectGroup convertToEntityAttribute(String dbData) {
		if (dbData != null) {
			String[] sqlData = dbData.split(";");
			if (sqlData != null && sqlData.length > 0)
				return new ProjectGroup(sqlData[0], sqlData[1]);
		}
		return null;
	}

}
