package org.eapol.bookstore.config;

import org.eapol.bookstore.StatusResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig {
  @Bean
  ResourceConfig config() {
    ResourceConfig config = new ResourceConfig();
    config.register(StatusResource.class);
    return config;
  }
}
