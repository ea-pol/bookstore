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
      .createQuery("FROM Book", Book.class)
      .getResultList();
  }

  private Session session() {
    return sessionFactory.getCurrentSession();
  }
}
