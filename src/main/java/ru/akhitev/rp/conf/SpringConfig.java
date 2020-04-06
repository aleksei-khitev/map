package ru.akhitev.rp.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import ru.akhitev.rp.Launcher;

import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.akhitev.rp.fleet.entity.FleetUnit;
import ru.akhitev.rp.fleet.repo.FleetUnitRepo;
import ru.akhitev.rp.resource.repo.CriticalResourceRepo;
import ru.akhitev.rp.ship.repo.ShipRepo;
import ru.akhitev.rp.star_system.repo.StarSystemRepository;
import ru.akhitev.rp.state_hood.repo.StateHoodRepository;
import ru.akhitev.rp.super_state_hood.repo.SuperStateHoodRepository;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {StarSystemRepository.class,
        FleetUnitRepo.class, ShipRepo.class, CriticalResourceRepo.class,
        StateHoodRepository.class, SuperStateHoodRepository.class},
        repositoryBaseClass = RefreshableEntityRepositoryImpl.class)
@ComponentScan(basePackageClasses={Launcher.class})
public class SpringConfig {
    private static Logger logger = LoggerFactory.getLogger(SpringConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.h2.Driver");
            dataSource.setUrl("jdbc:h2:file:./db/rp-db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
            dataSource.setUsername("sa");
            dataSource.setPassword("");
            return dataSource;
        } catch (Exception e) {
            logger.error("Ошибка при создании Data Source", e);
            return null;
        }
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.hbm2ddl.import_files", "db/resource_and_production.sql," +
                "db/fleet_general.sql," +
                "db/fleet_ships.sql," +
                "db/fleet_unit.sql," +
                "db/map.sql," +
                "db/star_system_fleet_unit.sql");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.max_fetch_depth", 3);
        properties.put("hibernate.jdbc.batch_size", 10);
        properties.put("hibernate.jdbc.fetch_size", 50);
        return properties;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan(
                "ru.akhitev.rp.super_state_hood.entity",
                "ru.akhitev.rp.state_hood.entity",
                "ru.akhitev.rp.resource.entity",
                "ru.akhitev.rp.production.entity",
                "ru.akhitev.rp.land_force.entity",
                "ru.akhitev.rp.small_aircraft.entity",
                "ru.akhitev.rp.weapon.entity",
                "ru.akhitev.rp.ship.entity",
                "ru.akhitev.rp.fleet.entity",
                "ru.akhitev.rp.star_system.entity"
                );
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaProperties(hibernateProperties());
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getNativeEntityManagerFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
}
