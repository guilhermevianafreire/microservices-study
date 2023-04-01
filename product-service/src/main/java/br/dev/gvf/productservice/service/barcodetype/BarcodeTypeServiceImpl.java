package br.dev.gvf.productservice.service.barcodetype;

import br.dev.gvf.productservice.constant.DatabaseLikeTypes;
import br.dev.gvf.productservice.constant.OrderByDirection;
import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeCreateDTO;
import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeDTO;
import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeFilterDTO;
import br.dev.gvf.productservice.mapper.BarcodeTypeMapper;
import br.dev.gvf.productservice.model.BarcodeType;
import br.dev.gvf.productservice.repository.BarcodeTypeRepository;
import br.dev.gvf.productservice.validation.BarcodeTypeValidation;
import jakarta.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Validated
public class BarcodeTypeServiceImpl implements BarcodeTypeService {

  private static final String NOT_DOUND_BY_UUID = "No BarcodeType found with the supplied uuid %s";

  private final BarcodeTypeRepository barcodeTypeRepository;
  private final BarcodeTypeMapper barcodeTypeMapper;
  private final BarcodeTypeValidation barcodeTypeValidation;

  @Value("${productService.rest.pagination.pageSize}")
  private Integer defaultPageSize;

  public BarcodeType findByIdExternal(final UUID id) {
    return barcodeTypeRepository.findByExternalId(id)
        .orElseThrow(() -> new EntityNotFoundException(NOT_DOUND_BY_UUID.formatted(id)));
  }

  public Page<BarcodeType> search(final BarcodeTypeFilterDTO filters) {
    Sort sort = null;
    if (CollectionUtils.isNotEmpty(filters.getOrderBy())) {
      sort = Sort.by(filters.getOrderBy().stream().map(baseFindByOrderDTO -> {
        if (OrderByDirection.ASC.equals(baseFindByOrderDTO.getDirection())) {
          return Sort.Order.asc(baseFindByOrderDTO.getPropertyName());
        } else {
          return Sort.Order.desc(baseFindByOrderDTO.getPropertyName());
        }
      }).toList());
    }

    PageRequest pageRequest = null;
    if (Objects.isNull(sort)) {
      PageRequest.of(filters.getPageNumber(),
          Optional.ofNullable(filters.getPageSize()).orElse(defaultPageSize));
    } else {
      PageRequest.of(filters.getPageNumber(),
          Optional.ofNullable(filters.getPageSize()).orElse(defaultPageSize), sort);
    }

    if (StringUtils.isNotBlank(filters.getName())) {
      return barcodeTypeRepository.findByNameLikeAndActiveTrue(
          DatabaseLikeTypes.CONTAINS.getPattern().formatted(filters.getName()), pageRequest);
    }
    return barcodeTypeRepository.findByActiveTrue(pageRequest);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public BarcodeTypeDTO create(final BarcodeTypeCreateDTO dto) {
    barcodeTypeValidation.checkNewNameUnique(dto.getName());
    BarcodeType newBarcodeType = barcodeTypeMapper.barcodeTypeCreateDTOToBarcodeType(dto)
        .setExternalId(UUID.randomUUID());
    return barcodeTypeMapper.barcodeTypeToBarcodeTypeDto(
        barcodeTypeRepository.save(newBarcodeType));
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void update(final BarcodeTypeDTO dto) {
    barcodeTypeValidation.checkExternalIdExists(dto.getId());
    barcodeTypeValidation.checkUpdateNameUnique(dto.getName(), dto.getId());
    barcodeTypeRepository.save(barcodeTypeMapper.barcodeTypeDtoToBarcodeType(dto,
        barcodeTypeRepository.findIdByExternalId(dto.getId())
            .orElseThrow(() -> new NoSuchElementException(
                "No BarcodeType was found with the supplied id '%s'".formatted(dto.getId())))));
  }

  @Override
  public void delete(UUID id) {
    barcodeTypeValidation.checkExternalIdExists(id);
    barcodeTypeValidation.checkHasProductRelationships(id);
    barcodeTypeRepository.deleteById(barcodeTypeRepository.findIdByExternalId(id)
        .orElseThrow(() -> new NoSuchElementException(
            "No BarcodeType was found with the supplied id '%s'".formatted(id))));
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void updateActive(final UUID externalId, final boolean active) {
    if (!barcodeTypeRepository.existsByExternalId(externalId)) {
      throw new EntityNotFoundException(NOT_DOUND_BY_UUID.formatted(externalId));
    }
    barcodeTypeRepository.updateActiveByExternalId(externalId, active);
  }
}
