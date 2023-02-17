package br.dev.gvf.productservice.dto.barcodetype.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BarcodeTypePagedListResponse extends PagedListResponse<BarcodeTypeResponse> {
}
