package br.dev.gvf.productservice.service.barcodetype;

import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeCreateDTO;
import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeDTO;
import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeFilterDTO;
import br.dev.gvf.productservice.model.BarcodeType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springframework.data.domain.Page;


public interface BarcodeTypeService {

  BarcodeType findByIdExternal(@Valid @NotNull final UUID externalId);

  Page<BarcodeType> search(@Valid final BarcodeTypeFilterDTO filters);

  BarcodeTypeDTO create(@Valid final BarcodeTypeCreateDTO dto);

  void update(@Valid final BarcodeTypeDTO barcodeTypeDTO);

  void updateActive(@Valid @NotNull final UUID externalId, final boolean active);

}
