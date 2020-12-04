package tran.example.smartcartest.utility.constants;

import com.smartcar.sdk.AuthClient;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import tran.example.smartcartest.configuration.MyFileReader;

/*
 * Constants to be used in the application.
 */
@Component
public class APIClientConstants {

    private static final boolean TEST_MODE = true;
    private static final String[] SCOPE = {"required:read_vehicle_info", "read_odometer", "required:read_tires", "read_vin"};
//    private static final String[] SCOPE = {"required:read_vehicle_info", "required:read_tires", "read_vin"};
//    private static final String[] SCOPE = {"required:read_vehicle_info", "required:read_tires", "read_vin"};
//    private static final String[] SCOPE = {"required:read_vehicle_info", "required:read_tires", "read_odometer"};
//    private static final String[] SCOPE = {"required:read_vehicle_info", "required:read_tires", "read_odometer"};

    /*
     * An instance of a custom client required to make HTTP requests to the Smartcar API.
     */
    public static AuthClient client;

    public APIClientConstants() {
        readConfigurationContent();
    }

    private void readConfigurationContent() {
        MyFileReader fileReader = new MyFileReader();

        JSONObject jsonObject = fileReader.getObjects("/etc/test_config.json");

        String clientId = fileReader.getContentFromKey(jsonObject, "CLIENT_ID");
        String clientSecret = fileReader.getContentFromKey(jsonObject, "CLIENT_SECRET");
        String port = fileReader.getContentFromKey(jsonObject, "PORT");
        String redirectURI  = "http://localhost:" + port + "/exchange";

        client = new AuthClient(
                clientId,
                clientSecret,
                redirectURI,
                SCOPE,
                TEST_MODE
        );
    }
}