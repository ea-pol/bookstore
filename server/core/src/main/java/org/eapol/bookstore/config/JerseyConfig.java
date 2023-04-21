package org.eapol.bookstore.config;

import org.eapol.bookstore.StatusResource;
import org.eapol.bookstore.author.AuthorResource;

import org.eapol.bookstore.book.BookResource;
import org.eapol.bookstore.exception.ConstraintViolationExceptionMapper;
import org.eapol.bookstore.exception.JsonProcessingExceptionMapper;
import org.eapol.bookstore.exception.MethodNotAllowedExceptionMapper;
import org.eapol.bookstore.exception.NotFoundExceptionMapper;
import org.eapol.bookstore.stats.StatsResource;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig {
  @Bean
  ResourceConfig config() {
    ResourceConfig config = new ResourceConfig();

    config.register(StatusResource.class);
    config.register(AuthorResource.class);
    config.register(BookResource.class);
    config.register(StatsResource.class);

    config.register(NotFoundExceptionMapper.class);
    config.register(ConstraintViolationExceptionMapper.class);
    config.register(JsonProcessingExceptionMapper.class);
    config.register(JacksonJaxbJsonProvider.class);
    config.register(MethodNotAllowedExceptionMapper.class);

    return config;
  }
}
