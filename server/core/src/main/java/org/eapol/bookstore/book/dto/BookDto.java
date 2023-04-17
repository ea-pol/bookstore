package org.eapol.bookstore.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eapol.bookstore.author.Author;

public class BookDto extends AbstractBookDto {
  @JsonProperty("book_id")
  private Long bookId;
  @JsonProperty("author")
  private Author author;

  public BookDto(
    Long bookId,
    Author author,
    String title,
    String firstSentence,
    Long price,
    Long amount
  ) {
    super(title, firstSentence, price, amount);
    this.bookId = bookId;
    this.author = author;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }
}
