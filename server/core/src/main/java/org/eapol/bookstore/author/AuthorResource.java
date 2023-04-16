package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/authors")
public class AuthorResource {
  private final AuthorDao authorDao;

  @Inject
  public AuthorResource(AuthorDao authorDao) {
    this.authorDao = authorDao;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<AuthorDto> authors() {
    return authorDao.all()
        .stream()
        .map(AuthorMapper::toDto)
        .toList();
  }
}
