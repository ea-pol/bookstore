package org.eapol.bookstore.author;

import org.eapol.bookstore.author.dto.AuthorDto;
import org.eapol.bookstore.author.dto.AuthorDtoPartial;

public class AuthorMapper {
  public static AuthorDto toDto(Author author) {
    return new AuthorDto(
        author.getAuthorId(),
        author.getFirstName(),
        author.getLastName());
  }

  public static Author fromDto(AuthorDtoPartial authorDtoPartial) {
    return new Author(
            authorDtoPartial.getFirstName(),
            authorDtoPartial.getLastName());
  }
}
