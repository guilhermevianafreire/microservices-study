package br.dev.gvf.productservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
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
public class BaseId<T extends BaseId<T>> {

  @Schema(
      title = "ID",
      description = "UUID (Universally Unique Identifier) of the Barcode Type",
      requiredMode = Schema.RequiredMode.REQUIRED,
      minLength = 36,
      maxLength = 36,
      example = "c2e559b6-e4e3-489a-bb30-a2666fc4e1d4"
  )
  private UUID id;

  @SuppressWarnings("unchecked")
  public T setId(UUID id) {
    this.id = id;
    return (T) this;
  }

}
