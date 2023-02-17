package br.dev.gvf.productservice.dto.barcodetype.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class PagedListResponse<T> {

    @Schema(title = "Content", description = "Elements of the page")
    private List<T> content;
    @Schema(title = "Page Size", description = "Maximun number of elements on this page", minimum = "1", example = "10")
    private Integer pageSize;
    @Schema(title = "Page Number", description = "Number of the current page", minimum = "1", defaultValue = "1", example = "1")
    private Integer pageNumber;
    @Schema(title = "Total Pages", description = "Total number of pages", minimum = "0", defaultValue = "0", example = "2")
    private Integer totalPages;
    @Schema(title = "Total Elements", description = "Total number of elements, including the elements that are not on this page", minimum = "0", defaultValue = "0", example = "50")
    private Integer totalElements;

}
