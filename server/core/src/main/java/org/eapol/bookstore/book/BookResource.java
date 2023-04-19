package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eapol.bookstore.author.AuthorService;
import org.eapol.bookstore.book.dto.BookDto;
import org.eapol.bookstore.book.dto.BookDtoPartial;
import java.util.List;

@Path("/api/books")
public class BookResource {
  private final BookService bookService;
  private final AuthorService authorService;

  @Inject
  public BookResource(
    BookService bookService,
    AuthorService authorService
  ) {
    this.bookService = bookService;
    this.authorService = authorService;
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
  public Response save(BookDtoPartial bookDtoPartial) {
    bookService.save(bookDtoPartial);
    return Response.noContent().build();
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
  public Response updateBook(@PathParam("id") Long id, BookDtoPartial bookDtoPartial) {
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
