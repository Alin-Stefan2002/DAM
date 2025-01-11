package org.scrum.domain.repositories;

import org.scrum.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

//@RestResource
@RepositoryRestResource(path = "projects")
public interface IProjectRepository extends JpaRepository<Project, Integer>
{
	List<Project> findAll(); // extends Repository only
	
	// Queriable Named Operations
	List<Project> findByName(String name);
	Project findByProjectNo(Integer projectNo);
	
	// Queriable Annotated Operation
	// REST Endpoint: http://localhost:8088/scrum/data/projects/search/byProjectName?pname=REST
	@RestResource(path="byProjectName", rel = "byProjectName")
	@Query("SELECT p FROM Project p WHERE p.name like %:pname%")
	List<Project> findByProjectName(@Param("pname") String name);

	// Queriable Annotated Operation
	// REST Endpoint: http://localhost:8088/scrum/data/projects/search/findProjectNoNameByProjectName?pname=REST
	@RestResource(path="findProjectNoNameByProjectName", rel = "findProjectNoNameByProjectName")
	@Query(value = "SELECT * FROM Project p WHERE p.name like %:pname%", nativeQuery = true)
	List<Project> findProjectNoNameByProjectName(@Param("pname") String name);
}