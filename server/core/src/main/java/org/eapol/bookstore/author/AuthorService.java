package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
  private final AuthorDao authorDao;

  @Inject
  public AuthorService(AuthorDao authorDao) {
    this.authorDao = authorDao;
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
  public Optional<Author> getById(Long id) {
    return authorDao.getById(id);
  }

  @Transactional
  public Author update(Long id, String firstName, String lastName) {
    return getById(id).map(author -> {
      author.setFirstName(firstName);
      author.setLastName(lastName);
      return author;
    }).get();
  }

  @Transactional
  public void deleteById(Long id) {
    authorDao.deleteById(id);
  }
}
