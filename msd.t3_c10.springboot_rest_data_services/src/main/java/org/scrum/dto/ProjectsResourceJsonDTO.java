package org.scrum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.scrum.domain.project.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectsResourceJsonDTO {
	@JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

	public static class Embedded{
	    @JsonProperty("projects")
	    List<Project> projects = new ArrayList<Project>();
	
	    public Embedded(){
	
	    }
	
	    public List<Project> getProjects() {
	        return projects;
	    }
	
	    public void setProjects(List<Project> projects) {
	        this.projects = projects;
	    }
	}

	@Override
	public String toString() {
		return "ProjectsResourceJsonDTO{" +
				"embedded=" + embedded +
				'}';
	}
}