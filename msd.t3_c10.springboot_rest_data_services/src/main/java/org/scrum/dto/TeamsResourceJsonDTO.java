package org.scrum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.scrum.domain.team.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamsResourceJsonDTO {
	@JsonProperty("_embedded")
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

	public static class Embedded{
	    @JsonProperty("teams")
	    List<Team> teams = new ArrayList<Team>();
	
	    public Embedded(){
	
	    }
	
	    public List<Team> getTeams() {
	        return teams;
	    }
	
	    public void setTeams(List<Team> teams) {
	        this.teams = teams;
	    }
	}
}