package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import org.eapol.bookstore.author.AuthorDao;
import org.eapol.bookstore.author.Author;
import org.eapol.bookstore.book.dto.BookDtoPartial;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
  private final BookDao bookDao;
  private final AuthorDao authorDao;

  @Inject
  public BookService(BookDao bookDao, AuthorDao authorDao) {
    this.bookDao = bookDao;
    this.authorDao = authorDao;
  }

  @Transactional(readOnly = true)
  public List<Book> getAll() {
    return bookDao.getAll();
  }

  @Transactional
  public void save(Book book) {
    bookDao.save(book);
  }

  @Transactional(readOnly = true)
  public Optional<Book> getByIdEager(Long id) {
    return bookDao.getByIdEager(id);
  }

  @Transactional(readOnly = true)
  public Optional<Book> getById(Long id) {
    return bookDao.getById(id);
  }

  @Transactional
  public Optional<Book> updateBook(Long id, BookDtoPartial bookDtoPartial) {
    return getById(id).map(book -> {
      Author author = authorDao.getById(bookDtoPartial.getAuthorId()).get();
      book.setAuthor(author);
      book.setTitle(bookDtoPartial.getTitle());
      book.setFirstSentence(bookDtoPartial.getFirstSentence());
      book.setAmount(bookDtoPartial.getAmount());
      book.setPrice(bookDtoPartial.getPrice());
      return book;
    });
  }

  @Transactional
  public void deleteById(Long id) {
    bookDao.deleteById(id);
  }
}
