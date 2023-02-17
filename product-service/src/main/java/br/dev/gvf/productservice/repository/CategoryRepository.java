package br.dev.gvf.productservice.repository;

import br.dev.gvf.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CategoryRepository extends JpaRepository<Category, BigInteger> {
}
