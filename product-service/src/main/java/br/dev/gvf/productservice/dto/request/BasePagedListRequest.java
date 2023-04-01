package br.dev.gvf.productservice.dto.request;

import br.dev.gvf.shared.annotation.validator.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
public class BasePagedListRequest<T extends BasePagedListRequest<T>> {

  @Schema(title = "Page Number", description = "Define the number of the page the response should return. First page is 0 (zero)", nullable = true, minimum = "0", defaultValue = "0", example = "0")
  @PositiveOrZero
  private Integer pageNumber = 0;
  @Schema(title = "Page Size", description = "Define the number of elements on the response page. Minimum value is 1 (one)", nullable = true, minimum = "1", example = "10")
  @Positive
  private Integer pageSize;
  @Schema(title = "Order by", description = "List of property names and directions concatenated by : and ,", example = "name:asc,email:desc")
  @OrderBy
  private String orderBy;

  @SuppressWarnings("unchecked")
  public T setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setOrderBy(String orderBy) {
    this.orderBy = orderBy;
    return (T) this;
  }
}
