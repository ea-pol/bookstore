package org.eapol.bookstore.stats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import com.squareup.okhttp.RequestBody;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eapol.bookstore.book.BookService;

import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

@Path("/api")
public class SentencesStatsResource {
  private final BookService bookService;
  private static final OkHttpClient client = new OkHttpClient();
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final String SENTENCE_ANALYZER_URL = "http://bookstore-sentence-analyzer:8081/api/stats";

  @Inject
  public SentencesStatsResource(BookService bookService) {
    this.bookService = bookService;
  }

  @GET
  @Path("/sentences-stats")
  @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
  public Response calcSentencesStats() throws JsonProcessingException {

    System.out.println("1");

    List<Sentence> sentences = bookService
      .getAllSentences()
      .stream()
      .map(Sentence::new)
      .toList();

    System.out.println("2");

    String sentencesJson = objectMapper.writeValueAsString(sentences);

    System.out.println(sentencesJson);

    RequestBody body = RequestBody.create(
      MediaType.parse("application/json"),
      sentencesJson);

    Request request = new Request.Builder()
      .url(SENTENCE_ANALYZER_URL)
      .post(body)
      .build();

    try {
      com.squareup.okhttp.Response response = client
        .newCall(request)
        .execute();

      String statsJson = response.body().string();

      System.out.println(statsJson);

      List<WordStats> topWords = objectMapper.readValue(
        statsJson, new TypeReference<LinkedList<WordStats>>(){});

      return Response.ok(topWords).build();
    } catch (IOException exception) {
      throw new RuntimeException();
    }
  }
}
