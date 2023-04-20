package org.eapol.bookstore.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper
  implements ExceptionMapper<ConstraintViolationException> {
  @Override
  public Response toResponse(ConstraintViolationException exception) {
    return Response
      .status(Response.Status.BAD_REQUEST)
      .entity(new Error("provided request body is invalid."))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
}
