package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eapol.bookstore.author.dto.AuthorDto;
import org.eapol.bookstore.author.dto.AuthorDtoPartial;
import org.springframework.web.bind.annotation.RequestBody;

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

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response newAuthor(@RequestBody AuthorDtoPartial authorDtoPartial) {
    Author author = new Author(
        authorDtoPartial.getFirstName(),
        authorDtoPartial.getLastName());

    authorDao.save(author);

    return Response.noContent().build();
  }
}
