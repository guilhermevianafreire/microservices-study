package dev.guilhermevianafreire.ms.serviceproduct.exception;

public class InvalidEntitySaveOperationException extends RuntimeException {

  private static final long serialVersionUID = 7558363912688064694L;

  public InvalidEntitySaveOperationException() {
    super();
  }

  public InvalidEntitySaveOperationException(
                                              String message,
                                              Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InvalidEntitySaveOperationException(
                                              String message,
                                              Throwable cause) {
    super(message, cause);
  }

  public InvalidEntitySaveOperationException(
                                              String message) {
    super(message);
  }

  public InvalidEntitySaveOperationException(
                                              Throwable cause) {
    super(cause);
  }

}
