package br.dev.gvf.productservice.validation;

import br.dev.gvf.productservice.exception.ValidationException;
import br.dev.gvf.productservice.model.BarcodeType;
import br.dev.gvf.productservice.model.BarcodeType_;
import br.dev.gvf.productservice.repository.BarcodeTypeRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BarcodeTypeValidationImpl implements BarcodeTypeValidation {

  private final BarcodeTypeRepository repository;

  @Override
  public void checkNewNameUnique(String name) {
    repository.findIdByName(name).ifPresent(barcodeTypeId -> {
      throw new ValidationException(BarcodeType.class, BarcodeType_.NAME,
          "BarcodeType name alredy exists",
          "The supplyed name '%s' is alredy registered to BarcodeType with id '%s'".formatted(name,
              barcodeTypeId));
    });
  }

  @Override
  public void checkUpdateNameUnique(String name, UUID externalId) {
    repository.findIdByNameAndNotExternalId(name, externalId).ifPresent(barcodeTypeId -> {
      throw new ValidationException(BarcodeType.class, BarcodeType_.NAME,
          "BarcodeType name alredy exists",
          "The supplyed name '%s' is alredy registered to BarcodeType with id '%s'".formatted(name,
              barcodeTypeId));
    });
  }

  @Override
  public void checkExternalIdExists(UUID id) {
    if (!repository.existsByExternalId(id)) {
      throw new ValidationException(BarcodeType.class, BarcodeType_.NAME,
          "BarcodeType doesen't exists",
          "No BarcodeType was found with the supplyed id '%s'".formatted(id));
    }
  }
  
}
