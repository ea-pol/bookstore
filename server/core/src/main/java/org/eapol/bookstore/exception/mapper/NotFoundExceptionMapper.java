package org.eapol.bookstore.exception.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.eapol.bookstore.exception.Error;
import org.eapol.bookstore.exception.NotFoundException;

public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
  @Override
  public Response toResponse(NotFoundException exception) {
    return Response
      .status(Response.Status.NOT_FOUND)
      .entity(new Error(exception.getMessage()))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
}
