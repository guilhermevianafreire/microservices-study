package dev.guilhermevianafreire.ms.serviceproduct.repository;

import dev.guilhermevianafreire.ms.serviceproduct.domain.Product;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@JaversSpringDataAuditable
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {

}
