package br.dev.gvf.productservice.repository;

import br.dev.gvf.productservice.model.Category;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, BigInteger> {

}
