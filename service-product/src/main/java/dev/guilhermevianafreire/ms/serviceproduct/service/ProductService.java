package dev.guilhermevianafreire.ms.serviceproduct.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.guilhermevianafreire.ms.serviceproduct.domain.Product;
import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;
import dev.guilhermevianafreire.ms.serviceproduct.exception.ExceptionHelper;
import dev.guilhermevianafreire.ms.serviceproduct.repository.ProductRepository;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductDTO;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductFilterDTO;
import dev.guilhermevianafreire.ms.serviceproduct.service.mapper.ProductMapper;
import dev.guilhermevianafreire.ms.serviceproduct.service.validation.ServiceValidationHelper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService extends BaseService<Product> {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final ServiceValidationHelper serviceValidation;
  private final ExceptionHelper exceptionBuilder;

  @Transactional(readOnly = true)
  public List<ProductDTO> listAll() {
    return productMapper.toDto(productRepository.findAll());
  }

  @Transactional(readOnly = true)
  public Page<ProductDTO> findBy(ProductFilterDTO productFilterDTO) {
    serviceValidation.validate(productFilterDTO);
    return productRepository.findAll(buildSpecificationFromDto(productFilterDTO), buildPageRequest(productFilterDTO)).map(productMapper::toDto);
  }

  @Transactional(readOnly = true)
  public boolean productIdExists(@NotNull UUID id) {
    return productRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public ProductDTO findProductById(@NotNull UUID id) {
    return productMapper.toDto(findById(id).orElseThrow(() -> exceptionBuilder.buildEntityNotFoundException(id)));
  }

  public ProductDTO save(ProductDTO dto) {
    serviceValidation.validate(dto, ProductDTO.ProductSaveGroup.class);
    return productMapper.toDto(productRepository.save(productMapper.toEntity(dto)));
  }

  public ProductDTO update(ProductDTO dto) {
    serviceValidation.validate(dto, ProductDTO.ProductUpdateGroup.class);
    Product productEntity = findById(dto.id()).orElseThrow(() -> exceptionBuilder.buildEntityNotFoundException(dto.id()));
    productMapper.updateEntityWithDto(productEntity, dto);
    return productMapper.toDto(productRepository.save(productEntity));
  }

  public ProductDTO changeStatusAndUpdate(@NotNull UUID id) {
    Product productEntity = findById(id).orElseThrow(() -> exceptionBuilder.buildEntityNotFoundException(id));
    productEntity.changeStatus();
    return productMapper.toDto(productRepository.save(productEntity));
  }

  public void delete(@NotNull UUID id) {
    productRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  private Optional<Product> findById(UUID id) {
    return productRepository.findById(id);
  }

  private PageRequest buildPageRequest(ProductFilterDTO productFilterDTO) {
    if (Objects.nonNull(productFilterDTO.directionType()) && Objects.nonNull(productFilterDTO.properties()))
      return PageRequest.of(productFilterDTO.page(), productFilterDTO.sizeOfPage(), Sort.by(Direction.valueOf(productFilterDTO.directionType().name()), productFilterDTO.properties()));
    else
      return PageRequest.of(productFilterDTO.page(), productFilterDTO.sizeOfPage());
  }

  private Specification<Product> buildSpecificationFromDto(ProductFilterDTO productFilterDTO) {
    Specification<Product> specification = Specification
                                                        .where(Objects.isNull(productFilterDTO.id()) ? null : equal("id", productFilterDTO.id()))
                                                          .and(Objects.isNull(productFilterDTO.name()) ? null : like("name", productFilterDTO.name()))
                                                          .and(Objects.isNull(productFilterDTO.description()) ? null : like("description", productFilterDTO.description()))
                                                          .and(Objects.isNull(productFilterDTO.statusCode()) ? null : equal(StatusType.lookupByCode(productFilterDTO.statusCode()).orElseThrow()));
    return specification;
  }

}
