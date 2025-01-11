package org.scrum.domain.sprint;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.scrum.domain.project.Feature;
import org.scrum.domain.team.Member;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Task implements Serializable{
	@EqualsAndHashCode.Include
	@Id @GeneratedValue
	private Integer taskID;
	private String name;
	private String description;
	
	// timing
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	private Integer estimatedTime; // initial, exprimat in ore
	private Integer remainingTime; // actualizat, exprimat in ore
	private Integer realTime;	
	
	private TaskStatus taskStatus;

	@ManyToOne
	private Feature feature;
	
	// assessment
	@ManyToOne
	private Member responsible;
	
	private TaskCategory taskCategory;
	
	// Burn down
	@Transient
	private Map<Date, Integer> burnDownRecords = new HashMap<>();

	public void setRemainingTime(Integer remainingTime) {
		this.remainingTime = remainingTime;
		burnDownRecords.put(new Date(), remainingTime);
	}
	
}
