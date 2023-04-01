package br.dev.gvf.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@NamedEntityGraph(
    name = "Category.ParentCategory",
    attributeNodes = {
        @NamedAttributeNode("parentCategory")
    }
)
@NamedEntityGraph(
    name = "Category.SubCategories",
    attributeNodes = {
        @NamedAttributeNode("subCategories")
    }
)
@NamedEntityGraph(
    name = "Category.ParentAndSub",
    attributeNodes = {
        @NamedAttributeNode("parentCategory"),
        @NamedAttributeNode("subCategories")
    }
)
@NamedEntityGraph(
    name = "Category.Products",
    attributeNodes = {
        @NamedAttributeNode("products")
    }
)
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "category")
public class Category extends BaseEntity<Category> {

  @Size(max = 200)
  @NotBlank
  @Column(
      length = 200,
      nullable = false,
      unique = true
  )
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "id_parent_category",
      referencedColumnName = "id"
  )
  private Category parentCategory;

  @OneToMany(mappedBy = "parentCategory")
  private Set<Category> subCategories;

  @OneToMany(mappedBy = "category")
  private Set<Product> products;

  @Override
  public String toString() {
    return "Category{" + "name='" + name + '\'' + ", parentCategory=" + Optional
        .ofNullable(parentCategory)
        .orElse(new Category())
        .getId() + "} " + super.toString();
  }
}
