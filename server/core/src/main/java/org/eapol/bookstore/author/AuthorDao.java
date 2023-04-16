package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

  private Session session() {
    return sessionFactory.getCurrentSession();
  }
}
