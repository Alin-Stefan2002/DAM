package org.scrum.domain.project;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import java.util.logging.Logger;

public class ProjectAuditListener {
	private static Logger logger = Logger.getLogger(ProjectAuditListener.class.getName());
	
	@PrePersist
    public void onPrePersist(Project project) {
		auditProject(project, ProjectEntityOperation.ADDED);
	}
       
    @PreUpdate
    public void onPreUpdate(Project project) {
    	auditProject(project, ProjectEntityOperation.UPDATED);
    }
       
    @PreRemove
    public void onPreRemove(Project project) {
    	auditProject(project, ProjectEntityOperation.DELETED);
    }
    
    private void auditProject(Project project, ProjectEntityOperation operation) {
    	logger.info(">>> JPA ProjectAuditListener: " + project + " > " + operation);
		
	}
    
    static public enum ProjectEntityOperation{
    	ADDED, UPDATED, DELETED;
    }
}
