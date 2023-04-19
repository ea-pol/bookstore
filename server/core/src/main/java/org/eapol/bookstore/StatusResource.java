package org.eapol.bookstore;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class StatusResource {

  @GET
  @Path("/status")
  public Response status() {
    return Response.ok().build();
  }

}
