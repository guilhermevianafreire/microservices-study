package br.dev.gvf.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ValidationException extends RuntimeException {

  private final Class<?> entity;
  private final String property;
  private final String message;
  private final String detail;

}
