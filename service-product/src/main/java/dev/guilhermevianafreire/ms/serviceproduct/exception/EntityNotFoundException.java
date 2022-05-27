package dev.guilhermevianafreire.ms.serviceproduct.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends javax.persistence.EntityNotFoundException {

    private final Class<?> entityClass;
    private final Object id;

    public EntityNotFoundException(Class<?> entityClass, Object id) {
        this.entityClass = entityClass;
        this.id = id;
    }
}
