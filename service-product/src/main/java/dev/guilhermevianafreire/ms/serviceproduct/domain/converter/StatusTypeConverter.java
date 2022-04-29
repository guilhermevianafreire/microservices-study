package dev.guilhermevianafreire.ms.serviceproduct.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;

@Converter(autoApply = true)
public class StatusTypeConverter implements AttributeConverter<StatusType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(StatusType attribute) {
		return attribute.getCode();
	}

	@Override
	public StatusType convertToEntityAttribute(Integer dbData) {
		return StatusType.lookupByCode(dbData).orElseThrow();
	}

}
