package org.eapol.bookstore.book;

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
import org.eapol.bookstore.book.dto.BookDtoPartial;

@Path("/api/books")
public class BookResource {
  private final BookService bookService;

  @Inject
  public BookResource(BookService bookService) {
    this.bookService = bookService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response books() {
    return Response
      .ok(bookService.getAll()
        .stream()
        .map(BookMapper::toDto)
        .toList())
      .build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response save(@Valid BookDtoPartial bookDtoPartial) {
    Book book = bookService.save(bookDtoPartial);
    return Response.ok(BookMapper.toDto(book)).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response book(@PathParam("id") Long id) {
    return Response
      .ok(BookMapper.toDto(bookService.getByIdEager(id)))
      .build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateBook(@PathParam("id") Long id, @Valid BookDtoPartial bookDtoPartial) {
    return Response
      .ok(BookMapper.toDto(bookService.updateBook(id, bookDtoPartial)))
      .build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteBook(@PathParam("id") Long id) {
    bookService.deleteById(id);
    return Response.noContent().build();
  }
}
