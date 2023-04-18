package org.eapol.bookstore.author;

import jakarta.persistence.*;
import org.eapol.bookstore.book.Book;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "author_id")
  private Long authorId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
  private List<Book> books = new ArrayList<>();

  public Author() {
  }

  public void addBook(Book book) {
    this.books.add(book);
  }

  public Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}
