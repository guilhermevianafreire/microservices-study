package br.dev.gvf.productservice.repository;

import br.dev.gvf.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger> {
}
