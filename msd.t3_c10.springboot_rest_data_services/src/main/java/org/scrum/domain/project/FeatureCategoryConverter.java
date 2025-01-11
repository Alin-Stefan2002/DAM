package org.scrum.domain.project;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
@Converter
public class FeatureCategoryConverter implements 
	AttributeConverter<FeatureCategory, String>{

	@Override
	public String convertToDatabaseColumn(FeatureCategory attributeValue) {
		if (attributeValue.equals(FeatureCategory.BUSINESS))
			return "Business_Feature";
		if (attributeValue.equals(FeatureCategory.TECHNICAL))
			return "Technical_Feature";
		return null;
	}

	@Override
	public FeatureCategory convertToEntityAttribute(String dbData) {
		if (dbData.equals("Business_Feature"))
			return FeatureCategory.BUSINESS;
		if (dbData.equals("Technical_Feature"))
			return FeatureCategory.TECHNICAL;
		return null;
	}
}


/*
switch (attributeValue) {
	case Feature.FeatureCategory.BUSINESS:
		return "Business.Feature";
	case FeatureCategory.TECHNICAL:
		return "Technical.Feature";
}
*/
