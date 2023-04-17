package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eapol.bookstore.book.dto.BookDto;

import java.util.List;

@Path("/api/books")
public class BookResource {
  private final BookService bookService;

  @Inject
  public BookResource(BookService bookService) {
    this.bookService = bookService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<BookDto> books() {
    return bookService.getAll()
      .stream()
      .map(BookMapper::toDto)
      .toList();
  }
}
