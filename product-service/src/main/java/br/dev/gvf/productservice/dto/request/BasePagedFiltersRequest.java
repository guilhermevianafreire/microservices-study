package br.dev.gvf.productservice.dto.request;

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
public class BasePagedFiltersRequest<T extends BasePagedFiltersRequest<T>> {

  private Integer pageSize;
  private Integer pageNumber = 1;

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
}
