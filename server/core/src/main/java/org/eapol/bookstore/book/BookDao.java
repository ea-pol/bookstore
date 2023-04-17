package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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
        "JOIN FETCH Author " +
        "WHERE book.author_id = author.author_id",
        Book.class)
      .getResultList();
  }

  public void save(Book book) {
    session().persist(book);
  }

  private Session session() {
    return sessionFactory.getCurrentSession();
  }
}
