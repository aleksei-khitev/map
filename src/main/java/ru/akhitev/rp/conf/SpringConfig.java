package ru.akhitev.rp.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ru.akhitev.rp.Launcher;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import ru.akhitev.rp.fleet.fleet_node.db.entity.FleetNode;
import ru.akhitev.rp.fleet.ship.db.entity.Ship;
import ru.akhitev.rp.fleet.ship.db.repo.ShipRepository;
import ru.akhitev.rp.fleet.weapon.db.entity.Weapon;
import ru.akhitev.rp.map.entity.StarSystem;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.akhitev.rp.map.repository.StarSystemRepository;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackageClasses = {StarSystemRepository.class, ShipRepository.class})
@EnableTransactionManagement
@ComponentScan(basePackageClasses={Launcher.class})
public class SpringConfig {
    private static final String DB_NAME = "classpath:bank_db";
    private static final String DB_DDL = "classpath:scheme.sql";
    private static final String INIT_DATA_SQL = "classpath:data.sql";
    private static final String DIALECT = "org.hibernate.dialect.HSQLDialect";
    private static final String SHOW_SQL = "false";
    private static final String FORMAT_SQL = "false";

    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .setName(DB_NAME).addScript(DB_DDL).addScript(INIT_DATA_SQL);
        return builder.build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(StarSystem.class.getPackage().getName(),
                Ship.class.getPackage().getName(),
                Weapon.class.getPackage().getName(),
                FleetNode.class.getPackage().getName());
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    private Properties hibernateProperties() {
        final Properties properties = new Properties();
        properties.put("db.hibernate.dialect", DIALECT);
        properties.put("db.hibernate.show_sql", SHOW_SQL);
        properties.put("db.hibernate.format_sql", FORMAT_SQL);
        return properties;
    }
}
