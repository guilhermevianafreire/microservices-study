package br.dev.gvf.productservice.controller;

import br.dev.gvf.productservice.dto.barcodetype.request.BarcodeTypeFilterRequest;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypePagedListResponse;
import br.dev.gvf.productservice.dto.barcodetype.response.BarcodeTypeResponse;
import br.dev.gvf.productservice.mapper.BarcodeTypeMapper;
import br.dev.gvf.productservice.service.BarcodeTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/V1/barcode-types")
@RequiredArgsConstructor
@Tag(name = "Barcode Type", description = "Manages teh Barcode Type resource")
public class BarcodeTypeController {

    private final BarcodeTypeService barcodeTypeService;
    private final BarcodeTypeMapper barcodeTypeMapper;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(method = "getById", summary = "Get a Barcode Type by id", description = "Get a Barcode Type by its UUID (Universally Unique Identifier)")
    public BarcodeTypeResponse getById(@PathVariable("id") UUID externalId) {
        return barcodeTypeMapper.barcodeTypeToBarcodeTypeResponse(
                barcodeTypeService.findByIdExternal(externalId)
        );
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(method = "listBarcodeTypes", summary = "List Barcode Types", description = "List Barcode Types in a paged response with the possibility of filtering by its properties")
    public BarcodeTypePagedListResponse listBarcodeTypes(@Validated BarcodeTypeFilterRequest request) {
        return barcodeTypeMapper.pageToBarcodeTypePagedListResponse(
                barcodeTypeService.findByNameLike(request.getName(), request.getPageNumber(), request.getPageSize())
        );
    }

}
