package org.eapol.bookstore.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class DtoValidationExceptionMapper
  implements ExceptionMapper<DtoValidationException> {

  @Override
  public Response toResponse(DtoValidationException exception) {
    return Response
      .status(Response.Status.BAD_REQUEST)
      .entity(new Error(exception.getMessage()))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
}