package dev.guilhermevianafreire.ms.serviceproduct.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @NotBlank(groups = {SaveGroup.class, UpdateGroup.class})
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Lob
    private String description;

}
