package org.eapol.bookstore.author.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class AuthorDtoPartial {
  @NotBlank
  @JsonProperty("first_name")
  protected String firstName;
  @NotBlank
  @JsonProperty("last_name")
  protected String lastName;

  public AuthorDtoPartial(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
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
