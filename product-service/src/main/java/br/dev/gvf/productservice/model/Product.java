package br.dev.gvf.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
public class Product extends BaseEntity<Product> {

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(
      name = "id_category",
      referencedColumnName = "id"
  )
  private Category category;

  @NotBlank
  @Size(max = 200)
  @Column(
      length = 200,
      nullable = false,
      unique = true
  )
  private String name;

  @NotBlank
  @Size(max = 50)
  @Pattern(
      regexp = "^[a-zA-Z0-9]+",
      message = "SKU inv\u00E1lido. Deve conter somente letras e n\u00FAmeros"
  )
  @Column(
      length = 50,
      nullable = false,
      unique = true
  )
  private String sku;

  @NotBlank
  @Size(max = 200)
  @Column(
      name = "short_description",
      length = 200,
      nullable = false
  )
  private String shortDescription;

  @NotBlank
  @Size(max = 2000)
  @Column(
      name = "full_description",
      length = 2000,
      nullable = false
  )
  private String fullDescription;

  @ManyToOne(optional = false)
  @JoinColumn(
      name = "id_barcode_type",
      referencedColumnName = "id"
  )
  private BarcodeType barcodeType;

  @Size(max = 200)
  @Column(
      length = 200,
      unique = true
  )
  private String barcode;

  @Override
  public String toString() {
    return "Product{" + "category=" + category.getId() + ", name='" + name + '\'' + ", sku='" + sku
        + '\'' + ", shortDescription='" + shortDescription + '\'' + ", fullDescription='"
        + fullDescription + '\'' + ", barcodeType=" + barcodeType.getId() + ", barcode='" + barcode
        + '\'' + "} " + super.toString();
  }
}
