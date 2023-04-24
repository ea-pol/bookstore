package org.eapol.bookstore.exception.mapper;

import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.eapol.bookstore.exception.Error;

public class MethodNotAllowedExceptionMapper
  implements ExceptionMapper<NotAllowedException> {
  @Override
  public Response toResponse(NotAllowedException exception) {
    return Response
      .status(Response.Status.METHOD_NOT_ALLOWED)
      .entity(new Error("method not allowed."))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
}
