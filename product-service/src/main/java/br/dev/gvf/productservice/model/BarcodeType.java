package br.dev.gvf.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.Set;

@NamedEntityGraph(
        name = "BarcodeType.Products",
        attributeNodes = {
                @NamedAttributeNode("products")
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "barcode_type")
public class BarcodeType extends BaseEntity {

    @Size(max = 200)
    @NotBlank
    @Column(length = 200, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "barcodeType")
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BarcodeType that = (BarcodeType) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BarcodeType{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
