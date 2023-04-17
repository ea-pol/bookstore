package org.eapol.bookstore.book;

import org.eapol.bookstore.book.dto.BookDto;

public class BookMapper {
  public static BookDto toDto(Book book) {
    return new BookDto(
      book.getBookId(),
      book.getAuthor(),
      book.getTitle(),
      book.getFirstSentence(),
      book.getPrice(),
      book.getAmount()
    );
  }
}
