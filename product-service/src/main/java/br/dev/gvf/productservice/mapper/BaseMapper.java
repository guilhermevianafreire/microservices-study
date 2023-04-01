package br.dev.gvf.productservice.mapper;

import br.dev.gvf.productservice.constant.OrderByDirection;
import br.dev.gvf.productservice.dto.BaseFindByOrderDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface BaseMapper {

  @Named("orderByStringToDTO")
  default List<BaseFindByOrderDTO> orderByStringToDTO(String orderBy) {
    if (StringUtils.isBlank(orderBy)) {
      return new ArrayList<>();
    }
    if (orderBy.contains(",")) {
      return Stream
          .of(orderBy.split(","))
          .map(group -> {
            String[] groupItems = group.split(":");
            return new BaseFindByOrderDTO()
                .setPropertyName(groupItems[0])
                .setDirection(OrderByDirection.valueOf(groupItems[1].toUpperCase()));
          })
          .toList();
    } else {
      String[] groupItems = orderBy.split(":");
      return Stream
          .of(new BaseFindByOrderDTO()
              .setPropertyName(groupItems[0])
              .setDirection(OrderByDirection.valueOf(groupItems[1].toUpperCase())))
          .toList();
    }

  }

}
