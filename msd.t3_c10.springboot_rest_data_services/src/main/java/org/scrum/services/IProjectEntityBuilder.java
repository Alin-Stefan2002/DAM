package org.scrum.services;

import org.scrum.domain.project.Project;

import java.util.Date;

public interface IProjectEntityBuilder {
	//
	IProjectEntityBuilder startBuilding();
	//
	IProjectEntityBuilder projectName(String projectName);
	IProjectEntityBuilder startDate(Date startDate);
	IProjectEntityBuilder releaseIntervalInMonths(Integer releaseIntervalInMonths);
	//
	Project build();
	//
}
