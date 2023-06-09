package org.eapol.bookstore.stats;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import org.eapol.bookstore.stats.dto.SentenceAnalyzerRequestParams;
import org.eapol.bookstore.stats.dto.WordStats;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class StatsService {
  private static final OkHttpClient client = new OkHttpClient();
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final String SENTENCE_ANALYZER_URL = "http://bookstore-sentence-analyzer:8081/api/stats";

  public List<WordStats> findTopWordsForSentences(
    SentenceAnalyzerRequestParams requestParams
  ) throws IOException {
    String requestParamsJson = objectMapper.writeValueAsString(requestParams);

    RequestBody body = RequestBody.create(
      MediaType.parse("application/json"),
      requestParamsJson);

    Request request = new Request.Builder()
      .url(SENTENCE_ANALYZER_URL)
      .post(body)
      .build();

    Response response = client
      .newCall(request)
      .execute();

    String statsJson = response.body().string();

    return objectMapper.readValue(
      statsJson,
      new TypeReference<LinkedList<WordStats>>() {});
  }
}
