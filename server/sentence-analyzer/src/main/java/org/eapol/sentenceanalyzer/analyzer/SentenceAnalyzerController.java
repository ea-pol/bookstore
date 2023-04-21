package org.eapol.sentenceanalyzer.analyzer;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Path("/api")
public class SentenceAnalyzerController {
  private static final int TOP_WORDS_LIMIT = 5;

  @POST
  @Path("/stats")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStats(List<Sentence> sentences) {

    List<WordStats> wordStats = sentences.stream()
      .map(Sentence::getSentence)
      .map(sentence -> sentence.split("[^a-zA-Z0-9]"))
      .flatMap(Arrays::stream)
      .filter(word -> !word.isBlank())
      .collect(groupingBy(identity(), counting()))
      .entrySet()
      .stream()
      .sorted(comparingByValue(reverseOrder()))
      .limit(TOP_WORDS_LIMIT)
      .map(entry -> new WordStats(entry.getKey(), entry.getValue()))
      .toList();

    return Response.ok(wordStats).build();
  }

}
