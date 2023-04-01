package br.dev.gvf.productservice.validation;

import br.dev.gvf.productservice.exception.ValidationException;
import br.dev.gvf.productservice.model.BarcodeType;
import br.dev.gvf.productservice.model.BarcodeType_;
import br.dev.gvf.productservice.repository.BarcodeTypeRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BarcodeTypeValidationImpl implements BarcodeTypeValidation {

  private final BarcodeTypeRepository repository;

  @Override
  public void checkNewNameUnique(String name) {
    repository.findIdByName(name).ifPresent(barcodeTypeId -> {
      throw new ValidationException(BarcodeType.class, BarcodeType_.NAME,
          "BarcodeType name already exists",
          "The supplied name '%s' is already registered to BarcodeType with id '%s'".formatted(name,
              barcodeTypeId));
    });
  }

  @Override
  public void checkUpdateNameUnique(String name, UUID id) {
    repository.findIdByNameAndNotExternalId(name, id).ifPresent(barcodeTypeId -> {
      throw new ValidationException(BarcodeType.class, BarcodeType_.NAME,
          "BarcodeType name already exists",
          "The supplied name '%s' is already registered to BarcodeType with id '%s'".formatted(name,
              barcodeTypeId));
    });
  }

  @Override
  public void checkExternalIdExists(UUID id) {
    if (!repository.existsByExternalId(id)) {
      throw new ValidationException(BarcodeType.class, BarcodeType_.NAME,
          "BarcodeType doesn't exists",
          "No BarcodeType was found with the supplied id '%s'".formatted(id));
    }
  }

  @Override
  public void checkHasProductRelationships(UUID id) {
    if (repository.existsProductsRelationshipsById(id)) {
      throw new ValidationException(BarcodeType.class, BarcodeType_.NAME,
          "BarcodeType has relationships and cannot be deleted",
          "BarcodeType with the supplied id '%s' has relationships with one or more products".formatted(
              id));
    }
  }
}
