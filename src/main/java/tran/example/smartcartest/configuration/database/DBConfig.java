package tran.example.smartcartest.configuration.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static tran.example.smartcartest.utility.constants.ConfigurationConstants.*;

/**
 * Creates (a) bean(s) (object(s)) to connect to database(s).
 */
@Configuration
public class DBConfig extends AbstractDBConfig {

    @Bean
    public DataSource getDataSource() {
        return super.getDataSource(CONFIGURATION_FILE_LOCATION, MYSQL_DRIVER_CLASS_NAME, MYSQL_DB_URL, MYSQL_DB_USER_NAME,
                MYSQL_DB_PASSWORD);
    }
}

