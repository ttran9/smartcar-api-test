package tran.example.smartcartest.utility.api;

import tran.example.smartcartest.model.domain.CustomToken;

import java.util.Date;

public class SmartCarApiHelper {

    public static void setCustomTokenHelper(CustomToken customToken, String accessToken, Date accessExpiration,
                                            String refreshToken, Date refreshExpiration) {
        customToken.setAccessToken(accessToken);
        customToken.setExpiration(accessExpiration);
        customToken.setRefreshToken(refreshToken);
        customToken.setRefreshExpiration(refreshExpiration);
    }
}
