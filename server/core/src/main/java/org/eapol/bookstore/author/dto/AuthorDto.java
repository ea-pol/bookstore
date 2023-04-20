package org.eapol.bookstore.author.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDto extends AuthorDtoPartial {
  @JsonProperty("author_id")
  private Long authorId;

  public AuthorDto(Long authorId, String firstName, String lastName) {
    super(firstName, lastName);
    this.authorId = authorId;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }
}
