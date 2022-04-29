package dev.guilhermevianafreire.ms.serviceproduct.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.guilhermevianafreire.ms.serviceproduct.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
