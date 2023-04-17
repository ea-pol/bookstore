package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDao {
  private final SessionFactory sessionFactory;

  @Inject
  public AuthorDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void save(Author author) {
    session().persist(author);
  }

  public List<Author> all() {
    return session()
            .createQuery("FROM Author", Author.class)
            .getResultList();
  }

  public Optional<Author> getById(Long id) {
    return Optional.ofNullable(
      session().get(Author.class, id));
  }

  public void deleteById(Long id) {
    session()
        .createQuery(
            "DELETE FROM Author author " +
            "WHERE author.authorId = :id")
        .setParameter("id", id)
        .executeUpdate();
  }

  private Session session() {
    return sessionFactory.getCurrentSession();
  }
}
