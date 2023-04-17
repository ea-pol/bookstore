package org.eapol.bookstore.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eapol.bookstore.author.Author;

public class BookDtoPartial {
  @JsonProperty("author")
  private Author author;
  @JsonProperty("title")
  private String title;
  @JsonProperty("first_sentence")
  private String firstSentence;
  @JsonProperty("price")
  private Long price;
  @JsonProperty("amount")
  private Long amount;

  BookDtoPartial(
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
