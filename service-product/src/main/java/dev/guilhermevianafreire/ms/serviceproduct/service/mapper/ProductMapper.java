package dev.guilhermevianafreire.ms.serviceproduct.service.mapper;

import dev.guilhermevianafreire.ms.serviceproduct.domain.Product;
import dev.guilhermevianafreire.ms.serviceproductclient.dto.ProductDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Mapping(target = "status", expression = "java(StatusType.lookupByCode(dto.statusCode()))")
    Product toEntity(ProductDTO dto);

    @Mapping(target = "statusCode", source = "status.code")
    ProductDTO toDto(Product entity);

    List<Product> toEntity(List<ProductDTO> dtoList);

    List<ProductDTO> toDto(List<Product> entityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Product destination,
                       Product origin);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(StatusType.lookupByCode(dto.statusCode()))")
    void updateEntityWithDto(@MappingTarget Product entity,
                             ProductDTO dto);


}
