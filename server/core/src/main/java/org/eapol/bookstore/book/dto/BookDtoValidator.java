package org.eapol.bookstore.book.dto;

public class BookDtoValidator {
  public static boolean isValidDto(BookDtoPartial bookDtoPartial) {
    if (bookDtoPartial.getAuthorId() == null) {
      return false;
    }

    if (bookDtoPartial.getTitle() == null || bookDtoPartial.getTitle().isBlank()) {
      return false;
    }

    if (bookDtoPartial.getFirstSentence() == null || bookDtoPartial.getFirstSentence().isBlank()) {
      return false;
    }

    if (bookDtoPartial.getPrice() == null || bookDtoPartial.getAmount() == null) {
      return false;
    }

    return true;
  }
}
