package org.scrum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.scrum.domain.sprint.Sprint;

import java.util.ArrayList;
import java.util.List;

public class SprintsResourceJsonDTO {
	@JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

	public static class Embedded{
	    @JsonProperty("sprints")
	    List<Sprint> sprints = new ArrayList<Sprint>();
	
	    public Embedded(){
	
	    }
	
	    public List<Sprint> getSprints() {
	        return sprints;
	    }
	
	    public void setSprints(List<Sprint> sprints) {
	        this.sprints = sprints;
	    }
	}
}