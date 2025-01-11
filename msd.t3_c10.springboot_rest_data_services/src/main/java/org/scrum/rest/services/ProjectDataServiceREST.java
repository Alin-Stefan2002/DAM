package org.scrum.rest.services;

import org.scrum.domain.project.Project;
import org.scrum.domain.repositories.IProjectRepository;
import org.scrum.services.IProjectDTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/*
 * http://localhost:8080/scrum/rest/data/projects
 */

@RestController @RequestMapping("/rest/data/projects") // REST.Resource Style
@Transactional
public class ProjectDataServiceREST {
	private static Logger logger = Logger.getLogger(ProjectDataServiceREST.class.getName());
	
	@Autowired
	private IProjectRepository projectEntityRepository;
	
	@Autowired
	private IProjectDTOFactory projectDTOFactory;

	//@GetMapping
	@RequestMapping(method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Collection<Project> toCollection() {
		logger.info("**** DEBUG SPRING-MVC-REST toCollection() >>> get All DTO projects::");
		List<Project> projects = projectEntityRepository.findAll();
		projects.forEach(p -> logger.info(">>> Project Entites: " + p));

		//return projects;
		List<Project> projectDTOs = new ArrayList<>();
		//modelMapper.map(projects, projectDTOs);
		projectDTOs = projectDTOFactory.toDTOList(projects);
		projectDTOs.forEach(p -> logger.info(">>> Project DTO: " + p));

		logger.info(">>>>> RETURN DTOs");
		return projectDTOs;
	}
	
	//@PostMapping
	@RequestMapping(method = RequestMethod.POST, 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Collection<Project> addIntoCollection(@RequestBody Project project) {
		// save aggregate
		projectEntityRepository.save(project);
		logger.info("**** DEBUG SPRING-MVC-REST save aggregate POST!");
		// return updated collection
		List<Project> projects = projectEntityRepository.findAll();
		return projects;
	}
	
	//@DeleteMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, 
			produces = {MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Collection<Project> removeFromCollection(@PathVariable Integer id) {
		logger.info("DEBUG: called SPRING-MVC-REST REMOVE - project: " + id);
		projectEntityRepository.deleteById(id);
		// return updated collection
		List<Project> projects = projectEntityRepository.findAll();
		return projects;
	}
	
	//@GetMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Project getById(@PathVariable("id") Integer id){
		try {
			Project project = projectEntityRepository.findById(id).get();
			logger.info("**** DEBUG SPRING-MVC-REST getById(" + id + ") = " + project);
			return project;
		}catch (Exception e){
			throw new ResourceNotFoundException("Project with ID: " + id + " is NOT FOUND!");
		}
	}
	
	//@PutMapping
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Project add(@PathVariable("id") Integer id, @RequestBody Project project) {
		logger.info("**** DEBUG Spring REST saving aggregate PUT: " + project + " [id]:" + id);
		Project projectBO = projectEntityRepository.findByProjectNo(id);
		if (projectBO == null || !projectBO.getProjectNo().equals(id))
			throw new RuntimeException("Project missing!");
		// save aggregate
		project = projectEntityRepository.save(project);
		// return saved project
		return project;
	}
	
	//@DeleteMapping
	/* http://localhost:8080/scrum/rest/data/projects?id=11 DELETE */
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
}