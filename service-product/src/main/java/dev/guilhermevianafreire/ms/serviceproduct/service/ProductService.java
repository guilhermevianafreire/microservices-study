package dev.guilhermevianafreire.ms.serviceproduct.service;

import dev.guilhermevianafreire.ms.serviceproduct.domain.Product;
import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;
import dev.guilhermevianafreire.ms.serviceproduct.repository.ProductRepository;
import dev.guilhermevianafreire.ms.serviceproduct.service.mapper.ProductMapper;
import dev.guilhermevianafreire.ms.serviceproduct.service.validation.ServiceValidationHelper;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditDataDTO;
import dev.guilhermevianafreire.ms.shared.dto.product.ProductDTO;
import dev.guilhermevianafreire.ms.shared.dto.product.ProductFilterDTO;
import dev.guilhermevianafreire.ms.shared.mapper.AuditMapper;
import lombok.RequiredArgsConstructor;
import org.javers.core.Javers;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService extends BaseService<Product> {

    private final Javers javers;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final AuditMapper auditMapper;
    private final ServiceValidationHelper serviceValidation;

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
    public ProductDTO findProductById(@NotNull UUID id) {
        return productMapper.toDto(productRepository.getById(id));
    }

    public List<AuditDataDTO> getAllChangesById(UUID id) {
        QueryBuilder queryBuilder = QueryBuilder.byInstanceId(id.toString(), Product.class);
        return auditMapper.snapshotsToDtos(javers.findSnapshots(queryBuilder.build()));
    }

    public ProductDTO save(ProductDTO dto) {
        serviceValidation.validate(dto, ProductDTO.ProductSaveGroup.class);
        return productMapper.toDto(productRepository.save(productMapper.toEntity(dto)));
    }

    public ProductDTO update(ProductDTO dto) {
        serviceValidation.validate(dto, ProductDTO.ProductUpdateGroup.class);
        Product productEntity = productRepository.getById(dto.id());
        productMapper.updateEntityWithDto(productEntity, dto);
        return productMapper.toDto(productRepository.save(productEntity));
    }

    public ProductDTO changeStatusAndUpdate(@NotNull UUID id) {
        Product productEntity = productRepository.getById(id);
        productEntity.changeStatus();
        return productMapper.toDto(productRepository.save(productEntity));
    }

    public void delete(@NotNull UUID id) {
        productRepository.deleteById(id);
    }

    private PageRequest buildPageRequest(ProductFilterDTO productFilterDTO) {
        if (Objects.nonNull(productFilterDTO.directionType()) && Objects.nonNull(productFilterDTO.properties()))
            return PageRequest.of(productFilterDTO.page(), productFilterDTO.sizeOfPage(), Sort.by(Direction.valueOf(productFilterDTO.directionType().name()), productFilterDTO.properties()));
        else
            return PageRequest.of(productFilterDTO.page(), productFilterDTO.sizeOfPage());
    }

    private Specification<Product> buildSpecificationFromDto(ProductFilterDTO productFilterDTO) {
        return Specification
                .where(Objects.isNull(productFilterDTO.id()) ? null : equal("id", productFilterDTO.id()))
                .and(Objects.isNull(productFilterDTO.name()) ? null : like("name", productFilterDTO.name()))
                .and(Objects.isNull(productFilterDTO.description()) ? null : like("description", productFilterDTO.description()))
                .and(Objects.isNull(productFilterDTO.statusCode()) ? null : equal(StatusType.lookupByCode(productFilterDTO.statusCode()).orElseThrow()));
    }

}
