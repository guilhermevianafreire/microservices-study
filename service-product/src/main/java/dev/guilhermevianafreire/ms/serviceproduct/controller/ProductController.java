package dev.guilhermevianafreire.ms.serviceproduct.controller;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.guilhermevianafreire.ms.serviceproduct.service.ProductService;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.PageRequestDTO;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public Page<ProductDTO> getAllProducts(@RequestBody PageRequestDTO pageRequest) {
    if (Objects.nonNull(pageRequest.directionType()) && Objects.nonNull(pageRequest.properties()))
      return productService.listAll(pageRequest.page(), pageRequest.sizeOfPage(), pageRequest.directionType(), pageRequest.properties());
    return productService.listAll(pageRequest.page(), pageRequest.sizeOfPage());
  }

}
