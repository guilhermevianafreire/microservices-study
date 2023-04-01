package br.dev.gvf.productservice.dto.barcodetype.response;

import br.dev.gvf.productservice.dto.BaseId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BarcodeTypePagedListItemResponse extends BaseId<BarcodeTypePagedListItemResponse> {

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
