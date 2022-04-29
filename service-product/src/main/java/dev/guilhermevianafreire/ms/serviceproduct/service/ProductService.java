package dev.guilhermevianafreire.ms.serviceproduct.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.guilhermevianafreire.ms.serviceproduct.repository.ProductRepository;
import dev.guilhermevianafreire.ms.serviceproduct.service.dto.ProductDTO;
import dev.guilhermevianafreire.ms.serviceproduct.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	public List<ProductDTO> listAll() {
		return productMapper.toDto(productRepository.findAll());
	}

}
