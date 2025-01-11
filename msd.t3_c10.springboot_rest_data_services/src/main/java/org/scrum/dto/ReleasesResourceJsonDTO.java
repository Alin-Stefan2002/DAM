package org.scrum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.scrum.domain.project.Release;

import java.util.ArrayList;
import java.util.List;

public class ReleasesResourceJsonDTO {
	@JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

	public static class Embedded{
	    @JsonProperty("releases")
	    List<Release> releases = new ArrayList<Release>();
	
	    public Embedded(){
	
	    }
	
	    public List<Release> getReleases() {
	        return releases;
	    }
	
	    public void setReleases(List<Release> releases) {
	        this.releases = releases;
	    }
	}
}