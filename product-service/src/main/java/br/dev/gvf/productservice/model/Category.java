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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.Set;

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
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Size(max = 200)
    @NotBlank
    @Column(length = 200, nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parent_category", referencedColumnName = "id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private Set<Category> subCategories;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
