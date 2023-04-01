package br.dev.gvf.productservice.dto;

import java.util.UUID;
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
public class BaseIdVersionDTO<T extends BaseIdVersionDTO<T>> {

  private UUID id;
  private Long version;

  @SuppressWarnings("unchecked")
  public T setId(UUID id) {
    this.id = id;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setVersion(Long version) {
    this.version = version;
    return (T) this;
  }

}
