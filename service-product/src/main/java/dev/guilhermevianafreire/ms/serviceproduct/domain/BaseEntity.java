package dev.guilhermevianafreire.ms.serviceproduct.domain;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
                                                                                               @Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
  })
  @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false)
  public UUID id;

  @NotNull
  @Column(name = "status", columnDefinition = "SMALINT", nullable = false)
  public StatusType status;

  @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false)
  public Instant createdDate;

  @Column(name = "created_by", updatable = false)
  public String createdBy;

  @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
  public Instant lastModifiedDate;

  @Column(name = "last_modified_by", updatable = false)
  public String lastModifiedBy;

  public void changeStatus() {
    if (StatusType.ACTIVE.equals(status))
      status = StatusType.INACTIVE;
    else
      status = StatusType.ACTIVE;
  }

}
