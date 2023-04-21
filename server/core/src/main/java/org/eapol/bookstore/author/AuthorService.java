package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import org.eapol.bookstore.author.dto.AuthorDtoPartial;
import org.eapol.bookstore.book.BookRepository;
import org.eapol.bookstore.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  @Inject
  public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  @Transactional
  public void save(Author author) {
    authorRepository.save(author);
  }

  @Transactional(readOnly = true)
  public List<Author> getAll() {
    return authorRepository.all();
  }

  @Transactional(readOnly = true)
  public Author getById(Long id) {
    return authorRepository
      .getById(id)
      .orElseThrow(() -> new NotFoundException(Author.class, id));
  }

  @Transactional
  public Author update(Long id, AuthorDtoPartial authorDtoPartial) {
    return authorRepository.getById(id).map(author -> {
      author.setFirstName(authorDtoPartial.getFirstName());
      author.setLastName(authorDtoPartial.getLastName());
      return author;
    }).orElseThrow(() -> new NotFoundException(Author.class, id));
  }

  @Transactional
  public void deleteById(Long id) {
    bookRepository.deleteByAuthorId(id);
    authorRepository.deleteById(id);
  }
}
