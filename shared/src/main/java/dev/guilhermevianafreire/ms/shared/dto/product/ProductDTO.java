package dev.guilhermevianafreire.ms.shared.dto.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

public record ProductDTO(@Null(groups = {ProductSaveGroup.class}) @NotNull(groups = {ProductUpdateGroup.class}) UUID id,
                         @NotBlank(groups = {ProductSaveGroup.class, ProductUpdateGroup.class}) @Size(max = 100, groups = {ProductSaveGroup.class, ProductUpdateGroup.class}) String name,
                         String description,
                         @NotNull(groups = {ProductSaveGroup.class, ProductUpdateGroup.class}) Integer statusCode,
                         @Null(groups = {ProductSaveGroup.class}) @NotNull(groups = {ProductUpdateGroup.class}) Long version) {

    public interface ProductUpdateGroup {
    }

    public interface ProductSaveGroup {
    }

}
