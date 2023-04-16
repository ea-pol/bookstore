package org.eapol.bookstore.author;

public class AuthorDto {
  private Long authorId;
  private String firstName;
  private String lastName;

  public AuthorDto(Long authorId, String firstName, String lastName) {
    this.authorId = authorId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
