package br.dev.gvf.productservice.repository;

import br.dev.gvf.productservice.model.Product;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger> {

}
