package org.scrum.domain.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.scrum.domain.team.ProjectManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"projectGroup", "hibernateLazyInitializer", "handler", "valid", "defaultReference"})
@Data @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity @EntityListeners({ProjectAuditListener.class})
public class Project implements Serializable, Comparable<Project>{
	/* internal stucture: member fields*/
	@EqualsAndHashCode.Include
	@Min(1) @NotNull(message = " ProjectNo is required!")
	@Id @GeneratedValue
	private Integer projectNo;
	
	@NotNull(message = " Project Name is required!") 
	@Size(min=1, message = " Project must have an explicit name!")
	private String name;
	
	@NotNull(message = " StartDate is required!")
	@Future(message = " StartDate must be a future date!")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Transient
	private ProjectManager projectManager;

	@JsonManagedReference //@JsonGetter("releases")
	@OneToMany(mappedBy="project", 
			cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER,
			orphanRemoval = true)
	private List<Release> releases = new ArrayList<>();
	
	@OneToOne
	private Release currentRelease;
	
	@Convert(converter = ProjectGroupConverter.class)
	private ProjectGroup projectGroup;
	
	@Override
	public String toString() {
		return "\nProject [projectNo=" + projectNo + ", name=" + name + ", startDate=" + startDate + ", releases="
				+ releases + "]";
	}

	/* Constructors */
	public Project(Integer projectNo, String name, Date startDate) {
		this.projectNo = projectNo;
		this.name = name;
		this.startDate = startDate;
	}

	public Project(Integer nrProiect, String numeProiect) {
		this.projectNo = nrProiect;
		this.name = numeProiect;
	}

	public Project(String name, Date startDate) {
		this.name = name;
		this.startDate = startDate;
	}
	@Override
	public int compareTo(Project other) {
		return this.projectNo.compareTo(other.getProjectNo());
	}
	
	@AssertTrue(message = "Release List must not be empty!")
	public boolean isValid() {
		if (this.releases == null || this.releases.isEmpty())
			return false;
		return true;
	}
	
	/*
	 * Computed fields: releaseCount, featureCount
	 * - transient
	 * - encapsulated: only getters?
	 * - computing rule: SummaringProjectDomainService
	 */
//	@Transient
	protected Integer releaseCount = 0;
//	@Transient
	protected Integer featureCount = 0;
	
	// JPA Triggers: https://www.baeldung.com/database-auditing-jpa
	@PrePersist
    public void onPrePersist() {
		System.out.println(">>> JPA Triggers: @PrePersist");
		this.setReleaseCount((this.releases==null)? 0 : this.releases.size());
	}
       
    @PreUpdate
    public void onPreUpdate() {
    	System.out.println(">>> JPA Triggers: @PreUpdate");
    	this.setReleaseCount((this.releases==null)? 0 : this.releases.size());
    }
       
    @PreRemove
    public void onPreRemove() {
    	System.out.println(">>> JPA Triggers: @PreRemove");
    }	
	
}

// https://www.baeldung.com/javax-validation-method-constraints
// https://www.baeldung.com/javax-validation
// https://www.baeldung.com/spring-mvc-custom-validator
// https://www.baeldung.com/database-auditing-jpa