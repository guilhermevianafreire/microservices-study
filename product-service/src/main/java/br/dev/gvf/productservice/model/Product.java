package br.dev.gvf.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@NamedEntityGraph(
        name = "Product.Category",
        attributeNodes = {
                @NamedAttributeNode("category")
        }
)
@NamedEntityGraph(
        name = "Product.BarcodeType",
        attributeNodes = {
                @NamedAttributeNode("barcodeType")
        }
)
@NamedEntityGraph(
        name = "Product.CategoryAndBarcodeType",
        attributeNodes = {
                @NamedAttributeNode("category"),
                @NamedAttributeNode("barcodeType")
        }
)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;

    @NotBlank
    @Size(max = 200)
    @Column(length = 200, nullable = false, unique = true)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]+", message = "SKU inv\u00E1lido. Deve conter somente letras e n\u00FAmeros")
    @Column(length = 50, nullable = false, unique = true)
    private String sku;

    @NotBlank
    @Size(max = 200)
    @Column(name = "short_description", length = 200, nullable = false)
    private String shortDescription;

    @NotBlank
    @Size(max = 2000)
    @Column(name = "full_description", length = 2000, nullable = false)
    private String fullDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barcode_type", referencedColumnName = "id")
    private BarcodeType barcodeType;

    @Size(max = 200)
    @Column(length = 200, unique = true)
    private String barcode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return category.equals(product.category) && name.equals(product.name) && sku.equals(product.sku) && shortDescription.equals(product.shortDescription) && fullDescription.equals(product.fullDescription) && Objects.equals(barcodeType, product.barcodeType) && Objects.equals(barcode, product.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, name, sku, shortDescription, fullDescription, barcodeType, barcode);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("name='").append(name).append('\'');
        sb.append(", sku='").append(sku).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append(", fullDescription='").append(fullDescription).append('\'');
        sb.append(", barcode='").append(barcode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
