package br.dev.gvf.productservice.dto;

import br.dev.gvf.productservice.constant.OrderByDirection;
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
public class BaseFindByOrderDTO {

  private String propertyName;
  private OrderByDirection direction;

}
