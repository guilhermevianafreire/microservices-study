package br.dev.gvf.productservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
public class BaseVersion<T extends BaseVersion<T>> {

  @PositiveOrZero
  @Schema(
      title = "Version",
      description = "Version of the database registry. Used for optimistic locking",
      minimum = "0",
      example = "0"
  )
  private Long version;

  @SuppressWarnings("unchecked")
  public T setVersion(Long version) {
    this.version = version;
    return (T) this;
  }
}
