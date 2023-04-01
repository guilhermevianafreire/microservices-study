package br.dev.gvf.productservice.config;

import br.dev.gvf.productservice.constant.Profiles;
import br.dev.gvf.productservice.constant.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true
)
public class SecurityConfig {

  @Bean
  @Profile(Profiles.DEVELOPMENT)
  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails user = User
        .withUsername("user")
        .password(passwordEncoder.encode("user"))
        .roles(Roles.CONFIG_USER)
        .build();

    UserDetails admin = User
        .withUsername("admin")
        .password(passwordEncoder.encode("admin"))
        .roles(Roles.CONFIG_ADMIN)
        .build();

    return new InMemoryUserDetailsManager(
        user,
        admin
    );
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http)
      throws
      Exception {
    http
        .authorizeHttpRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .csrf()
        .disable()
        .cors()
        .disable();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
