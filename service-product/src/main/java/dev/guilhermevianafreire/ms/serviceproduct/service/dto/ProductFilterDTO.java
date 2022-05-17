package dev.guilhermevianafreire.ms.serviceproduct.service.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public record ProductFilterDTO(UUID id,
                               @Size(max = 100) String name,
                               String description,
                               Integer statusCode,
                               @NotNull
                               @Min(0)
                               int page,
                               @NotNull
                               @Positive
                               int sizeOfPage,
                               SortDirectionType directionType,
                               String[] properties) {

}
