package br.dev.gvf.productservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode
public class BasePagedListResponse<B, T extends BasePagedListResponse<B, T>> {

  private List<B> content;
  @Schema(
      title = "Page Size",
      description = "Maximun number of elements on this page",
      minimum = "1",
      example = "10"
  )
  private Integer pageSize;
  @Schema(
      title = "Page Number",
      description = "Number of the current page",
      minimum = "1",
      defaultValue = "1",
      example = "1"
  )
  private Integer pageNumber;
  @Schema(
      title = "Total Pages",
      description = "Total number of pages",
      minimum = "0",
      defaultValue = "0",
      example = "2"
  )
  private Integer totalPages;
  @Schema(
      title = "Total Elements",
      description = "Total number of elements, including the elements that are not on this page",
      minimum = "0",
      defaultValue = "0",
      example = "50"
  )
  private Integer totalElements;

  @SuppressWarnings("unchecked")
  public T setContent(List<B> content) {
    this.content = content;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return (T) this;
  }
}
