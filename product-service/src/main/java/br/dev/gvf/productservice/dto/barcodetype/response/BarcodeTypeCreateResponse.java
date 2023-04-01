package br.dev.gvf.productservice.dto.barcodetype.response;

import br.dev.gvf.productservice.dto.BaseIdVersion;
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
public class BarcodeTypeCreateResponse extends BaseIdVersion<BarcodeTypeCreateResponse> {

}
