package org.scrum.domain.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Release implements Serializable{
	/* internal member fields*/
	@EqualsAndHashCode.Include
	@Id @GeneratedValue
	private Integer releaseId;
	private String codeName; // NEW born
	private String indicative; // vers 1.1
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date publishDate; // dataEstimarePublicare
	
	@ManyToOne @JsonBackReference
	private Project project;

	@OneToMany(cascade = ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	private List<Feature> features = new ArrayList<>();

	public Release(Integer releaseId, String codeName, Date publishDate,
			Project project) {
		this.releaseId = releaseId;
		this.codeName = codeName;
		this.publishDate = publishDate;
		this.project = project;
	}
	public Release(String codeName, Date publishDate,
				   Project project) {
		this.codeName = codeName;
		this.publishDate = publishDate;
		this.project = project;
	}

	@Override
	public String toString() {
		return "\n\tRelease [releaseId=" + releaseId + ", codeName=" + codeName
				+ " project " + (project!=null ? project.getProjectNo() : "NULL")
				+ ", indicative=" + indicative + ", features: " + features + "]";
	}
	
	public void addFeature(String featureName) {
		//Integer featureID = (this.features.size() == 0) ? 1 : this.features.size();
		this.features.add(new Feature(null, featureName));
	}	
	
	public void addFeature(String featureName, String description) {
		//Integer featureID = (this.features.size() == 0) ? 1 : this.features.size();
		this.features.add(new Feature(null, featureName, description));
	}	
	
	public void addFeature(String featureName, FeatureCategory category) {
		//Integer featureID = (this.features.size() == 0) ? 1 : this.features.size();
		this.features.add(new Feature(null, featureName, category));
	}	
}