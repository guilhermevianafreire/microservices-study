package dev.guilhermevianafreire.ms.serviceproduct.controller;

import dev.guilhermevianafreire.ms.serviceproduct.service.ProductService;
import dev.guilhermevianafreire.ms.shared.dto.audit.AuditDataDTO;
import dev.guilhermevianafreire.ms.shared.dto.product.ProductDTO;
import dev.guilhermevianafreire.ms.shared.dto.product.ProductFilterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> getProducts(@RequestBody ProductFilterDTO productFilterDTO) {
        return productService.findBy(productFilterDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") UUID id) {
        return productService.findProductById(id);
    }

    @GetMapping("/audit/{id}")
    public List<AuditDataDTO> getProductAudit(@PathVariable("id") UUID id) {
        return productService.getAllChangesById(id);
    }

    @PostMapping
    public ProductDTO saveNewProduct(@RequestBody ProductDTO productNewDTO) {
        return productService.save(productNewDTO);
    }

    @PutMapping
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.update(productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") UUID id) {
        productService.delete(id);
    }

    @PutMapping("/status/change/{id}")
    public ProductDTO changeProductStatus(@PathVariable("id") UUID id) {
        return productService.changeStatusAndUpdate(id);
    }

}
