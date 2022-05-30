package dev.guilhermevianafreire.ms.serviceproduct.domain;

import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;
import dev.guilhermevianafreire.ms.serviceproductclient.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

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
    @Column(columnDefinition = "BINARY(16)", updatable = false)
    @Null(groups = SaveGroup.class)
    @NotNull(groups = UpdateGroup.class)
    public UUID id;

    @NotNull(groups = {SaveGroup.class, UpdateGroup.class})
    @Column(columnDefinition = "SMALINT", nullable = false)
    public StatusType status;

    @Null(groups = {SaveGroup.class})
    @NotNull(groups = {UpdateGroup.class})
    @Version
    @Column(columnDefinition = "INTEGER", nullable = false)
    public Long version;

    public void changeStatus() {
        if (StatusType.ACTIVE.equals(status))
            status = StatusType.INACTIVE;
        else
            status = StatusType.ACTIVE;
    }

    public interface UpdateGroup {
    }

    public interface SaveGroup {
    }

}
