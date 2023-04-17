package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
  private final SessionFactory sessionFactory;

  @Inject
  public BookDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  private Session session() {
    return sessionFactory.getCurrentSession();
  }
}
