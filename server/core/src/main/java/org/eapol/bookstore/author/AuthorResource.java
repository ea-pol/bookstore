package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eapol.bookstore.author.dto.AuthorDto;
import org.eapol.bookstore.author.dto.AuthorDtoPartial;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// TODO: handle exception in case of errors
// TODO: validate request data

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

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public AuthorDto author(@PathParam("id") Long id) {
    return authorDao.getById(id)
        .map(AuthorMapper::toDto)
        .get();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public AuthorDto update(
      @PathParam("id") Long id,
      @RequestBody AuthorDtoPartial authorDtoPartial
  ) {
    return AuthorMapper.toDto(
        authorDao.update(id,
            authorDtoPartial.getFirstName(),
            authorDtoPartial.getLastName()));
  }

  @DELETE
  @Path("/{id}")
  public Response deleteById(@PathParam("id") Long id) {
    authorDao.deleteById(id);
    return Response.noContent().build();
  }
}
