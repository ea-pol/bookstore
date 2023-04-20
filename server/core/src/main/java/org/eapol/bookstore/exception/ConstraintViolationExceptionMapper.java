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
      .entity(new Error("specified parameters are invalid."))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
}
