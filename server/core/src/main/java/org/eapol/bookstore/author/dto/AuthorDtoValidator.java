package org.eapol.bookstore.author.dto;

public class AuthorDtoValidator {
  public static boolean isValidDto(AuthorDtoPartial authorDtoPartial) {
    if (authorDtoPartial.getFirstName() == null || authorDtoPartial.getFirstName().isBlank()) {
      return false;
    }

    if (authorDtoPartial.getLastName() == null || authorDtoPartial.getFirstName().isBlank()) {
      return false;
    }

    return true;
  }
}
