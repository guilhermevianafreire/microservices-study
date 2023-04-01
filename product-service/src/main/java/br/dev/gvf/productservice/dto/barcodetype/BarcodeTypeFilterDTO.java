package br.dev.gvf.productservice.dto.barcodetype;

import br.dev.gvf.productservice.dto.BaseFindByPagedDTO;
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
public class BarcodeTypeFilterDTO extends BaseFindByPagedDTO<BarcodeTypeFilterDTO> {

  @Size(max = 200)
  private String name;

}
