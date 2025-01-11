package org.scrum.domain.project;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * https://www.baeldung.com/jpa-persisting-enums-in-jpa
 * https://thoughts-on-java.org/jpa-21-how-to-implement-type-converter/
 */

@Data @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Feature implements Comparable<Feature>, Serializable
{
	@EqualsAndHashCode.Include
	@Id @GeneratedValue
	protected Integer featureID;
	private String name;
	private String description;
	
//	@Enumerated
//	@Enumerated(EnumType.ORDINAL)
//	@Enumerated(EnumType.STRING)
	/* No @Enumerated then apply type-converter FeatureCategoryConverter with @Converter(autoApply = true) */
	@Convert(converter = FeatureCategoryConverter.class) // explicit attribute converter
	protected FeatureCategory category = FeatureCategory.TECHNICAL;
	
	public Feature(Integer featureID, String name, String description) {
		super();
		this.featureID = featureID;
		this.name = name;
		this.description = description;
	}
	public Feature(Integer featureID, String name) {
		super();
		this.featureID = featureID;
		this.name = name;
		this.category = FeatureCategory.BUSINESS;
	}
	
	public Feature(Integer featureID, String name, FeatureCategory category) {
		super();
		this.featureID = featureID;
		this.name = name;
		this.category = category;
	}
	@Override
	public String toString() {
		return "\n\t\tFeature [featureID=" + featureID + ", name=" + name
				+ ", description=" + description + ", category=" + category + "]";
	}

	@Override
	public int compareTo(Feature other) {
		if (this.equals(other))
			return 0;
		return this.getName().compareTo(other.getName());
	}
}