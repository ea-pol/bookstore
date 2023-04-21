package org.eapol.bookstore;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.awaitility.Awaitility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookstoreApplicationTests {
  private static final OkHttpClient client = new OkHttpClient();
  private static final String HOST = "http://localhost:8080";

  @BeforeAll
  public static void runServer() {
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
