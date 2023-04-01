package br.dev.gvf.productservice.mapper;

import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeCreateDTO;
import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeDTO;
import br.dev.gvf.productservice.dto.barcodetype.BarcodeTypeFilterDTO;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeCreateRequest;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeFilterRequest;
import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeUpdateRequest;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeBasePagedListResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeCreateResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeGetByIdResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypePagedListItemResponse;
import br.dev.gvf.productservice.model.BarcodeType;
import java.math.BigInteger;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(uses = {BaseMapper.class})
public interface BarcodeTypeMapper {

  @Mapping(target = "id", source = "externalId")
  BarcodeTypeGetByIdResponse barcodeTypeToBarcodeTypeResponse(BarcodeType barcodeType);

  @Mapping(target = "id", source = "externalId")
  BarcodeTypePagedListItemResponse barcodeTypeToBarcodeTypePagedListItemResponse(
      BarcodeType barcodeType);

  @Mapping(target = "content", source = "content")
  @Mapping(target = "pageNumber", source = "number")
  @Mapping(target = "pageSize", source = "size")
  BarcodeTypeBasePagedListResponse pageToBarcodeTypePagedListResponse(
      Page<BarcodeType> barcodeTypes);


  BarcodeTypeCreateDTO barcodeTypeCreateNewRequestToBarcodeTypeCreateDTO(
      BarcodeTypeCreateRequest barcodeTypeCreateRequest);

  @Mapping(target = "id", source = "externalId")
  BarcodeTypeCreateResponse barcodeTypeToBarcodeTypeCreateNewResponse(BarcodeType barcodeType);

  BarcodeTypeCreateResponse barcodeTypeDtoToBarcodeTypeCreateNewResponse(
      BarcodeTypeDTO barcodeTypeDTO);

  @Mapping(target = "orderBy", source = "orderBy", qualifiedByName = "orderByStringToDTO")
  BarcodeTypeFilterDTO barcodeTypeFilterRequestToBarcodeTypeFindByNamePaged(
      BarcodeTypeFilterRequest request);

  @Mapping(target = "externalId", source = "id")
  @Mapping(target = "version", source = "request.version")
  @Mapping(target = "name", source = "request.name")
  BarcodeType barcodeTypeUpdateRequestToBarcodeType(UUID id, BarcodeTypeUpdateRequest request);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "version", source = "request.version")
  @Mapping(target = "name", source = "request.name")
  BarcodeTypeDTO barcodeTypeDtoUpdateRequestToBarcodeType(UUID id,
      BarcodeTypeUpdateRequest request);

  @Mapping(target = "products", ignore = true)
  BarcodeType barcodeTypeCreateDTOToBarcodeType(BarcodeTypeCreateDTO barcodeTypeCreateDTO);

  @Mapping(target = "id", source = "externalId")
  BarcodeTypeDTO barcodeTypeToBarcodeTypeDto(BarcodeType barcodeType);

  @Mapping(target = "products", ignore = true)
  @Mapping(target = "id", source = "barcodeId")
  BarcodeType barcodeTypeDtoToBarcodeType(BarcodeTypeDTO barcodeTypeDTO, BigInteger barcodeId);

}
