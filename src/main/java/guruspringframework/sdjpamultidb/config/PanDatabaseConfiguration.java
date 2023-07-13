package guruspringframework.sdjpamultidb.config;

import com.zaxxer.hikari.HikariDataSource;
import guruspringframework.sdjpamultidb.domain.pan.CreditCardPAN;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = "guruspringframework.sdjpamultidb.repositories.pan",
    entityManagerFactoryRef = "panEntityManagerFactory",
    transactionManagerRef = "panTransactionManager"
)
public class PanDatabaseConfiguration {

  @Bean
  @Primary
  @ConfigurationProperties("spring.pan.datasource")
  public DataSourceProperties panDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  public DataSource panDataSource(
      @Qualifier("panDataSourceProperties") DataSourceProperties panDataSource
  ) {
    return panDataSource.initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(
      @Qualifier("panDataSource") DataSource panDataSource,
      EntityManagerFactoryBuilder builder
  ) {
    return builder.dataSource(panDataSource)
        .packages(CreditCardPAN.class)
        .persistenceUnit("pan")
        .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager panTransactionManager(
    @Qualifier("panEntityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory
  ){
    return new JpaTransactionManager(panEntityManagerFactory.getObject());
  }
}
