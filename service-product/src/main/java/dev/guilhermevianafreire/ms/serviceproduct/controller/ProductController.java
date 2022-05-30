package dev.guilhermevianafreire.ms.serviceproduct.controller;

import dev.guilhermevianafreire.ms.serviceproduct.service.ProductService;
import dev.guilhermevianafreire.ms.serviceproduct.service.mapper.ProductMapper;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditChangeDataDTO;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditHistoryDataDTO;
import dev.guilhermevianafreire.ms.serviceproductclient.dto.ProductDTO;
import dev.guilhermevianafreire.ms.serviceproductclient.dto.ProductFilterDTO;
import dev.guilhermevianafreire.ms.shared.mapper.AuditMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final AuditMapper auditMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDTO> getProducts(@RequestBody ProductFilterDTO filterDTO) {
        return productService.findBy(filterDTO).map(productMapper::toDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProduct(@PathVariable("id") UUID id) {
        return productMapper.toDto(productService.findProductById(id));
    }

    @GetMapping("/audit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AuditHistoryDataDTO> getProductAuditHistory(@PathVariable("id") UUID id) {
        return productService.getHistoryById(id).stream().map(auditMapper::snapshotToDto).toList();
    }

    @GetMapping("/audit/{id}/changes/{commitId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AuditChangeDataDTO> getProductAuditChanges(@PathVariable("id") UUID id, @PathVariable("commitId") BigDecimal commitID) {
        return auditMapper.changesToDtos(productService.getChangesById(id, commitID));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveNewProduct(@Valid @Validated @RequestBody ProductDTO dto) {
        return productMapper.toDto(productService.save(productMapper.toEntity(dto)));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@RequestBody ProductDTO dto) {
        return productMapper.toDto(productService.update(productMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") UUID id) {
        productService.delete(id);
    }

    @PutMapping("/status/change/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO changeProductStatus(@PathVariable("id") UUID id) {
        return productMapper.toDto(productService.changeStatusAndUpdate(id));
    }

}
