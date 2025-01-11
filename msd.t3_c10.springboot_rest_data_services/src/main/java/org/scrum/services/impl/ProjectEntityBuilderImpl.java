package org.scrum.services.impl;

import org.scrum.domain.project.Project;
import org.scrum.services.IProjectEntityBuilder;
import org.scrum.services.IProjectEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProjectEntityBuilderImpl implements IProjectEntityBuilder {
	private String projectName;
	private Date startDate;
	private Integer releaseIntervalInMonths;
	
	@Autowired
	private IProjectEntityFactory entityFactory;
	
	private ProjectEntityBuilderImpl(IProjectEntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	@Override
	public IProjectEntityBuilder projectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	@Override
	public IProjectEntityBuilder startDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}

	@Override
	public IProjectEntityBuilder releaseIntervalInMonths(Integer releaseIntervalInMonths) {
		this.releaseIntervalInMonths = releaseIntervalInMonths;
		return this;
	}

	@Override
	public Project build() {
		if (this.projectName != null && this.startDate != null && this.releaseIntervalInMonths != null)
			return this.entityFactory.buildProjectWith2R(projectName, startDate, releaseIntervalInMonths);
		throw new RuntimeException("Not enough data to build Project!");
	}

	@Override
	public IProjectEntityBuilder startBuilding() {
		return new ProjectEntityBuilderImpl(this.entityFactory);
	}

}
