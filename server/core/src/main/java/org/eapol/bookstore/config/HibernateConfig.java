package org.eapol.bookstore.config;

import org.eapol.bookstore.author.Author;
import org.eapol.bookstore.book.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.PostgreSQLDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {

  @Value("${database.url}")
  private String DATABASE_URL;

  @Value("${database.username}")
  private String DATABASE_USERNAME;

  @Value("${database.password}")
  private String DATABASE_PASSWORD;

  @Value("${hibernate.hbm2ddl.auto}")
  private String HBM2DDL_AUTO;

  @Bean
  public DataSource dataSource() {
    return new DriverManagerDataSource(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
  }

  @Bean
  public LocalSessionFactoryBean localSessionFactoryBean() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

    sessionFactory.setDataSource(dataSource());
    sessionFactory.setAnnotatedClasses(Author.class, Book.class);
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public SessionFactory sessionFactory(LocalSessionFactoryBean localSessionFactoryBean) {
    return localSessionFactoryBean.getObject();
  }

  @Bean
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    return new HibernateTransactionManager(sessionFactory);
  }

  private Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();

    hibernateProperties.put(Environment.DIALECT, PostgreSQLDialect.class.getName());
    hibernateProperties.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);

    return hibernateProperties;
  }
}
