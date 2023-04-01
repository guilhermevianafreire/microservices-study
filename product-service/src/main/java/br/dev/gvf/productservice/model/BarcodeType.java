package br.dev.gvf.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@NamedEntityGraph(
    name = "BarcodeType.Products",
    attributeNodes = {
        @NamedAttributeNode("products")
    }
)
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "barcode_type")
public class BarcodeType extends BaseEntity<BarcodeType> {

  @Size(max = 200)
  @NotBlank
  @Column(
      length = 200,
      nullable = false,
      unique = true
  )
  private String name;

  @OneToMany(mappedBy = "barcodeType")
  private Set<Product> products;

  @Override
  public String toString() {
    return "BarcodeType{" + "name='" + name + '\'' + "} " + super.toString();
  }
}
