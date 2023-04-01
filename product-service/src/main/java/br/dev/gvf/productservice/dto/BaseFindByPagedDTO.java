package br.dev.gvf.productservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
public class BaseFindByPagedDTO<T extends BaseFindByPagedDTO<T>> {

  @NotNull
  @PositiveOrZero
  private Integer pageNumber = 0;

  @Positive
  private Integer pageSize;

  private List<BaseFindByOrderDTO> orderBy;

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
  public T setOrderBy(List<BaseFindByOrderDTO> orderBy) {
    this.orderBy = orderBy;
    return (T) this;
  }
}
