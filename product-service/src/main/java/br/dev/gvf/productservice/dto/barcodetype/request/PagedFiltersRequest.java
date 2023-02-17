package br.dev.gvf.productservice.dto.barcodetype.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PagedFiltersRequest {

    private Integer pageSize;
    private Integer pageNumber = 1;

}
