package org.eapol.sentenceanalyzer.config;

import org.eapol.sentenceanalyzer.analyzer.AnalyzerResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig {
  @Bean
  ResourceConfig config() {
    ResourceConfig config = new ResourceConfig();
    config.register(AnalyzerResource.class);
    return config;
  }
}
