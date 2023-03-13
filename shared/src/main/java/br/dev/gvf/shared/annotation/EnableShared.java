package br.dev.gvf.shared.annotation;

import br.dev.gvf.shared.SharedComponentScanConfig;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SharedComponentScanConfig.class)
public @interface EnableShared {
    @AliasFor(
            annotation = Import.class,
            attribute = "value"
    ) Class<?>[] value() default {SharedComponentScanConfig.class};

}
