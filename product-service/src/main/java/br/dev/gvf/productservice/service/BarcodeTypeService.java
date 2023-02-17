package br.dev.gvf.productservice.service;

import br.dev.gvf.productservice.constant.DatabaseLikeTypes;
import br.dev.gvf.productservice.model.BarcodeType;
import br.dev.gvf.productservice.repository.BarcodeTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BarcodeTypeService {

    private final BarcodeTypeRepository barcodeTypeRepository;

    @Value("${productService.rest.response.pagination.pageSize}")
    private Integer defaultPageSize;

    public BarcodeType findByIdExternal(final UUID externalId) {
        return barcodeTypeRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("No BarcodeType entity found with externalId equals %s".formatted(externalId)));
    }

    public Page<BarcodeType> findByNameLike(final String name, final Integer pageNumber, final Integer pageSize) {
        return barcodeTypeRepository.findByNameLike(
                DatabaseLikeTypes.START_AND_END_WITH.getPattern().formatted(name),
                PageRequest.of(pageNumber, Optional.ofNullable(pageSize).orElse(defaultPageSize))
        );
    }
}
