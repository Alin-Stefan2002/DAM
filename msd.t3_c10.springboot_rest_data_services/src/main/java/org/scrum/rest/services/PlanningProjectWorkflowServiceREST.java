package org.scrum.rest.services;

import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.services.IPlanningProjectWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/* REST.RPC API
 * 	http://localhost:8080/scrum/rest/service/workflow/planNewProject/{projectName}/{startDate}
 *  http://localhost:8080/scrum/rest/service/workflow/planNewProject/Rest_Project/05-12-2019
 *  http://localhost:8080/scrum/rest/service/workflow/planNewProject?projectName={projectName}&startDate={startDate}
 *  http://localhost:8080/scrum/rest/service/workflow/planNewProject?projectName=Rest_Project&startDate=05-12-2019
 * 
 * 	http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject/{projectId}/{featureName}/{featureDescription}
 *  http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject/34/Feature_1/REST_RPC_Feature
 *  http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject?projectId={projectId}&featureName={featureName}&featureDescription={featureDescription}
 *  http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject?projectId=34&featureName=Feature_1&featureDescription=REST_RPC_Feature
 *  http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject?projectId=34&featureName=Feature_1&featureDescription="REST_RPC Feature"
 * 
 *	http://localhost:8080/scrum/rest/service/workflow/planCurrentRelease/{projectId}/{publishDate}
 *	http://localhost:8080/scrum/rest/service/workflow/planCurrentRelease/34/05-03-2020
 *  http://localhost:8080/scrum/rest/service/workflow/planCurrentRelease?projectId={projectId}&publishDate={publishDate}
 *  http://localhost:8080/scrum/rest/service/workflow/planCurrentRelease?projectId=34&publishDate=07-03-2020
 *
 * 	http://localhost:8080/scrum/rest/service/workflow/getProjectSummaryData/{projectId}
 * 	http://localhost:8080/scrum/rest/service/workflow/getProjectSummaryData/34
 *  http://localhost:8080/scrum/rest/service/workflow/getProjectSummaryData?projectId={projectId}
 *  http://localhost:8080/scrum/rest/service/workflow/getProjectSummaryData?projectId=34
 */
@RestController
@RequestMapping("/rest/service/workflow") // REST.RPC Style
@Transactional
public class PlanningProjectWorkflowServiceREST {
	private static Logger logger = Logger.getLogger(PlanningProjectWorkflowServiceREST.class.getName());
	
	@Autowired
	private IPlanningProjectWorkflowService planningProjectWorkflowService;
	
	@RequestMapping(
			path = "/planNewProject/{projectName}/{startDate}", 
			method = RequestMethod.GET)
	@ResponseBody
	// (1) Create new project with default template: projectName, startDate
	public Integer planNewProject(
			@PathVariable("projectName") String projectName, 
			@PathVariable("startDate") String startDate) throws Exception{
		logger.info(">>> Start Procesing: planNewProject /" + projectName + "/" + startDate);
		
		Date startDateFromString = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
		Integer id = planningProjectWorkflowService.planNewProject(projectName, startDateFromString);
		
		logger.info(">>> End Procesing: planNewProject /" + projectName + "/" + startDate + ": " + id);
		return id;
	}
	
	@RequestMapping(path = "/planNewProject",  method = RequestMethod.GET)
	@ResponseBody
	public Integer planNewProjectRequestHandler(
			@RequestParam("projectName") String projectName,
			@RequestParam("startDate") String startDate) throws Exception{
		return planNewProject(projectName, startDate);
	}

	@RequestMapping(
			path = "/addFeatureToProject/{projectId}/{featureName}/{featureDescription}", 
			method = RequestMethod.GET)
	@ResponseBody
	// (2) Create new project with default template: projectName, startDate
	public Integer addFeatureToProject(
			@PathVariable("projectId") Integer projectId, 
			@PathVariable("featureName") String featureName, 
			@PathVariable("featureDescription") String featureDescription) {
		
		logger.info(">>> Start Procesing: addFeatureToProject /" + projectId + "/" + featureName + "/" + featureDescription);
		
		Integer id = planningProjectWorkflowService.addFeatureToProject(projectId, featureName, featureDescription);
		
		logger.info(">>> End Procesing: addFeatureToProject /" + projectId + "/" + featureName + "/" + featureDescription + ": " + id);
		return id;
	}
	
	@RequestMapping(
			path = "/addFeatureToProject", 
			method = RequestMethod.GET)
	@ResponseBody
	// (2) Create new project with default template: projectName, startDate
	public Integer addFeatureToProjectRequestHandler(
			@RequestParam("projectId") Integer projectId, 
			@RequestParam("featureName") String featureName, 
			@RequestParam("featureDescription") String featureDescription) {
		return addFeatureToProject(projectId, featureName, featureDescription);
	}

	@RequestMapping(
			path = "/planCurrentRelease/{projectId}/{publishDate}", 
			method = RequestMethod.GET)
	@ResponseBody
	// (3) Create new project with default template: projectName, startDate
	public Integer planCurrentRelease(
			@PathVariable("projectId") Integer projectId, 
			@PathVariable("publishDate") String publishDate) throws Exception{
		logger.info(">>> Start Procesing: planCurrentRelease /" + projectId + "/" + publishDate);
		
		Date publishDateFromString = new SimpleDateFormat("dd-MM-yyyy").parse(publishDate);
		Integer id = planningProjectWorkflowService.planCurrentRelease(projectId, publishDateFromString);
		
		logger.info(">>> End Procesing: planCurrentRelease /" + projectId + "/" + publishDate + ": " + id);
		return id;
	}
	
	@RequestMapping(path = "/planCurrentRelease", method = RequestMethod.GET)
	@ResponseBody
	public Integer planCurrentReleaseRequestHandler(
			@RequestParam("projectId") Integer projectId, 
			@RequestParam("publishDate") String publishDate) throws Exception{
		return planCurrentRelease(projectId, publishDate);
	}

	@RequestMapping(
			path = "/getProjectSummaryData/{projectId}", 
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	// (4) Get project summary data: ProjectCurrentReleaseView
	public ProjectCurrentReleaseView getProjectSummaryData(
			@PathVariable("projectId") Integer projectId) {
		logger.info(">>> Start Procesing: getProjectSummaryData /" + projectId );
		
		ProjectCurrentReleaseView viewData = planningProjectWorkflowService.getProjectSummaryData(projectId);
		
		logger.info(">>> End Procesing: getProjectSummaryData /" + projectId + ":" + viewData.toString());
		return viewData;
	}
	
	@RequestMapping(path = "/getProjectSummaryData", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ProjectCurrentReleaseView getProjectSummaryDataRequestHandler(
			@RequestParam("projectId") Integer projectId) {
		return getProjectSummaryData(projectId);
	}
}