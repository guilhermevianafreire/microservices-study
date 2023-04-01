package br.dev.gvf.productservice.dto.barcodetype.request;

import br.dev.gvf.productservice.dto.request.BasePagedListRequest;
import io.swagger.v3.oas.annotations.media.Schema;
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
@EqualsAndHashCode(callSuper = true)
public class BarcodeTypeFilterRequest extends BasePagedListRequest<BarcodeTypeFilterRequest> {

  @Schema(
      title = "Name",
      description = "Barcode Type name",
      nullable = true,
      example = "EAN"
  )
  @Size(max = 200)
  private String name;

}
