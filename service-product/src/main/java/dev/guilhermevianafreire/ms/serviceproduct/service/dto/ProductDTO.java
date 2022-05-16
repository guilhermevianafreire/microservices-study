package dev.guilhermevianafreire.ms.serviceproduct.service.dto;

import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;
import lombok.Builder;

@Builder
public record ProductDTO(
                         UUID id,
                         @NotBlank @Size(max = 100) String name,
                         String description,
                         @NotNull StatusType status,
                         Instant createdDate,
                         String createdBy,
                         Instant lastModifiedDate,
                         String lastModifiedBy) {

}
