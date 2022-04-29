package dev.guilhermevianafreire.ms.serviceproduct.service.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import dev.guilhermevianafreire.ms.serviceproduct.domain.Product;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductDTO;

@Mapper
public interface ProductMapper {
	
	Product toEntity(ProductDTO dto);

	ProductDTO toDto(Product entity);

    List<Product> toEntity(List<ProductDTO> dtoList);

    List<ProductDTO> toDto(List<Product> entityList);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Product destination, Product origin);
    
    @Named("partialUpdateDto")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdateDto(@MappingTarget Product entity, ProductDTO dto);

}
