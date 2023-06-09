package org.eapol.bookstore.author;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
import org.eapol.bookstore.author.dto.AuthorDtoPartial;

@Path("/api/authors")
public class AuthorResource {
  private final AuthorService authorService;

  @Inject
  public AuthorResource(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllAuthors() {
    return Response
      .ok(authorService.getAll()
        .stream()
        .map(AuthorMapper::toDto)
        .toList())
      .build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createNewAuthor(@Valid AuthorDtoPartial authorDtoPartial) {
    Author author = authorService.save(AuthorMapper.fromDto(authorDtoPartial));
    return Response.ok(AuthorMapper.toDto(author)).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAuthorById(@PathParam("id") Long id) {
    return Response
      .ok(AuthorMapper.toDto(authorService.getById(id)))
      .build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateAuthor(@PathParam("id") Long id, @Valid AuthorDtoPartial authorDtoPartial) {
    return Response
      .ok(AuthorMapper.toDto(authorService.update(id, authorDtoPartial)))
      .build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteAuthorById(@PathParam("id") Long id) {
    authorService.deleteById(id);
    return Response.noContent().build();
  }
}
