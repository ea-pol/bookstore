package org.eapol.bookstore.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException(Class<?> clazz, Long id) {
    super(String.format("%s entity with specified id = %s not found", clazz.getSimpleName(), id));
  }
}
