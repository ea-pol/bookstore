package org.eapol.bookstore.stats;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eapol.bookstore.book.BookService;
import org.eapol.bookstore.stats.dto.SentenceAnalyzerRequestParams;
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
  @Produces(MediaType.APPLICATION_JSON)
  public Response calcSentencesStats(
    @QueryParam("minWordLength") Integer minWordLength,
    @QueryParam("maxNumOfWords") Integer maxNumOfWords,
    @QueryParam("wordsToBeExcluded") List<String> wordsToBeExcluded
  ) throws IOException {

    List<String> sentences = bookService.getAllSentences();

    SentenceAnalyzerRequestParams requestParams =
      new SentenceAnalyzerRequestParams(
        sentences,
        minWordLength,
        maxNumOfWords,
        wordsToBeExcluded
      );

    List<WordStats> wordStats = statsService.findTopWordsForSentences(requestParams);
    return Response.ok(wordStats).build();
  }
}
