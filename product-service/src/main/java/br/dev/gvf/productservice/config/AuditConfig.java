package br.dev.gvf.productservice.config;

import java.util.Objects;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@Configuration
public class AuditConfig {

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (Objects.isNull(authentication) || !authentication.isAuthenticated()) {
        return Optional.empty();
      }
      if (authentication.getPrincipal() instanceof User user){
        return Optional.of(user.getUsername());
      } else {
        return Optional.of(authentication.getPrincipal().toString());
      }
    };
  }

}
