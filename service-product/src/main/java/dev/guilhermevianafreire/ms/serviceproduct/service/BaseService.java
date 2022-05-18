package dev.guilhermevianafreire.ms.serviceproduct.service;

import java.text.MessageFormat;
import java.util.UUID;

import dev.guilhermevianafreire.ms.serviceproduct.domain.constants.StatusType;
import org.springframework.data.jpa.domain.Specification;

public class BaseService<E> {

  protected Specification<E> equal(String parameterName,
                                       UUID value) {
    return (root,
            query,
            builder) -> builder.equal(root.get(parameterName), value);
  }

  protected Specification<E> equal(String parameterName,
                                         String value) {
    return (root,
            query,
            builder) -> builder.equal(root.get(parameterName), value);
  }
  
  protected Specification<E> equal(StatusType value) {
    return (root,
            query,
            builder) -> builder.equal(root.get("status"), value);
  }

  protected Specification<E> like(String parameterName,
                                        String value) {
    return (root,
            query,
            builder) -> builder.like(root.get(parameterName), likeFormatting(value));
  }

  private String likeFormatting(String expression) {
    return MessageFormat.format("%{0}%", expression);
  }

}
