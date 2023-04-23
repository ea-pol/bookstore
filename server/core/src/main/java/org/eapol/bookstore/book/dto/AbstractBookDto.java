package org.eapol.bookstore.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public abstract class AbstractBookDto {
  @NotBlank
  private String title;

  @NotBlank
  private String firstSentence;

  @NotNull
  private Long publicationYear;

  public AbstractBookDto(
    String title,
    String firstSentence,
    Long publicationYear
  ) {
    this.title = title;
    this.firstSentence = firstSentence;
    this.publicationYear = publicationYear;
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

  public Long getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(Long publicationYear) {
    this.publicationYear = publicationYear;
  }
}
