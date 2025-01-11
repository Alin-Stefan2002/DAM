package org.scrum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.scrum.domain.project.ProjectCurrentReleaseView;

import java.util.ArrayList;
import java.util.List;

public class ProjectCurrentReleaseViewResourceJsonDTO {
	@JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

	public static class Embedded{
	    @JsonProperty("projectCurrentReleaseViews")
	    List<ProjectCurrentReleaseView> projectCurrentReleaseViews = new ArrayList<ProjectCurrentReleaseView>();
	
	    public Embedded(){
	
	    }
	
	    public List<ProjectCurrentReleaseView> getProjectCurrentReleaseViews() {
	        return projectCurrentReleaseViews;
	    }
	
	    public void setProjectCurrentReleaseView(List<ProjectCurrentReleaseView> projectCurrentReleaseViews) {
	        this.projectCurrentReleaseViews = projectCurrentReleaseViews;
	    }
	}
}