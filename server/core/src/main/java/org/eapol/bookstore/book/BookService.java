package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  private final BookDao bookDao;

  @Inject
  public BookService(BookDao bookDao) {
    this.bookDao = bookDao;
  }
}
