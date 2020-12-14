package tran.example.smartcartest.service.security;

import com.smartcar.sdk.SmartcarException;

/**
 * Declares expected behaviors for custom token validation.
 */
public interface TokenValidationService {
    boolean checkToken(String userName) throws SmartcarException;
}
