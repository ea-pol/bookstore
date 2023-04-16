package org.eapol.bookstore;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api")
public class StatusResource {

  @GET
  @Path("/status")
  public String status() {
    return "I'm alive!";
  }

}
