package org.eapol.sentenceanalyzer.analyzer;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eapol.sentenceanalyzer.analyzer.dto.SentenceAnalyzerRequestParams;
import org.eapol.sentenceanalyzer.analyzer.dto.WordStats;

import java.util.*;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Path("/api")
public class AnalyzerResource {
  private static final int DEFAULT_MAX_NUM_OF_WORDS = 5;
  private static final int DEFAULT_MIN_WORD_LENGTH = 1;

  @POST
  @Path("/stats")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getStats(SentenceAnalyzerRequestParams requestParams) {
    List<String> sentences = requestParams.getSentences();

    int maxNumOfWords = Optional
      .ofNullable(requestParams.getMaxNumOfWords())
      .orElse(DEFAULT_MAX_NUM_OF_WORDS);

    int minWordLength = Optional
      .ofNullable(requestParams.getMinWordLength())
      .orElse(DEFAULT_MIN_WORD_LENGTH);

    Set<String> wordsToBeExcluded = new HashSet<>(Optional
      .ofNullable(requestParams.getWordsToBeExcluded())
      .orElse(Collections.emptyList()));

    List<WordStats> wordStats = sentences.stream()
      .map(sentence -> sentence.split("[^a-zA-Z0-9]"))
      .flatMap(Arrays::stream)
      .filter(word -> !word.isBlank())
      .filter(word -> word.length() >= minWordLength)
      .map(String::toLowerCase)
      .filter(word -> !wordsToBeExcluded.contains(word))
      .collect(groupingBy(identity(), counting()))
      .entrySet()
      .stream()
      .sorted(comparingByValue(reverseOrder()))
      .limit(maxNumOfWords)
      .map(entry -> new WordStats(entry.getKey(), entry.getValue()))
      .toList();

    return Response.ok(wordStats).build();
  }
}
