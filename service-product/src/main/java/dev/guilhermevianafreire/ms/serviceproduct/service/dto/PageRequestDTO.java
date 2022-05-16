package dev.guilhermevianafreire.ms.serviceproduct.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record PageRequestDTO(
                             @NotNull
                             @Min(0)
                             int page,
                             @NotNull
                             @Positive
                             int sizeOfPage,
                             SortDirectionType directionType,
                             String properties) {

}
