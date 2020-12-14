package tran.example.smartcartest.configuration.database;

import org.json.simple.JSONObject;
import org.springframework.boot.jdbc.DataSourceBuilder;
import tran.example.smartcartest.configuration.fileReader.CustomFileReader;

import javax.sql.DataSource;

import static tran.example.smartcartest.utility.constants.ConfigurationConstants.CONFIGURATION_FILE_LOCATION;

/**
 * Provides a default implementation for reading in database credentials and returning an object to be able to connect
 * to the specified database.
 */
public abstract class AbstractDBConfig {

    public DataSource getDataSource(String fileLocation, String driverClassName, String dbUrl, String dbUserName,
                                    String dbPassword) {
        // TODO: check for errors when grabbing the credentials.
        CustomFileReader reader = new CustomFileReader();
        JSONObject jsonObject = reader.getObjects(fileLocation);
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName((String) jsonObject.get(driverClassName));
        dataSourceBuilder.url((String) jsonObject.get(dbUrl));
        dataSourceBuilder.username((String) jsonObject.get(dbUserName));
        dataSourceBuilder.password((String) jsonObject.get(dbPassword));
        return dataSourceBuilder.build();
    }
}
