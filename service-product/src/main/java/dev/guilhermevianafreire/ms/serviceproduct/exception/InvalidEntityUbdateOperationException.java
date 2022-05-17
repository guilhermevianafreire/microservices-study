package dev.guilhermevianafreire.ms.serviceproduct.exception;

public class InvalidEntityUbdateOperationException extends RuntimeException {

  private static final long serialVersionUID = 7558363912688064694L;

  public InvalidEntityUbdateOperationException() {
    super();
  }

  public InvalidEntityUbdateOperationException(
                                              String message,
                                              Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InvalidEntityUbdateOperationException(
                                              String message,
                                              Throwable cause) {
    super(message, cause);
  }

  public InvalidEntityUbdateOperationException(
                                              String message) {
    super(message);
  }

  public InvalidEntityUbdateOperationException(
                                              Throwable cause) {
    super(cause);
  }

}
