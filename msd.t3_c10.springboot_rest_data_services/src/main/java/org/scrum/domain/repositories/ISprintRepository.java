package org.scrum.domain.repositories;

import org.scrum.domain.sprint.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
 * URL-base: http://localhost:8080/scrum/data/api/sprints
 * ALPS: http://localhost:8080/scrum/data/api/profile/sprints/
 */
@RepositoryRestResource(collectionResourceRel = "sprints", path = "sprints")
public interface ISprintRepository extends JpaRepository<Sprint, Integer>
{
	
}