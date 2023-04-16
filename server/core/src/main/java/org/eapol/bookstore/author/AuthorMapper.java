package org.eapol.bookstore.author;

public class AuthorMapper {
  public static AuthorDto toDto(Author author) {
    return new AuthorDto(
        author.getAuthorId(),
        author.getFirstName(),
        author.getLastName());
  }
}
