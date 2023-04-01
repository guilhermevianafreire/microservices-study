package br.dev.gvf.productservice.dto.request;

import jakarta.validation.constraints.NotNull;
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
public class BaseFindByPagedRequest<T extends BaseFindByPagedRequest<T>> {

  @NotNull
  @PositiveOrZero
  private Integer pageNumber = 0;

  @Positive
  private Integer pageSize;

  private String[] orderBy;

  @SuppressWarnings("unchecked")
  public T setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setOrderBy(String[] orderBy) {
    this.orderBy = orderBy;
    return (T) this;
  }
}
