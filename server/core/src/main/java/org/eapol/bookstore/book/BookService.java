package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import org.eapol.bookstore.author.AuthorRepository;
import org.eapol.bookstore.author.Author;
import org.eapol.bookstore.book.dto.BookDtoPartial;
import org.eapol.bookstore.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @Inject
  public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  @Transactional(readOnly = true)
  public List<Book> getAll() {
    return bookRepository.getAll();
  }

  @Transactional
  public void save(BookDtoPartial bookDtoPartial) {
    Long authorId = bookDtoPartial.getAuthorId();

    Author author = authorRepository
      .getById(authorId)
      .orElseThrow(() -> new NotFoundException(Author.class, authorId));

    Book book = new Book(author,
      bookDtoPartial.getTitle(),
      bookDtoPartial.getFirstSentence(),
      bookDtoPartial.getPrice(),
      bookDtoPartial.getAmount());

    bookRepository.save(book);
  }

  @Transactional(readOnly = true)
  public Book getByIdEager(Long id) {
    return bookRepository
      .getByIdEager(id)
      .orElseThrow(() -> new NotFoundException(Book.class, id));
  }

  @Transactional(readOnly = true)
  public Book getById(Long id) {
    return bookRepository
      .getById(id)
      .orElseThrow(() -> new NotFoundException(Book.class, id));
  }

  @Transactional
  public Book updateBook(Long id, BookDtoPartial bookDtoPartial) {
    Long authorId = bookDtoPartial.getAuthorId();

    Author author = authorRepository
      .getById(authorId)
      .orElseThrow(() -> new NotFoundException(Author.class, authorId));

    return bookRepository.getById(id).map(book -> {
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
    bookRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<String> getAllSentences() {
    return bookRepository.getAllSentences();
  }
}
