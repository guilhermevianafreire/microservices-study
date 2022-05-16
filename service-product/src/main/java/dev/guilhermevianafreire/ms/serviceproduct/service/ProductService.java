package dev.guilhermevianafreire.ms.serviceproduct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.guilhermevianafreire.ms.serviceproduct.repository.ProductRepository;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductDTO;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.SortDirectionType;
import dev.guilhermevianafreire.ms.serviceproduct.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public List<ProductDTO> listAll() {
    return productMapper.toDto(productRepository.findAll());
  }
  
  public Page<ProductDTO> listAll(int page, int sizeOfPage) {
    return productRepository.findAll(PageRequest.of(page, sizeOfPage)).map(productMapper::toDto);
  }
  
  public Page<ProductDTO> listAll(int page, int sizeOfPage, SortDirectionType directionType, String... properties) {
    return productRepository.findAll(PageRequest.of(page, sizeOfPage, Sort.by(Direction.valueOf(directionType.name()), properties))).map(productMapper::toDto);
  }

}
