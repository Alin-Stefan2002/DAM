package org.scrum.domain.project;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor
@Entity
public class BusinessFeature extends Feature implements Serializable{
	private String functionalCategory; // basic, improvement
	private String useCaseDescription; // scenariu-flux dialog utilizator final - aplicatie

	public BusinessFeature(Integer featureID, String name, String functionalCategory, String useCaseDescription) {
		super(featureID, name);
		this.functionalCategory = functionalCategory;
		this.useCaseDescription = useCaseDescription;
	}

	@Override
	public String toString() {
		return "BusinessFeature [functionalCategory=" + functionalCategory
				+ ", useCaseDescription=" + useCaseDescription + "]";
	}
}
