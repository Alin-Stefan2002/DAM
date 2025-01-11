package org.scrum.rest.services;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.scrum.domain.project.Project;
import org.scrum.domain.project.Release;
import org.scrum.domain.repositories.IProjectRepository;
import org.scrum.dto.ProjectDTO;
import org.scrum.dto.ReleaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * http://localhost:8080/scrum/rest/service/projects
 */

@RestController @RequestMapping("/rest/service/projects") // REST.Resource Style
@Transactional
public class ProjectAppServiceREST {
	private static Logger logger = Logger.getLogger(ProjectAppServiceREST.class.getName());
	
	@Autowired
	private IProjectRepository projectEntityRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	//@GetMapping
	@RequestMapping(method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Collection<ProjectDTO> toCollection() {
		logger.info("**** DEBUG SPRING-MVC-REST toCollection() >>> get All DTO projects::");
		List<Project> projects = projectEntityRepository.findAll();
		projects.forEach(p -> logger.info(">>> Project Entites: " + p));

		// prepare dtos
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		projectDTOs = projects.stream().map(p -> modelMapper.map(p, ProjectDTO.class)).collect(Collectors.toList());
		projectDTOs.forEach(p -> logger.info(">>> Project DTO: " + p));

		logger.info(">>>>> RETURN DTOs");
		return projectDTOs;
	}
	
	//@PostMapping
	@RequestMapping(method = RequestMethod.POST, 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Collection<ProjectDTO> addIntoCollection(@RequestBody ProjectDTO projectDTO) {
		Project project = modelMapper.map(projectDTO, Project.class);
		// fix back references
		project.getReleases().forEach(r -> r.setProject(project));
		// save aggregate
		projectEntityRepository.save(project);
		logger.info("**** DEBUG SPRING-MVC-REST save aggregate POST!");
		// return updated collection
		List<Project> projects = projectEntityRepository.findAll();
		//
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		projectDTOs = projects.stream().map(p -> modelMapper.map(p, ProjectDTO.class)).collect(Collectors.toList());
		projectDTOs.forEach(p -> logger.info(">>> Project DTO: " + p));

		logger.info(">>>>> RETURN DTOs");
		return projectDTOs;
	}
	
	//@DeleteMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
			produces = {MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Collection<ProjectDTO> removeFromCollection(@PathVariable Integer id) {
		logger.info("DEBUG: called SPRING-MVC-REST REMOVE - project: " + id);
		projectEntityRepository.deleteById(id);
		// return updated collection
		List<Project> projects = projectEntityRepository.findAll();
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		projectDTOs = projects.stream().map(p -> modelMapper.map(p, ProjectDTO.class)).collect(Collectors.toList());
		projectDTOs.forEach(p -> logger.info(">>> Project DTO: " + p));

		logger.info(">>>>> RETURN DTOs");
		return projectDTOs;
	}
	
	//@GetMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ProjectDTO getById(@PathVariable("id") Integer id){
		Project project = projectEntityRepository.findById(id).get();
		logger.info("**** DEBUG SPRING-MVC-REST getById(" + id +") = " + project);
		//
		return modelMapper.map(project, ProjectDTO.class);
	}
	
	//@PutMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ProjectDTO add(@PathVariable("id") Integer id, @RequestBody ProjectDTO projectDTO) {
		logger.info("**** DEBUG Spring REST saving aggregate PUT: " + projectDTO + " [id]:" + id);
		Project projectBO = projectEntityRepository.findByProjectNo(id);
		if (projectBO == null || !projectBO.getProjectNo().equals(id))
			throw new RuntimeException("Project missing!");
		// save aggregate
		Project project = modelMapper.map(projectDTO, Project.class);
		// fix back references
		project.getReleases().forEach(r -> r.setProject(project));
		//
		Project savedProject = projectEntityRepository.save(project);
		// return saved project
		return modelMapper.map(savedProject, ProjectDTO.class);
	}
	
	//@DeleteMapping
	/* http://localhost:8080/scrum/rest/service/projects?id=11 DELETE */
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public void remove(@RequestParam(name = "id") Integer id) {
		logger.info("DEBUG: called SPRING-MVC-REST REMOVE - project: " + id);
		projectEntityRepository.deleteById(id);
	}
	
	@GetMapping(path = "/test")
	//@RequestMapping(path = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String getMessage(){
		return "Project DataService SPRING-MVC-REST is working...";
	}

	@PostConstruct
	private void setUps(){
		logger.info(">>>>> Setting MAPPER");
		// Setup Project DTO
		TypeMap<Project, ProjectDTO> projectDTOMapper = this.modelMapper.createTypeMap(Project.class, ProjectDTO.class);
		projectDTOMapper.addMappings(mapper -> mapper.map(src -> src.getProjectNo(), ProjectDTO::setProjectNo));
		projectDTOMapper.addMappings(mapper -> mapper.map(src -> src.getName(), ProjectDTO::setName));
		projectDTOMapper.addMappings(mapper -> mapper.map(src -> src.getStartDate(), ProjectDTO::setStartDate));
		projectDTOMapper.addMappings(mapper -> mapper.map(src -> src.getReleases(), ProjectDTO::setReleases));
		// Setup Release DTO
		TypeMap<Release, ReleaseDTO> releaseDTOMapper = this.modelMapper.createTypeMap(Release.class, ReleaseDTO.class);
		releaseDTOMapper.addMappings(mapper -> mapper.map(src -> src.getReleaseId(), ReleaseDTO::setReleaseId));
		releaseDTOMapper.addMappings(mapper -> mapper.map(src -> src.getCodeName(), ReleaseDTO::setCodeName));
		releaseDTOMapper.addMappings(mapper -> mapper.map(src -> src.getPublishDate(), ReleaseDTO::setPublishDate));
	}
}