package br.dev.gvf.productservice.controller.barcodetype;

import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeActiveRequest;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeCreateRequest;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeFilterRequest;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeUpdateRequest;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeBasePagedListResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeCreateResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeGetByIdResponse;
import br.dev.gvf.productservice.mapper.BarcodeTypeMapper;
import br.dev.gvf.productservice.service.barcodetype.BarcodeTypeServiceImpl;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1/barcode-types")
@RequiredArgsConstructor
public class BarcodeTypeV1ControllerImpl implements BarcodeTypeV1Controller {

  private final BarcodeTypeServiceImpl barcodeTypeService;
  private final BarcodeTypeMapper barcodeTypeMapper;

  @Override
  public BarcodeTypeGetByIdResponse findById(UUID externalId) {
    return barcodeTypeMapper.barcodeTypeToBarcodeTypeResponse(
        barcodeTypeService.findByIdExternal(externalId));
  }

  @Override
  public BarcodeTypeBasePagedListResponse list(BarcodeTypeFilterRequest request) {
    return barcodeTypeMapper.pageToBarcodeTypePagedListResponse(barcodeTypeService.search(
        barcodeTypeMapper.barcodeTypeFilterRequestToBarcodeTypeFindByNamePaged(request)));
  }

  @Override
  public BarcodeTypeCreateResponse create(BarcodeTypeCreateRequest request) {
    return barcodeTypeMapper.barcodeTypeDtoToBarcodeTypeCreateNewResponse(barcodeTypeService.create(
        barcodeTypeMapper.barcodeTypeCreateNewRequestToBarcodeTypeCreateDTO(request)));
  }

  @Override
  public void update(UUID id, BarcodeTypeUpdateRequest request) {
    barcodeTypeService.update(
        barcodeTypeMapper.barcodeTypeDtoUpdateRequestToBarcodeType(id, request));
  }

  @Override
  public void changeActive(UUID id, BarcodeTypeActiveRequest request) {
    barcodeTypeService.updateActive(id, request.getActive());
  }

}
