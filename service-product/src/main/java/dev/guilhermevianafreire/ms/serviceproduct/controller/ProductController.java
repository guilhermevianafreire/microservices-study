package dev.guilhermevianafreire.ms.serviceproduct.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.guilhermevianafreire.ms.serviceproduct.service.ProductService;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.listAll();
	}
	
}
