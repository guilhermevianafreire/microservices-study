package dev.guilhermevianafreire.ms.serviceproduct.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.guilhermevianafreire.ms.serviceproduct.domain.Product;
import dev.guilhermevianafreire.ms.serviceproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public List<Product> listAll() {
		return productRepository.findAll();
	}

}
