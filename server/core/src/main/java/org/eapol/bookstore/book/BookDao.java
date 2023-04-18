package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public class BookDao {
  private final SessionFactory sessionFactory;

  @Inject
  public BookDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public List<Book> getAll() {
    return session()
      .createQuery(
        "FROM Book book " +
          "JOIN FETCH book.author",
        Book.class)
      .getResultList();
  }

  public void save(Book book) {
    session().persist(book);
  }

  public Optional<Book> getByIdEager(Long id) {
    try {
      Book book = session()
        .createQuery(
          "FROM Book book " +
            "JOIN FETCH book.author " +
            "WHERE book.bookId = :id",
          Book.class)
        .setParameter("id", id)
        .getSingleResult();

      return Optional.of(book);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public Optional<Book> getById(Long id) {
    return Optional.ofNullable(
      session().get(Book.class, id));
  }

  public void deleteById(Long id) {
    session()
      .createQuery(
        "DELETE FROM Book book " +
          "WHERE book.bookId = :id")
      .setParameter("id", id)
      .executeUpdate();
  }

  public void deleteByAuthorId(Long authorId) {
    session()
      .createQuery(
        "DELETE FROM Book book " +
          "WHERE book.author.authorId = :authorId")
      .setParameter("authorId", authorId)
      .executeUpdate();
  }

  private Session session() {
    return sessionFactory.getCurrentSession();
  }
}
