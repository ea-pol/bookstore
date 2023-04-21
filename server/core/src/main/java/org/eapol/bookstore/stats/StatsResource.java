package org.eapol.bookstore.stats;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eapol.bookstore.book.BookService;
import org.eapol.bookstore.stats.dto.Sentence;
import org.eapol.bookstore.stats.dto.WordStats;

import java.io.IOException;
import java.util.List;

@Path("/api")
public class StatsResource {
  private final BookService bookService;
  private final StatsService statsService;


  @Inject
  public StatsResource(BookService bookService, StatsService statsService) {
    this.bookService = bookService;
    this.statsService = statsService;
  }

  @GET
  @Path("/sentences-stats")
  @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
  public Response calcSentencesStats() throws IOException {
    List<Sentence> sentences = bookService
      .getAllSentences()
      .stream()
      .map(Sentence::new)
      .toList();

    List<WordStats> wordStats = statsService.findTopWordsForSentences(sentences);
    return Response.ok(wordStats).build();
  }
}
