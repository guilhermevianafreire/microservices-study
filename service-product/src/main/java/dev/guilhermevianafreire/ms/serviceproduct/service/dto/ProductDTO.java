package dev.guilhermevianafreire.ms.serviceproduct.service.dto;

import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;

@Builder
public record ProductDTO(
                         @NotNull(groups = {
                                             ProductUpdateGroup.class
                         }) UUID id,
                         @NotBlank(groups = {
                                              ProductSaveGroup.class,
                                              ProductUpdateGroup.class
                         }) @Size(max = 100, groups = {
                                                        ProductSaveGroup.class,
                                                        ProductUpdateGroup.class
                         }) String name,
                         String description,
                         @NotNull(groups = {
                                             ProductSaveGroup.class,
                                             ProductUpdateGroup.class
                         }) Integer statusCode,
                         Instant createdDate,
                         String createdBy,
                         Instant lastModifiedDate,
                         String lastModifiedBy){

  public interface ProductUpdateGroup {
  }

  public interface ProductSaveGroup {
  }

}
