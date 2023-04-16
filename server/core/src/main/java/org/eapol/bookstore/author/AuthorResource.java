package org.eapol.bookstore.author;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/authors")
public class AuthorResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<AuthorDto> authors() {
    return null;
  }
}
