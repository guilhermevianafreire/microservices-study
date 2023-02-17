package br.dev.gvf.productservice.repository;

import br.dev.gvf.productservice.model.BarcodeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BarcodeTypeRepository extends JpaRepository<BarcodeType, BigInteger>, JpaSpecificationExecutor<BarcodeType> {
    Optional<BarcodeType> findByExternalId(UUID externalId);

    Page<BarcodeType> findByNameLike(String name, Pageable pageable);
}
