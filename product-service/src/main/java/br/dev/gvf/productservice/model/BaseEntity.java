package br.dev.gvf.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@MappedSuperclass
@Getter
@Setter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
public class BaseEntity<T extends BaseEntity<T>> {

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
}
