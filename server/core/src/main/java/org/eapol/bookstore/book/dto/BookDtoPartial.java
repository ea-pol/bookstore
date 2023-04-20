package org.eapol.bookstore.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class BookDtoPartial extends AbstractBookDto {
  @NotNull
  @JsonProperty("author_id")
  private Long authorId;

  BookDtoPartial(
    Long authorId,
    String title,
    String firstSentence,
    Long price,
    Long amount
  ) {
    super(title, firstSentence, price, amount);
    this.authorId = authorId;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }
}
