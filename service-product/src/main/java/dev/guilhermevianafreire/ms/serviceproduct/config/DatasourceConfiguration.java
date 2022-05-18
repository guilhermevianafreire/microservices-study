package dev.guilhermevianafreire.ms.serviceproduct.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({
                         "dev.guilhermevianafreire.ms.serviceproduct.repository"
})
@EnableTransactionManagement
public class DatasourceConfiguration {

}
