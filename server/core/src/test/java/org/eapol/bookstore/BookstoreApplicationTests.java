package org.eapol.bookstore;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BookstoreApplicationTests {
  private static final OkHttpClient client = new OkHttpClient();
  private static final String HOST = "http://localhost:8080";

  @Test
  public void dummyTest() {
    assertTrue(true);
  }

  @Test
  public void testStatusResource() throws IOException, InterruptedException {
    Thread thread = new Thread(() -> {
      try {
        BookstoreApplication.main(new String[]{});
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    thread.start();

    Thread.sleep(10_000);

    Request request = new Request.Builder()
      .url(HOST + "/api/status")
      .build();

    Response response = client.newCall(request).execute();
    System.out.println(response.code());

    assertEquals(response.code(), 200);
  }
}
