package org.eapol.bookstore.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.eapol.bookstore.author.Author;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "book_id")
  private Long bookId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private Author author;

  @Column(name = "title")
  private String title;

  @Column(name = "first_sentence")
  private String firstSentence;

  @Column(name = "price")
  private Long price;

  @Column(name = "amount")
  private Long amount;

  public Book() {
  }

  public Book(
    Author author,
    String title,
    String firstSentence,
    Long price,
    Long amount
  ) {
    this.author = author;
    this.title = title;
    this.firstSentence = firstSentence;
    this.price = price;
    this.amount = amount;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirstSentence() {
    return firstSentence;
  }

  public void setFirstSentence(String firstSentence) {
    this.firstSentence = firstSentence;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }
}
