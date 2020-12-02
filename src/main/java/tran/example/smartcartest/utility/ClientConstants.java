package tran.example.smartcartest.utility;

import com.smartcar.sdk.AuthClient;

/*
 * Constants to be used in the application.
 */
public class ClientConstants {

    private static final String CLIENT_ID = System.getenv("CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
    private static final String PORT = System.getenv("TEST_PORT");
    private static final String REDIRECT_URI = "http://localhost:" + PORT + "/exchange";
    private static final boolean TEST_MODE = true;
    private static final String[] SCOPE = {"required:read_vehicle_info", "read_odometer", "required:read_tires", "read_vin"};

    /*
     * An instance of a custom client required to make HTTP requests to the Smartcar API.
     */
    public static AuthClient client = new AuthClient(
            CLIENT_ID,
            CLIENT_SECRET,
            REDIRECT_URI,
            SCOPE,
            TEST_MODE
    );
}