package org.eapol.bookstore.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eapol.bookstore.author.dto.AuthorDto;

public class BookDto extends AbstractBookDto {
  @JsonProperty("book_id")
  private Long bookId;
  @JsonProperty("author")
  private AuthorDto authorDto;

  public BookDto(
    Long bookId,
    AuthorDto authorDto,
    String title,
    String firstSentence,
    Long price,
    Long amount
  ) {
    super(title, firstSentence, price, amount);
    this.bookId = bookId;
    this.authorDto = authorDto;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public AuthorDto getAuthorDto() {
    return authorDto;
  }

  public void setAuthorDto(AuthorDto authorDto) {
    this.authorDto = authorDto;
  }
}
