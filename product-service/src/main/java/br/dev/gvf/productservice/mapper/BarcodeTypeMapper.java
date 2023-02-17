package br.dev.gvf.productservice.mapper;

import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypePagedListResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeResponse;
import br.dev.gvf.productservice.model.BarcodeType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper
public interface BarcodeTypeMapper {

    @Mapping(target = "id", source = "externalId")
    BarcodeTypeResponse barcodeTypeToBarcodeTypeResponse(BarcodeType barcodeType);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "pageNumber", source = "number")
    @Mapping(target = "pageSize", source = "size")
    BarcodeTypePagedListResponse pageToBarcodeTypePagedListResponse(Page<BarcodeType> barcodeTypes);

}
