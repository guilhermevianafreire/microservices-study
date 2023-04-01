package br.dev.gvf.productservice.dto.barcodetype;

import br.dev.gvf.productservice.dto.BaseIdVersionDTO;
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
@EqualsAndHashCode(callSuper = true)
public class BarcodeTypeDTO extends BaseIdVersionDTO<BarcodeTypeDTO> {

  @NotBlank
  @Size(max = 200)
  private String name;

}
