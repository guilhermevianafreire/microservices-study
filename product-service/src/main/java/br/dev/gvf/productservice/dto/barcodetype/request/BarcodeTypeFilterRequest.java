package br.dev.gvf.productservice.dto.barcodetype.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BarcodeTypeFilterRequest extends PagedListRequest {

    @NotBlank
    @Size(max = 200)
    @Schema(title = "Name", description = "Barcode Type name", nullable = true, example = "EAN-128")
    private String name;

}
