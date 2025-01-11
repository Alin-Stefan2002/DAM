package org.scrum.services;

import org.scrum.domain.project.Project;

import java.util.Collection;
import java.util.List;

public interface IProjectDTOFactory {

	/* DTO Logic*/
	Project toDTO(Project project);

	Project toDTOAggregate(Project project);

	List<Project> toDTOList(Collection<Project> projects);
	
	List<Project> toDTOListOfAggregates(Collection<Project> projects);

	Collection<Project> toDTOs(Collection<Project> projects);

}