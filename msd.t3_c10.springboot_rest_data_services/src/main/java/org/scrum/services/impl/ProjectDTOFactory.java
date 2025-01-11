package org.scrum.services.impl;

import org.scrum.domain.project.Feature;
import org.scrum.domain.project.Project;
import org.scrum.domain.project.Release;
import org.scrum.services.IProjectDTOFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProjectDTOFactory implements IProjectDTOFactory {
	private static Logger logger = Logger.getLogger(ProjectDTOFactory.class.getName());
	
	// Default constructor
	public ProjectDTOFactory() {
		logger.info(">>> BEAN: ProjectDTOFactoryEJB instantiated!");
	}
	 
	/* DTO Project Logic*/
	@Override
	public Project toDTO(Project project){
		Project projectDTO = new Project(project.getProjectNo(), project.getName(), project.getStartDate()); 
		return projectDTO;
	}
	
	@Override
	public List<Project> toDTOList(Collection<Project> projects){
		List<Project> projectDTOList = new ArrayList<>();
		for(Project p: projects){
			projectDTOList.add(toDTO(p));
		}
		return projectDTOList;
	}	

	@Override
	public Collection<Project> toDTOs(Collection<Project> projects){
		List<Project> projectDTOList = new ArrayList<>();
		for(Project p: projects){
			projectDTOList.add(toDTO(p));
		}
		return projectDTOList;
	}	
	
	@Override
	public Project toDTOAggregate(Project project){
		if (project == null)
			return null;
		Project projectDTO = toDTO(project);
		List<Release> releasesDTO = toDTOReleaseAggregateList(project.getReleases());
		projectDTO.setReleases(releasesDTO);
		if(project.getCurrentRelease() != null)
			projectDTO.setCurrentRelease(toDTOReleaseAggregate(project.getCurrentRelease()));
		return projectDTO;
	}	
	
	/* DTO Release Logic*/
	public Release toDTORelease(Release r){
		return new Release(r.getReleaseId(), r.getIndicative(), r.getPublishDate(), toDTO(r.getProject()));
	}
	
	public List<Release> toDTOReleaseList(List<Release> releases){
		List<Release> releaseDTOList = new ArrayList<>();
		for(Release r: releases){
			releaseDTOList.add(toDTORelease(r));
		}
		return releaseDTOList;
	}
	
	public Release toDTOReleaseAggregate(Release r){
		Release releaseDTO = new Release(r.getReleaseId(), r.getIndicative(), r.getPublishDate(), null);
		List<Feature> featureDTOLst = new ArrayList<>();
		for (Feature f: r.getFeatures())
			featureDTOLst.add(new Feature(f.getFeatureID(), f.getName(), f.getDescription()));
		releaseDTO.setFeatures(featureDTOLst);
		return releaseDTO;
		
	}	
	
	public List<Release> toDTOReleaseAggregateList(List<Release> releases){
		List<Release> releaseDTOList = new ArrayList<>();
		for(Release r: releases){
			releaseDTOList.add(toDTOReleaseAggregate(r));
		}
		return releaseDTOList;
	}

	@Override
	public List<Project> toDTOListOfAggregates(Collection<Project> projects) {
		List<Project> projectDTOList = new ArrayList<>();
		for(Project p: projects){
			projectDTOList.add(toDTOAggregate(p));
		}
		return projectDTOList;
	}
}
