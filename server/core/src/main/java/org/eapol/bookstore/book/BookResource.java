package org.eapol.bookstore.book;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eapol.bookstore.author.AuthorService;
import org.eapol.bookstore.book.dto.BookDto;
import org.eapol.bookstore.book.dto.BookDtoPartial;

import org.eapol.bookstore.author.Author;

import java.util.List;
import java.util.Optional;

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
  public List<BookDto> books() {
    return bookService.getAll()
      .stream()
      .map(BookMapper::toDto)
      .toList();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response save(BookDtoPartial bookDtoPartial) {
    Optional<Author> optionalAuthor = authorService.getById(bookDtoPartial.getAuthorId());

    optionalAuthor.ifPresent(author -> {
      Book book = new Book(author,
        bookDtoPartial.getTitle(),
        bookDtoPartial.getFirstSentence(),
        bookDtoPartial.getPrice(),
        bookDtoPartial.getAmount());

      bookService.save(book);
    });

    return Response.noContent().build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public BookDto book(@PathParam("id") Long id) {
    return bookService.getByIdEager(id)
      .map(BookMapper::toDto)
      .get();
  }
}
