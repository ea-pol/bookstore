package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDao {
  private final SessionFactory sessionFactory;

  @Inject
  public AuthorDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional
  public void save(Author author) {
    session().persist(author);
  }

  @Transactional(readOnly = true)
  public List<Author> all() {
    return session()
            .createQuery("FROM Author", Author.class)
            .getResultList();
  }

  @Transactional(readOnly = true)
  public Optional<Author> getById(Long id) {
    return Optional.ofNullable(
      session().get(Author.class, id));
  }

  @Transactional
  public void deleteById(Long id) {
    session()
        .createQuery(
            "DELETE FROM Author author " +
            "WHERE author.authorId = :id")
        .setParameter("id", id)
        .executeUpdate();
  }

  @Transactional
  public Author update(Author author) {
    return session().merge(author);
  }

  private Session session() {
    return sessionFactory.getCurrentSession();
  }
}
