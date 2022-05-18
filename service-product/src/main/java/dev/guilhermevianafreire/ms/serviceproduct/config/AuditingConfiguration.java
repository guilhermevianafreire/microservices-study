package dev.guilhermevianafreire.ms.serviceproduct.config;

import org.javers.spring.auditable.AuthorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditingConfiguration {

    private static class MockAuthorProvider implements AuthorProvider {
        @Override
        public String provide() {
            return "mock-security-author";
        }
    }

    @Bean
    public AuthorProvider provideJaversAuthor() {
        return new MockAuthorProvider();
    }
}
