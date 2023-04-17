package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
  private final BookDao bookDao;

  @Inject
  public BookService(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Transactional(readOnly = true)
  public List<Book> getAll() {
    return bookDao.getAll();
  }

  @Transactional
  public void save(Book book) {
    bookDao.save(book);
  }
}
