package br.dev.gvf.productservice.dto.barcodetype.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class BarcodeTypeCreateRequest {

  @NotBlank
  @Size(
      min = 1,
      max = 200
  )
  @Schema(
      title = "Name",
      description = "Name of the Barcode Type",
      requiredMode = Schema.RequiredMode.REQUIRED,
      minLength = 1,
      maxLength = 200,
      example = "EAN-128"
  )
  private String name;

}
