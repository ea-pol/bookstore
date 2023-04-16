package org.eapol.bookstore.author;

import org.eapol.bookstore.author.dto.AuthorDto;

public class AuthorMapper {
  public static AuthorDto toDto(Author author) {
    return new AuthorDto(
        author.getAuthorId(),
        author.getFirstName(),
        author.getLastName());
  }
}
