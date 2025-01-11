package org.scrum.domain.sprint;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.scrum.domain.project.Feature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Sprint implements Serializable{
	@EqualsAndHashCode.Include
	@Id
	private Integer sprintID;
	private String objective;
	
	@OneToMany
	private List<Feature> features = new ArrayList<>();
	
	@OneToMany(cascade = ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Task> tasks = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	private String review;

}
