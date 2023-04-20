package org.eapol.bookstore.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public abstract class AbstractBookDto {
  @NotBlank
  @JsonProperty("title")
  private String title;

  @NotBlank
  @JsonProperty("first_sentence")
  private String firstSentence;

  @NotNull
  @JsonProperty("price")
  private Long price;

  @NotNull
  @JsonProperty("amount")
  private Long amount;

  public AbstractBookDto(
    String title,
    String firstSentence,
    Long price,
    Long amount
  ) {
    this.title = title;
    this.firstSentence = firstSentence;
    this.price = price;
    this.amount = amount;
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
