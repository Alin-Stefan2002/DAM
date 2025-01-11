package org.scrum.domain.repositories;

import org.scrum.domain.project.Release;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface IReleasesRepository extends JpaRepository<Release, Integer>{

}
