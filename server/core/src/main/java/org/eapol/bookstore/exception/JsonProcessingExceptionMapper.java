package org.eapol.bookstore.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class JsonProcessingExceptionMapper
  implements ExceptionMapper<JsonProcessingException> {
  @Override
  public Response toResponse(JsonProcessingException exception) {
    return Response
      .status(Response.Status.BAD_REQUEST)
      .entity(new Error("provided request body is invalid"))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
}
