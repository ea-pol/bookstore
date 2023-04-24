package org.eapol.bookstore.exception.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.eapol.bookstore.exception.Error;
import org.springframework.dao.DataIntegrityViolationException;

public class DataIntegrityViolationExceptionMapper
  implements ExceptionMapper<DataIntegrityViolationException> {
  @Override
  public Response toResponse(DataIntegrityViolationException exception) {
    return Response
      .status(422)
      .entity(new Error("entity with the specified field values already exists."))
      .type(MediaType.APPLICATION_JSON)
      .build();
  }
}
