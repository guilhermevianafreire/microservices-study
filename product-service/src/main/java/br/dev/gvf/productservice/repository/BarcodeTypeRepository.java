package br.dev.gvf.productservice.repository;

import br.dev.gvf.productservice.model.BarcodeType;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BarcodeTypeRepository extends JpaRepository<BarcodeType, BigInteger>,
    JpaSpecificationExecutor<BarcodeType> {

  @Query("SELECT b.id FROM BarcodeType b WHERE b.externalId = :externalId")
  Optional<BigInteger> findIdByExternalId(@Param("externalId") UUID externalId);

  @Query("SELECT b.externalId FROM BarcodeType b WHERE b.name = :name")
  Optional<UUID> findIdByName(@Param("name") String name);

  @Query("SELECT b.externalId FROM BarcodeType b WHERE b.name = :name and b.externalId <> :externalId")
  Optional<UUID> findIdByNameAndNotExternalId(@Param("name") String name,
      @Param("externalId") UUID id);

  Optional<BarcodeType> findByExternalId(UUID externalId);

  boolean existsByExternalId(UUID externalId);

  boolean existsByName(String name);

  Page<BarcodeType> findByActiveTrue(Pageable pageable);

  Page<BarcodeType> findByNameLikeAndActiveTrue(String name, Pageable pageable);

  @Modifying
  @Query("UPDATE BarcodeType b SET b.active = :active WHERE b.externalId = :externalId")
  void updateActiveByExternalId(@Param("externalId") UUID externalId,
      @Param("active") boolean active);

}
