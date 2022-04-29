package dev.guilhermevianafreire.ms.serviceproduct.domain.converter;

import javax.persistence.AttributeConverter;

import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;

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
