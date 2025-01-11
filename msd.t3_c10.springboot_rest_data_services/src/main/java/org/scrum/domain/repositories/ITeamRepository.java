package org.scrum.domain.repositories;

import org.scrum.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
 * URL-base: http://localhost:8080/scrum/data/api/teams
 * ALPS: http://localhost:8080/scrum/data/api/profile/teams/
 */
@RepositoryRestResource(collectionResourceRel = "teams", path = "teams")
public interface ITeamRepository extends JpaRepository<Team, Integer>
{
	
}