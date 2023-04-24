package org.eapol.bookstore.book.dto;

import jakarta.validation.constraints.NotNull;

public class BookDtoPartial extends AbstractBookDto {
  @NotNull
  private Long authorId;

  BookDtoPartial(
    Long authorId,
    String title,
    String firstSentence,
    Long publicationYear
  ) {
    super(title, firstSentence, publicationYear);
    this.authorId = authorId;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }
}
