package config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import utils.ConfigUtils;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
@EnableTransactionManagement
@ComponentScan({"api", "config", "services", "dao", "model"})
@PropertySource(value = "classpath:persistence.properties")
public class PersistenceConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        return ConfigUtils.getLocalSessionFactoryBean(dataSource(), hibernateProperties());
    }

    @Bean
    public DataSource dataSource() {
        return ConfigUtils.getDataSource(environment);
    }

    private Properties hibernateProperties() {
        return ConfigUtils.getHibernateProperties(environment);
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

}
