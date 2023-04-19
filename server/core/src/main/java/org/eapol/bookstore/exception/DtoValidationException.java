package org.eapol.bookstore.exception;

public class DtoValidationException extends RuntimeException {

  public DtoValidationException() {
    super("Request parameters are invalid.");
  }
}
