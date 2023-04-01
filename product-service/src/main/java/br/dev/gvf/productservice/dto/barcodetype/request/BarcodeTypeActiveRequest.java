package br.dev.gvf.productservice.dto.barcodetype.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
public class BarcodeTypeActiveRequest {

  @Schema(
      title = "Active",
      description = "Indicates if a Barcode Type is active or not. ",
      allowableValues = {
          "true",
          "false"
      },
      example = "true"
  )
  @NotNull
  private Boolean active;

}
