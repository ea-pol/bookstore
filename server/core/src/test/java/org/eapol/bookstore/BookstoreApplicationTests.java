package org.eapol.bookstore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.squareup.okhttp.*;
import org.awaitility.Awaitility;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eapol.bookstore.author.dto.AuthorDto;
import org.eapol.bookstore.author.dto.AuthorDtoPartial;
import org.eapol.bookstore.book.dto.BookDto;
import org.eapol.bookstore.book.dto.BookDtoPartial;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookstoreApplicationTests {
  private static final OkHttpClient client = new OkHttpClient();
  private static final String HOST = "http://localhost:8080";
  private static final ObjectMapper mapper = new ObjectMapper();
  private static EmbeddedPostgres postgres;

  @BeforeAll
  public static void runServer() throws IOException {
    postgres = EmbeddedPostgres.builder().setPort(5432).start();
    runAndWaitServer();
  }

  @Test
  public void testStatusResource() throws IOException {
    Request request = new Request.Builder()
      .url(HOST + "/api/status")
      .build();

    Response response = client.newCall(request).execute();
    assertEquals(response.code(), 200);
  }

  @Test
  public void testPostGetAuthors() throws IOException {
    AuthorDtoPartial author1 = new AuthorDtoPartial("Ray", "Bradbury");
    AuthorDtoPartial author2 = new AuthorDtoPartial("Leo", "Tolstoy");

    Response response1 = postAuthor(author1);
    Response response2 = postAuthor(author2);

    assertEquals(response1.code(), 200);
    assertEquals(response2.code(), 200);

    Response response = getAuthors();

    assertEquals(response.code(), 200);

    List<AuthorDto> authors = mapper.readValue(
      response.body().string(),
      new TypeReference<LinkedList<AuthorDto>>() {});

    // Since tests share the same database and tests execution
    // order isn't determined, this assert can fail sometimes.
    // assertEquals(authors.size(), 2);

    boolean firstAuthorInList = authors.stream().anyMatch(author ->
      Objects.equals(author.getFirstName(), author1.getFirstName()) &&
        Objects.equals(author.getLastName(), author1.getLastName()));

    assertTrue(firstAuthorInList);

    boolean secondAuthorInList = authors.stream().anyMatch(author ->
      Objects.equals(author.getFirstName(), author2.getFirstName()) &&
        Objects.equals(author.getLastName(), author2.getLastName()));

    assertTrue(secondAuthorInList);
  }

  @Test
  public void testPostGetBooks() throws IOException {
    AuthorDtoPartial author = new AuthorDtoPartial("John", "Doe");
    Response postAuthorResponse = postAuthor(author);

    assertEquals(postAuthorResponse.code(), 200);

    AuthorDto createdAuthor = mapper.readValue(
      postAuthorResponse.body().string(),
      AuthorDto.class);

    BookDtoPartial book1 = new BookDtoPartial(
      createdAuthor.getAuthorId(),
      "Book title",
      "Some sentence",
      2023L);

    BookDtoPartial book2 = new BookDtoPartial(
      createdAuthor.getAuthorId(),
      "Another book title",
      "Some other sentence",
      2024L);

    Response postBook1Response = postBook(book1);
    assertEquals(postBook1Response.code(), 200);

    Response postBook2Response = postBook(book2);
    assertEquals(postBook2Response.code(), 200);

    Response getBooksResponse = getBooks();
    assertEquals(getBooksResponse.code(), 200);

    List<BookDto> books = mapper.readValue(
      getBooksResponse.body().string(),
      new TypeReference<LinkedList<BookDto>>() {});

    assertEquals(books.size(), 2);

    boolean firstBookInList = books.stream().anyMatch(book ->
      Objects.equals(book.getAuthorDto().getAuthorId(), book1.getAuthorId()) &&
        Objects.equals(book.getTitle(), book1.getTitle()));

    assertTrue(firstBookInList);

    boolean secondBookInList = books.stream().anyMatch(book ->
      Objects.equals(book.getAuthorDto().getAuthorId(), book2.getAuthorId()) &&
        Objects.equals(book.getTitle(), book2.getTitle()));

    assertTrue(secondBookInList);
  }

  private static Response getBooks() throws IOException {
    Request request = new Request.Builder()
      .url(HOST + "/api/books")
      .build();

    return client.newCall(request).execute();
  }

  private static Response postBook(BookDtoPartial book) throws IOException {
    String bookJson = mapper.writeValueAsString(book);

    RequestBody body = RequestBody.create(
      MediaType.parse("application/json"),
      bookJson);

    Request request = new Request.Builder()
      .url(HOST + "/api/books")
      .post(body)
      .build();

    return client.newCall(request).execute();
  }

  private static Response getAuthors() throws IOException {
    Request request = new Request.Builder()
      .url(HOST + "/api/authors")
      .build();

    return client.newCall(request).execute();
  }

  private static Response postAuthor(AuthorDtoPartial author) throws IOException {
    String authorJson = mapper.writeValueAsString(author);

    RequestBody body = RequestBody.create(
      MediaType.parse("application/json"),
      authorJson);

    Request request = new Request.Builder()
      .url(HOST + "/api/authors")
      .post(body)
      .build();

    return client.newCall(request).execute();
  }

  private static void runAndWaitServer() {
    Thread thread = new Thread(() -> {
      try {
        BookstoreApplication.main(new String[]{});
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    thread.start();

    Awaitility.await()
      .atMost(10, TimeUnit.SECONDS)
      .pollInterval(500, TimeUnit.MILLISECONDS)
      .until(BookstoreApplicationTests::isServerUp, IsEqual.equalTo(true));
  }

  private static boolean isServerUp() {
    try {
      Request request = new Request.Builder()
        .url(HOST + "/api/status")
        .build();

      Response response = client.newCall(request).execute();

      return response.code() == 200;
    } catch (Exception e) {
      return false;
    }
  }
}
