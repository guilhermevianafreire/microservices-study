package dev.guilhermevianafreire.ms.serviceproduct.service.dto;

import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
	
	public UUID id;
	
	@NotBlank
	@Size(max = 100)
	private String name;
	
	private String description;
	
	@NotNull
	public StatusType status;
	
	public Instant createdDate;
	
	public String createdBy;
	
	public Instant lastModifiedDate;

	public String lastModifiedBy;
}
