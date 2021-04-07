package tran.example.smartcartest.configuration.smartcar;

import com.smartcar.sdk.AuthClient;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import tran.example.smartcartest.configuration.fileReader.CustomFileReader;

import static tran.example.smartcartest.configuration.smartcar.constants.SmartCarApiConstants.*;
import static tran.example.smartcartest.utility.constants.ConfigurationConstants.SC_CREDENTIALS;
import static tran.example.smartcartest.utility.constants.PathConstants.EXCHANGE_PATH;

/**
 * A bean used to assist with the OAuth2 flow used by Smartcar in order to grab relevant data from the Smartcar API.
 */
@Component
public class CustomAPIClient {
    private static final boolean TEST_MODE = true;
    private static final String[] SCOPE = {"required:read_vehicle_info", "read_odometer", "required:read_tires", "read_vin"};

    /*
     * An instance of a custom client required to make HTTP requests to the Smartcar API.
     */
    private static AuthClient client;

    public CustomAPIClient() {
        readConfigurationContent();
    }

    private void readConfigurationContent() {
        CustomFileReader fileReader = new CustomFileReader();

        JSONObject jsonObject = fileReader.getObjects(SC_CREDENTIALS);

        String clientId = fileReader.getContentFromKey(jsonObject, CLIENT_ID_KEY);
        String clientSecret = fileReader.getContentFromKey(jsonObject, CLIENT_SECRET_KEY);
        String port = fileReader.getContentFromKey(jsonObject, PORT_KEY);
        String domain = fileReader.getContentFromKey(jsonObject, DOMAIN_KEY);
        String redirectURI = domain + port + EXCHANGE_PATH;

        client = new AuthClient(
                clientId,
                clientSecret,
                redirectURI,
                SCOPE,
                TEST_MODE
        );
    }

    public static AuthClient getClient() {
        return client;
    }
}
