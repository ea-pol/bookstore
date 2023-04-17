package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/api/books")
public class BookResource {
  private final BookService bookService;

  @Inject
  public BookResource(BookService bookService) {
    this.bookService = bookService;
  }
}
