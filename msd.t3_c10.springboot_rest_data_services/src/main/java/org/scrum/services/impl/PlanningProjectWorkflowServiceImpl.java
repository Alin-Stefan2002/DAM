package org.scrum.services.impl;

import org.scrum.domain.project.Project;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.domain.project.Release;
import org.scrum.domain.repositories.IProjectRepository;
import org.scrum.services.DomainEvent;
import org.scrum.services.IPlanningProjectWorkflowService;
import org.scrum.services.IProjectCurrentReleaseConsolidatingService;
import org.scrum.services.IProjectEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.logging.Logger;

@Service
@Transactional
public class PlanningProjectWorkflowServiceImpl
		implements IPlanningProjectWorkflowService {
	private static Logger logger = Logger.getLogger(PlanningProjectWorkflowServiceImpl.class.getName());
	
	// Support Services
	@Autowired
	private IProjectRepository entityRepository;
	
	@Autowired
	private IProjectEntityFactory entityFactory;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private IProjectCurrentReleaseConsolidatingService consolidatingProjectService;
	
	// (1) Create new project with default template: projectName, startDate
	@Override
	public Integer planNewProject(String projectName, Date startDate) {
		Project project = entityFactory.buildProjectWith2R(projectName, startDate, 3);
		
		// Validate Event to invoke Validation Service
		// applicationEventPublisher.publishEvent(new DomainEvent(this, project));
		
		entityRepository.save(project);
		return project.getProjectNo();
	}
	
	// (2) Create new project with default template: projectName, startDate
	@Override
	public Integer addFeatureToProject(Integer projectId, String featureName, String featureDescription) {
		Project project = entityRepository.getById(projectId);
		project.getCurrentRelease().addFeature(featureName);
		
		entityRepository.save(project);
		return project.getCurrentRelease().getReleaseId();
	}
	
	// (3) Create new project with default template: projectName, startDate
	@Override
	public Integer planCurrentRelease(Integer projectId, Date publishDate) {
		Project project = entityRepository.getById(projectId);
		Release currentRelease = project.getCurrentRelease();
		currentRelease.setPublishDate(publishDate);
		
		entityRepository.save(project);
		return project.getCurrentRelease().getReleaseId();
	}
	
	// (4) Get project summary data: ProjectCurrentReleaseView
	@Override
	public ProjectCurrentReleaseView getProjectSummaryData(Integer projectId) {
		Project project = entityRepository.getById(projectId);
		applicationEventPublisher.publishEvent(new DomainEvent(this, project));
		
		return consolidatingProjectService.getProjectCurrentReleaseViewDataOf(project);
	}
}
