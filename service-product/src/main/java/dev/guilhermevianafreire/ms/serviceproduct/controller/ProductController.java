package dev.guilhermevianafreire.ms.serviceproduct.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.guilhermevianafreire.ms.serviceproduct.service.ProductService;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductDTO;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductFilterDTO;
import lombok.RequiredArgsConstructor;

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
