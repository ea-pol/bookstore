package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import org.eapol.bookstore.author.AuthorDao;
import org.eapol.bookstore.author.Author;
import org.eapol.bookstore.book.dto.BookDtoPartial;
import org.eapol.bookstore.exception.NotFoundException;
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
  public void save(BookDtoPartial bookDtoPartial) {
    Long authorId = bookDtoPartial.getAuthorId();

    Author author = authorDao
      .getById(authorId)
      .orElseThrow(() -> new NotFoundException(Author.class, authorId));

    Book book = new Book(author,
      bookDtoPartial.getTitle(),
      bookDtoPartial.getFirstSentence(),
      bookDtoPartial.getPrice(),
      bookDtoPartial.getAmount());

    bookDao.save(book);
  }

  @Transactional(readOnly = true)
  public Book getByIdEager(Long id) {
    return bookDao
      .getByIdEager(id)
      .orElseThrow(() -> new NotFoundException(Book.class, id));
  }

  @Transactional(readOnly = true)
  public Book getById(Long id) {
    return bookDao
      .getById(id)
      .orElseThrow(() -> new NotFoundException(Book.class, id));
  }

  @Transactional
  public Book updateBook(Long id, BookDtoPartial bookDtoPartial) {
    Long authorId = bookDtoPartial.getAuthorId();

    Author author = authorDao
      .getById(authorId)
      .orElseThrow(() -> new NotFoundException(Author.class, authorId));

    return bookDao.getById(id).map(book -> {
      book.setAuthor(author);
      book.setTitle(bookDtoPartial.getTitle());
      book.setFirstSentence(bookDtoPartial.getFirstSentence());
      book.setAmount(bookDtoPartial.getAmount());
      book.setPrice(bookDtoPartial.getPrice());
      return book;
    }).orElseThrow(() -> new NotFoundException(Book.class, id));
  }

  @Transactional
  public void deleteById(Long id) {
    bookDao.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<String> getAllSentences() {
    return bookDao.getAllSentences();
  }
}
