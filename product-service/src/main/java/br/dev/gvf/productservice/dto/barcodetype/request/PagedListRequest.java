package br.dev.gvf.productservice.dto.barcodetype.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PagedListRequest {

    @Schema(title = "Page Size", description = "Define the number of elements on the response page", nullable = true, minimum = "1", example = "10")
    private Integer pageSize;
    @Schema(title = "Page Number", description = "Define the number of the page the response should return", nullable = true, minimum = "0", defaultValue = "0", example = "1")
    private Integer pageNumber = 0;
    @Schema(title = "Order by", description = "List of property names and directions concatenated by : and ,", example = "name:asc,email:desc")
    private String[] orderBy;

}
