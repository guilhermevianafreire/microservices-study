package br.dev.gvf.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity<T extends AuditableEntity<T>> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;

  @NotNull
  @Column(
      name = "external_id",
      nullable = false,
      updatable = false,
      unique = true
  )
  private UUID externalId = UUID.randomUUID();

  private boolean active = true;

  @Version
  private long version;

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @CreatedDate
  @Column(name = "created_date", updatable = false)
  private Instant createdDate;

  @LastModifiedBy
  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  private Instant lastModifiedDate;

  @SuppressWarnings("unchecked")
  public T setId(BigInteger id) {
    this.id = id;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setExternalId(UUID externalId) {
    this.externalId = externalId;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setActive(boolean active) {
    this.active = active;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setVersion(long version) {
    this.version = version;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
    return (T) this;
  }

  @SuppressWarnings("unchecked")
  public T setLastModifiedDate(Instant lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return (T) this;
  }
}
