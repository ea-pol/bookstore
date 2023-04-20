package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import org.eapol.bookstore.author.dto.AuthorDtoPartial;
import org.eapol.bookstore.book.BookDao;
import org.eapol.bookstore.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
  private final AuthorDao authorDao;
  private final BookDao bookDao;

  @Inject
  public AuthorService(AuthorDao authorDao, BookDao bookDao) {
    this.authorDao = authorDao;
    this.bookDao = bookDao;
  }

  @Transactional
  public void save(Author author) {
    authorDao.save(author);
  }

  @Transactional(readOnly = true)
  public List<Author> getAll() {
    return authorDao.all();
  }

  @Transactional(readOnly = true)
  public Author getById(Long id) {
    return authorDao
      .getById(id)
      .orElseThrow(() -> new NotFoundException(Author.class, id));
  }

  @Transactional
  public Author update(Long id, AuthorDtoPartial authorDtoPartial) {
    return authorDao.getById(id).map(author -> {
      author.setFirstName(authorDtoPartial.getFirstName());
      author.setLastName(authorDtoPartial.getLastName());
      return author;
    }).orElseThrow(() -> new NotFoundException(Author.class, id));
  }

  @Transactional
  public void deleteById(Long id) {
    bookDao.deleteByAuthorId(id);
    authorDao.deleteById(id);
  }
}
