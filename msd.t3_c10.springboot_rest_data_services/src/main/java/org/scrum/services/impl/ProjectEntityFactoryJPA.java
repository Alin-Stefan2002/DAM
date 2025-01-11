package org.scrum.services.impl;

import org.scrum.domain.project.Project;
import org.scrum.domain.project.Release;
import org.scrum.domain.repositories.IProjectRepository;
import org.scrum.services.DateUtils4J8API;
import org.scrum.services.IProjectEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
@Scope("singleton")
public class ProjectEntityFactoryJPA implements IProjectEntityFactory {
	private static Logger logger = Logger.getLogger(ProjectEntityFactoryJPA.class.getName());
	public ProjectEntityFactoryJPA() {
		logger.info(">>> BEAN: ProjectEntityFactoryCDI instantiated!");
	}
	
	// build project with: 1 release, startDate is Now, release publish date:
	// startDate + 1 Month
	@Override
	public Project buildSimpleProject(String projectName) {
		LocalDateTime startLocalDate = LocalDateTime.now();
		Project newProject = new Project(projectName, DateUtils4J8API.asDate(startLocalDate));
		// add one release
		List<Release> releasesProject = new ArrayList<>();
		Date dataPublicare = DateUtils4J8API.asDate(startLocalDate.plusMonths(1));

		Release release = new Release("R1", dataPublicare, newProject);
		releasesProject.add(release);
		newProject.setReleases(releasesProject);
		// make release current
		newProject.setCurrentRelease(release);
		return newProject;
	}

	@Override
	public Project buildProjectWith2R(String projectName, Date startDate, Integer releaseIntervalInMonths) {
		LocalDateTime startLocalDate = DateUtils4J8API.asLocalDateTime(startDate);
		Project newProject = new Project(projectName, DateUtils4J8API.asDate(startLocalDate));

		List<Release> releasesProject = new ArrayList<>();
		Date dataPublicare = DateUtils4J8API.asDate(startLocalDate.plusMonths(1));
		
		// first release
		releasesProject.add(new Release("R1", dataPublicare, newProject));
		dataPublicare = DateUtils4J8API.asDate(startLocalDate.plusMonths(releaseIntervalInMonths));
		newProject.setCurrentRelease(releasesProject.get(0));
		
		// second release
		releasesProject.add(new Release("R2", dataPublicare, newProject));
		newProject.setReleases(releasesProject);
		
		return newProject;
	}

	@Override
	public Project buildProjectWith2R(String projectName, LocalDateTime startDate, Integer releaseIntervalInMonths) {
		LocalDateTime startLocalDate = startDate;
		Project newProject = new Project(projectName, DateUtils4J8API.asDate(startLocalDate));

		List<Release> releasesProject = new ArrayList<>();
		Date dataPublicare = DateUtils4J8API.asDate(startLocalDate.plusMonths(1));
		releasesProject.add(new Release("R1", dataPublicare, newProject));
		dataPublicare = DateUtils4J8API.asDate(startLocalDate.plusMonths(releaseIntervalInMonths));
		releasesProject.add(new Release("R2", dataPublicare, newProject));
		newProject.setReleases(releasesProject);

		return newProject;
	}

	@Override
	public Project buildProjectWithXR(String projectName, List<Date> releaseStartDates) {
		LocalDateTime startLocalDate = LocalDateTime.now();
		// ? startLocalDate
		Project newProject = new Project(projectName, DateUtils4J8API.asDate(startLocalDate));
		
		List<Release> releasesProject = new ArrayList<>();
		int releaseID = 0;
		for(Date releaseStartDate: releaseStartDates) {
			releasesProject.add(new Release("R" + (releaseID++), releaseStartDate, newProject));
		}
		newProject.setReleases(releasesProject);
		
		return newProject;
	}

	// Dependency
	@Autowired
	private IProjectRepository entityRepository;

	//@Override
	public void setProjectEntityRepository(IProjectRepository repository) {
		this.entityRepository = repository;
	}

	public ProjectEntityFactoryJPA(IProjectRepository entityRepository) {
		super();
		this.entityRepository = entityRepository;
	}

	// build Project entity from DTO and update with DTO
	@Override
	public Project toEntity(Project projectDTO) {
		Project projectEntity = this.entityRepository.findById(projectDTO.getProjectNo()).orElseThrow();
		projectEntity.setName(projectDTO.getName());
		projectEntity.setStartDate(projectDTO.getStartDate());
		return projectEntity;
	}
	
//	@PostConstruct
	@Override
	public void initDomainServiceEntities() {
		logger.info(">> PostConstruct :: initDomainServiceEntities");
		for(int i=1; i<=3; i++) {
			Project newProject = buildProjectWith2R("Project_" + i, new Date(), 1);
			newProject.setCurrentRelease(newProject.getReleases().get(0));
			newProject.getCurrentRelease().addFeature("Feature_sample_" + newProject.getProjectNo() + "_1");
			newProject.getCurrentRelease().addFeature("Feature_sample_" + newProject.getProjectNo() + "_2");
			newProject.getCurrentRelease().addFeature("Feature_sample_" + newProject.getProjectNo() + "_3");
			
			entityRepository.save(newProject);
		}
		logger.info(">> EntityRepository project.count :: " + entityRepository.count());
	}
}
