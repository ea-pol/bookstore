package org.eapol.bookstore.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eapol.bookstore.author.Author;

public class BookDto extends BookDtoPartial {
  @JsonProperty("book_id")
  private Long bookId;

  public BookDto(
    Long bookId,
    Author author,
    String title,
    String firstSentence,
    Long price,
    Long amount
  ) {
    super(author, title, firstSentence, price, amount);
    this.bookId = bookId;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }
}
